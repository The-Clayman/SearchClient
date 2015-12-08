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

    @Override
    public void run() {

        try {
            sock = new Socket(InetAddress.getByName("localhost"), port);
            System.out.println("connected to " + sock.toString());
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            while(true);

        } catch (IOException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
