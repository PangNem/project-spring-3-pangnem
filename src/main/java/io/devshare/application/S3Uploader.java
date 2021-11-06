package io.devshare.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    public S3Uploader(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.s3.bucket.imageDir}")
    private String bucketImageDir;

    public String upload(MultipartFile mfile) throws IOException {
        File convertedFile = convert(mfile);
        String key = getKey(convertedFile);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, convertedFile)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3Client.putObject(putObjectRequest);

        return amazonS3Client.getUrl(bucketName, key)
                .toString();
    }

    private String getKey(File file) {
        return bucketImageDir + "/" + UUID.randomUUID() + file.getName();
    }

    private File convert(MultipartFile mfile) throws IOException {
        String fileName = System.getProperty("user.dir") + "/" + mfile.getOriginalFilename();

        File file = new File(fileName);
        mfile.transferTo(file);

        return file;
    }
}
