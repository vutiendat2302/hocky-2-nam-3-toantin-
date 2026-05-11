"""
Vehicle Routing Problem with Time Windows (VRPTW)
Phương pháp giải chính xác: Branch and Bound (via MILP - docplex)
Bộ dữ liệu: Solomon Benchmark C101
Nhóm 2 - Khoa Toán - Cơ - Tin học
"""

from docplex.mp.model import Model
import numpy as np
import math
import time
from collections import defaultdict


# ============================================================
# 1. ĐỌC DỮ LIỆU TỪ FILE SOLOMON BENCHMARK
# ============================================================

def parse_solomon_file(filepath: str):
    """
    Đọc và phân tích file Solomon Benchmark (định dạng chuẩn).

    Parameters
    ----------
    filepath : str
        Đường dẫn tới file dữ liệu (ví dụ: 'C101.txt').

    Returns
    -------
    n_vehicles : int
        Số xe tối đa.
    capacity : int
        Sức chứa mỗi xe.
    data : np.ndarray, shape (N, 7)
        Ma trận dữ liệu: [id, x, y, demand, ready, due, service].
    """
    with open(filepath, "r") as f:
        lines = [ln.strip() for ln in f if ln.strip()]

    n_vehicles = capacity = None
    rows = []

    for line in lines:
        parts = line.split()
        if (n_vehicles is None and len(parts) == 2
                and parts[0].isdigit() and parts[1].isdigit()):
            n_vehicles = int(parts[0])
            capacity   = int(parts[1])
        elif len(parts) == 7 and parts[0].isdigit():
            rows.append([int(v) for v in parts])

    data = np.array(rows, dtype=float)   # shape (N, 7)
    return n_vehicles, capacity, data


# ============================================================
# 2. XÂY DỰNG MA TRẬN THAM SỐ
# ============================================================

def build_parameters(data: np.ndarray, n_customers: int,
                     capacity: int, n_vehicles: int):
    """
    Xây dựng các tham số bài toán từ ma trận dữ liệu thô.

    Parameters
    ----------
    data        : np.ndarray, shape (N, 7)
        Ma trận dữ liệu Solomon [id, x, y, demand, ready, due, service].
    n_customers : int
        Số khách hàng cần lấy (không kể depot).
    capacity    : int
        Sức chứa mỗi xe (Q).
    n_vehicles  : int
        Số xe tối đa (V).

    Returns
    -------
    n   : int              – tổng số đỉnh (depot + khách hàng)
    D   : np.ndarray (n,n) – ma trận khoảng cách Euclidean
    q   : np.ndarray (n,)  – nhu cầu tại mỗi đỉnh
    Q   : int              – sức chứa xe
    s   : np.ndarray (n,)  – thời gian phục vụ
    t   : np.ndarray (n,)  – ready time (mở cửa)
    T   : np.ndarray (n,)  – due date (đóng cửa)
    V   : int              – số xe
    """
    rows = data[: n_customers + 1]   # depot (hàng 0) + n_customers
    n    = len(rows)

    x_coord = rows[:, 1]
    y_coord = rows[:, 2]

    # Ma trận khoảng cách Euclidean
    D = np.zeros((n, n), dtype=float)
    for i in range(n):
        for j in range(n):
            D[i, j] = round(
                math.hypot(x_coord[i] - x_coord[j],
                           y_coord[i] - y_coord[j]), 1
            )

    q = rows[:, 3].astype(float)   # demand
    s = rows[:, 6].astype(float)   # service time
    t = rows[:, 4].astype(float)   # ready time
    T = rows[:, 5].astype(float)   # due date

    return n, D, q, capacity, s, t, T, n_vehicles


# ============================================================
# 3. MÔ HÌNH MILP VÀ GIẢI BẰNG BRANCH AND BOUND (docplex)
# ============================================================

