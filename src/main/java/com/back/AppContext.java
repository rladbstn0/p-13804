package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository;
import com.back.domain.wiseSaying.repository.WiseSayingMemoryRepository;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static SystemController systemController;
    public static WiseSayingFileRepository wiseSayingFileRepository;
    public static WiseSayingMemoryRepository wiseSayingMemoryRepository;
    public static WiseSayingRepository wiseSayingRepository;
    public static WiseSayingService wiseSayingService;
    public static WiseSayingController wiseSayingController;

    public static void renew(Scanner _scanner) {
        scanner = _scanner;
        systemController = new SystemController();
        wiseSayingFileRepository = new WiseSayingFileRepository();
        wiseSayingMemoryRepository = new WiseSayingMemoryRepository();
        wiseSayingRepository = wiseSayingFileRepository;
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController();
    }

    public static void renew() {
        renew(new Scanner(System.in));
    }
}