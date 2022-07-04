package io.devshare.dto;

import io.devshare.domain.File;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileSaveRequest {

    @NotBlank
    private String fileUrl;

    public File toEntity() {
        return new File(toReadAble(this.fileUrl));
    }

    private String toReadAble(String fileUrl) {
        return Arrays.stream(fileUrl.split("\\?"))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
