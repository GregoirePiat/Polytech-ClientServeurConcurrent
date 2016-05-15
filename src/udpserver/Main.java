package udpserver;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*InetAddress adr1 = null;
        // IP
        try {
            adr1 = InetAddress.getByName("127.0.0.1") ;
            System.out.println(adr1);
        }
        catch (UnknownHostException ex){
            System.out.println("could not find server");
        }


        // Socket
        try{
            DatagramSocket cli = new DatagramSocket();
            DatagramSocket cli2 = new DatagramSocket(64200, adr1);
        // suite du programme
        }
        catch (SocketException ex) {
            ex.printStackTrace();
            System.err.println("Port déjà occupé");
        }*/

        //System.out.println(scanudp.scan(1, 65535));

        Client cli = new Client();
        Server serv = new Server(124);

        Thread servThread = new Thread(serv);
        servThread.start();

        Thread clientThread = new Thread(cli);
        clientThread.start();

        cli.connect("127.0.0.1");
        cli.send("Hello serveur RX302", 124);
    }



}
