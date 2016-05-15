/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpserver;

import java.io.IOException;
import java.net.*;


public class Communication implements Runnable{
    private DatagramSocket ds;
    private DatagramPacket dp;
    private String name;
    private boolean isRunning;
    private InetAddress adrClient;
    private int portClient;


    public Communication(InetAddress adrClient, int portClient){
        Thread comThread = new Thread(this);
        comThread.start();

        this.adrClient = adrClient;
        this.portClient = portClient;
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning == true) {
            start();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void start() {
        dp = new DatagramPacket(new byte[128],128);
        try {
            ds.receive(dp);
            System.out.println("Serveur try - message reçu");
            this.stop();
            this.print();
        }
        catch (IOException ex){
            //System.err.println("Serveur - Aucune donnée reçue");
            ex.printStackTrace();
            System.out.println("Serveur catch - message reçu");
        }
        ds.close();
    }

    public void print() {
        String message = "";
        try {
            message = new String(dp.getData(), "ASCII");
            adrClient=dp.getAddress();
        }
        catch(Exception e) {
            System.err.println("Err décodage message");
        }
        System.out.println("message  = " + message);
    }
}