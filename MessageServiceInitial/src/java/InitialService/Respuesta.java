/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitialService;

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
     */
    @WebMethod(operationName = "Calc")
    public int calc(@WebParam(name = "Num1") int Num1, @WebParam(name = "Num2") int Num2) {
        return Num1+Num2;
    }
}
