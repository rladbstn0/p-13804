package com.back.global.app;

public class AppConfig {

    public static String mode = "dev";

    public void setDevMode(){
        mode = "dev";
    }

    public static void setTestMode(){
        mode = "test";
    }
    public static String getMode(){
        return mode;
    }
}
