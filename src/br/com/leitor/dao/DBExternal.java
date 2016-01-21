package br.com.leitor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

public class DBExternal {

    private Statement statment;
    private String url = "sindical.rtools.com.br";
    private String port = "5432";
    private String database = "ComercioRP";
    private String user = "postgres";
    private String password = "r#@tools";

    public Connection getConnection() {
        try {
            //String url = "jdbc:postgresql://200.158.101.9:5432/Rtools";
            String uri = "jdbc:postgresql://" + this.url + ":" + port + "/" + database;
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            //props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection(uri, props);
            return conn;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Conexao", 3);
        }
        return null;
    }

    public Statement getStatment() throws SQLException {
        statment = getConnection().createStatement();
        return statment;
    }

    public void setStatment(Statement statment) {
        this.statment = statment;
    }

    public void closeStatment() throws SQLException {
        getConnection().close();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
}
