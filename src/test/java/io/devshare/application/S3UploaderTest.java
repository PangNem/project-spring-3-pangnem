package io.devshare.application;

import com.amazonaws.services.s3.AmazonS3Client;
import io.devshare.errors.NotSupportedImageExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml" +
        ",classpath:/aws_test.yml"
)
class S3UploaderTest {

    private static final String IMAGE_URL = "https://test-bucket-name.s3.ap-northeast-2.amazonaws.com/images/0c9a36d8-4052-46b8-8a9f-47b2108d8514test.png";
    private static final String IMAGE_FILENAME = "src/test/resources/static/img/test.png";
    private static final String TEXT_FILENAME = "src/test/resources/static/text/test.txt";

    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() throws MalformedURLException {
        AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);
        s3Uploader = new S3Uploader(amazonS3Client);

        given(amazonS3Client.getUrl(eq(null), anyString()))
                .willReturn(new URL(IMAGE_URL));
    }

    @Test
    @DisplayName("upload 메서드는 S3에 저장한 이미지 URL을 리턴한다")
    void upload() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "test.png",
                "image/png",
                new FileInputStream(IMAGE_FILENAME)
        );

        String url = s3Uploader.upload(file);

        assertThat(url).isEqualTo(IMAGE_URL);
    }

    @Test
    @DisplayName("upload 메서드는 올바르지 않은 확장자일 경우 에러를 던진다")
    void upload_with_invalidExtension() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "test.txt",
                "image/png",
                new FileInputStream(TEXT_FILENAME)
        );

        assertThatThrownBy(() -> s3Uploader.upload(file))
                .isInstanceOf(NotSupportedImageExtension.class);
    }
}
