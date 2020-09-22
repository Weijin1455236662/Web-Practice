
package erpservice.bom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the erpservice.bom package. 
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

    private final static QName _GetAllBOMResponse_QNAME = new QName("http://BOM.ERPService/", "getAllBOMResponse");
    private final static QName _GetAllBOM_QNAME = new QName("http://BOM.ERPService/", "getAllBOM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: erpservice.bom
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllBOM }
     * 
     */
    public GetAllBOM createGetAllBOM() {
        return new GetAllBOM();
    }

    /**
     * Create an instance of {@link GetAllBOMResponse }
     * 
     */
    public GetAllBOMResponse createGetAllBOMResponse() {
        return new GetAllBOMResponse();
    }

    /**
     * Create an instance of {@link BomItem }
     * 
     */
    public BomItem createBomItem() {
        return new BomItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBOMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BOM.ERPService/", name = "getAllBOMResponse")
    public JAXBElement<GetAllBOMResponse> createGetAllBOMResponse(GetAllBOMResponse value) {
        return new JAXBElement<GetAllBOMResponse>(_GetAllBOMResponse_QNAME, GetAllBOMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBOM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BOM.ERPService/", name = "getAllBOM")
    public JAXBElement<GetAllBOM> createGetAllBOM(GetAllBOM value) {
        return new JAXBElement<GetAllBOM>(_GetAllBOM_QNAME, GetAllBOM.class, null, value);
    }

}
