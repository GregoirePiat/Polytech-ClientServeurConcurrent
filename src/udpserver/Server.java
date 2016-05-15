/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements Runnable {
    private int port;
    private DatagramSocket ds;
    private DatagramPacket dp;
    private String name;
    private boolean isRunning;
    private InetAddress adr1;

    public Server(int i) {
        port = i;
        name = "RX302";

        try {
            ds = new DatagramSocket(port);
            ds.setSoTimeout(100000);

        }
        catch (SocketException ex){
            System.err.println("Port déjà occupé");
        }
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning == true) {
            start();
        }
    }

    public void stop() {
        this.stop();
        isRunning = false;
    }

    public void start() {
        dp = new DatagramPacket(new byte[128],128);
        try {
            ds.receive(dp);
            System.out.println("Serveur try - message reçu");

            this.print();
        }
        catch (IOException ex){
            //System.err.println("Serveur - Aucune donnée reçue");
            ex.printStackTrace();
            System.out.println("Serveur catch - message reçu");
        }
        this.send("Réponse du serveur au client");
        ds.close();
    }

    public void print() {
        String message = "";
        try {
            message = new String(dp.getData(), "ASCII");
            adr1=dp.getAddress();
        }
        catch(Exception e) {
            System.err.println("Err décodage message");
        }
        System.out.println("message  = " + message);
    }


    public void send(String message){
        try {
            byte[] data = message.getBytes("ASCII");
            DatagramPacket dps = new DatagramPacket(data, data.length,
                    dp.getAddress(), dp.getPort());
            ds.send(dps);
            System.out.println("Serveur - message envoyé");
        }
        catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}