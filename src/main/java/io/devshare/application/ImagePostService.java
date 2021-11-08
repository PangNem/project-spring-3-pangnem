package io.devshare.application;

import io.devshare.domain.ImagePost;
import io.devshare.domain.ImagePostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagePostService {
    private ImagePostRepository imagePostRepository;

    public ImagePostService(ImagePostRepository imagePostRepository) {
        this.imagePostRepository = imagePostRepository;
    }

    public List<ImagePost> getAllImagePosts() {
        return imagePostRepository.findAll();
    }

    public ImagePost add(ImagePost imagePost) {
        return imagePostRepository.save(imagePost);
    }
}
