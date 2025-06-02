package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        lab1();
    }

    public static void lab1() {
        String input = """
                등록
                나의 죽음을 적들에게 알리지 말라
                """;

        Scanner sc = new Scanner(input);

        String cmd = sc.nextLine();
        String content = sc.nextLine();

        System.out.println("cmd : " + cmd);
        System.out.println("content : " + content);
    }
}
