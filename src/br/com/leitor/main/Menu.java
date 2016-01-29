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
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

public class Menu extends JFrame implements ActionListener {

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
            ocultar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/arrow_right_down_16x16.png")).getImage()));
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
            TrayIcon trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/images/finger_16x16.png")).getImage(), "Leitor Biométrico - " + title);
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
            if (actionInstance) {
                return;
            }
            timer.setDelay(0);
            BiometriaAtualizaCatraca bac = new BiometriaAtualizaCatracaDao().refresh(conf.getDevice());
            bac = (BiometriaAtualizaCatraca) new Dao().rebind(bac);
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
                new Dao().update(bac, true);
            }
            if (nitgen.getHardware()) {
                if (reload) {
                    reload = false;
                    nitgen.setLoad(true);
                    List<Biometria> list = getListBiometria();
                    for (int i = 0; i < list.size(); i++) {
                        if (null != conf.getDevice()) {
                            switch (conf.getDevice()) {
                                case 1:
                                    list.get(i).setDataAtualizacaoAparelho1(null);
                                    break;
                                case 2:
                                    list.get(i).setDataAtualizacaoAparelho2(null);
                                    break;
                                case 3:
                                    list.get(i).setDataAtualizacaoAparelho3(null);
                                    break;
                                case 4:
                                    list.get(i).setDataAtualizacaoAparelho4(null);
                                    break;
                                default:
                                    break;
                            }
                        }
                        new Dao().update(list.get(i), true);
                    }
                    Preloader p = new Preloader();
                    p.setAppTitle("Dispostivo - " + conf.getBrand() + " - " + conf.getModel());
                    p.setWaitingStarted(false);
                    if (reloadListBiometria) {
                        p.setAppTitle("Recarregando base de dados, isto pode levar alguns minutos...aguarde, não feche a aplicação.");
                        p.setAppStatus("Recarregando base de dados, isto pode levar alguns minutos...aguarde, não feche a aplicação.");
                    } else {
                        p.setAppStatus("Carregando base de dados, isto pode levar alguns minutos...aguarde, não feche a aplicação.");
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
                listBiometria.isEmpty();
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
            new Gravar().setVisible(true);
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

            try {
                for (int i = 0; i < 1; i++) {
                    if (pool.getBiometriaCaptura() != null) {
                        if (stop < 1) {
                            stop++;
                            start();
                            pool = new Pool();
                        }
                    } else {
                        // Thread.
                    }
                    Thread.sleep(4000);
                    i--;
                }
            } catch (Exception e) {
                e.getMessage();
                Close.clear();
            }
        }
    };

    public void start() {
        BiometriaDao biometriaDao = new BiometriaDao();
        List list = biometriaDao.pesquisaBiometriaCapturaPorMacFilial(MacFilial.getAcessoFilial().getId());
        BiometriaCaptura bc = new BiometriaCaptura();
        if (!list.isEmpty()) {
            bc = (BiometriaCaptura) list.get(0);
            biometria = biometriaDao.pesquisaBiometriaPorPessoa(bc.getPessoa().getId());
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
            nitgen.setPessoa(bc.getPessoa());
            Integer status = nitgen.readSave();
            if (status != null) {
                switch (status) {
                    case 1:
                        biometria = nitgen.getBiometria();
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
            stop = 0;
            i = 0;
            Close.clear();
            biometria = null;
            nitgen.dispose();
        } catch (Exception e) {
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
        if (reloadListBiometria) {
            if (!startedDate.equals(DataHoje.data())) {
                reloadListBiometria = false;
                listBiometria.clear();
                startedDate = DataHoje.data();
                new BiometriaDao().reload();
            }
        }
        if (listBiometria.isEmpty()) {
            if (reloadListBiometria) {
                listBiometria = new BiometriaDao().reloadListBiometria(conf.getDevice());
            } else if (nitgen != null) {
                if (!nitgen.getExistsDB()) {
                    listBiometria = new BiometriaDao().listBiometria();
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

}
