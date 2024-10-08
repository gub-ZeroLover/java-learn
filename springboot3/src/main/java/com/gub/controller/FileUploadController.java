package com.gub.controller;


import com.gub.pojo.Result;
import com.gub.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

//        file.transferTo(new File("/home/gub/Downloads/java-learn/springboot3/" + filename));
         String url = AliOssUtil.uploadFile(filename, file.getInputStream());

        return Result.success(url);
    }
}
