"""
Branch and Bound cho bài toán VRPTW
Bộ dữ liệu: Solomon Benchmark C101 (đọc từ file C101.txt)
Chạy với n = 5, 10, 15, 25
"""




# ============================================================
# 1. ĐỌC DỮ LIỆU TỪ FILE C101.TXT
# ============================================================

def parse_solomon_file(filepath: str) -> dict:
    """
    Đọc và phân tích file Solomon Benchmark (định dạng chuẩn).

    Tham số
    -------
    filepath : đường dẫn tới file .txt (vd: 'C101.txt').

    Trả về
    ------
    dict gồm:
        n_vehicles : số xe tối đa
        capacity   : sức chứa mỗi xe
        customers  : list of dict {id, x, y, demand, ready, due, service}
    """
    with open(filepath, "r") as f:
        lines = [ln.strip() for ln in f if ln.strip()]

    # Dòng chứa số xe và sức chứa (2 số nguyên liên tiếp)
    n_vehicles = capacity = None
    data_rows  = []

    i = 0
    while i < len(lines):
        parts = lines[i].split()
        # Tìm dòng "25  200" (vehicle number + capacity)
        if (n_vehicles is None and len(parts) == 2
                and parts[0].isdigit() and parts[1].isdigit()):
            n_vehicles = int(parts[0])
            capacity   = int(parts[1])
        # Dòng dữ liệu khách hàng: 7 số
        elif len(parts) == 7 and parts[0].isdigit():
            data_rows.append([int(v) for v in parts])
        i += 1

    customers = [
        {
            "id":      r[0],
            "x":       r[1],
            "y":       r[2],
            "demand":  r[3],
            "ready":   r[4],
            "due":     r[5],
            "service": r[6],
        }
        for r in data_rows
    ]

    return {
        "n_vehicles": n_vehicles,
        "capacity":   capacity,
        "customers":  customers,   # index 0 = depot
    }


# ============================================================
# 2. XÂY DỰNG DỮ LIỆU BÀI TOÁN
# ============================================================

def build_problem(raw: dict, n_customers: int) -> dict:
    """
    Tạo instance VRPTW từ dữ liệu đã đọc.

    Tham số
    -------
    raw         : dict từ parse_solomon_file().
    n_customers : số khách hàng cần lấy (5 / 10 / 15 / 25).

    Trả về
    ------
    dict gồm:
        nodes      – danh sách id đỉnh (0 = depot, 1..n = khách hàng)
        depot      – id kho (= 0)
        customers  – danh sách id khách hàng
        demand     – dict {id: nhu cầu}
        ready      – dict {id: thời gian mở cửa}
        due        – dict {id: thời gian đóng cửa}
        service    – dict {id: thời gian phục vụ}
        dist       – dict {(i,j): khoảng cách Euclidean}
        capacity   – sức chứa xe
        n_vehicles – số xe tối đa
    """
    rows  = raw["customers"][: n_customers + 1]   # depot + n_customers
    nodes = [r["id"] for r in rows]

    x_coord = {r["id"]: r["x"]       for r in rows}
    y_coord = {r["id"]: r["y"]       for r in rows}
    demand  = {r["id"]: r["demand"]  for r in rows}
    ready   = {r["id"]: r["ready"]   for r in rows}
    due     = {r["id"]: r["due"]     for r in rows}
    service = {r["id"]: r["service"] for r in rows}

    dist = {
        (i, j): math.hypot(x_coord[i] - x_coord[j],
                            y_coord[i] - y_coord[j])
        for i in nodes for j in nodes if i != j
    }

    return {
        "nodes":      nodes,
        "depot":      0,
        "customers":  nodes[1:],
        "demand":     demand,
        "ready":      ready,
        "due":        due,
        "service":    service,
        "dist":       dist,
        "capacity":   raw["capacity"],
        "n_vehicles": raw["n_vehicles"],
    }


# ============================================================
# 3. KIỂM TRA TÍNH KHẢ THI MỘT TUYẾN ĐƠN
# ============================================================

def is_route_feasible(route: list, problem: dict):
    """
    Kiểm tra tính khả thi của một tuyến đường.
    Tuyến truyền vào KHÔNG bao gồm depot (depot thêm tự động).

    Ràng buộc:
        - Sức chứa: tổng demand <= capacity
        - Time window: bắt đầu phục vụ trong [ready[j], due[j]]
        - Xe chờ nếu đến sớm hơn ready[j]

    Trả về
    ------
    (feasible: bool, total_cost: float, finish_time: float)
    """
    dist     = problem["dist"]
    ready    = problem["ready"]
    due      = problem["due"]
    service  = problem["service"]
    demand   = problem["demand"]
    depot    = problem["depot"]
    capacity = problem["capacity"]

    # Ràng buộc sức chứa
    if sum(demand[c] for c in route) > capacity:
        return False, math.inf, math.inf

    full_route   = [depot] + route + [depot]
    current_time = 0.0
    total_cost   = 0.0

    for k in range(len(full_route) - 1):
        i, j   = full_route[k], full_route[k + 1]
        travel  = dist[(i, j)]
        total_cost   += travel
        current_time += travel

        # Xe chờ nếu đến sớm
        if current_time < ready[j]:
            current_time = ready[j]

        # Vi phạm time window (đến muộn)
        if current_time > due[j]:
            return False, math.inf, math.inf

        # Cộng thời gian phục vụ (bỏ qua depot cuối)
        if j != depot:
            current_time += service[j]

    return True, total_cost, current_time


