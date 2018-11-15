/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudpsocket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class ReceiveImageClient extends JFrame implements Serializable, ActionListener {

    JLabel label;
    JButton buttonReceive;
    byte[] buffer;
    DatagramSocket client;

    public static void main(String[] args) {
        ReceiveImageClient receiveImageClient = new ReceiveImageClient();

    }

    public ReceiveImageClient() {
        buffer = new byte[1000000];
        this.setSize(300, 300);
        this.setTitle("Client");
        label = new JLabel();
        this.add(label, BorderLayout.CENTER);
        this.revalidate();
        label.setSize(50, 50);

        buttonReceive = new JButton("RECEIVE IMAGE");
        this.add(buttonReceive, BorderLayout.SOUTH);
        buttonReceive.addActionListener(this);
        this.setVisible(true);
        
        try {
            client = new DatagramSocket(1107);
        } catch (SocketException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand() == "RECEIVE IMAGE") {                
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                client.receive(receivePacket);
                byte[] receiveData = receivePacket.getData();                
                ByteArrayInputStream in = new ByteArrayInputStream(receiveData);
                BufferedImage image = ImageIO.read(in);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, image.SCALE_SMOOTH));
                label.setIcon(icon);
            }
        } catch (Exception ex) {

        }
    }
}
