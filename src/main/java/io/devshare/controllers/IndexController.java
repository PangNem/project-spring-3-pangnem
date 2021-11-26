package io.devshare.controllers;

import io.devshare.application.ImagePostService;
import io.devshare.application.S3ImageUploader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 서버 라우팅 담당.
 */
@Controller
public class IndexController {

    private S3ImageUploader s3ImageUploader;
    private ImagePostService imagePostService;

    public IndexController(S3ImageUploader s3ImageUploader, ImagePostService imagePostService) {
        this.s3ImageUploader = s3ImageUploader;
        this.imagePostService = imagePostService;
    }

    /**
     * 메인 페이지를 처리합니다.
     *
     * @param model 페이지 모델
     * @return 메인 페이지
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("ImagePosts", imagePostService.getAllImagePosts());

        return "index";
    }

    /**
     * 이미지 업로드 처리 후 메인 페이지로 이동합니다.
     *
     * @param uploader 이미지 업로더 이름
     * @param images   이미지 파일
     * @return 메인 페이지
     * @throws IOException 멀티파트 파일 변환에 실패한 경우
     */
    @PostMapping(value = "/images")
    public ModelAndView image(
            @RequestParam("uploader") String uploader,
            @RequestParam("images") MultipartFile images
    ) throws IOException {
        String imageUrl = s3ImageUploader.upload(images);

        imagePostService.add(uploader, imageUrl);

        return new ModelAndView("redirect:" + "/");
    }
}
