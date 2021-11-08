package io.devshare.application;

import io.devshare.domain.ImagePost;
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

        imagePostService.add(ImagePost.createImagePostFrom("uploaderName"));
    }

    @Test
    @DisplayName("getAllImagePosts 메서드는 모든 ImagePost를 반환한다")
    void getAllImagePosts() {
        List<ImagePost> allImagePosts = imagePostService.getAllImagePosts();

        assertThat(allImagePosts).hasSize(1);
    }

    @Test
    @DisplayName("add 메서드는 ImagePost를 추가한다")
    void add() {
        ImagePost imagePost = ImagePost.createImagePostFrom("uploaderName");

        imagePostService.add(imagePost);

        assertThat(imagePostService.getAllImagePosts()).hasSize(2);
    }
}
