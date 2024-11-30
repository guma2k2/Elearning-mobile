package com.example.elearningmobile.ultity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static File getFileFromUri(Context context, Uri uri) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // Mở InputStream từ URI
            inputStream = context.getContentResolver().openInputStream(uri);
            // Tạo tên file từ URI (bạn có thể thay đổi cách này nếu cần)
            String fileName = "file_from_uri";
            // Tạo đối tượng File trong thư mục cache của ứng dụng
            File file = new File(context.getCacheDir(), fileName);
            // Tạo OutputStream để ghi dữ liệu vào file
            outputStream = new FileOutputStream(file);
            // Đọc dữ liệu từ InputStream và ghi vào OutputStream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            // Trả về đối tượng File đã tạo
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu có
            return null;
        } finally {
            // Đóng InputStream và OutputStream khi không cần dùng nữa
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
