
package erpservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Goods", targetNamespace = "http://ERPService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Goods {


    /**
     * 
     * @return
     *     returns java.util.List<erpservice.GoodsItem>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllGoods", targetNamespace = "http://ERPService/", className = "erpservice.GetAllGoods")
    @ResponseWrapper(localName = "getAllGoodsResponse", targetNamespace = "http://ERPService/", className = "erpservice.GetAllGoodsResponse")
    @Action(input = "http://ERPService/Goods/getAllGoodsRequest", output = "http://ERPService/Goods/getAllGoodsResponse")
    public List<GoodsItem> getAllGoods();

}
