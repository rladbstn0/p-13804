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
    @Test
    @DisplayName("등록")
    void t2() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(rs)
                .contains("명령) ")
                .contains("명언 : ")
                .contains("작가 : ");
    }
}