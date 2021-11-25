package io.devshare.application;

import io.devshare.errors.NotSupportedImageExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageExtensionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a.jpg", "b.jpeg", "c.png", "g.gif"})
    void validExtension_withValidImageExtension(String fileName) {
        assertThatCode(() -> ImageExtension.validate(fileName))
                .doesNotThrowAnyException();

        assertThatCode(() -> ImageExtension.validate(fileName.toUpperCase()))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a.1.txt", "b.java", "c.html"})
    void validExtension_withInValidImageExtension(String fileName) {
        assertThatThrownBy(() -> ImageExtension.validate(fileName))
                .isInstanceOf(NotSupportedImageExtension.class);
    }
}
