package io.devshare.application;

import com.amazonaws.services.s3.AmazonS3Client;
import io.devshare.errors.NotSupportedImageExtensionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    private static final String IMAGE_DIR_NAME = "src/test/resources/static/img/test.png";

    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() throws MalformedURLException {
        AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);
        s3Uploader = new S3Uploader(amazonS3Client);

        given(amazonS3Client.getUrl(eq(null), anyString()))
                .willReturn(new URL(IMAGE_URL));
    }

    @Nested
    @DisplayName("upload 메서드는")
    class Describe_upload {

        @Nested
        @DisplayName("지원되지 않는 확장자를 가진 파일 이름일 경우")
        class context_notSupportedExtensionFileName {

            private String unSupportedExtensionFileName;

            @BeforeEach
            void setUp() {
                unSupportedExtensionFileName = "test.txt";
            }

            @Test
            @DisplayName("에러를 던진다")
            void it_throws_error() throws IOException {
                MockMultipartFile file = getFile(unSupportedExtensionFileName);

                assertThatThrownBy(() -> s3Uploader.upload(file))
                        .isInstanceOf(NotSupportedImageExtensionException.class);
            }
        }

        @Nested
        @DisplayName("지원되는 확장자를 가진 파일 이름일 경우")
        class context_supportedExtensionFileName {

            private String supportedExtensionFileName;

            @BeforeEach
            void setUp() {
                supportedExtensionFileName = "test.png";
            }

            @Test
            @DisplayName("S3에 저장한 이미지 URL을 리턴한다")
            void it_returns_savedS3ImageURL() throws IOException {
                MockMultipartFile file = getFile(supportedExtensionFileName);

                String url = s3Uploader.upload(file);

                assertThat(url).isEqualTo(IMAGE_URL);
            }
        }

        private MockMultipartFile getFile(String fileName) throws IOException {
            return new MockMultipartFile(
                    "image",
                    fileName,
                    "image/png",
                    new FileInputStream(IMAGE_DIR_NAME)
            );
        }
    }
}
