package br.com.leitor.utils;

import br.com.leitor.sistema.conf.ConfWebService;
import br.com.leitor.webservice.classes.WSStatus;
import com.google.gson.Gson;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class WebService {

    private Boolean status;
    private WSStatus wSStatus;
    private Object o;

    public WebService() {
        status = false;
        wSStatus = null;
        o = null;
    }

    public String POST(String page, String action, String params) {
        return execute(page, "POST", action, params);
    }

    public String GET(String page, String action, String params) {
        return execute(page, "GET", action, params);
    }

    public String PUT(String page, String action, String params) {
        return execute(page, "PUT", action, params);
    }

    public String DELETE(String page, String action, String params) {
        return execute(page, "DELETE", action, params);
    }

    public String execute(String page, String method, String action, String params) {
        wSStatus = null;
        o = null;
        String mac = Mac.getInstance();
        ConfWebService cws = new ConfWebService();
        cws.loadJson();
        String urlString = "";
        if (cws.getSsl()) {
            urlString += "https://";
        } else {
            urlString += "http://";
        }
        Boolean errors = false;
        String string_errors = "";
        if (cws.getUrl().isEmpty()) {
            errors = true;
            string_errors += "url; ";
        }
        if (cws.getUser().isEmpty()) {
            string_errors += "user; ";
        }
        if (cws.getPassword().isEmpty()) {
            string_errors += "password; ";
        }
        if (cws.getClient().isEmpty()) {
            string_errors += "client; ";
        }
//        if (cws.getApp().isEmpty()) {
//            string_errors += "app; ";
//        }
//        if (cws.getKey().isEmpty()) {
//            string_errors += "key; ";
//        }
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
        if (cws.getPort() == null || cws.getPort() == 80 || cws.getPort() == 0) {
            urlString += cws.getUrl() + "/";
        } else {
            urlString += cws.getUrl() + ":" + cws.getPort() + "/";
        }
        if (!cws.getUser().isEmpty()) {
            urlParams.add("user=" + cws.getUser());
        }
        if (!cws.getPassword().isEmpty()) {
            urlParams.add("password=" + cws.getPassword());
        }
        if (!cws.getApp().isEmpty()) {
            urlParams.add("app=" + cws.getApp());
        }
        if (!cws.getKey().isEmpty()) {
            urlParams.add("key=" + cws.getKey());
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
        if (!cws.getClient().isEmpty()) {
            urlParams.add("client=" + cws.getClient());
        }
        if (!cws.getContext().isEmpty()) {
            urlString += cws.getContext() + "/ws/" + page;
        }
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
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            con.setUseCaches(true);
            con.setRequestMethod(method);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String str = in.readLine();
                Gson gson = new Gson();
                if (!status) {
                    wSStatus = gson.fromJson(str, WSStatus.class);
                }
                if (o != null) {
                    o = gson.fromJson(str, o.getClass());
                }
                return str;
            }
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    string_errors,
                    "Arquivo de configuração > conf",
                    JOptionPane.WARNING_MESSAGE);
            Logs logs = new Logs();
            logs.save("Exception", e.getMessage());
            System.exit(0);
        }
        status = false;
        return null;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean aStatus) {
        status = aStatus;
    }

    public WSStatus getwSStatus() {
        return wSStatus;
    }

    public void setwSStatus(WSStatus awSStatus) {
        wSStatus = awSStatus;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object aO) {
        o = aO;
    }
}
