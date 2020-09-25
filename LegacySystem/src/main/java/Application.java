import AttendanceService.Schedule.Schedule;
import AttendanceService.WorkTime.WorkTime;
import ERPService.BOM.BOM;
import ERPService.Goods.Goods;
import ERPService.Resource.Resource;
import OrderService.Order.Order;
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

        String address4= "http://localhost:8080/OrderService";
        Endpoint.publish(address4, new Order());
        System.out.println("OrderService Published Successfully!");
        System.out.println("Address4:" + address4);

        String address5= "http://localhost:8080/ScheduleService";
        Endpoint.publish(address5, new Schedule());
        System.out.println("ScheduleService Published Successfully!");
        System.out.println("Address5:" + address5);

        String address6= "http://localhost:8080/WorkTimeService";
        Endpoint.publish(address6, new WorkTime());
        System.out.println("WorkTimeService Published Successfully!");
        System.out.println("Address6:" + address6);
    }
}
