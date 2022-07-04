package io.devshare.domain;

import io.devshare.dto.FileResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    public File(String url) {
        this.url = url;
    }

    public FileResponse toFileResponse() {
        return new FileResponse(this.url);
    }
}
