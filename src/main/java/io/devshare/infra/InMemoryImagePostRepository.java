package io.devshare.infra;

import io.devshare.domain.ImagePost;
import io.devshare.domain.ImagePostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryImagePostRepository implements ImagePostRepository {
    private List<ImagePost> imagePosts = new ArrayList<>();

    @Override
    public List<ImagePost> findAll() {
        return imagePosts;
    }

    @Override
    public ImagePost save(ImagePost imagePost) {
        imagePosts.add(imagePost);

        return imagePost;
    }
}
