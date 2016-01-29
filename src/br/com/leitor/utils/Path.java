package br.com.leitor.utils;

import br.com.leitor.seguranca.Conf;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static String getRealPath() {
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (Exception ex) {
        }
        return path;
    }
}
