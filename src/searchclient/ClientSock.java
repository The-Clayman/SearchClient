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
    int[][] check = new int[SearchClient.r + 1][2];

    public ClientSock(double[] prob) {
        this.prob = prob;
    }

    @Override
    public void run() {

        try {
  //          while (true) {
                sock = new Socket(InetAddress.getByName("localhost"), port);
                System.out.println("connected to " + sock.toString());
                out = new PrintWriter(sock.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(sock.getInputStream()));
                int num = 0;
                long startTime = System.currentTimeMillis();
                boolean run = true;
                try {
                    while (run) {
                        int numX = genX();
                        if (SearchClient.forTest) {
                            System.out.println("Client "+SearchClient.probFileName+": sending " + numX);
                        }
                        out.println(numX);
                        out.flush();
                        String msg = in.readLine();
                        if (SearchClient.forTest) {
                            System.out.println("Client "+SearchClient.probFileName+" got reply " + msg + " for query " + numX);
                        }
                        if (!SearchClient.forTest) {
                            chackIfcorrect(numX, new Integer(msg));
                        }
                        if (!sock.isConnected()){
                            run = false;
                        }
                        //             num++;
                    }
                } finally {
                    sock.close();
                }
      //      }

        } catch (IOException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int genX() {
        double rand = Math.random();
        int ans = -1;
        for (int i = 0; i < prob.length; i++) {
            if (rand < prob[i]) {
                ans = i + 1;
                i = prob.length;
            }
        }
        return ans;
    }

    public void chackIfcorrect(int x, int y) {
        if (check[x][1] == 0) {
            check[x][0] = x;
            check[x][1] = y;
        } else {
            if (check[x][1] != y) {
                check[0][0] = 100;
            }
        }

    }

    public void printCheck() {
        System.out.println("");
        for (int i = 0; i < check.length; i++) {
            System.out.println(check[i][0] + " , " + check[i][1]);

        }
        System.out.println("");
        if (check[0][0] != 0) {
            System.out.println("not pass");
        } else {
            System.out.println("pass");
        }
    }

}
