# 📢 Thông báo thi giữa kỳ & Hướng dẫn cuối kỳ môn Machine Learning (MAT3533)

Chào các bạn,

Lớp chúng ta sẽ thi giữa kỳ theo hình thức **báo cáo bài tập lớn** vào:

- 📅 **Thứ Tư tuần tới (06/05/2026)**
- Trong trường hợp không đủ thời gian, sẽ sử dụng thêm **buổi học bù**

---

## ⏰ Hạn nộp bài (Giữa kỳ)
- **Trước 23:59 ngày 02/05/2026**

---

## 📂 Các tài liệu cần nộp (Giữa kỳ)

### 1. 📝 Báo cáo (PDF)
- Viết theo dạng **tiểu luận**, bao gồm:
  - Đặt vấn đề (Mở đầu)
  - Kiến thức liên quan
  - Mô tả dữ liệu
  - Phương pháp
  - Kết quả thực nghiệm
  - Kết luận

- Nếu chia chương:
  - **Không quá 03 chương**
  - **Mở đầu và Kết luận không đánh số chương**

- 📌 Yêu cầu:
  - File riêng lẻ
  - Định dạng **PDF**
  - ❌ Không nén

### 2. 📊 Slide trình chiếu
- Nộp file riêng
- ❌ Không nén

> ⚠️ **Lưu ý quan trọng:**  
> **KHÔNG chèn code** vào báo cáo và slide

### 3. 💻 Chương trình nguồn

#### 3.1 Notebook (.ipynb)
- Phải **chạy đầy đủ**
- Bao gồm **tất cả kết quả** như trong báo cáo

#### 3.2 File Python (.py)
- Cần có thêm:
  - **01 file text chứa kết quả**

#### 3.3 File thông tin nhóm
- Nội dung gồm:
  - Thành viên nhóm
  - Công việc từng người
  - Hướng dẫn tổ chức chương trình
  - Kịch bản thực nghiệm

- Định dạng:
  - `.txt` hoặc `.doc`

#### 3.4 Dữ liệu & hướng dẫn
- Link tải dữ liệu
- Hướng dẫn tổ chức thư mục để chạy thực nghiệm

---

## 📌 Lưu ý chung (Giữa kỳ)
- Tất cả file:
  - 📂 **Để riêng lẻ**
  - ❌ **Không nén**
- 👤 Nộp bài theo **nhóm**
  - Chỉ **01 người đại diện nộp**
  - Các thành viên khác **không nộp**

---

# 🧠 Yêu cầu mở rộng cho CUỐI KỲ (dựa trên tài liệu môn học)

Dưới đây là các nội dung **bắt buộc phải có trong sản phẩm cuối kỳ** (sau khi kết thúc môn học), được tổng hợp từ file:
- `Projects_Ideas.pdf`
- `quy_tac_cham_bai_thuc_hanh_ml.pdf`

## A. Đối với bài toán HỒI QUY (nếu nhóm chọn)

### 1. Tiền xử lý dữ liệu
- Đọc, mô tả cấu trúc, thống kê sơ bộ.
- Xử lý lỗi (thiếu, sai định dạng, giá trị không hợp lệ).
- Chuyển đổi category → one-hot hoặc dạng số.
- Chuẩn hóa giá trị (nếu cần).
- Mô tả dữ liệu sau chuẩn hóa.

### 2. Phân tích & trực quan hóa (với ít nhất 2 phương pháp giảm chiều)
- PCA, t-SNE, hoặc các phương pháp khác.
- Phân tích tham số thống kê.
- Đánh giá thành phần chính.
- Hiển thị theo cặp 2 thành phần chính (4–6 thành phần).
- Xác định explained variance.
- Trực quan hóa mối quan hệ với đầu ra.
- **So sánh giữa các phương pháp giảm chiều.**

### 3. Phân cụm (chọn ≥2 phương pháp)
- K-Means, GMM (EM), DBScan.
- Phân cụm sau khi bỏ qua đầu ra.
- Đánh giá quan hệ giữa cụm và đầu ra bằng độ đo định lượng.
- Trực quan hóa với màu sắc phân biệt cụm.

### 4. Phân tích hồi quy
#### a) Thực hiện ≥2 phương pháp hồi quy
- Ví dụ: K-NN, Linear Regression, MLP.
- Chia train/validation: 4:1, 7:3, 6:4.
- Làm trên:
  - Dữ liệu gốc
  - Dữ liệu đã giảm chiều
- So sánh, nhận xét overfit.
- Áp dụng regularization (Ridge, Lasso, early stopping...).

#### b) Trực quan hóa phần dư
- Đánh giá sự phù hợp của mô hình.

#### c) Chuyển bài toán phân loại (sau khi chia đầu ra thành 3–4 khoảng đều mẫu)
- Dùng Naïve Bayes + 1 phương pháp khác.
- Làm trên dữ liệu gốc và giảm chiều còn 1/3 số chiều.
- So sánh, giải thích kết quả.

---

## B. Đối với bài toán PHÂN LOẠI (nếu nhóm chọn)

### 1. Tiền xử lý (tương tự hồi quy)

### 2. Phân tích & trực quan hóa (giống mục A.2)

### 3. Phân cụm (chọn ≥1 phương pháp)
- K-Means hoặc GMM.
- Đánh giá mối quan hệ với nhãn.
- Trực quan hóa bằng cả màu (cụm) và hình dạng (nhãn).

