package com.back.domain.system.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemControllerTest {
    @Test
    @DisplayName("종료")
    void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs)
                .contains("종료되었습니다.");
    }
}

