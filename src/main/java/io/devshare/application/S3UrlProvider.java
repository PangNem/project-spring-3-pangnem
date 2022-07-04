package io.devshare.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import io.devshare.dto.PreSignedUrl;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class S3UrlProvider {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;
    @Value("${cloud.aws.s3.bucket.imageDir}")
    private String bucketImageDir;

    @Value("${cloud.aws.s3.bucket.fileDir}")
    private String bucketFileDir;

    public PreSignedUrl preSignedUrl(String filename) {
        String key = getKeyWith(filename);

        putS3(key);
        return getPreSignedUrl(key);
    }

    private PreSignedUrl getPreSignedUrl(String key) {
        URL url = amazonS3Client.generatePresignedUrl(bucketName, key, getExpiration(), HttpMethod.PUT);
        String preSignedUrl = url.toString();

        return new PreSignedUrl(preSignedUrl);
    }

    private void putS3(String key) {
        amazonS3Client.putObject(bucketName, key, key);
    }

    private Date getExpiration() {
        return DateTime.now().plusDays(1).toDate();
    }

    /**
     * 파일 이름을 포함하여 만든 키를 리턴합니다.
     * <p>
     * Note: 키에 현재시간이 포함되어 있기 때문에 메서드를 재호출 해서는 안됩니다.
     *
     * @param filename 파일 이름
     * @return 파일 이름을 포함하여 만든 키
     */
    private String getKeyWith(String filename) {
        if (ImageExtension.isImage(filename)) {
            return getKey(bucketImageDir, filename);
        }

        return getKey(bucketFileDir, filename);
    }

    private String getKey(String prefix, String filename) {
        String pattern = "yyyy-MMdd-H-mm-s"; // 2022-0511-10-45-30
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return prefix +
                "/" +
                simpleDateFormat.format(DateTime.now().toDate()) +
                "-" +
                filename;
    }
}
