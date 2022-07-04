package io.devshare.dto;

import lombok.Getter;

@Getter
public class FileResponse {
    private final String url;

    public FileResponse(String url) {
        this.url = url;
    }
}
