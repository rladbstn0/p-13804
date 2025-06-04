package com.back.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilTest {
    @Test
    @DisplayName("파일을 생성할 수 있다.")
    public void t1() {
        // given
        String filePath = "temp/test.txt";

        // when
        Util.file.touch(filePath);

        // then
        assertThat(
                Util.file.exists(filePath)
        ).isTrue();

        Util.file.delete(filePath);
    }

}
