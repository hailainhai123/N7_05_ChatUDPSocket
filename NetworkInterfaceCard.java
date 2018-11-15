/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niclist;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NetworkInterfaceCard {
    public static void main(String[] args) {        
        // hiện thị các giao tiếp mạng và địa chỉ của chúng        
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface nif : Collections.list(nets)){
                System.out.println("Display name: "+ nif.getDisplayName());
                System.out.println("Name: " + nif.getName());               
                displayInterfaceInformation(nif);
                System.out.println("===============================");
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ ex.getMessage());
        }        
        
        
    }
    
    private static void displayInterfaceInformation(NetworkInterface nif) {
        Enumeration<InetAddress> listInetAddress = nif.getInetAddresses();
        for(InetAddress inetAddress : Collections.list(listInetAddress)){
            System.out.println("InetAddress: " + inetAddress);
        }
    }
}
