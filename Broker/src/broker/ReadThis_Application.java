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
    
    
    
    public static void readThisExec() {
        
          try {
              System.out.println(System.getProperty("user.home")+"/TFG/textRecognition/ReadThisExec.sh");
    ProcessBuilder pb = new ProcessBuilder(
      System.getProperty("user.home")+"/TFG/textRecognition/ReadThisExec.sh");
    Process p = pb.start();     // Start the process.
    p.waitFor();                // Wait for the process to finish.
    System.out.println("Script executed successfully");
  } catch (Exception e) {
    e.printStackTrace();
  }

    }
    
    
    
    
    
}
