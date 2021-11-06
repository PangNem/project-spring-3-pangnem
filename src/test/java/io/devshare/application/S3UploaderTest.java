package io.devshare.application;


import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml" +
        ",classpath:/aws.yml"
)
class S3UploaderTest {

    private static final String IMAGE_URL = "https://test-bucket-name.s3.ap-northeast-2.amazonaws.com/images/0c9a36d8-4052-46b8-8a9f-47b2108d8514test.png";
    private static final String FILENAME = "src/test/resources/static/img/test.png";

    private AmazonS3Client amazonS3Client;
    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() {
        amazonS3Client = mock(AmazonS3Client.class);
        s3Uploader = new S3Uploader(amazonS3Client);
    }

    @Test
    @DisplayName("upload 메서드는 S3에 저장한 이미지 URL을 리턴한다")
    void upload() throws IOException {
        given(amazonS3Client.getUrl(eq(null), anyString()))
                .willReturn(new URL(IMAGE_URL));

        MockMultipartFile file = new MockMultipartFile(
                "image",
                FILENAME,
                "image/png",
                new FileInputStream(FILENAME)
        );

        String url = s3Uploader.upload(file);

        assertThat(url).isEqualTo(IMAGE_URL);
    }
}