# ============================================================
# 4. SINH CÁC TUYẾN KHẢ THI
# ============================================================

def generate_feasible_routes(unvisited: set, problem: dict,
                              max_route_len: int = 5) -> list:
    """
    Sinh danh sách tất cả tuyến khả thi từ tập khách hàng chưa phân công.

    Tham số
    -------
    unvisited     : tập id khách hàng chưa được phân công.
    problem       : dict bài toán.
    max_route_len : số khách hàng tối đa trên một tuyến.

    Trả về
    ------
    Danh sách dict {customers, cost, finish_time}, sắp xếp cost tăng dần.
    """
    candidates = []
    cust_list  = list(unvisited)
    limit      = min(max_route_len, len(cust_list))

    for length in range(1, limit + 1):
        for perm in permutations(cust_list, length):
            feasible, cost, ft = is_route_feasible(list(perm), problem)
            if feasible:
                candidates.append({
                    "customers":   list(perm),
                    "cost":        cost,
                    "finish_time": ft,
                })

    candidates.sort(key=lambda r: r["cost"])
    return candidates


# ============================================================
# 5. HẠ CẬN (LOWER BOUND)
# ============================================================

def compute_lower_bound(assigned_routes: list,
                        unvisited: set,
                        problem: dict) -> float:
    """
    Tính hạ cận tại một nút trong cây B&B.

    Công thức:
        LB = (chi phí các tuyến đã gán)
           + Σ_{c ∈ unvisited} [ (dist(depot,c) + dist(c,depot)) / 2 ]

    Tham số
    -------
    assigned_routes : danh sách tuyến đã xác định.
    unvisited       : tập khách hàng chưa phân công.
    problem         : dict bài toán.

    Trả về
    ------
    Giá trị lower bound (float).
    """
    dist  = problem["dist"]
    depot = problem["depot"]

    cost_fixed = sum(r["cost"] for r in assigned_routes)
    cost_relax = sum(
        (dist[(depot, c)] + dist[(c, depot)]) / 2.0 for c in unvisited
    )
    return cost_fixed + cost_relax


# ============================================================
# 6. CHỌN PIVOT (KHÁCH HÀNG PHÂN NHÁNH)
# ============================================================

def select_pivot(unvisited: set, problem: dict) -> int:
    """
    Chọn khách hàng để phân nhánh tiếp theo.

    Chiến lược: khách hàng có due date nhỏ nhất (time window chặt nhất)
    được ưu tiên — giúp phát hiện infeasibility sớm.

    Trả về
    ------
    id khách hàng được chọn làm pivot.
    """
    return min(unvisited, key=lambda c: problem["due"][c])


# ============================================================
# 7. BRANCH AND BOUND CHÍNH
# ============================================================

def branch_and_bound(problem: dict,
                     time_limit: float = 120.0,
                     max_route_len: int = 5) -> dict:
    """
    Thuật toán Branch and Bound (Best-First Search) cho VRPTW.

    Cấu trúc nút heap: (lower_bound, node_id, assigned_routes, unvisited)

    Các bước:
        1. Khởi tạo: nút gốc với LB ban đầu, best_cost = +∞.
        2. Node selection: pop nút có LB nhỏ nhất từ heap.
        3. Bounding: tính LB của nút hiện tại.
        4. Pruning:
            - LB >= best_cost  → bỏ qua.
            - Hết xe, còn khách → bỏ qua.
            - Unvisited rỗng   → cập nhật best nếu tốt hơn.
        5. Branching: chọn pivot, sinh tuyến khả thi chứa pivot,
           thêm nút con vào heap.
        6. Lặp đến khi heap rỗng hoặc hết time_limit.

    Tham số
    -------
    problem       : dict từ build_problem().
    time_limit    : giới hạn thời gian (giây).
    max_route_len : độ dài tuyến tối đa khi sinh feasible routes.

    Trả về
    ------
    dict gồm best_cost, best_routes, nodes_explored, elapsed, n_vehicles_used.
    """
    n_vehicles    = problem["n_vehicles"]
    all_customers = set(problem["customers"])
    start_time    = time.time()

    # Khởi tạo
    counter = 0
    root_lb = compute_lower_bound([], all_customers, problem)
    heap    = [(root_lb, counter, [], all_customers)]

    best_cost   = math.inf
    best_routes = None
    nodes_explored = 0

    while heap:
        # Kiểm tra time limit
        if time.time() - start_time > time_limit:
            print(f"    [!] Hết time_limit = {time_limit}s "
                  "— trả về nghiệm tốt nhất hiện tại.")
            break

        lb, _, assigned, unvisited = heapq.heappop(heap)
        nodes_explored += 1

        # Pruning theo cận
        if lb >= best_cost:
            continue

        # Pruning: hết xe mà còn khách
        if len(assigned) >= n_vehicles and unvisited:
            continue

        # Nghiệm hoàn chỉnh
        if not unvisited:
            total = sum(r["cost"] for r in assigned)
            if total < best_cost:
                best_cost   = total
                best_routes = deepcopy(assigned)
            continue

        # Phân nhánh
        pivot = select_pivot(unvisited, problem)

        all_routes   = generate_feasible_routes(unvisited, problem,
                                                max_route_len)
        pivot_routes = [r for r in all_routes if pivot in r["customers"]]

        for route in pivot_routes:
            new_assigned  = assigned + [route]
            new_unvisited = unvisited - set(route["customers"])
            new_lb        = compute_lower_bound(new_assigned,
                                                new_unvisited, problem)
            if new_lb < best_cost:
                counter += 1
                heapq.heappush(heap,
                               (new_lb, counter,
                                new_assigned, new_unvisited))

    elapsed = time.time() - start_time
    return {
        "best_cost":       best_cost,
        "best_routes":     best_routes,
        "nodes_explored":  nodes_explored,
        "elapsed":         elapsed,
        "n_vehicles_used": len(best_routes) if best_routes else 0,
    }