### 4. Phân loại
#### a) Thực hiện ≥3 phương pháp phân loại
- Nhóm 1: K-NN, Decision Tree, Naïve Bayes
- Nhóm 2: Softmax/Logistic Regression, MLP
- Nhóm 3: SVM
- Chia train/validation: 4:1, 7:3, 6:4
- Làm trên dữ liệu gốc và giảm chiều
- So sánh, nhận xét overfit, áp dụng regularization

#### b) Trực quan hóa dự đoán vs thực tế
- Đánh giá sự phù hợp của mô hình

#### c) Chuyển bài toán hồi quy (dùng decision function của Softmax hoặc SVM)
- Chọn 1 phân lớp.
- Xây dựng đầu ra mới dạng hồi quy.
- Thực hiện ≥2 mô hình hồi quy.
- Làm trên dữ liệu gốc và giảm chiều còn 1/3.
- So sánh, giải thích.

---

## C. Đối với nhóm tự chọn đề tài khác
- Phải đảm bảo đủ khối lượng, bao quát kiến thức, kích thước dữ liệu tương đương.
- Nêu rõ các phương pháp học máy được áp dụng.

---

# 📌 Thang điểm & tiêu chí đánh giá (quan trọng)

Dựa trên file `quy_tac_cham_bai_thuc_hanh_ml.pdf`:

### 1. Sản phẩm chương trình (40%)
| Tiêu chí | Trọng số | Giỏi (9-10) | Khá (7-8) |
|----------|-----------|--------------|------------|
| Mức độ hoàn thành | 30% | Đáp ứng **tất cả** yêu cầu, mở rộng, không lỗi, kiến trúc rõ ràng, đúng lý thuyết | ≥4 yêu cầu mức giỏi, không lỗi |
| Phương pháp | 20% | Hệ thống rõ ràng, có mở rộng, xác định đúng vấn đề - dữ liệu - công cụ | Chấp nhận được |
| Kiến thức & kỹ năng | 30% | Nắm vững lý thuyết, mở rộng, phân tích hệ thống, xây dựng công cụ | Đủ để cài đặt |
| Kỹ năng làm việc nhóm | 20% | Phân chia công bằng, tối ưu, module hóa, giám sát tiến độ | Hợp lý, đúng tiến độ |

### 2. Báo cáo kỹ thuật (30%)
- Tổng quan: 30%
- Phương pháp: 20%
- Kiến thức & kỹ năng: 40%
- Cấu trúc & ngôn ngữ: 10%

### 3. Thuyết trình (30%)
- Hình thức slide: 20%
- Nội dung: 30%
- Phong cách & kỹ năng: 30%
- Thảo luận: 20%

> ✅ **Muốn đạt Giỏi (8.5–10)**: Phải có **mở rộng** so với kiến thức đã học, phân tích sâu, so sánh nhiều phương pháp, giải thích rõ kết quả, không lỗi kỹ thuật, làm việc nhóm chặt chẽ.

---

# 🧭 Gợi ý chiến lược làm bài tối ưu

1. **Chọn dữ liệu phù hợp**:
   - Regression: ≥15 features (số + category), ≥300 mẫu.
   - Classification: tương tự, hoặc ảnh/chữ (không phải MNIST).
   - Tránh trùng đề tài với nhóm khác (đăng ký sớm).

2. **Phân công rõ ràng**:
   - 1 người: tiền xử lý + giảm chiều + trực quan.
   - 1 người: phân cụm + hồi quy/phân loại.
   - 1 người: viết báo cáo + slide + hậu kỳ.

3. **Làm đủ số lượng mô hình yêu cầu**:
   - Regression: 2 hồi quy + 2 phân cụm + 2 phân loại (sau khi chia khoảng).
   - Classification: 3 phân loại + 2 hồi quy (từ decision function).

4. **So sánh rõ ràng**:
   - Trước/sau giảm chiều.
   - Giữa các tỷ lệ train/val.
   - Giữa các phương pháp giảm chiều.

5. **Chú ý overfit & regularization**:
   - Ghi rõ độ lỗi train/test.
   - Áp dụng Ridge, Lasso, Dropout, EarlyStopping.

6. **Trực quan hóa đẹp, dễ hiểu**:
   - PCA 2D/3D.
   - Confusion matrix.
   - Đồ thị phần dư.

---

## ✅ Checklist cuối kỳ (trước khi nộp)

- [ ] Đã có ít nhất 2 phương pháp giảm chiều.
- [ ] Đã có đủ số mô hình hồi quy / phân loại / phân cụm theo yêu cầu.
- [ ] Đã so sánh kết quả giữa các phương pháp.
- [ ] Đã nhận xét overfit và áp dụng regularization.
- [ ] Báo cáo không chèn code, đúng cấu trúc 15–20 trang.
- [ ] Slide không quá dài, dễ nhìn, không đọc chữ.
- [ ] Code chạy lại được đầy đủ, có file `.py` + `.ipynb`.
- [ ] Có file hướng dẫn tổ chức chương trình và link data.
- [ ] Phân công công việc rõ ràng trong file nhóm.
- [ ] Tất cả file để riêng lẻ, không nén.

---

Chúc các bạn làm bài tốt và đạt điểm cao cả giữa kỳ lẫn cuối kỳ 💪