package io.devshare.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.devshare.errors.NotSupportedImageExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 파일을 S3 저장소에 업로드 후, 저장한 URL을 리턴합니다.
     *
     * @param multipartFile 멀티파트 파일
     * @return 업로드한 파일 URL
     * @throws IOException                멀티파트 파일 변환에 실패한 경우
     * @throws NotSupportedImageExtension 올바르지 않은 이미지 확장자일 경우
     */
    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        ImageExtension.validate(fileName);

        String key = getKey(fileName);

        amazonS3Client.putObject(bucketName, key, multipartFile.getInputStream(), new ObjectMetadata());

        return amazonS3Client.getUrl(bucketName, key).toString();
    }

    private String getKey(String fileName) {
        return bucketImageDir + "/" + UUID.randomUUID() + fileName;
    }
}