# ============================================================
# 8. IN KẾT QUẢ
# ============================================================

def print_result(n: int, result: dict):
    """
    In chi tiết kết quả của một instance.

    Tham số
    -------
    n      : số khách hàng.
    result : dict từ branch_and_bound().
    """
    print(f"\n{'='*62}")
    print(f"  Instance C101_n{n:02d}  |  n = {n} khách hàng")
    print(f"{'='*62}")

    if result["best_routes"] is None:
        print("  Không tìm được nghiệm khả thi trong giới hạn thời gian.")
        return

    print(f"  Tổng chi phí (Z*)    : {result['best_cost']:.4f}")
    print(f"  Số xe sử dụng        : {result['n_vehicles_used']}")
    print(f"  Số nút B&B duyệt     : {result['nodes_explored']}")
    print(f"  Thời gian chạy       : {result['elapsed']:.4f}s")
    print(f"\n  Chi tiết các tuyến:")
    for idx, route in enumerate(result["best_routes"], 1):
        cust_str = " -> ".join(str(c) for c in route["customers"])
        print(f"    Xe {idx:2d}: 0 -> {cust_str} -> 0"
              f"  (cost = {route['cost']:.4f})")


# ============================================================
# 9. CHẠY THỰC NGHIỆM
# ============================================================

def run_experiments(filepath: str = "C101.txt"):
    """
    Chạy B&B cho C101 với n = 5, 10, 15, 25.

    Tham số
    -------
    filepath : đường dẫn tới file C101.txt.
    """
    print(f"[*] Đọc dữ liệu từ: {filepath}")
    raw = parse_solomon_file(filepath)
    print(f"    Số xe: {raw['n_vehicles']}  |  Sức chứa: {raw['capacity']}")

    configs = [
        {"n":  5, "time_limit":  30, "max_route_len": 5},
        {"n": 10, "time_limit":  60, "max_route_len": 5},
        {"n": 15, "time_limit": 120, "max_route_len": 4},
        {"n": 25, "time_limit": 180, "max_route_len": 3},
    ]

    summary = []

    for cfg in configs:
        n   = cfg["n"]
        tl  = cfg["time_limit"]
        mrl = cfg["max_route_len"]

        print(f"\n[*] Giải C101_n{n:02d}  "
              f"(time_limit={tl}s, max_route_len={mrl}) ...")
        problem = build_problem(raw, n)
        result  = branch_and_bound(problem,
                                   time_limit=tl,
                                   max_route_len=mrl)
        print_result(n, result)

        summary.append({
            "n":       n,
            "cost":    f"{result['best_cost']:.4f}" if result["best_routes"] else "N/A",
            "vehicles": result["n_vehicles_used"],
            "nodes":   result["nodes_explored"],
            "time_s":  f"{result['elapsed']:.4f}",
        })

    # Bảng tổng hợp
    print(f"\n\n{'='*70}")
    print("  KẾT QUẢ THỰC NGHIỆM — Branch and Bound — Solomon Benchmark C101")
    print(f"{'='*70}")
    header = (f"  {'Instance':<14} {'Z* (tối ưu)':>14} "
              f"{'Số xe':>7} {'Số nút B&B':>12} {'T.gian (s)':>12}")
    print(header)
    print(f"  {'-'*14} {'-'*14} {'-'*7} {'-'*12} {'-'*12}")
    for s in summary:
        inst = f"C101_n{s['n']:02d}"
        print(f"  {inst:<14} {s['cost']:>14} {s['vehicles']:>7}"
              f" {s['nodes']:>12} {s['time_s']:>12}")
    print(f"{'='*70}\n")


# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    import sys
    filepath = sys.argv[1] if len(sys.argv) > 1 else "C101.txt"
    run_experiments(filepath)
