/*
  Author : Mehedi Hasan Tamim;
  Time : 28/12/2020 11:20 PM
*/
package com.newgen.lms.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

@Component
public class UtilClz {

    public static void upload(MultipartFile file, String fileName) throws IOException {
        Path uploadPath = Paths.get(Constants.FILE_PATH);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    /*uniqueCodeGeneratorForFile*/
    public static String uniqueCodeGeneratorForFile(String fileName) {
        int digit = 999999;
        Random rnd = new Random();
        int number = rnd.nextInt(digit);
        return Integer.toString(number).concat("_" + fileName);
    }
}
