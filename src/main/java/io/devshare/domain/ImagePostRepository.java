package io.devshare.domain;

import java.util.List;

public interface ImagePostRepository {
    List<ImagePost> findAllByOrderByIdDesc();

    ImagePost save(ImagePost imagePost);
}
