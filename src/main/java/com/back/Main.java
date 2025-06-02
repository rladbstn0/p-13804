package com.back;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //lab1();
        lab2();
    }

    public static void lab1() {
        String input = """
                등록
                나의 죽음을 적들에게 알리지 말라
                """;

        Scanner sc = new Scanner(input); //input을 입력

        String cmd = sc.nextLine();
        String content = sc.nextLine();

        System.out.println("cmd : " + cmd);
        System.out.println("content : " + content);
    }

    public static void lab2() {
        System.out.println("안녕하세요.");

        // 기존의 output 스트림 저장
        PrintStream ORIGNAL_OUT = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream, true);
        System.setOut(printStream);

        System.out.println("반갑습니다.");
        String str = byteArrayOutputStream.toString();

        // output 스트림 복원
        System.setOut(ORIGNAL_OUT);
        System.out.println("str : " + str);

    }
}
