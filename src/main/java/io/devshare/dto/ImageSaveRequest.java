package io.devshare.dto;

import lombok.Getter;

@Getter
public class ImageSaveRequest {
    private final PreSignedUrl preSignedUrl;

    public ImageSaveRequest(PreSignedUrl preSignedUrl) {
        this.preSignedUrl = preSignedUrl;
    }

    public String toReadableUrl() {
        return this.preSignedUrl.toReadableUrl();
    }
}
