package io.devshare.errors;

public class NotSupportedImageExtensionException extends RuntimeException {
    public NotSupportedImageExtensionException(String fileName) {
        super("지원되지 않는 확장자 입니다: " + fileName);
    }
}
