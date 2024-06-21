# Nhật kí MMD đi thỉnh kinh

"Thế giới có hai thứ bất tử: Nicolas Flamel và bug trong code của anh Mạc Duy"

Ngày xửa ngày xưa, Mạc Minh Duy được một vị hiền nhân (thật ra là bạn bố nó) tặng cho bộ bí kíp bất tử của Nicolas Flamel - một nhà giả kim xàm l nào đó ở xứ Anh xa xôi. Tuy nhiên, thần kì thế đ nào mà bộ sách của ông bác tặng cho anh lại thiếu cmn 2 quyển đầu đít, và anh thì đ thích đọc nửa bộ thì thôi.

Sau một thời gian lướt web để kiếm truyện về đọc, anh Duy đã tìm thấy một trang có full bộ truyện - với một vấn đề nho nhỏ: ảnh đ thể nào down được sách về, mà phải đọc online. vãi l chưa?

Trong cái khó ló cái ngu, thay vì down sách ở nguồn khác về, thì anh nghĩ cmn ra một kế hoạch thiên tài. Kế hoạch có nội dung như sau:
_"Thay vì down sách về, sao mình không cào toàn bộ các chương về, đóng vào một file Latex, format và xuất file pdf một cách TỰ CMN ĐỘNG bằng Java?"_

Và đó là lí do dự án này ra đời :>

# Day 1
Về cơ bản, backend của dự án gồm hai tính năng chính:
- Cào web: Sử dụng selenium để anti bọn load web = javascript
- Convert văn bản cào được thành code latex
Ngày đầu tiên sẽ tập trung vào setup Selenium - một trải nghiệm đau đơn về cả thể chất lẫn tinh thần

Một số kiếp nạn khi setup cái thư viện củ cak kia vào dự án:
- Chọn sai loại dự án: Dự án Java có thể thêm maven, nhưng dự án maven không thể thêm Java (dễ dàng). ĐỪNG BAO GIỜ chọn setup Maven Project trong Intelij nhé :<
- SLF4J: Một cách thần kì nào đấy, Selenium của Java không hề "plug-and-play" như của Python. MMD đã mất cả tiếng đồng hồ loay hoay tìm cách setup SLF4J, chỉ để nhận ra để chạy được Selenium, bên cạnh packet của selenium thì ta cần HAI DEPENDENCY cho SLF4J, và cả hai phải có cùng phiên bản
- ChromeDriver: Tưởng chừng kiếp nạn đã qua, ai ngờ sóng gió vẫn còn ở phía trước. Vấn đề lớn nhất nằm ở việc setup ChromeDriver, khi:
  - ChromeDriver cũng KHÔNG ĐI KÈM với Selenium
  - Có thể sử dụng DriverManager, tuy nhiên con này sẽ TỰ ĐỘNG TẢI PHIÊN BẢN 114 về? Vl luôn?
Và MMD mất thêm một tiếng nữa lục lọi khắp stackoverflow & chatGPT, cho đến khi tìm được trang web này:
_https://googlechromelabs.github.io/chrome-for-testing/#beta_
Thiên đường UwU
