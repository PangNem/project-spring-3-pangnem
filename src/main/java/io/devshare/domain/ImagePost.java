package io.devshare.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 이미지 포스트 도메인.
 */
@Entity
@Getter
public class ImagePost {

    @Id
    @GeneratedValue
    private Long id;
    private String uploader;
    private String url;

    public ImagePost() {
    }

    private ImagePost(String uploader) {
        this.uploader = uploader;
    }

    /**
     * 업로더가 설정된 도메인 객체를 생성해 리턴합니다.
     *
     * @param uploader 업로더 이름
     * @return 업로더 이름이 설정되어 생성된 도메인 객체
     */
    public static ImagePost create(String uploader) {
        return new ImagePost(uploader);
    }

    /**
     * 이미지를 업로드 합니다.
     *
     * @param url 업로드한 url
     */
    public void upload(String url) {
        this.url = url;
    }
}
