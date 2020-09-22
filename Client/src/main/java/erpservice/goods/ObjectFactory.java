
package erpservice.goods;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the erpservice.goods package. 
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

    private final static QName _GetAllGoodsResponse_QNAME = new QName("http://Goods.ERPService/", "getAllGoodsResponse");
    private final static QName _GetAllGoods_QNAME = new QName("http://Goods.ERPService/", "getAllGoods");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: erpservice.goods
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllGoodsResponse }
     * 
     */
    public GetAllGoodsResponse createGetAllGoodsResponse() {
        return new GetAllGoodsResponse();
    }

    /**
     * Create an instance of {@link GetAllGoods }
     * 
     */
    public GetAllGoods createGetAllGoods() {
        return new GetAllGoods();
    }

    /**
     * Create an instance of {@link GoodsItem }
     * 
     */
    public GoodsItem createGoodsItem() {
        return new GoodsItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllGoodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Goods.ERPService/", name = "getAllGoodsResponse")
    public JAXBElement<GetAllGoodsResponse> createGetAllGoodsResponse(GetAllGoodsResponse value) {
        return new JAXBElement<GetAllGoodsResponse>(_GetAllGoodsResponse_QNAME, GetAllGoodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllGoods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Goods.ERPService/", name = "getAllGoods")
    public JAXBElement<GetAllGoods> createGetAllGoods(GetAllGoods value) {
        return new JAXBElement<GetAllGoods>(_GetAllGoods_QNAME, GetAllGoods.class, null, value);
    }

}
