/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udpserver;

import java.net.DatagramSocket;
import java.net.SocketException;


public class scanudp {

    public static String scan(int firstPort, int lastPort) {
        String s="";
        for (int i=firstPort; i<lastPort; ++i) {
            try {
                DatagramSocket ds = new DatagramSocket(i);
                ds.close();
            }
            catch (SocketException ex){
                s += ";" + i;
            }
        }
        return s;
    }
}
