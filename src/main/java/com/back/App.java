package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        WiseSayingController wiseSayingController = new WiseSayingController(scanner);
        SystemController systemController = new SystemController();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();


            switch (cmd) {
                case "등록" -> {
                    wiseSayingController.actionWrite();

                }
                case "목록" -> {
                    wiseSayingController.actionList();

                }
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}