package com.back;

import com.back.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("`== 명언 앱 ==` 출력")
    void t1() {
        Scanner scanner = TestUtil.genScanner("종료");

        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        new App(scanner).run();
        String rs = output.toString();

        assertThat(rs)
                .contains("== 명언 앱 ==");
    }
}