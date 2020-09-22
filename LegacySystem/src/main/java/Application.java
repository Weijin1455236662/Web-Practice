import ERPService.BOM.BOM;
import ERPService.Goods.Goods;
import ERPService.Resource.Resource;
import StaffService.Staff.Staff;

import javax.xml.ws.Endpoint;

public class Application {
    public static void main(String[] args) {
        String address="http://localhost:8080/resourceService";
        Endpoint.publish(address,new Resource());
        System.out.println("ResourceService Published Successfully!");
        System.out.println("Address:" + address);

        String address1 = "http://localhost:8080/goodsService";
        Endpoint.publish(address1, new Goods());
        System.out.println("GoodsService Published Successfully!");
        System.out.println("Address1:" + address1);

        String address2= "http://localhost:8080/BOMService";
        Endpoint.publish(address2, new BOM());
        System.out.println("BOMService Published Successfully!");
        System.out.println("Address2:" + address2);

        String address3= "http://localhost:8080/StaffService";
        Endpoint.publish(address3, new Staff());
        System.out.println("StaffService Published Successfully!");
        System.out.println("Address3:" + address3);
    }
}
