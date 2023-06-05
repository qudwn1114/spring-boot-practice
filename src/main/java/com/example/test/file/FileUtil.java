package com.example.test.file;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtil {
    public String getOrCreateDirectory(String path){
        String uploadPath = System.getProperty("user.dir") + path.replace("/", File.separator);

        File uploadFilePath = new File(uploadPath);

        if(uploadFilePath.exists() == false){
            uploadFilePath.mkdirs();
        }
        
        return uploadPath;
    }

    public String getDatePath(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return date;
    }
}
