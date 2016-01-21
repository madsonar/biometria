package br.com.leitor.sistema.conf;

import br.com.leitor.utils.Logs;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class DataBase {

    private String host;
    private String database;
    private String password;

    public DataBase() {
        this.host = "";
        this.database = "";
        this.password = "";
    }

    public DataBase(String host, String database, String password) {
        this.host = host;
        this.database = database;
        this.password = password;
    }

    public void loadJson() {
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException ex) {
        }
        Logs logs = new Logs();
        try {
            File file = new File(path + "\\lib\\database.json");
            if (!file.exists()) {
                return;
            }
            String json = null;
            try {
                json = FileUtils.readFileToString(file);
            } catch (IOException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jSONObject = new JSONObject(json);
            try {
                host = jSONObject.getString("host");
            } catch (Exception e) {
                logs.save("DataBase Erro", "(String) host: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                database = jSONObject.getString("database");
            } catch (Exception e) {
                logs.save("DataBase Erro", "(String) database: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                password = jSONObject.getString("password");
            } catch (Exception e) {
                logs.save("DataBase Erro", "(String) password: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
        } catch (JSONException ex) {
            logs.save("Conf JSONException", ex.getMessage());
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
