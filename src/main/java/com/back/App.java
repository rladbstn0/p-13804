package com.back;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");



        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "등록" -> {
                    System.out.print("명언 : ");
                    String content = scanner.nextLine();

                    System.out.print("작가 : ");
                    String author = scanner.nextLine();

                    System.out.print("1번 명언이 등록되었습니다.");
                }
                case "종료" -> {
                    return;
                }
            }
        }
    }
}