package br.com.leitor.sistema.conf;

import br.com.leitor.utils.Logs;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device {

    private Boolean invisible;
    /**
     * Tela de boas vindas do leitor
     */
    private Boolean welcome;
    /**
     * Desabilida dedos
     */
    private List disabled_finger;

    public Device() {
        this.invisible = false;
        this.welcome = false;
        this.disabled_finger = new ArrayList<>();
        loadJson();
    }

    public Device(Boolean invisible) {
        this.invisible = invisible;
    }

    public final void loadJson() {
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException ex) {
        }
        Logs logs = new Logs();
        try {
            File file = new File(path + "\\lib\\conf\\device.json");
            if (!file.exists()) {
                return;
            }
            String json = null;
            try {
                json = FileUtils.readFileToString(file);
            } catch (IOException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jSONObject = new JSONObject(json);
            try {
                invisible = jSONObject.getBoolean("invisible");
            } catch (Exception e) {
                if (invisible == null) {
                    logs.save("Device Erro", "(Boolean) invisible: Configuração errada.  Verique o arquivo de configuração (device)." + e.getMessage());
                }
            }
            try {
                welcome = jSONObject.getBoolean("welcome");
            } catch (Exception e) {
                if (welcome == null) {
                    logs.save("Device Erro", "(Boolean) welcome: Configuração errada.  Verique o arquivo de configuração (device)." + e.getMessage());
                }
            }
            try {
                disabled_finger = new ArrayList<>();
                JSONArray jsonArray = jSONObject.getJSONArray("disabled_finger");
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        disabled_finger.add(jsonArray.get(i).toString());
                    }
                }
            } catch (Exception e) {
                if (disabled_finger == null) {
                    logs.save("Device Erro", "(List) disabled_finger: Configuração errada.  Verique o arquivo de configuração (device)." + e.getMessage());
                }
            }
        } catch (JSONException ex) {
            logs.save("Device JSONException", ex.getMessage());
        }
    }

    public Boolean getInvisible() {
        return invisible;
    }

    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    public Boolean getWelcome() {
        return welcome;
    }

    public void setWelcome(Boolean welcome) {
        this.welcome = welcome;
    }

    public List getDisabled_finger() {
        return disabled_finger;
    }

    public void setDisabled_finger(List disabled_finger) {
        this.disabled_finger = disabled_finger;
    }

}
