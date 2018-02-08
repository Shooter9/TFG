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
    
    
    
    public static void readThisExec() throws IOException{
        ProcessBuilder pb = new ProcessBuilder("~/TFG/textRecognition/ReadThisExec.sh", "", "");
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null)
        {
           System.out.println(line);
        }
    }
    
    
    
    
    
}
