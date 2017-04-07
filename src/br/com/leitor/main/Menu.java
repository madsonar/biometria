package br.com.leitor.main;

import br.com.leitor.dao.Dao;
import br.com.leitor.pessoa.Biometria;
import br.com.leitor.pessoa.BiometriaAtualizaCatraca;
import br.com.leitor.pessoa.BiometriaCaptura;
import br.com.leitor.pessoa.BiometriaCatraca;
import br.com.leitor.pessoa.Pessoa;
import br.com.leitor.pessoa.dao.BiometriaAtualizaCatracaDao;
import br.com.leitor.pessoa.dao.BiometriaCatracaDao;
import br.com.leitor.pessoa.dao.BiometriaDao;
import br.com.leitor.seguranca.Conf;
import br.com.leitor.seguranca.MacFilial;
import br.com.leitor.utils.DataHoje;
import br.com.leitor.utils.Logs;
import br.com.leitor.utils.Nitgen;
import br.com.leitor.utils.Path;
import br.com.leitor.utils.Preloader;
import br.com.leitor.webservice.classes.WSBiometria;
import br.com.leitor.webservice.classes.WSBiometriaCaptura;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import rtools.WebService;

// MANTER AS CONFIGURAÇÕES DO JAVA JDK PARA 32 BITS
public class Menu extends JFrame implements ActionListener {

    public interface simpleDLL extends Library {

        simpleDLL INSTANCE = (simpleDLL) Native.loadLibrary(
                (Platform.isWindows() ? "simpleDLL" : "simpleDLLLinuxPort"), simpleDLL.class);

        int giveIntGetInt(int a);               // int giveIntGetInt(int a);
    }

    private Integer tipo;
    public JMenuBar barra;
    public JMenu biometriaMenu, sobre;
    public JMenuItem cadastrar, sair, ocultar, atualizar;
    private static int i = 0;
    private static int stop = 0;
    private Biometria biometria;
    private List<Biometria> listBiometria;
    private Boolean read;
    private Nitgen nitgen;
    public Thread thread;
    public Conf conf;
    public String ip;
    public String title;
    private Timer timer;
    private PopupMenu popupMenu;
    private MenuItem aboutItem, exitItem, restart, reiniciarDB, folderLogs;
    private Boolean started;
    private Boolean reload = true;
    private Boolean reloadListBiometria = false;
    private String startedDate = DataHoje.data();
    private Boolean actionInstance;
    public Preloader preloader;
    private ServerSocket serverSocket;

