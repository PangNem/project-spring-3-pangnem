package io.devshare.errors;

public class NotSupportedImageExtension extends RuntimeException {
    public NotSupportedImageExtension(String fileName) {
        super("지원되지 않는 확장자 입니다: " + fileName);
    }
}
