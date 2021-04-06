Đề tài môn Công nghệ phần mềm
Tên đề tài: Ứng dụng điểm danh trong lớp học
Nhóm thực hiện: Nhóm 02
Danh sách các yêu cầu của đề tài:
1.
- Yêu cầu: Đăng nhập
- Giải thích: Mỗi giảng viên cần có 1 tài khoản để truy cập vào từng lớp học của mình (Mở rộng sinh viên cũng có tài khoản để có thể tự điểm danh của mình vào tiết học ngày hôm đó, có sự giám sát và xác nhận của giáo viên).
- Input: Username, password, quyền (Là sinh viên hay giảng viên)
- Output: Đăng nhập được vào phần mềm 
2.
- Yêu cầu: Cập nhật thông tin sinh viên
- Giải thích: Thêm tên, mssv, hình ảnh cho từng sinh viên trong lớp học.
- Input: Sinh viên sẽ tự cập nhật thông tin cá nhân của mình lên hệ thống (Hình ảnh, Tên, MSSV, Lớp,...)
- Output: Thông tin của từng sinh viên.
3. 
- Yêu cầu: Training thông tin sinh viên thông qua hình ảnh để sử dụng nhận diện khuôn mặt.
- Giải thích: Thuận tiện hơn cho việc nhận dạng thông tin của sinh viên (Chức năng này sử dụng API của Microsoft).
- Input: Hình ảnh của mỗi sinh viên (Yêu cầu rõ mặt, chụp từ nhiều góc)
- Output: Training được hình ảnh của từng sinh viên để nhận dạng.
4.
- Yêu cầu: Chia danh sách sinh viên theo từng môn học.
- Giải thích: Mỗi giảng viên sẽ dạy nhiều môn học theo nhiều khung giờ khác nhau, nên phân danh sách sinh viên theo từng môn sẽ dễ quản lý hơn.
- Input: Nhập vào bảng đăng kí môn học của từng sinh viên.
- Output: Danh sách sinh viên theo từng môn học.
5. 
- Yêu cầu: Điểm danh bằng cách sử dụng camera để quét mã vạch, hoặc khuôn mặt.
- Giải thích: Để tránh mất thời gian và nhầm lẫn thì nên để từng sinh viên đi ngang qua camera trước khi ra khỏi lớp sẽ có độ chính xác cao hơn.
- Input: Mã QR code hoặc khuôn mặt của sinh viên.
- Output: Nhận dạng được sinh viên đó đã đến lớp.
6. 
- Yêu cầu: Tìm kiếm môn học, tìm kiếm sinh viên.
- Giải thích: Để dễ quản lý thì ứng dụng cho phép giáo viên tìm kiếm môn học theo tên hoặc mã, tìm kiếm sinh viên theo tên hoặc mã.
- Input: Mã môn học, tên môn học, mã sinh viên, tên sinh viên.
- Output: Xuất ra danh sách thông tin môn học hoặc sinh viên cần tìm.
7. 
- Yêu cầu: Lọc các sinh viên đi học hoặc vắng.
- Giải thích: Trong 1 buổi học, có bao nhiêu sinh viên đi học, có bao nhiêu sinh viên nghỉ học… Ứng dụng sẽ cho phép giáo viên lọc được những thông tin đó.
- Input: Nhập ngày, môn học đó.
- Output: Xuất ra danh sách sinh viên vắng của buổi học hôm đó.
8. 
- Yêu cầu: Tra cứu sinh viên đi học, vắng học theo ngày, tháng, năm.
- Giải thích: Đôi lúc giảng viên cần kiểm tra xem ngày hôm đó sẽ có những sinh viên nào tham gia.
- Input: Nhập ngày, tháng, năm, loại sinh viên cần lọc (Vắng hay đi học)
- Output: Xuất ra danh sách sinh viên đi học hay vắng của ngày tháng năm đó.
9.
- Yêu cầu: Database tối ưu, ứng dụng chạy nhanh, chính xác, dễ dàng cập nhật và vá lỗi.
- Giải thích: Trong quá trình dùng thực tế sẽ phát sinh những lỗi nhỏ khi đó lập trình viên có thể dễ dàng sửa lỗi và nâng cấp các tính năng mới.
- Input: Tối ưu hệ thống từ lúc bắt đầu lên ý tưởng và thực hiện viết database.
- Output: Cho ra 1 hệ thống tối ưu, xử lý dữ liệu nhanh, dễ bảo trì và sửa lỗi.
