/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mario.arias
 */
public class ReadThis_Application {
    
    
    
    public static void readThisExec() throws IOException {
        
          
              
 String path = System.getProperty("user.home")+"/TFG/textRecognition/ReadThisExec.sh/";
        try {
            Process proc = Runtime.getRuntime().exec(path); 
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
}
}
