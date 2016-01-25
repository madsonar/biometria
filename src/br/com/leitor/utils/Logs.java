package br.com.leitor.utils;

import br.com.leitor.seguranca.Conf;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {

    private String current_date = null;
    private File file = null;
    private String path;
    private Conf conf;

    protected String create_file(String filename) {
        try {
            String logDir = getPath();
            if (!new File(logDir).exists()) {
                File f = new File(logDir);
                f.mkdir();
            }
            String day = (DataHoje.data()).substring(0, 2);
            String month = (DataHoje.data()).substring(3, 5);
            String year = (DataHoje.data()).substring(6, 10);
            current_date = year + "-" + month + "-" + day;
            String c1 = getPath() + current_date;
            File fileData = new File(c1);
            if (!fileData.exists()) {
                fileData.mkdir();
            }
            String c2 = getPath() + current_date + "/log_" + filename + "_" + current_date + ".txt";
            file = new File(c2);
            if (!file.exists()) {
                FileOutputStream fileData2 = new FileOutputStream(c2);
                fileData2.close();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String save(String location, String content) {
        conf = new Conf();
        conf.loadJson();
        try {
            location = location.toLowerCase();
            location = location.replace(" ", "_");
            location = location.replace(">", "_");
            create_file(location);
            if (file != null && file.exists()) {

                FileWriter writer = new FileWriter(file, true);
                try (BufferedWriter buffWriter = new BufferedWriter(writer)) {
                    buffWriter.write(content);
                    buffWriter.newLine();
                    buffWriter.flush();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    protected File getFile() {
        return file;
    }

    protected void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        if (path == null) {
            path = Path.getUserPath() + "/Rtools/" + conf.getBrand() + "/" + conf.getModel() + "/logs/";
            try {
                File fp = new File(path);
                if (!fp.exists()) {
                    fp.mkdirs();
                }
            } catch (Exception ex) {
                Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
