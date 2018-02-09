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
        
          
              
 String cmd = System.getProperty("user.home")+"/TFG/textRecognition/ReadThisExec.sh";  
//String cmd = "D://script.bat" //for windows
 ProcessBuilder pb = new ProcessBuilder(cmd); 
 try
 {
 Process process = pb.start();
 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
 StringBuilder builder = new StringBuilder();
 String line = null;
 while ( (line = reader.readLine()) != null) {
 builder.append(line);
 }
 String result = builder.toString();
 System.out.print(result);
 System.out.println("end of script execution");
 }
 catch (IOException e)
 { System.out.print("error");
 e.printStackTrace();
 }

    
    
    
    
    
    
}
}
