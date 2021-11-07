package io.devshare.domain;

import java.util.List;

public interface ImagePostRepository {
    List<ImagePost> findAll();

    ImagePost save(ImagePost imagePost);
}
