/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.leitor.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author rtools2
 */
public class Mac {

    public static String getInstance() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
            byte[] macAddressBytes = netInter.getHardwareAddress();

            String macAddress = String.format("%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                    macAddressBytes[0], macAddressBytes[1],
                    macAddressBytes[2], macAddressBytes[3],
                    macAddressBytes[4], macAddressBytes[5]).toUpperCase();
            return macAddress;
        } catch (UnknownHostException | SocketException e) {
            return "";
        }
    }

}
