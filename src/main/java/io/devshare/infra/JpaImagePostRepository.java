package io.devshare.infra;

import io.devshare.domain.ImagePost;
import io.devshare.domain.ImagePostRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public interface JpaImagePostRepository extends ImagePostRepository, CrudRepository<ImagePost, Long> {
    List<ImagePost> findAll();

    ImagePost save(ImagePost imagePost);
}
