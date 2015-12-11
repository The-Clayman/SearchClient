/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roy
 */
public class ClientSock implements Runnable {

    Socket sock;
    final int port = 18524;
    PrintWriter out;
    BufferedReader in;
    double[] prob;
    
    public ClientSock(double[] prob){
        this.prob = prob;
    }

    @Override
    public void run() {

        try {
            sock = new Socket(InetAddress.getByName("localhost"), port);
            System.out.println("connected to " + sock.toString());
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            int num = 0;
            while(num<100){
                int numX = genX();
                System.out.println("Client <name>: sending "+numX);
                out.println(numX);
                out.flush();
                String msg = in.readLine();
                System.out.println("Client <name>: got reply "+msg+" for query "+numX);
                num++;
            }

        } catch (IOException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int genX(){
        double rand = Math.random();
        int ans = -1;
        for (int i = 0; i < prob.length; i++) {
            if (rand < prob[i]){
                ans = i+1;
                i = prob.length;
            }
        }
        return ans;
    }

}
