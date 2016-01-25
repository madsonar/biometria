package br.com.leitor.seguranca;

import br.com.leitor.utils.Logs;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Conf {

    private String client;
    private Integer type;
    private Integer device;
    private String brand;
    private String model;
    private String ip;
    /**
     * Limpa base de dados
     */
    private Boolean local_db;
    /**
     * HABILITA SOMENTE SE INSERIR O DEDO
     */
    private Boolean placed;
    private String filial;
    private String app;
    private String key;
    private String user;
    private String password;
    private String method;
    private String action;
    private Integer port;
    private Boolean web_service;
    private Boolean ssl;

    public Conf() {
        this.client = "";
        this.type = 0;
        this.device = 0;
        this.brand = "";
        this.model = "";
        this.ip = "";
        this.ip = "";
        this.local_db = false;
        this.placed = false;
        this.filial = "";
        this.app = "";
        this.key = "";
        this.user = "";
        this.password = "";
        this.method = "";
        this.action = "";
        this.port = null;
        this.web_service = false;
        this.ssl = false;
    }

    public Conf(String client, Integer type, Integer device, String brand, String model, String ip, Boolean local_db, Boolean placed, String filial, String app, String key, String user, String password, String method, String action, Integer port, Boolean web_service, Boolean ssl) {
        this.client = client;
        this.type = type;
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.ip = ip;
        this.local_db = local_db;
        this.placed = placed;
        this.filial = filial;
        this.app = app;
        this.key = key;
        this.user = user;
        this.password = password;
        this.method = method;
        this.action = action;
        this.port = port;
        this.web_service = web_service;
        this.ssl = ssl;
    }

    public void loadJson() {
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException ex) {
        }
        Logs logs = new Logs();
        try {
            File file = new File(path + "\\lib\\conf.json");
            if (!file.exists()) {
                return;
            }
            String json = null;
            try {
                json = FileUtils.readFileToString(file);
            } catch (IOException ex) {
                Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jSONObject = new JSONObject(json);
            try {
                client = jSONObject.getString("client");
            } catch (Exception e) {
                logs.save("Conf Erro", "client: Configuração errada. Verique o arquivo de configuração (conf). " + e.getMessage());
            }
            try {
                type = jSONObject.getInt("type");
            } catch (Exception e) {
                logs.save("Conf Erro", "(Integer) type: Configuração errada. Verique o arquivo de configuração (conf). " + e.getMessage());
            }
            try {
                device = jSONObject.getInt("device");
            } catch (Exception e) {
                logs.save("Conf Erro", "(Integer) device: Configuração errada. Verique o arquivo de configuração (conf). " + e.getMessage());
            }
            try {
                brand = jSONObject.getString("brand");
            } catch (Exception e) {
                logs.save("Conf Erro", "(String) brand: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                model = jSONObject.getString("model");
            } catch (Exception e) {
                logs.save("Conf Erro", "(String) brand: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                ip = jSONObject.getString("ip");
            } catch (Exception e) {
                logs.save("Conf Erro", "(String) ip: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                local_db = jSONObject.getBoolean("local_db");
            } catch (Exception e) {
                if (placed == null) {
                    logs.save("Conf Erro", "(Boolean) clear_db: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
            }
            try {
                placed = jSONObject.getBoolean("placed");
            } catch (Exception e) {
                logs.save("Conf Erro", "(Boolean) placed: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            try {
                filial = jSONObject.getString("filial");
            } catch (Exception e) {
                logs.save("Conf Erro", "(Boolean) filial: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }

            try {
                web_service = jSONObject.getBoolean("web_service");
                if (web_service == null) {
                    return;
                }
            } catch (Exception e) {
                logs.save("Conf Erro", "(Boolean) web_service: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
            }
            if (web_service) {
                try {
                    app = jSONObject.getString("app");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) app: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    key = jSONObject.getString("key");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) key: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    user = jSONObject.getString("user");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) user: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    password = jSONObject.getString("password");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) password: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    action = jSONObject.getString("action");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) action: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    port = jSONObject.getInt("port");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(Integer) port: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    method = jSONObject.getString("method");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(String) method: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
                try {
                    ssl = jSONObject.getBoolean("ssl");
                } catch (Exception e) {
                    logs.save("Conf Erro", "(Boolean) ssl: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }

            }
        } catch (JSONException ex) {
            logs.save("Conf JSONException", ex.getMessage());

        }
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getPlaced() {
        return placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getWeb_service() {
        return web_service;
    }

    public void setWeb_service(Boolean web_service) {
        this.web_service = web_service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }

    public Boolean getLocal_db() {
        return local_db;
    }

    public void setLocal_db(Boolean local_db) {
        this.local_db = local_db;
    }

}
