package br.com.leitor.sistema.conf;

import br.com.leitor.utils.Logs;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Device {

    private Boolean invisible;

    public Device() {
        this.invisible = false;
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
            File file = new File(path + "\\lib\\device.json");
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
                    logs.save("DataBase Erro", "(Boolean) invisible: Configuração errada.  Verique o arquivo de configuração (conf)." + e.getMessage());
                }
            }
        } catch (JSONException ex) {
            logs.save("Conf JSONException", ex.getMessage());
        }
    }

    public Boolean getInvisible() {
        return invisible;
    }

    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

}
