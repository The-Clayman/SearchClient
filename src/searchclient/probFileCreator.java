/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchclient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roy
 */
public class probFileCreator {

    public static void main(String[] args) {
        //String name = args[0];
        //int range = new Integer(args[1]);
        String name = "prob.txt";
        File file = new File(name);
        int range = 100;
        String content = "";
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        double count = 0;
        double[] arr = new double[100];
        for (int i = 0; i < arr.length; i++) {
            double num = Math.random();
            arr[i] = num;
            count += num;
        }
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i]/count;
        }
        Arrays.sort(arr);
        for (int i = 0; i < range - 1; i++) {
            String toasign = df.format(arr[i]);
            content = toasign + "," + content;
        }
        // String content = "0.100,0.200,0.300,0.150,0.200,0.050";
        //    String content = "0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05";
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(probFileCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(probFileCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
