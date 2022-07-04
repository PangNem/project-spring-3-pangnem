package io.devshare.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreSignedUrl {
    private static final String PARAMS_DELIMITER = "\\?";

    @NotBlank
    private String preSignedUrl;

    public PreSignedUrl(String preSignedUrl) {
        this.preSignedUrl = preSignedUrl;
    }

    public String toReadableUrl() {
        return Arrays.stream(this.preSignedUrl.split(PARAMS_DELIMITER))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
