/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitialService;

import java.awt.image.BufferedImage;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mario.arias
 */
@WebService(serviceName = "Respuesta")
public class Respuesta {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "MensajeInicial")
    public String MensajeInicial() {            
            return ("InitialMessageService funciona correctamente"); 
    }
}