def solve_vrptw(n: int, D: np.ndarray, q: np.ndarray,
                Q: int, s: np.ndarray, t: np.ndarray,
                T: np.ndarray, V: int):
    """
    Xây dựng mô hình MILP cho bài toán VRPTW và giải bằng
    Branch and Bound (thông qua docplex / CPLEX).

    Mô hình toán học
    ----------------
    Biến quyết định:
        x[i,j,k] in {0,1}  – xe k đi trực tiếp từ i đến j
        ta[i]    >= 0       – thời điểm đến tại đỉnh i
        w[i]     >= 0       – thời gian chờ tại đỉnh i

    Hàm mục tiêu:
        min  sum_{i,j,k} D[i,j] * x[i,j,k]

    Ràng buộc:
        (1) Số xe xuất phát <= V
        (2) Mỗi xe: ra khỏi depot = vào depot, tối đa 1 lần
        (3) Mỗi khách hàng được phục vụ đúng 1 lần (vào và ra)
        (4) Sức chứa mỗi xe <= Q
        (5) Khung thời gian: t[i] <= ta[i] + w[i] <= T[i]
        (6) Thứ tự thời gian: Big-M linearization

    Parameters
    ----------
    n : int              – số đỉnh (depot + khách hàng)
    D : np.ndarray (n,n) – ma trận khoảng cách
    q : np.ndarray (n,)  – nhu cầu
    Q : int              – sức chứa xe
    s : np.ndarray (n,)  – thời gian phục vụ
    t : np.ndarray (n,)  – ready time
    T : np.ndarray (n,)  – due date
    V : int              – số xe

    Returns
    -------
    model    : docplex Model
    x, ta, w : biến quyết định
    solution : đối tượng kết quả docplex
    """
    model = Model(name='VRPTW')
    M_big = float(max(T))

    # ── Biến quyết định ────────────────────────────────────────
    x  = model.binary_var_cube(n, n, V, name='x')
    ta = model.continuous_var_list(n, lb=0, name='ta')
    w  = model.continuous_var_list(n, lb=0, name='w')

    # ── Hàm mục tiêu ───────────────────────────────────────────
    model.minimize(
        model.sum(D[i, j] * x[i, j, k]
                  for i in range(n)
                  for j in range(n) if i != j
                  for k in range(V))
    )

    # ── Ràng buộc (1): Số xe tối đa ────────────────────────────
    model.add_constraint(
        model.sum(x[0, j, k] for k in range(V) for j in range(1, n)) <= V,
        ctname='max_vehicles'
    )

    # ── Ràng buộc (2): Cân bằng depot mỗi xe ──────────────────
    for k in range(V):
        model.add_constraint(
            model.sum(x[0, j, k] for j in range(1, n)) ==
            model.sum(x[j, 0, k] for j in range(1, n)),
            ctname=f'depot_balance_k{k}'
        )
        model.add_constraint(
            model.sum(x[0, j, k] for j in range(1, n)) <= 1,
            ctname=f'one_route_k{k}'
        )

    # ── Ràng buộc (3): Phục vụ mỗi khách đúng 1 lần ───────────
    for i in range(1, n):
        model.add_constraint(
            model.sum(x[i, j, k]
                      for j in range(n) if j != i
                      for k in range(V)) == 1,
            ctname=f'leave_{i}'
        )
    for j in range(1, n):
        model.add_constraint(
            model.sum(x[i, j, k]
                      for i in range(n) if i != j
                      for k in range(V)) == 1,
            ctname=f'enter_{j}'
        )

    # ── Ràng buộc (4): Sức chứa ────────────────────────────────
    for k in range(V):
        model.add_constraint(
            model.sum(
                q[i] * model.sum(x[i, j, k] for j in range(n) if j != i)
                for i in range(n)
            ) <= Q,
            ctname=f'capacity_k{k}'
        )

    # ── Ràng buộc (5): Khung thời gian ─────────────────────────
    model.add_constraint(ta[0] == 0, ctname='depot_ta')
    model.add_constraint(w[0]  == 0, ctname='depot_w')

    for i in range(1, n):
        model.add_constraint(ta[i] >= t[i],              ctname=f'ready_{i}')
        model.add_constraint(ta[i] + w[i] <= T[i],       ctname=f'due_{i}')

    # ── Ràng buộc (6): Thứ tự thời gian (Big-M) ───────────────
    for k in range(V):
        for i in range(n):
            for j in range(1, n):
                if i != j:
                    model.add_constraint(
                        ta[j] >= ta[i] + s[i] + w[i]
                                 - M_big * (1 - x[i, j, k]),
                        ctname=f'time_seq_{i}_{j}_k{k}'
                    )

    # ── Giải bằng Branch and Bound ─────────────────────────────
    solution = model.solve(log_output=False)

    return model, x, ta, w, solution


# ============================================================
# 4. TRÍCH XUẤT TUYẾN ĐƯỜNG TỪ NGHIỆM
# ============================================================

