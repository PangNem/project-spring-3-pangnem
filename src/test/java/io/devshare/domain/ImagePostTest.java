package io.devshare.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagePostTest {

    @Test
    @DisplayName("createImage 메서드는 업로더를 설정한다.")
    void createImage_set_uploader() {
        ImagePost imagePost = ImagePost.createImagePostFrom("ImagePostUploader");

        String uploaderName = imagePost.getUploader();

        assertThat(uploaderName).isEqualTo("ImagePostUploader");
    }
}
