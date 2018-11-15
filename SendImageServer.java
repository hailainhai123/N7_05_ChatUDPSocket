/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudpsocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
public class SendImageServer extends JFrame implements ActionListener,Serializable {

    JButton buttonSend;
    JTextField txtField;
    JTextArea txtarea;
    JLabel label;
    byte[] buffer;
    DatagramSocket server;

    public static void main(String[] args) {
        SendImageServer sendImageServer = new SendImageServer();
    }

    public SendImageServer() {
        this.setSize(300, 300);
        this.setTitle("Server");
        label = new JLabel();
        this.add(label, BorderLayout.CENTER);
        this.revalidate();
        label.setSize(50, 50);
        try {
            BufferedImage image = ImageIO.read(
                    new File("C:\\Users\\Admin\\Desktop\\baseline-3d_rotation-black-18\\1x\\abc.png"));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, image.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        buttonSend = new JButton("SEND IMAGE");
        this.add(buttonSend, BorderLayout.SOUTH);
        buttonSend.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand() == "SEND IMAGE") {
                BufferedImage img = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\baseline-3d_rotation-black-18\\1x\\abc.png"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                baos.flush();
                buffer = baos.toByteArray();
                server = new DatagramSocket();
                InetAddress IP = InetAddress.getByName("127.0.0.1");
                System.out.println(buffer.length);
                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IP, 1107);
                server.send(sendPacket);
            }

        } catch (Exception ex) {

        }
    }

}
