package io.devshare.controllers;

import io.devshare.application.ImagePostService;
import io.devshare.application.S3ImageUploader;
import io.devshare.dto.ImagePostCreateRequest;
import io.devshare.dto.ImageUploadRequest;
import io.devshare.dto.PreSignedUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImagePostService imagePostService;
    private final S3ImageUploader s3ImageUploader;

    @PostMapping("save")
    public ModelAndView save(
            @RequestBody @Valid PreSignedUrl preSignedUrl
    ) {
        imagePostService.add(preSignedUrl);

        return new ModelAndView("redirect:" + "/");
    }

    /**
     * 이미지 업로드 처리 후 메인 페이지로 이동합니다.
     *
     * @param imageUploadRequest 이미지 업로드 요청 시 데이터
     * @return 메인 페이지
     * @throws IOException 멀티파트 파일 변환에 실패한 경우
     */
    @PostMapping
    public ModelAndView imageUpload(
            ImageUploadRequest imageUploadRequest
    ) throws IOException {
        String imageUrl = s3ImageUploader.upload(imageUploadRequest);
        String uploader = imageUploadRequest.getUploader();

        ImagePostCreateRequest imagePostCreateRequest = ImagePostCreateRequest.builder()
                .url(imageUrl)
                .uploader(uploader)
                .build();

        imagePostService.add(imagePostCreateRequest);

        return new ModelAndView("redirect:" + "/");
    }

}
