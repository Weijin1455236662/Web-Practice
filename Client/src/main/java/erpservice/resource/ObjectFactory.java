
package erpservice.resource;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the erpservice.resource package. 
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

    private final static QName _GetHumanResource_QNAME = new QName("http://Resource.ERPService/", "getHumanResource");
    private final static QName _GetLineResourceResponse_QNAME = new QName("http://Resource.ERPService/", "getLineResourceResponse");
    private final static QName _GetLineResource_QNAME = new QName("http://Resource.ERPService/", "getLineResource");
    private final static QName _GetHumanResourceResponse_QNAME = new QName("http://Resource.ERPService/", "getHumanResourceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: erpservice.resource
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHumanResourceResponse }
     * 
     */
    public GetHumanResourceResponse createGetHumanResourceResponse() {
        return new GetHumanResourceResponse();
    }

    /**
     * Create an instance of {@link GetHumanResource }
     * 
     */
    public GetHumanResource createGetHumanResource() {
        return new GetHumanResource();
    }

    /**
     * Create an instance of {@link GetLineResourceResponse }
     * 
     */
    public GetLineResourceResponse createGetLineResourceResponse() {
        return new GetLineResourceResponse();
    }

    /**
     * Create an instance of {@link GetLineResource }
     * 
     */
    public GetLineResource createGetLineResource() {
        return new GetLineResource();
    }

    /**
     * Create an instance of {@link ResourceItem }
     * 
     */
    public ResourceItem createResourceItem() {
        return new ResourceItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHumanResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Resource.ERPService/", name = "getHumanResource")
    public JAXBElement<GetHumanResource> createGetHumanResource(GetHumanResource value) {
        return new JAXBElement<GetHumanResource>(_GetHumanResource_QNAME, GetHumanResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLineResourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Resource.ERPService/", name = "getLineResourceResponse")
    public JAXBElement<GetLineResourceResponse> createGetLineResourceResponse(GetLineResourceResponse value) {
        return new JAXBElement<GetLineResourceResponse>(_GetLineResourceResponse_QNAME, GetLineResourceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLineResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Resource.ERPService/", name = "getLineResource")
    public JAXBElement<GetLineResource> createGetLineResource(GetLineResource value) {
        return new JAXBElement<GetLineResource>(_GetLineResource_QNAME, GetLineResource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHumanResourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Resource.ERPService/", name = "getHumanResourceResponse")
    public JAXBElement<GetHumanResourceResponse> createGetHumanResourceResponse(GetHumanResourceResponse value) {
        return new JAXBElement<GetHumanResourceResponse>(_GetHumanResourceResponse_QNAME, GetHumanResourceResponse.class, null, value);
    }

}
