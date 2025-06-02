package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    @DisplayName("`== 명언 앱 ==` 출력")
    void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs)
                .contains("== 명언 앱 ==")
                .contains("명령) ");

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
                .contains("명언 : ")
                .contains("작가 : ");
    }

    @Test
    @DisplayName("등록할 때 명언 번호 추가")
    void t3() {
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

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록할 때 마다 생성되는 명언 번호가 증가")
    void t4() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t5() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }
}