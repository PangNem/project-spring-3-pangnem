package io.devshare.errors;

/**
 * 지원하지 않은 이미지 확장자일 경우 던집니다.
 */
public class NotSupportedImageExtensionException extends RuntimeException {
    public NotSupportedImageExtensionException(String fileName) {
        super("지원되지 않는 확장자 입니다: " + fileName);
    }
}
