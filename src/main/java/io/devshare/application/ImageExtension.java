package io.devshare.application;

import io.devshare.errors.NotSupportedImageExtensionException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 이미지 확장자.
 */
public enum ImageExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    GIF("gif"),
    PNG("png");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    private static final Map<String, ImageExtension> stringToEnum = Arrays.stream(ImageExtension.values())
            .collect(Collectors.toMap(it -> it.extension, it -> it));

    /**
     * 파일 이름이 올바르지 않은 이미지 확장자일 경우 에러를 던집니다.
     *
     * @param fileName 파일 이름
     * @throws NotSupportedImageExtensionException 파일 이름이 올바르지 않은 이미지 확장자일 경우
     */
    public static void validate(String fileName) {
        String extension = getExtension(fileName)
                .orElseThrow(IllegalArgumentException::new);

        if (!validExtension(extension)) {
            throw new NotSupportedImageExtensionException(fileName);
        }
    }

    private static boolean validExtension(String extension) {
        String key = extension.toLowerCase();
        ImageExtension imageExtension = stringToEnum.get(key);

        return imageExtension != null;
    }

    private static Optional<String> getExtension(String fileName) {
        String[] dotSplit = fileName.split("\\.");

        return Stream.of(dotSplit)
                .skip(dotSplit.length - 1)
                .findFirst();
    }

    public static boolean isImage(String fileName) {
        getExtension(fileName)
                .orElseThrow(IllegalArgumentException::new);

        return Optional.ofNullable(stringToEnum.get(fileName))
                .isPresent();
    }
}