    static {
        try {
            // -Djava.library.path="D:\MEUS PROJETOS\Leitor\dll\"  -Xshare:auto
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        new Menu().setVisible(true);
    }

    public Menu() {
        super("Exemplo de Menus com submenus");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    tipo = 0;
                    nitgen.close();
                    poll = null;
                    Close.clear();
                    Close.confirm();
                    close();
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        started = false;
        preloader = new Preloader();
        initComponents();
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
        conf = new Conf();
        conf.loadJson();
        preloader.setAppTitle("Dispostivo - " + conf.getBrand() + " - " + conf.getModel());
        preloader.setAppStatus("Verificando dispostivo...");
        preloader.setShowIcon(true);
        preloader.setWaitingStarted(true);
        preloader.show();
        preloader.hide();
        actionInstance = false;
        tipo = conf.getType();
        // tipo = "gravar";
        listBiometria = new ArrayList();
        try {
            nitgen = new Nitgen();
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tipo == 1) {
            if (!nitgen.getExistsDB()) {
                preloader.reloadStatus("Carregando base de dados...");
            } else {
                preloader.reloadStatus("Atualizando base de dados...");
            }
            listBiometria = getListBiometria();
        }
        preloader.hide();
        if (!started) {
            Container tela = getContentPane();
            tela.setLayout(null);

            barra = new JMenuBar();
            setJMenuBar(barra);
            biometriaMenu = new JMenu("Biometria");
            sobre = new JMenu("Sobre");
            //cadastrar = new JMenuItem("Cadastrar");
            //cadastrar.setActionCommand("Cadastrar");
            sair = new JMenuItem("Sair");
            ocultar = new JMenuItem("");
            ocultar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/arrow_right_down_16x16.png")).getImage()));
            ocultar.setToolTipText("Esconder na bandeja do sistema");
            atualizar = new JMenuItem("Atualizar");
            sair.addActionListener(this);
            ocultar.addActionListener(this);
            atualizar.addActionListener(this);
            ocultar.setMnemonic(KeyEvent.VK_L);
            //ler.addActionListener(this);
            //cadastrar.addActionListener(this);
            //cadastrar.setMnemonic(KeyEvent.VK_L);
            //ler.setMnemonic(KeyEvent.VK_L);
            sobre.add("Leitor Biométrico - Hamster DX - Nitgen");
            sobre.add("Versão: 1.0");
            sobre.add("Rtools Comércio de Sistemas Ltda");
            barra.add(biometriaMenu);
            barra.add(sair);
            barra.add(sobre);
            barra.add(ocultar);
            biometriaMenu.addSeparator();
            SystemTray tray = SystemTray.getSystemTray();
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    about();
                }
            };
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
            if (tipo == 1) {
                title = "Ler";
            } else if (tipo == 2) {
                title = "Gravar";

            }
            popupMenu = new PopupMenu();
            // Create a pop-up menu components
            aboutItem = new MenuItem("Sobre");
            aboutItem.addActionListener(this);
            CheckboxMenuItem cb1 = new CheckboxMenuItem("Atualizar");
            cb1.setEnabled(false);
            restart = new MenuItem("Reiniciar");
            restart.addActionListener(this);
            if (tipo == 1) {
                reiniciarDB = new MenuItem("Reiniciar DB");
                reiniciarDB.addActionListener(this);
            }
            folderLogs = new MenuItem("Logs");
            folderLogs.addActionListener(this);
            exitItem = new MenuItem("Sair");
            exitItem.addActionListener(this);

            //Add components to pop-up menu
            popupMenu.add(aboutItem);
            popupMenu.addSeparator();
            popupMenu.add(cb1);
            popupMenu.addSeparator();
            popupMenu.add(restart);
            popupMenu.addSeparator();
            if (tipo == 1) {
                popupMenu.add(reiniciarDB);
                popupMenu.addSeparator();
            }
            popupMenu.add(folderLogs);
            popupMenu.addSeparator();
            popupMenu.add(exitItem);
            popupMenu.addSeparator();
            TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/resources/images/finger_16x16.png")).getImage(), "Leitor Biométrico - " + title);
            trayIcon.addActionListener(actionListener);
            trayIcon.setPopupMenu(popupMenu);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println("TrayIcon could not be added.");
            }
            started = true;
        }
        InetAddress ia = null;
        try {
            if (conf.getIp().isEmpty()) {
                ia = InetAddress.getLocalHost();
                ip = ia.getHostAddress();
            } else {
                ip = conf.getIp();
            }
        } catch (UnknownHostException e) {
        }
        if (tipo == 1) {
            try {
                nitgen.loadHardware();
                if (!nitgen.getHardware()) {
                    non_device();
                } else if (nitgen.getDevice_start() == 0) {
                    non_device();
                }
                timer = new Timer(1000, new clockListener());
                timer.start();
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            thread = null;
            thread = new Thread(poll);
            thread.start();
        }
    }

    class clockListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!WebService.existConnection()) {
                Preloader p = new Preloader();
                p.setAppTitle("Servidor offline, aguarde");
                p.setAppStatus("Servidor offline, aguarde");
                p.setWaitingStarted(false);
                p.setMinModal(true);
                p.show();
                for (int x = 0; x < 1; x++) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (WebService.existConnection()) {
                        p.hide();
                        break;
                    }
                    x--;
                }
            }
            WebService webService = new WebService();
            if (actionInstance) {
                return;
            }
            timer.setDelay(0);
            BiometriaAtualizaCatraca bac = null;
            if (conf.getWeb_service()) {
                try {
                    webService.param("device_number", conf.getDevice());
                    webService.PUT("biometria_atualiza_catraca");
                    webService.execute();
                    bac = (BiometriaAtualizaCatraca) webService.object(new BiometriaAtualizaCatraca());
                } catch (Exception ex) {

                }
            } else {
                bac = new BiometriaAtualizaCatracaDao().refresh(conf.getDevice());
                bac = (BiometriaAtualizaCatraca) new Dao().rebind(bac);
            }
            if (bac != null) {
                if (null != conf.getDevice()) {
                    switch (conf.getDevice()) {
                        case 1:
                            reload = true;
                            listBiometria.clear();
                            bac.setAparelho1(false);
                            break;
                        case 2:
                            reload = true;
                            listBiometria.clear();
                            bac.setAparelho2(false);
                            break;
                        case 3:
                            reload = true;
                            listBiometria.clear();
                            bac.setAparelho3(false);
                            break;
                        case 4:
                            reload = true;
                            listBiometria.clear();
                            bac.setAparelho4(false);
                            break;
                        default:
                            break;
                    }
                }
                if (conf.getWeb_service()) {
                    try {
                        webService.paramObject("biometria_atualiza_catraca", bac);
                        webService.action("update");
                        webService.PUT("biometria_atualiza_catraca");
                        webService.execute();
                    } catch (Exception ex) {

                    }
                } else {
                    new Dao().update(bac, true);
                }
            }
            if (nitgen.getHardware()) {
                if (reload) {
                    reload = false;
                    nitgen.setLoad(true);
                    List<Biometria> list = getListBiometria();
                    if (conf.getWeb_service()) {
                        webService.param("device_number", conf.getDevice());
                    }
                    String codigo_biometria = "";
                    int x = 0;
                    for (int i = 0; i < list.size(); i++) {
                        Boolean next = false;
                        if (null != conf.getDevice()) {
                            switch (conf.getDevice()) {
                                case 1:
                                    if (!next) {
                                        next = true;
                                        list.get(i).setDataAtualizacaoAparelho1(null);
                                    }
                                case 2:
                                    if (!next) {
                                        next = true;
                                        list.get(i).setDataAtualizacaoAparelho2(null);
                                    }
                                case 3:
                                    if (!next) {
                                        next = true;
                                        list.get(i).setDataAtualizacaoAparelho3(null);
                                    }
                                case 4:
                                    if (!next) {
                                        next = true;
                                        list.get(i).setDataAtualizacaoAparelho4(null);
                                    }
                            }
                        }
                        if (conf.getWeb_service()) {
                            if (next) {
                                if (x == 0) {
                                    codigo_biometria = "";
                                    codigo_biometria += "" + list.get(i).getId();
                                } else {
                                    codigo_biometria += "," + list.get(i).getId();
                                }
                                x++;
                            }
                        } else {
                            new Dao().update(list.get(i), true);
                        }
                    }
                    if (conf.getWeb_service()) {
                        if (!codigo_biometria.isEmpty()) {
                            codigo_biometria += "";
                        }
                        webService.param("codigo_biometria", codigo_biometria);
                        webService.GET("biometria_atualizar.jsf", "update_aparelho");
                        try {
                            webService.execute();
                        } catch (Exception ex) {

                        }
                    }
                    Preloader p = new Preloader();
                    p.setAppTitle("Dispostivo - " + conf.getBrand() + " - " + conf.getModel());
                    p.setWaitingStarted(false);
                    if (reloadListBiometria) {
                        p.setAppTitle("Recarregando base, isto pode levar alguns minutos...aguarde.");
                        p.setAppStatus("Recarregando base, isto pode levar alguns minutos...aguarde.");
                    } else {
                        p.setAppTitle("Carregando base, isto pode levar alguns minutos...aguarde.");
                        p.setAppStatus("Carregando base, isto pode levar alguns minutos...aguarde.");
                    }
                    p.show();
                    // add(p.getFrame());
                    nitgen.loadBiometria(reloadListBiometria, list);
                    // remove(p.getFrame());
                    p.hide();
                    if (!reloadListBiometria) {
                        reloadListBiometria = true;
                    }
                    nitgen.setLoad(false);
                }
                if (nitgen.getOpen()) {
                    Map<Integer, Integer> result = nitgen.comparer();
                    if (result == null) {
                        return;
                    }
                    Integer status = 0;
                    Integer id = 0;
                    int j = 0;
                    for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                        status = entry.getKey();
                        id = entry.getValue();
                    }
                    if (conf.getWeb_service()) {
                        webService.param("biometria_ip", ip);
                        if (id != null) {
                            webService.param("codigo_pessoa", id);
                        }
                        webService.param("biometria_status", status);
                        webService.action("update_catraca");
                        webService.GET("biometria_atualiza_catraca");
                        try {
                            webService.execute();
                            if (conf.getCatraca_server().isEmpty()) {
                                if (getCatraca(id)) {
//                                    if (status == 1) {
//                                        Logs logs = new Logs();
//                                        logs.save("menu", "Biometria Catraca - Não encontrada!");
//                                        return;
//                                    }
                                } else {
                                    Logs logs = new Logs();
                                    logs.save("erro", "Erro na hora de atualizar a catraca!");
                                    return;
                                }
                            }
                        } catch (Exception ex) {

                        }

                    } else {
                        BiometriaCatraca bc = new BiometriaCatraca();
                        BiometriaCatracaDao bcd = new BiometriaCatracaDao();
                        if (status == 1) {
                            bcd.destroy(ip);
                            System.err.println("Biometria não encontrada!");
                            Logs logs = new Logs();
                            logs.save("menu", "Biometria Catraca - Não encontrada!");
                            bc.setIp(ip);
                            bc.setPessoa(null);
                            new Dao().save(bc, true);
                            return;
                        }
                        bc.setIp(ip);
                        if (id == null) {
                            bc.setPessoa(null);
                        } else {
                            List list = bcd.findByPessoa(id);
                            for (int i = 0; i < list.size(); i++) {
                                new Dao().delete(list.get(i), true);
                            }
                            bc.setPessoa((Pessoa) new Dao().find(new Pessoa(), id));
                        }
                        new Dao().save(bc, true);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        Logs logs = new Logs();
                        logs.save("Erro", ex.getMessage());
                    }
                }
            }
        }
    }

    public void non_device() {
        Logs logs = new Logs();
        logs.save("menu", "Nenhum dispostivo encontrado");
        try {
            nitgen.close();
        } catch (Exception ex) {
            logs.save("Erro", ex.getMessage());
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        tipo = 0;
        poll = null;
        Close.clear();
        Close.confirm();
        close();
        System.exit(0);
    }

    public void exit() {
        tipo = 0;
        try {
            nitgen.close();
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        poll = null;
        Object[] options = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(null,
                "Tem certeza que deseja sair?", "Mensagem do Programa",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (resposta == 0) {
            try {
                // nitgen.getnBioBSPJNI().CloseDevice();
                nitgen.close();
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            nitgen = null;
            Close.clear();
            Close.confirm();
            System.exit(0);
        }
        actionInstance = false;
        load();
        thread.start();
    }

    public void about() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Leitor Biométrico - Modelo: " + conf.getModel() + " - Marca: " + conf.getBrand() + ". Versão: 1.0. Desenvolvidor por: Rtools Comércio de Sistemas Ltda"));
        JOptionPane.showMessageDialog(null, panel, "Sobre", 1);
    }

    public void restart() {
        Object[] options = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(null,
                "Tem certeza que deseja reiniciar a aplicação?", "Mensagem do Programa",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (resposta == 0) {
            try {
                nitgen.close();
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            timer.stop();
            listBiometria.clear();
            initComponents();
        }
        actionInstance = false;

    }

    public void reloadDB() {
        Object[] options = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(null,
                "Tem certeza que deseja limpar a base de dados? Isso pode levar alguns minutos!", "Mensagem do Programa",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (resposta == 0) {
            try {
                reloadListBiometria = false;
                nitgen.removeDB();
                reload = true;
                listBiometria.clear();
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        actionInstance = false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = "";
        JComponent jc = null;
        try {
            jc = (JComponent) e.getSource();
            actionInstance = true;
        } catch (Exception ex) {
            action = e.getActionCommand();
            actionInstance = true;
        }
        if (jc == null && !action.isEmpty()) {
            switch (action) {
                case "Sair":
                    exit();
                    break;
                case "Sobre":
                    about();
                    break;
                case "Reiniciar":
                    restart();
                    break;
                case "Reiniciar DB":
                    reloadDB();
                    break;
                case "Logs":
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        File dirToOpen = new File(Path.getUserPath() + "/rtools/" + conf.getBrand() + "/" + conf.getModel() + "/logs/");
                        desktop.open(dirToOpen);
                    } catch (IllegalArgumentException iae) {
                        System.out.println("File Not Found");
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        } else if (jc == ocultar) {
            // super.dispose();
            setVisible(false);
        } else if (jc == cadastrar) {
            super.dispose();
            // new Gravar().setVisible(true);
        } else if (jc == sair) {
            exit();
        } else if (jc == atualizar) {
            listBiometria.clear();
            nitgen.setLoad(true);
            getListBiometria();
        }
        actionInstance = false;
    }

    private Runnable poll = new Runnable() {
        Pool pool = new Pool();

        @Override
        public void run() {
//            if (conf.getSocket()) {
//                String result = "";
//                while (true) {
//                    try {
//                        srvSocket = new ServerSocket(conf.getSocket_port());
//                        Socket socket = srvSocket.accept();
//                        if (!WebService.existConnection()) {
//                            Preloader p = new Preloader();
//                            p.setAppTitle("Servidor offline, aguarde");
//                            p.setAppStatus("Servidor offline, aguarde");
//                            p.setWaitingStarted(false);
//                            p.show();
//                            for (int x = 0; x < 1; x++) {
//                                Thread.sleep(10000);
//                                if (WebService.existConnection()) {
//                                    p.hide();
//                                    break;
//                                }
//                                x--;
//                            }
//                        }
//                        if (pool.captura()) {
//                            if (stop < 1) {
//                                stop++;
//                                start();
//                                pool = new Pool();
//                            }
//                        } else {
//                            // Thread.
//                        }
//                        Thread.sleep(4000);
//                    } catch (IOException | InterruptedException e) {
//
//                    }
//                }
//            } else {
            try {
                for (int i = 0; i < 1; i++) {
                    if (!WebService.existConnection()) {
                        Preloader p = new Preloader();
                        p.setAppTitle("Servidor offline, aguarde");
                        p.setAppStatus("Servidor offline, aguarde");
                        p.setWaitingStarted(false);
                        p.show();
                        for (int x = 0; x < 1; x++) {
                            Thread.sleep(10000);
                            if (WebService.existConnection()) {
                                p.hide();
                                break;
                            }
                            x--;
                        }
                    }
                    Boolean captura = false;
                    if (conf.getSocket()) {
                        if (conf.getSocket_port() == 0 || conf.getSocket_port() == null) {
                            JOptionPane.showMessageDialog(null, "PORTA DO SOCKET NÃO CONFIGURADA!");
                            System.exit(0);
                        }
                        try {
                            serverSocket = null;
                            serverSocket = new ServerSocket(conf.getSocket_port());
                            Socket socket = serverSocket.accept();
                            serverSocket.close();
                            captura = true;
                        } catch (IOException e) {
                            captura = false;
                        }
                    } else {
                        captura = pool.captura();
                    }
                    if (captura) {
                        if (stop < 1) {
                            stop++;
                            start();
                            pool = new Pool();
                        }
                    } else {
                        // Thread.
                    }
                    if (conf.getSocket() == null || !conf.getSocket()) {
                        Thread.sleep(4000);
                    }
                    i--;
                }
            } catch (Exception e) {
                e.getMessage();
                Close.clear();
            }
        }
//        }
    };

    public void start() {
        Preloader p = new Preloader();
        p.setAppTitle("Solicitação");
        p.setAppStatus("Pedido de cadastramento recebido, aguarde");
        p.setWaitingStarted(false);
        p.show();
        Integer codigo_pessoa = null;
        BiometriaCaptura bc = new BiometriaCaptura();
        if (conf.getWeb_service()) {
            try {
                WebService webService = new WebService();
                webService.action("pedido_captura");
                webService.GET("biometria_captura");
                webService.execute();
                WSBiometriaCaptura wsbc = (WSBiometriaCaptura) webService.object(new WSBiometriaCaptura());
                if (wsbc != null) {
                    codigo_pessoa = wsbc.getCodigo_pessoa();
                }
            } catch (Exception ex) {
            }
        } else {
            BiometriaDao biometriaDao = new BiometriaDao();
            List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(MacFilial.getAcessoFilial().getId());
            codigo_pessoa = bc.getPessoa().getId();
            if (!list.isEmpty()) {
                bc = (BiometriaCaptura) list.get(0);
                biometria = biometriaDao.pesquisaBiometriaPorPessoa(bc.getPessoa().getId());
            }
        }
        super.setAlwaysOnTop(true);
        super.toFront();
        super.requestFocus();
        super.setAlwaysOnTop(false);
        try {
            nitgen = new Nitgen();
            nitgen.loadHardware();
            if (!nitgen.getHardware()) {
                non_device();
            } else if (nitgen.getDevice_start() == 0) {
                non_device();
            }
            p.hide();
            Integer status = null;
            if (conf.getWeb_service()) {
                if (codigo_pessoa != null) {
                    nitgen.setCodigo_pessoa(codigo_pessoa);
                    status = nitgen.readSaveWS();
                }
            } else {
                nitgen.setPessoa(bc.getPessoa());
                status = nitgen.readSave();
            }
            if (codigo_pessoa != null) {
                if (status != null) {
                    switch (status) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "Biometria cadastrada com sucesso");
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar biometria");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Digital não encontrada! Tente novamente!");
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Dispositivo desconectado!");
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!");
                            break;
                        default:
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro de status null!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma pessoa encontrada!");
            }
            stop = 0;
            i = 0;
            Close.clear();
            biometria = null;
            nitgen.dispose();
        } catch (Exception e) {
            p.hide();
            Close.clear();
        }
    }

    public Biometria getBiometria() {
        return biometria;
    }

    public void setBiometria(Biometria biometria) {
        this.biometria = biometria;
    }

    public List getListBiometria() {
        WebService webService = new WebService();
        if (reloadListBiometria) {
            if (!startedDate.equals(DataHoje.data())) {
                if (conf.getWeb_service()) {
                    webService.param("device_number", conf.getDevice());
                    webService.PUT("biometria_reload");
                    try {
                        webService.execute();
                    } catch (Exception ex) {
                    }
                } else {
                    reloadListBiometria = false;
                    listBiometria.clear();
                    startedDate = DataHoje.data();
                    new BiometriaDao().reload();
                }
            }
        }
        if (listBiometria.isEmpty()) {
            if (!nitgen.getExistsDB()) {
                reloadListBiometria = false;
            }
            if (reloadListBiometria) {
                if (conf.getWeb_service()) {
                    try {
                        webService.action("reload");
                        webService.GET("biometria_lista.jsf", "", "device_number=" + conf.getDevice());
                        String result = webService.execute();
                        Gson gson = new Gson();
                        List<WSBiometria> list = gson.fromJson(result, new TypeToken<List<WSBiometria>>() {
                        }.getType());
                        converter(list);

                    } catch (Exception ex) {
                        Logger.getLogger(Menu.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    listBiometria = new BiometriaDao().reloadListBiometria(conf.getDevice());
                }
            } else if (nitgen != null) {
                if (!nitgen.getExistsDB()) {
                    if (conf.getWeb_service()) {
                        try {
                            webService.GET("biometria_lista.jsf", "", "");
                            String result = webService.execute();
                            Gson gson = new Gson();
                            List<WSBiometria> list = gson.fromJson(result, new TypeToken<List<WSBiometria>>() {
                            }.getType());
                            converter(list);

                        } catch (Exception ex) {
                            Logger.getLogger(Menu.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        listBiometria = new BiometriaDao().listBiometria();
                    }
                }
            }
        }
        return listBiometria;
    }

    public void setListBiometria(List listBiometria) {
        this.listBiometria = listBiometria;
    }

    public void load() {
        if (tipo == null || tipo == 0) {

        }
    }

    public Integer getTipo() {
        if (tipo == 0) {
            tipo = conf.getType();
        }
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void converter(List l) {
        List<WSBiometria> list = l;
        listBiometria = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Biometria b = new Biometria();
            Pessoa pessoa = new Pessoa();
            pessoa.setId(list.get(i).getCodigo());
            b.setId(list.get(i).getCodigo_biometria());
            b.setPessoa(pessoa);
            b.setBiometria(list.get(i).getBiometria());
            b.setAtivo(list.get(i).getAtivo());
            b.setDataAtualizacaoAparelho1(list.get(i).getDataAtualizacaoAparelho1());
            b.setDataAtualizacaoAparelho2((list.get(i).getDataAtualizacaoAparelho2()));
            b.setDataAtualizacaoAparelho3((list.get(i).getDataAtualizacaoAparelho3()));
            b.setDataAtualizacaoAparelho4((list.get(i).getDataAtualizacaoAparelho4()));
            listBiometria.add(b);
        }
    }

    public Boolean getCatraca(Integer codigo_pessoa) {
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL(conf.getCatraca_server() + "/monitorCatraca/ws/liberar_pessoa.xhtml?cliente=" + conf.getClient() + "&pessoa=" + codigo_pessoa + "&" + "catraca=" + conf.getCatraca_number());
            Charset charset = Charset.forName("UTF8");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
            con.setRequestMethod("GET");
            con.connect();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), charset))) {
                response.append(in.readLine());
                in.close();
            }
            con.disconnect();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

//                    FutureTask<String> theTask = null;
//                    Thread thread = null;
//                    try {
//
//                        // EXECUTA A QUERY COM UM TEMPO LIMITE ----------------------
//                        theTask = new FutureTask(new Callable() {
//                            @Override
//                            public Object call() throws Exception {
//                                String result = "";
//                                try {
//                                    result = "SUCCESS";
//                                } catch (Exception e) {
//                                    e.getMessage();
//                                    result = "INTERRUPTED";
//                                }
//                                return result;
//                            }
//
//                        });
//                        thread = new Thread(theTask);
//                        thread.start();
//                        result = theTask.get(60 * 60, TimeUnit.MINUTES);
//                        theTask.cancel(true);
//                        try {
//                            thread.interrupt();
//                        } catch (Exception e) {
//
//                        }
//                   
//                }catch (TimeoutException | InterruptedException | ExecutionException ee) {
//                        if (theTask != null) {
//                            theTask.cancel(true);
//                        }
//                        if (thread != null) {
//                            thread.interrupt();
//                        }
//                        result = "TIMEOUT";
//                    }
