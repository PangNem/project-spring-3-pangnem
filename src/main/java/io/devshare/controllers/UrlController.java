package io.devshare.controllers;

import io.devshare.application.S3UrlProvider;
import io.devshare.dto.PreSignedUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {
    private final S3UrlProvider s3UrlProvider;

    @GetMapping("/presigned")
    public PreSignedUrl imagePreSignedUrls(@RequestParam String filename) {
        return s3UrlProvider.preSignedUrl(filename);
    }
}
