
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

    private final static QName _MensajeInicial_QNAME = new QName("http://InitialService/", "MensajeInicial");
    private final static QName _MensajeInicialResponse_QNAME = new QName("http://InitialService/", "MensajeInicialResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: broker
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MensajeInicialResponse }
     * 
     */
    public MensajeInicialResponse createMensajeInicialResponse() {
        return new MensajeInicialResponse();
    }

    /**
     * Create an instance of {@link MensajeInicial }
     * 
     */
    public MensajeInicial createMensajeInicial() {
        return new MensajeInicial();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MensajeInicial }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://InitialService/", name = "MensajeInicial")
    public JAXBElement<MensajeInicial> createMensajeInicial(MensajeInicial value) {
        return new JAXBElement<MensajeInicial>(_MensajeInicial_QNAME, MensajeInicial.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MensajeInicialResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://InitialService/", name = "MensajeInicialResponse")
    public JAXBElement<MensajeInicialResponse> createMensajeInicialResponse(MensajeInicialResponse value) {
        return new JAXBElement<MensajeInicialResponse>(_MensajeInicialResponse_QNAME, MensajeInicialResponse.class, null, value);
    }

}
