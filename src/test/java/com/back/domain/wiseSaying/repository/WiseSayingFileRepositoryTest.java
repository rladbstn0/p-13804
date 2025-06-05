package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class WiseSayingFileRepositoryTest {
    private final WiseSayingFileRepository wiseSayingFileRepository;

    public WiseSayingFileRepositoryTest() {
        this.wiseSayingFileRepository = AppContext.wiseSayingFileRepository;
    }

    @BeforeAll
    static void beforeAll() {
        AppContext.renew();
    }

    @BeforeEach
    void beforeEach() {
        Util.file.rmdir("db/wiseSaying");
    }

    @Test
    @DisplayName("저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundWiseSaying = wiseSayingFileRepository.findById(1);

        assertThat(
                foundWiseSaying
        ).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("2번째 등록에서는 2번 명언이 생성")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "이순신");
        wiseSayingFileRepository.save(wiseSaying2);


        assertThat(wiseSaying2.getId()).isEqualTo(2);
    }
}
