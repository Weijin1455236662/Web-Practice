import ERPService.Goods;
import ERPService.Resource;

import javax.xml.ws.Endpoint;

public class Application {
    public static void main(String[] args) {
        String address="http://localhost:8080/resourceService";
        Endpoint.publish(address,new Resource());
        System.out.println("ResourceService Publishes Success!");
        System.out.println("Address:" + address);

        String address1 = "http://localhost:8080/goodsService";
        Endpoint.publish(address1, new Goods());
        System.out.println("GoodsService Published Successfully!");
        System.out.println("Address1:" + address1);
    }
}
