package io.devshare.controllers;

import io.devshare.application.ImagePostService;
import io.devshare.application.S3Uploader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class IndexController {

    private S3Uploader s3Uploader;
    private ImagePostService imagePostService;

    public IndexController(S3Uploader s3Uploader, ImagePostService imagePostService) {
        this.s3Uploader = s3Uploader;
        this.imagePostService = imagePostService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("ImagePosts", imagePostService.getAllImagePosts());

        return "index";
    }

    @PostMapping(value = "/images")
    public ModelAndView image(
            @RequestParam("uploader") String uploader,
            @RequestParam("images") MultipartFile images
    ) throws IOException {
        String imageUrl = s3Uploader.upload(images, uploader);

        System.out.println(imageUrl);

        return new ModelAndView("redirect:" + "/");
    }
}
