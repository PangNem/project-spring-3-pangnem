package io.devshare.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagePostTest {

    @Test
    @DisplayName("create 메서드는 업로더가 설정된 도메인 객체를 리턴한다")
    void createImagePostFrom_returns_set_uploader() {
        ImagePost imagePost = ImagePost.create("ImagePostUploader");

        String uploaderName = imagePost.getUploader();

        assertThat(uploaderName).isEqualTo("ImagePostUploader");
    }

    @Test
    @DisplayName("이미지 포스트를 업로드 할 수 있다.")
    void upload() {
        ImagePost imagePost = ImagePost.create("ImagePostUploader");

        imagePost.upload("url");

        assertThat(imagePost.getUrl()).isEqualTo("url");
    }
}
