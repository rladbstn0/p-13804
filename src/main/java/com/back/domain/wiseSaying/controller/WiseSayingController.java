package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.global.rq.Rq;
import com.back.standard.dto.Pageable;

import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService = AppContext.wiseSayingService;
    private final Scanner scanner = AppContext.scanner;

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = AppContext.scanner.nextLine();

        System.out.print("작가 : ");
        String author = AppContext.scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    public void actionList(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        int pageSize = rq.getParamsInt("pageSize", 5);
        int pageNo = rq.getParamsInt("page", 1);

        Pageable pageable = new Pageable(pageNo, pageSize);

        String keywordType = rq.getParam("keywordType", "all");
        String keyword =  rq.getParam("keyword", "");

        for (WiseSaying wiseSaying : wiseSayingService.findForList(keywordType, keyword, pageable)) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamsInt("id", -1);

        if(id == -1){
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        boolean deleted = wiseSayingService.delete(id);

        if(!deleted){
            System.out.printf("%d번 명언은 존재하지 않습니다.", id);
            return;
        }


        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void actionModify(Rq rq) {


        int id = rq.getParamsInt("id", -1);

        if(id == -1){
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);
        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.printf("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.printf("작가 : ");
        String author = scanner.nextLine();

        wiseSayingService.modify(wiseSaying, content, author);
    }
}
