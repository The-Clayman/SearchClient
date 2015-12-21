/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roy
 */
public class SearchClient {

    static int r = 70;
    static String probFileName = "prob.txt";
    static double prob[];
    static boolean forTest = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            parse(args);
        } else {
            System.out.println("no arguments received");
            parse(null);
            //  return;
        }
        
        ClientSock cs = new ClientSock(prob);
        (new Thread(cs)).start();


    }

    private static void parse(String[] args) {
        if (args != null) {
            r = new Integer(args[0]);
            probFileName = args[1];
        }
        BufferedReader br = null;
        try {
            FileReader file = new FileReader(probFileName);

            if (!file.ready()) {
                System.out.println("file no found");
                System.exit(0);
            }

            br = new BufferedReader(file);
            prob = new double[r];
            String cline;
            String line = "";
            while ((cline = br.readLine()) != null) {
                line = line + cline;
            }
            String[] parts = line.split(",");
            double count = 0;
            for (int i = 0; i < SearchClient.r; i++) {
                count +=Double.parseDouble(parts[i]);
                prob[i] = count;
            }
            prob[prob.length-1] = 1;
            for (int i = 0; i < prob.length; i++) {
                System.out.println(prob[i]);
            }

        } catch (IOException ex) {
            Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(SearchClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
