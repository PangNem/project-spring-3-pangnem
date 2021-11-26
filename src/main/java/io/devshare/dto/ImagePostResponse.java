package io.devshare.dto;

import io.devshare.domain.ImagePost;
import lombok.Getter;

@Getter
public class ImagePostResponse {

    private final String uploader;
    private final String url;

    public ImagePostResponse(ImagePost imagePost) {
        this.uploader = imagePost.getUploader();
        this.url = imagePost.getUrl();
    }
}
