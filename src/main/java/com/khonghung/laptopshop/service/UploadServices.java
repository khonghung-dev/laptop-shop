package com.khonghung.laptopshop.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadServices {
    private final ServletContext servletContext;

    public UploadServices(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finalName = "";
        try {
            byte[] bytes = file.getBytes();
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalName;
    }
    public void handleDeleteFile(String fileName, String targetFolder) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return;
        }
        try {
            String rootPath = this.servletContext.getRealPath("/resources/images");
            File fileToDelete = new File(rootPath + File.separator + targetFolder + File.separator + fileName);
            if (fileToDelete.exists()) {
                fileToDelete.delete(); // Xóa file cũ khỏi server
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
