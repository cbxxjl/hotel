package com.cbxjl.hotel.common.utils;



import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 文件工具类
 *
 * @author : cbxjl
 * @date : 2024/3/17 15:48
 */
public class FileUtils {
    /**
     * file转为base64编码
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String fileToBase64(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static File base64ToFile(String base64, String fileName) throws IOException {
        // 解码 Base64 字符串为字节数组
        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        // 创建临时文件
        File tempFile = File.createTempFile(fileName, null);

        // 将字节数组写入临时文件
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(decodedBytes);
        }
        return tempFile;
    }
}