def extract_routes(x, n: int, V: int):
    """
    Trích xuất các tuyến đường từ giá trị nghiệm biến x.

    Parameters
    ----------
    x : biến quyết định x[i,j,k]
    n : số đỉnh
    V : số xe

    Returns
    -------
    routes : list of dict
        Mỗi phần tử: {vehicle, path, edges}
    """
    def dfs(graph, node, visited):
        visited.add(node)
        path = [node]
        while True:
            nexts = [j for j in graph.get(node, []) if j not in visited]
            if not nexts:
                break
            node = nexts[0]
            visited.add(node)
            path.append(node)
        return path

    routes = []
    for k in range(V):
        edges = [
            (i, j)
            for i in range(n)
            for j in range(n)
            if i != j and x[i, j, k].solution_value > 0.5
        ]
        if not edges:
            continue

        graph = defaultdict(list)
        for (i, j) in edges:
            graph[i].append(j)

        path = dfs(graph, 0, set())
        if len(path) > 1:
            path.append(0)   # quay về depot
            routes.append({"vehicle": k, "path": path, "edges": edges})

    return routes


# ============================================================
# 5. IN KẾT QUẢ
# ============================================================

def print_solution(n: int, D: np.ndarray, ta, w,
                   solution, routes: list):
    """
    In chi tiết nghiệm: tổng chi phí, các tuyến đường,
    thời gian đến và chờ tại từng đỉnh.

    Parameters
    ----------
    n        : số đỉnh
    D        : ma trận khoảng cách
    ta, w    : biến quyết định đã giải
    solution : đối tượng kết quả docplex
    routes   : danh sách tuyến từ extract_routes()
    """
    if solution is None:
        print("  [!] Không tìm được nghiệm khả thi.")
        return

    print(f"\n  Tổng chi phí (Z*)  : {solution.objective_value:.4f}")
    print(f"  Số xe sử dụng      : {len(routes)}")

    print("\n  Chi tiết các tuyến:")
    for r in routes:
        path     = r["path"]
        cost     = sum(D[path[i], path[i+1]] for i in range(len(path)-1))
        path_str = " -> ".join(map(str, path))
        print(f"    Xe {r['vehicle']+1:2d}: {path_str}  (cost = {cost:.4f})")

    print("\n  Thời gian tại các khách hàng:")
    print(f"    {'Node':>5}  {'ta (đến)':>10}  {'w (chờ)':>9}")
    print(f"    {'-----':>5}  {'----------':>10}  {'---------':>9}")
    for i in range(1, n):
        print(f"    {i:>5}  {ta[i].solution_value:>10.2f}"
              f"  {w[i].solution_value:>9.2f}")


# ============================================================
# 6. CHẠY THỰC NGHIỆM n = 5, 10, 15, 25
# ============================================================

def run_experiments(filepath: str = "C101.txt"):
    """
    Chạy B&B (MILP) cho Solomon C101 với n = 5, 10, 15, 25.

    Parameters
    ----------
    filepath : str
        Đường dẫn tới file C101.txt.
    """
    print(f"[*] Đọc dữ liệu từ: {filepath}")
    n_vehicles_raw, capacity, data = parse_solomon_file(filepath)
    print(f"    Số xe tối đa : {n_vehicles_raw}")
    print(f"    Sức chứa     : {capacity}")

    configs = [5, 10, 15, 25]
    summary = []

    for n_cust in configs:
        print(f"\n{'='*62}")
        print(f"  Instance C101_n{n_cust:02d}  |  n = {n_cust} khách hàng")
        print(f"{'='*62}")

        n, D, q, Q, s, t, T, V = build_parameters(
            data, n_cust, capacity, n_vehicles_raw
        )

        t_start = time.time()
        model, x, ta, w, solution = solve_vrptw(n, D, q, Q, s, t, T, V)
        elapsed = time.time() - t_start

        routes = extract_routes(x, n, V) if solution else []
        print_solution(n, D, ta, w, solution, routes)
        print(f"\n  Thời gian giải : {elapsed:.4f}s")

        summary.append({
            "n":       n_cust,
            "cost":    f"{solution.objective_value:.4f}" if solution else "N/A",
            "vehicles": len(routes),
            "time_s":  f"{elapsed:.4f}",
        })

    # Bảng tổng hợp
    print(f"\n\n{'='*62}")
    print("  KẾT QUẢ THỰC NGHIỆM — Branch and Bound — Solomon C101")
    print(f"{'='*62}")
    print(f"  {'Instance':<14} {'Z* (tối ưu)':>14} "
          f"{'Số xe':>7} {'T.gian (s)':>12}")
    print(f"  {'-'*14} {'-'*14} {'-'*7} {'-'*12}")
    for row in summary:
        inst = f"C101_n{row['n']:02d}"
        print(f"  {inst:<14} {row['cost']:>14} "
              f"{row['vehicles']:>7} {row['time_s']:>12}")
    print(f"{'='*62}\n")


# ============================================================
# ENTRY POINT
# ============================================================

if __name__ == "__main__":
    import sys
    filepath = sys.argv[1] if len(sys.argv) > 1 else "C101.txt"
    run_experiments(filepath)
