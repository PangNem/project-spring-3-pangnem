package io.devshare.infra;

import io.devshare.domain.ImagePost;
import io.devshare.domain.ImagePostRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public interface JpaImagePostRepository extends ImagePostRepository, JpaRepository<ImagePost, Long> {
    List<ImagePost> findAllByOrderByIdDesc();

    ImagePost save(ImagePost imagePost);
}
