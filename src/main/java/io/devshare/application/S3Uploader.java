package io.devshare.application;

import com.amazonaws.services.s3.AmazonS3Client;
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

    /**
     * 파일을 S3 저장소에 업로드 후, 저장한 URL을 리턴합니다.
     *
     * @param mfile 멀티파트 파일
     * @return 업로드한 파일 URL
     * @throws IOException 멀티파트 파일 변환에 실패한 경우
     */
    public String upload(MultipartFile mfile) throws IOException {
        File convertedFile = convert(mfile);

        String key = getKey(convertedFile);
        amazonS3Client.putObject(bucketName, key, convertedFile);

        convertedFile.delete();

        return amazonS3Client.getUrl(bucketName, key).toString();
    }

    private String getKey(File file) {
        return bucketImageDir + "/" + UUID.randomUUID() + file.getName();
    }

    /**
     * 멀티파트 파일을 일반 파일 객체로 변환합니다.
     *
     * @param mfile 멀티파트 파일
     * @return 변환된 일반 파일 객체
     * @throws IOException 멀티파트 파일 변환에 실패한 경우
     */
    private File convert(MultipartFile mfile) throws IOException {
        String fileName = System.getProperty("user.dir") + "/" + mfile.getOriginalFilename();
        File file = new File(fileName);

        mfile.transferTo(file);

        return file;
    }
}
