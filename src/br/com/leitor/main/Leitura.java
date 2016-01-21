package br.com.leitor.main;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Leitura extends JFrame implements ActionListener {

    NBioBSPJNI nitgen = new NBioBSPJNI();
    NBioBSPJNI.IndexSearch objIndexSearch = nitgen.new IndexSearch();
    private JMenuBar barra;
    private JMenu biometria;
    private JMenuItem voltar, sair;
    private JButton btnLeitura;

    public static void main(String args[]) {
        new Menu().setVisible(true);
    }

    public Leitura() {
        super("Exemplo de Menus com submenus");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
                System.exit(0);
            }
        });
        initComponents();
        setLayout(null);
        setSize(320, 320);
        setLocationRelativeTo(null);
        setTitle("Biometria > Identificar Usuário");
        setIconImage(new ImageIcon(getClass().getResource("/images/finger.png")).getImage());
        add(barra);
        add(btnLeitura);
        // add(jPanel);
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
        Container tela = getContentPane();
        tela.setLayout(null);
        barra = new JMenuBar();
        btnLeitura = new JButton("Validar");
        btnLeitura.setBounds(10, 90, 200, 25);
        btnLeitura.addActionListener(this);
        setJMenuBar(barra);
        biometria = new JMenu("O que deseja fazer?");
        voltar = new JMenuItem("Menu Principal");
        sair = new JMenuItem("Sair");
        sair.addActionListener(this);
        voltar.addActionListener(this);
        voltar.setMnemonic(KeyEvent.VK_L);
        barra.add(biometria);
        barra.add(sair);
        biometria.add(voltar);
        biometria.addSeparator();
//        try {
//            nitgen = new NBioBSPJNI();
//            objIndexSearch = nitgen.new IndexSearch();
//        } catch (Exception e) {
//
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent jc = (JComponent) e.getSource();
        if (jc == voltar) {
            super.dispose();
            new Menu().setVisible(true);
        } else if (jc == btnLeitura) {
            lerBiometria();
        } else if (jc == sair) {
            int resposta;
            resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Mensagem do Programa", JOptionPane.YES_NO_OPTION);
            if (resposta == 0) {
                System.exit(0);
            }
        }
    }

    public void lerBiometria() {
        NBioBSPJNI.FIR_HANDLE hSavedFIR;
        hSavedFIR = nitgen.new FIR_HANDLE();
        //Abre o sensor, faz a captura e fecha
        nitgen.OpenDevice();
        nitgen.Capture(hSavedFIR);
        nitgen.CloseDevice();
        //Declara a variável para obter a string de caracteres
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;
        textSavedFIR = nitgen.new FIR_TEXTENCODE();
        //Pega a string de caracteres a partir do handle capturado
        nitgen.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
        //Declara o input_fir para fazer a comparação da digital
        NBioBSPJNI.INPUT_FIR inputFIR = nitgen.new INPUT_FIR();
        inputFIR.SetTextFIR(textSavedFIR);
        NBioBSPJNI.IndexSearch.FP_INFO fpInfo = objIndexSearch.new FP_INFO();
        /* Faz o processo de identificação:
         * - Primeiro parametro: digital capturada no momento
         * - Segundo parametro: nivel de segurança. varia de 1 a 9. 5 é default.
         * - Terceiro parametro: informação do usuário
         * */
        objIndexSearch.Identify(inputFIR, 5, fpInfo);
        JOptionPane.showMessageDialog(null, "ID Identificado pelo IndexSearch:  " + String.valueOf(fpInfo.ID));
        //jTextField2.setText(textSavedFIR.TextFIR);
    }

}
