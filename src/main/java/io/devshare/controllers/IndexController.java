package io.devshare.controllers;

import io.devshare.application.S3Uploader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class IndexController {

    private S3Uploader s3Uploader;

    public IndexController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/images")
    public String image(
            @RequestParam("uploader") String uploader,
            @RequestParam("images") MultipartFile images
    ) throws IOException {
        String imageUrl = s3Uploader.upload(images, uploader);

        System.out.println(imageUrl);

        return "index";
    }
}
