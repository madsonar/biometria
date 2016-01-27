package br.com.leitor.main;

import br.com.leitor.dao.Dao;
import br.com.leitor.pessoa.BiometriaServidor;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.seguranca.Conf;
import br.com.leitor.seguranca.MacFilial;
import br.com.leitor.seguranca.dao.MacFilialDao;
import br.com.leitor.usuario.Usuario;
import br.com.leitor.usuario.dao.UsuarioDao;
import br.com.leitor.utils.Block;
import br.com.leitor.utils.BlockInterface;
import br.com.leitor.utils.Logs;
import br.com.leitor.utils.Mac;
import br.com.leitor.utils.Ping;
import br.com.leitor.utils.Session;
import br.com.leitor.utils.WebService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Index extends JFrame implements ActionListener {

    private JButton btnAcessar;
    private JLabel lblLogin;
    private JTextField txtLogin;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private GridLayout grid;

    public static void main(String args[]) {
        Conf conf = new Conf();
        conf.loadJson();
        Block.TYPE = "" + conf.getType();
        Ping.execute();
        if (!Block.registerInstance()) {
            // instance already running.
            System.out.println("Another instance of this application is already running.  Exiting.");
            JOptionPane.showMessageDialog(null, "Aplicação já esta em execução");
            System.exit(0);
        }
        Block.setBlockInterface(new BlockInterface() {
            @Override
            public void newInstanceCreated() {
                System.out.println("New instance detected...");
                // this is where your handler code goes...
            }
        });
        new Index().setVisible(false);
    }

    public Index() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
                System.exit(0);
            }
        });
        Conf conf = new Conf();
        conf.loadJson();
        BiometriaDao biometriaDao = new BiometriaDao();
        if (conf.getType().equals(2)) {
            String mac = Mac.getInstance();
            if (conf.getWeb_service()) {
                Object result = WebService.GET("web_service.jsf", "", "");
                // http://localhost:8080/Sindical/web_service.jsf?client=Sindical&user=teste&password=123456&app=teste&key=123456&&method=GET&action=biometria
            } else {
                MacFilialDao macFilialDao = new MacFilialDao();
                MacFilial macFilial = macFilialDao.findMacFilial(mac);
                if (macFilial == null) {
                    JOptionPane.showMessageDialog(null,
                            "Computador não registrado!",
                            "Validação",
                            JOptionPane.WARNING_MESSAGE);
                    Logs logs = new Logs();
                    logs.save("index", "Computador não registrado!");
                    System.exit(0);
                    return;
                }
                List<BiometriaServidor> list = biometriaDao.pesquisaStatusPorComputador(macFilial.getId());
                Dao dao = new Dao();
                if (!list.isEmpty()) {
                    BiometriaServidor biometriaServidor = list.get(0);
                    if (!biometriaServidor.getAtivo()) {
                        biometriaServidor.setDataAtivo(new Date());
                        biometriaServidor.setAtivo(true);
                        dao.update(biometriaServidor, true);
                    }
                } else {
                    BiometriaServidor biometriaServidor = new BiometriaServidor();
                    biometriaServidor.setDataAtivo(new Date());
                    biometriaServidor.setAtivo(true);
                    biometriaServidor.setMacFilial(macFilial);
                    dao.save(biometriaServidor, true);
                }
            }
        } else {
            new BiometriaDao().reload(conf.getDevice());
        }
        Close.clear();
        // PreloaderDialog pd = new PreloaderDialog();
        // pd.show("Iniciando");
        // pd.hide();
        new Menu().setVisible(false);
    }

    public void close() {
        super.dispose();
    }

    public void init() {

    }

    public void destroy() {

    }

    @Override
    public void dispose() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        grid = new GridLayout(0, 2);
        lblLogin = new JLabel("Usuário:");
        lblLogin.setBounds(10, 20, 200, 25);
        txtLogin = new JTextField();
        txtLogin.setBounds(80, 20, 200, 25);
        lblPassword = new JLabel("Senha:");
        lblPassword.setBounds(10, 50, 200, 25);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(80, 50, 200, 25);
        btnAcessar = new JButton("Validar");
        btnAcessar.setBounds(80, 90, 200, 25);
        btnAcessar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent jc = (JComponent) e.getSource();
        if (jc == btnAcessar) {
            if (txtLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Informar login!",
                        "Validação",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Informar senha!",
                        "Validação",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.findUsuario(txtLogin.getText(), txtPassword.getText());
            if (usuario == null) {
                JOptionPane.showMessageDialog(null,
                        "Usuário inválido!",
                        "Validação",
                        JOptionPane.INFORMATION_MESSAGE);

                return;
            }
            super.dispose();
            Session session = new Session();
            session.setUsuario(usuario);
            new Menu().setVisible(true);
        }
    }

}
