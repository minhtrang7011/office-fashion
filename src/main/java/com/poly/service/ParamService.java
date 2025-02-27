package com.poly.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest req;

    /**
     * Đọc chuỗi giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public String getString(String name, String defaultValue) {
        String value = req.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(req.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc số thực giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(req.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc giá trị boolean của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = req.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Đọc giá trị thời gian của tham số
     *
     * @param name    tên tham số
     * @param pattern là định dạng thời gian
     * @return giá trị tham số hoặc null nếu không tồn tại
     * @throws RuntimeException lỗi sai định dạng
     */
    public Date getDate(String name, String pattern) {
        String value = req.getParameter(name);
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            // Tạo đối tượng SimpleDateFormat với pattern được truyền vào
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

            // Chuyển đổi chuỗi thành đối tượng Date
            return dateFormat.parse(value);
        } catch (ParseException e) {
            // Ném lỗi nếu định dạng ngày không hợp lệ
            throw new RuntimeException("Invalid date format: " + value, e);
        }
    }

    /**
     * Lưu file upload vào thư mục
     *
     * @param file chứa file upload từ client
     * @param path đường dẫn tính từ webroot
     * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
     * @throws RuntimeException lỗi lưu file
     */
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            // Tạo đường dẫn file đích bằng cách nối đường dẫn thư mục với tên file
            String filePath = path + File.separator + file.getOriginalFilename();
            File dest = new File(filePath);

            // Lưu file vào đường dẫn chỉ định
            file.transferTo(dest);

            // Trả về file đã lưu
            return dest;
        } catch (IOException e) {
            // Ném lỗi nếu có vấn đề khi lưu file
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
