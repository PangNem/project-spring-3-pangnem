package io.devshare.infra;

import io.devshare.domain.ImagePost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryImagePostRepositoryTest {

    private InMemoryImagePostRepository inMemoryImagePostRepository;

    @BeforeEach
    void setUp() {
        inMemoryImagePostRepository = new InMemoryImagePostRepository();
    }

    @Test
    @DisplayName("save 메서드는 이미지 포스트를 저장한다")
    void save_test() {
        ImagePost imagePost = ImagePost.create("imagePost");
        assertThat(inMemoryImagePostRepository.findAll())
                .hasSize(0);

        inMemoryImagePostRepository.save(imagePost);

        assertThat(inMemoryImagePostRepository.findAll())
                .hasSize(1);
    }
}
