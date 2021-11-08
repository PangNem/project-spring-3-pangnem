package io.devshare.infra;

import com.github.jknack.handlebars.Options;
import org.springframework.stereotype.Component;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;

/**
 * Handlebars Build-in 헬퍼 외의 사용자 지정 헬퍼
 */
@Component
@HandlebarsHelper
public class CustomHelper {

    /**
     * 정수형 i가 3으로 나누어 떨어지면 fn(참)을 리턴, 아닐 경우 inverse(거짓)를 리턴합니다.
     *
     * @param i       정수형
     * @param options Handlebars 옵션
     * @return 정수형 i가 3으로 나누어 떨어지면 fn(참)을 리턴, 아닐 경우 inverse(거짓)를 리턴
     * @throws IOException
     */
    public CharSequence isMultipleOfThree(int i, Options options) throws IOException {
        if (i % 3 == 0) {
            return options.fn(i);
        }

        return options.inverse(i);
    }

    /**
     * 정수형 i가 1을 더한 값이 3으로 나누어 떨어지면 fn(참)을 리턴, 아닐 경우 inverse(거짓)를 리턴합니다.
     *
     * @param i       정수형
     * @param options Handlebars 옵션
     * @return 정수형 i가 1을 더한 값이 3으로 나누어 떨어지면 fn(참)을 리턴, 아닐 경우 inverse(거짓)를 리턴
     * @throws IOException
     */
    public CharSequence isPlusOneMultipleOfThree(int i, Options options) throws IOException {
        if ((i + 1) % 3 == 0) {
            return options.fn(i);
        }

        return options.inverse(i);
    }
}
