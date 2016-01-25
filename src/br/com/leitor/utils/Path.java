package br.com.leitor.utils;

public class Path {

    public static String getUserPath() {
        String path = "";
        String os_name = Property.getOSName();
        String app_data = System.getenv("APPDATA");
        if (os_name.toLowerCase().contains("windows")) {
            return app_data;
        } else if (os_name.toLowerCase().contains("linux")) {
        } else if (os_name.toLowerCase().contains("centos")) {
        }
        try {
        } catch (Exception ex) {
            return null;
        }
        return path;
    }
}
