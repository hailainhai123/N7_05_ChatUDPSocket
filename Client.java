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
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {

    JTextField txtfield;
    JTextField imageIcon;
    JTextArea txtarea;
    byte[] serverbffr, clientbffr;
    String senddata;
    DatagramSocket client, server;
    JButton button1;
    JButton sendImage;

    public static void main(String[] args) {
        Client obj = new Client();
    }

    public Client() {
        this.setSize(500, 500);
        this.setTitle("Client");
        this.setBackground(Color.black);
        txtfield = new JTextField();
        txtfield.setBackground(Color.white);
        txtfield.setForeground(Color.blue);
        this.add(txtfield, BorderLayout.NORTH);

        txtarea = new JTextArea();
        txtarea.setBackground(Color.black);
        txtarea.setForeground(Color.green);
        this.add(txtarea, BorderLayout.CENTER);

        button1 = new JButton("SEND MESSAGE");
        this.add(button1, BorderLayout.SOUTH);
        button1.addActionListener(this);
        this.setVisible(true);
        serverbffr = new byte[1024];
        clientbffr = new byte[1024];
        //setDefaultCloseOperation(EXIT_ON_CLOSE);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //****************************************************************
        try {
            client = new DatagramSocket();
            server = new DatagramSocket(9998);
            while (true) {
                DatagramPacket datapack = new DatagramPacket(serverbffr, serverbffr.length);
                server.receive(datapack);
                String msg = new String(datapack.getData());
                txtarea.append("\nServer:" + msg);

                
            }
        } catch (Exception e) {
        }        

    }

    //*********************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand() == "SEND MESSAGE") {
                String message = txtfield.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String stringDate = dateFormat.format(date);
                String str = message + "\t" + stringDate;                 
                clientbffr = str.getBytes();
                DatagramPacket sendpack = new DatagramPacket(clientbffr, clientbffr.length, InetAddress.getLocalHost(), 9999);
                client.send(sendpack);
                txtarea.append("\nClient:" + str);                
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
