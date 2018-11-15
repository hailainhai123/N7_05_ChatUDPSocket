/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudpsocket;

/**
 *
 * @author Admin
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends JFrame implements ActionListener {

    JTextField txtfield;
    JTextArea txtarea;
    byte[] serverbffr, clientbffr;
    DatagramSocket client, server;
    JButton button1;

    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {
        this.setSize(500, 500);
        this.setTitle("Server");
        txtfield = new JTextField(100);
        txtfield.setBackground(Color.white);
        txtfield.setForeground(Color.blue);
        this.add(txtfield, BorderLayout.NORTH);

        txtarea = new JTextArea();
        this.add(txtarea, BorderLayout.CENTER);
        txtarea.setBackground(Color.black);
        txtarea.setForeground(Color.green);

        button1 = new JButton("SEND MESSAGE");
        this.add(button1, BorderLayout.SOUTH);
        button1.addActionListener(this);
        this.setVisible(true);
        serverbffr = new byte[1024];
        clientbffr = new byte[1024];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //*******************************************************************
        try {
            client = new DatagramSocket();
            server = new DatagramSocket(9999);
            while (true) {
                DatagramPacket datapack = new DatagramPacket(clientbffr, clientbffr.length);
                server.receive(datapack);
                String msg = new String(datapack.getData());
                txtarea.append("\nClient:" + msg);

            }
        } catch (Exception e) {
        }        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand() == "SEND MESSAGE") {
                String message = txtfield.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String stringDate = dateFormat.format(date);
                String str = message + "\t" + stringDate; 
                serverbffr = str.getBytes();
                DatagramPacket sendpack = new DatagramPacket(serverbffr, serverbffr.length, InetAddress.getLocalHost(), 9998);
                client.send(sendpack);
                txtarea.append("\nServer:" + message + "\t" + stringDate);                
                txtfield.setText("");
            }
        } catch (Exception a) {
        }
    }

    public void TimeCurrent() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);
        txtarea.append("\t" + stringDate);
    }
}
