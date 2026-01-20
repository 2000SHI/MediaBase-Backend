package com.example.media_base.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class CloudStorageUtil {

    public static String upload(
            S3Client s3Client,
            String bucketName,
            MultipartFile multipartFile) throws IOException {
        String filename = UUID.randomUUID() + "_" +
                StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        PutObjectRequest request = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));
        return "https://" + bucketName + ".s3.amazonaws.com/" + filename;
    }

}
