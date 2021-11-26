package io.devshare.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ImageUploadRequest {
    private final String uploader;
    private final MultipartFile images;

    @Builder
    public ImageUploadRequest(String uploader, MultipartFile images) {
        this.uploader = uploader;
        this.images = images;
    }
}
