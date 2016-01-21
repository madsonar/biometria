/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leitor.utils;

import br.com.leitor.seguranca.Conf;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rtools2
 */
public class Ping {

    public static void execute() {
        Logs logs = new Logs();
        try {
            Conf conf = new Conf();
            conf.loadJson();
            // Endereço IP a ser verificado
            String ip = Inet4Address.getLocalHost().getHostAddress();
            if (ip.equals("127.0.0.1")) {
                JOptionPane.showMessageDialog(null, "Verifique sua conexão de rede!");
                logs.save("Erro na rede", "Rede local desabilitada!");
                System.exit(0);
            }
            String addr = null;
            switch (conf.getClient()) {
                case "ComercioLimeira":
                    addr = "200.204.32.23";
                    break;                
                case "Sindical":
                    addr = "200.158.101.9";
                    break;
            }
//            if (addr != null) {
//                // Verifica se o IP responde com timeout de 2 segundos (2000 milissegundos)
//                if (InetAddress.getByName(addr).isReachable(2000)) {
//                    String name = InetAddress.getByName(addr).getHostName();
//                    System.out.println("Host " + name + " (" + addr + ") ativo!");
//                    System.exit(0);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Servidor da remoto encontra-se inátivo! Possível causa: A rede central esta com problemas.");
//                    logs.save("Erro na rede", "Servidor da remoto encontra-se inátivo! Possível causa: A rede central esta com problemas. DNS?. Acesso a internet está ok?");
//                    System.out.println("Host " + addr + " inativo!");
//                    System.exit(0);
//                }
//            }
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Servidor da remoto encontra-se inátivo! Possível causa: A rede central esta com problemas. DNS?. Acesso a internet está ok?");
            logs.save("Erro na rede", "Rede não esta ativa. Verifique as conexão de rede! " + ex.getMessage());
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
}
