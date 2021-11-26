package io.devshare.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImagePostCreateRequest {
    private final String uploader;
    private final String url;

    @Builder
    public ImagePostCreateRequest(String uploader, String url) {
        this.uploader = uploader;
        this.url = url;
    }
}
