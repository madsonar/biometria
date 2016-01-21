package br.com.leitor.utils;

import br.com.leitor.seguranca.Conf;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

// http://localhost:8080/Sindical/web_service.jsf?client=Sindical&user=teste&password=123456&app=teste&key=123456&&method=GET&action=biometria
public class WebService {

    public static Object POST(String page, String action, String params) {
        return execute(page, "POST", action, params);
    }

    public static Object GET(String page, String action, String params) {
        return execute(page, "GET", action, params);
    }

    public static Object PUT(String page, String action, String params) {
        return execute(page, "PUT", action, params);
    }

    public static Object DELETE(String page, String action, String params) {
        return execute(page, "DELETE", action, params);
    }

    public static Object execute(String page, String method, String action, String params) {
        String mac = Mac.getInstance();
        Conf conf = new Conf();
        conf.loadJson();
        String urlString = "";
        if (conf.getSsl()) {
            urlString += "https://";
        } else {
            urlString += "http://";
        }
        Boolean errors = false;
        String string_errors = "";
        if (conf.getIp().isEmpty()) {
            errors = true;
            string_errors += "ip; ";
        }
        if (conf.getUser().isEmpty()) {
            string_errors += "user; ";
        }
        if (conf.getPassword().isEmpty()) {
            string_errors += "password; ";
        }
        if (conf.getClient().isEmpty()) {
            string_errors += "client; ";
        }
        if (conf.getApp().isEmpty()) {
            string_errors += "app; ";
        }
        if (conf.getKey().isEmpty()) {
            string_errors += "key; ";
        }
        if (params.isEmpty()) {
            string_errors += "page not found; ";
        }
        if (page.isEmpty()) {
            string_errors += "parans is not null; ";
        }
        if (errors) {
            JOptionPane.showMessageDialog(null,
                    string_errors,
                    "Arquivo de configuração > conf",
                    JOptionPane.WARNING_MESSAGE);
            Logs logs = new Logs();
            logs.save("Sistema", "Arquivo de configuração > conf. Erros: " + string_errors);
            System.exit(0);
        }
        List urlParams = new ArrayList<>();
        if (conf.getPort() == null || conf.getPort() == 80 || conf.getPort() == 0) {
            urlString += conf.getIp() + "/";
        } else {
            urlString += conf.getIp() + ":" + conf.getPort() + "/";
        }
        if (!conf.getUser().isEmpty()) {
            urlParams.add("user=" + conf.getUser());
        }
        if (!conf.getUser().isEmpty()) {
            urlParams.add("user=" + conf.getUser());
        }
        if (!conf.getPassword().isEmpty()) {
            urlParams.add("password=" + conf.getPassword());
        }
        if (!conf.getApp().isEmpty()) {
            urlParams.add("app=" + conf.getApp());
        }
        if (!conf.getKey().isEmpty()) {
            urlParams.add("key=" + conf.getKey());
        }
        if (!params.isEmpty()) {
            urlParams.add(params);
        }
        if (!method.isEmpty()) {
            urlParams.add("method=" + method);
        }
        if (!mac.isEmpty()) {
            urlParams.add("mac=" + mac);
        }
        if (!conf.getClient().isEmpty()) {
            urlParams.add("client=" + conf.getClient());
        }
        urlString += "Sindical" + "/api/" + page;
        for (int i = 0; i < urlParams.size(); i++) {
            if (i == 0) {
                urlString += "?" + urlParams.get(i).toString();
            } else {
                urlString += "&" + urlParams.get(i).toString();
            }
        }
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String str = in.readLine();
                JSONObject obj = new JSONObject(str);
                Integer status_code = obj.getInt("status_code");
                String status_details = obj.getString("status_details");
                if (status_code != 0) {
                    JOptionPane.showMessageDialog(null,
                            status_details,
                            "Sistema",
                            JOptionPane.WARNING_MESSAGE);
                    Logs logs = new Logs();
                    logs.save("web_service", status_details);
                    System.exit(0);
                }
                return obj;
            }
        } catch (IOException | JSONException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    string_errors,
                    "Arquivo de configuração > conf",
                    JOptionPane.WARNING_MESSAGE);
            Logs logs = new Logs();
            logs.save("Exception", e.getMessage());
            System.exit(0);
        }
        return null;
    }

}
