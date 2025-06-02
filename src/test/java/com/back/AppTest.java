package com.back;

import com.back.standard.util.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("`== 명언 앱 ==` 출력")
    void t1() {
        String rs = AppTestRunner.run("종료");
//        Scanner scanner = TestUtil.genScanner("종료");

//        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
//        new App(scanner).run();
//        String rs = output.toString();

        assertThat(rs)
                .contains("== 명언 앱 ==");
    }
}