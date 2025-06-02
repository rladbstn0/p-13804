package com.back;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        int lastId = 0;


        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "등록" -> {
                    System.out.print("명언 : ");
                    String content = scanner.nextLine();

                    System.out.print("작가 : ");
                    String author = scanner.nextLine();

                    int id = ++lastId;

                    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
                }
                case "종료" -> {
                    return;
                }
            }
        }
    }
}