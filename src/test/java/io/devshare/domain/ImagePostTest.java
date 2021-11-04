package io.devshare.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagePostTest {

    @Test
    @DisplayName("createImagePostFrom 메서드는 업로더가 설정된 도메인 객체를 리턴한다")
    void createImagePostFrom_returns_set_uploader() {
        ImagePost imagePost = ImagePost.createImagePostFrom("ImagePostUploader");

        String uploaderName = imagePost.getUploader();

        assertThat(uploaderName).isEqualTo("ImagePostUploader");
    }
}
