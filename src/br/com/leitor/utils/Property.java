package br.com.leitor.utils;

public class Property {

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }
    
    public static String getAppData() {
        return System.getenv("APPDATA");
    }
}
