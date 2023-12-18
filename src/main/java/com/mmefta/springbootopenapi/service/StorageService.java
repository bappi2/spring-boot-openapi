package com.mmefta.springbootopenapi.service;

import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class StorageService {
    private String bucketName = "s3-upload-demo-bucket-mmefta";
    private S3Operations s3Template;

    @Autowired
    public StorageService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            s3Template.upload(bucketName, file.getOriginalFilename() , inputStream);
            //log.info(String.valueOf(s3Template.bucketExists(bucketName)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

        return "File uploaded : " + file.getOriginalFilename();
    }

    public byte[] download(String fileName) {
        S3Resource s3Resource = s3Template.download(bucketName, fileName);
        try {
            return s3Resource.getContentAsByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String file) {
        s3Template.deleteObject(bucketName, file);
        return "OK";
    }



}
