
package broker;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the broker package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalcResponse_QNAME = new QName("http://InitialService/", "CalcResponse");
    private final static QName _Calc_QNAME = new QName("http://InitialService/", "Calc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: broker
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Calc }
     * 
     */
    public Calc createCalc() {
        return new Calc();
    }

    /**
     * Create an instance of {@link CalcResponse }
     * 
     */
    public CalcResponse createCalcResponse() {
        return new CalcResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalcResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://InitialService/", name = "CalcResponse")
    public JAXBElement<CalcResponse> createCalcResponse(CalcResponse value) {
        return new JAXBElement<CalcResponse>(_CalcResponse_QNAME, CalcResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Calc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://InitialService/", name = "Calc")
    public JAXBElement<Calc> createCalc(Calc value) {
        return new JAXBElement<Calc>(_Calc_QNAME, Calc.class, null, value);
    }

}
