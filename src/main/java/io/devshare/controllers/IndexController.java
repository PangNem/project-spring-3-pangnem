package io.devshare.controllers;

import io.devshare.application.ImagePostService;
import io.devshare.application.S3ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 서버 라우팅 담당.
 */
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final ImagePostService imagePostService;

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
}
