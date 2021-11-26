package io.devshare.application;

import io.devshare.domain.ImagePost;
import io.devshare.dto.ImagePostCreateRequest;
import io.devshare.infra.InMemoryImagePostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagePostServiceTest {
    private ImagePostService imagePostService;

    @BeforeEach
    void setUp() {
        imagePostService = new ImagePostService(new InMemoryImagePostRepository());

        ImagePostCreateRequest imagePostCreateRequest = ImagePostCreateRequest.builder()
                .uploader("uploaderName")
                .url("url")
                .build();

        imagePostService.add(imagePostCreateRequest);
    }

    @Test
    @DisplayName("getAllImagePosts 메서드는 모든 ImagePost를 반환한다")
    void getAllImagePosts() {
        List<ImagePost> allImagePosts = imagePostService.getAllImagePosts();

        assertThat(allImagePosts).hasSize(1);
    }

    @Test
    @DisplayName("add 메서드는 업로드된 ImagePost를 추가한다")
    void add() {
        String uploader = "uploaderName";
        String url = "url";
        ImagePostCreateRequest imagePostCreateRequest = ImagePostCreateRequest.builder()
                .uploader(uploader)
                .url(url)
                .build();

        ImagePost uploadedImagePost = imagePostService.add(imagePostCreateRequest);

        assertThat(uploadedImagePost.getUploader()).isEqualTo(uploader);
        assertThat(uploadedImagePost.getUrl()).isEqualTo(url);
    }
}
