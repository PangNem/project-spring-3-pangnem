package io.devshare.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 이미지 포스트 도메인.
 */
@Entity
public class ImagePost {

    @Id
    @GeneratedValue
    private Long id;

    private String uploader;

    protected ImagePost() {
    }

    private ImagePost(String uploader) {
        this.uploader = uploader;
    }

    /**
     * 업로더를 설정하고, 도메인 객체를 생성해 리턴합니다.
     *
     * @param uploader 업로더 이름
     * @return 생성된 도메인 객체
     */
    public static ImagePost createImagePostFrom(String uploader) {
        return new ImagePost(uploader);
    }

    public String getUploader() {
        return uploader;
    }
}
