package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Pageable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        wiseSayingFileRepository.clear();
    }

    @Test
    @DisplayName("저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundWiseSaying = wiseSayingFileRepository.findById(1).get();

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

    @Test
    @DisplayName("명언 삭제")
    void t3() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "나폴레옹");
        wiseSayingFileRepository.save(wiseSaying2);

        wiseSayingFileRepository.delete(wiseSaying2);

        Optional<WiseSaying> opFoundWiseSaying = wiseSayingFileRepository.findById(2);

        assertThat(opFoundWiseSaying.isEmpty());
    }

    @Test
    @DisplayName("명언 다건조회")
    public void t4() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingFileRepository.save(wiseSaying2);

        assertThat(
                wiseSayingFileRepository.findForList(new Pageable(1, 5)).getContent()
        ).containsExactly(wiseSaying2, wiseSaying1);
    }
}
