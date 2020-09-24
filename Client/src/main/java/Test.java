
import erpservice.bom.BOM;
import erpservice.bom.BOMService;
import erpservice.bom.BomItem;
import erpservice.goods.Goods;
import erpservice.goods.GoodsItem;
import erpservice.goods.GoodsService;
import erpservice.resource.Resource;
import erpservice.resource.ResourceItem;
import erpservice.resource.ResourceService;
import orderservice.order.Order;
import orderservice.order.OrderItem;
import orderservice.order.OrderService;
import staffservice.staff.Staff;
import staffservice.staff.StaffIItem;
import staffservice.staff.StaffService;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ResourceService resourceService = new ResourceService();
        Resource resourcePort = resourceService.getResourcePort();
        List<ResourceItem> resourceItems = resourcePort.getHumanResource();
        for (ResourceItem resourceItem: resourceItems){
            System.out.println(resourceItem.getId() + " " + resourceItem.getName());
        }

        resourceItems = resourcePort.getLineResource();
        for (ResourceItem resourceItem: resourceItems){
            System.out.println(resourceItem.getId() + " " + resourceItem.getName());
        }

        GoodsService goodsService = new GoodsService();
        Goods goodsPort = goodsService.getGoodsPort();
        List<GoodsItem> goodsItems = goodsPort.getAllGoods();
        for (GoodsItem goodsItem: goodsItems){
            System.out.println(goodsItem.getCode() + " " + goodsItem.getName());
        }

        BOMService bomService = new BOMService();
        BOM bomPort = bomService.getBOMPort();
        List<BomItem> bomItems = bomPort.getAllBOM();
        for(BomItem bomItem: bomItems){
            System.out.println(bomItem.getMaterialCode() + " " + bomItem.getBOM() + " " +bomItem.getProperty());
        }

        StaffService staffService = new StaffService();
        Staff staffPort = staffService.getStaffPort();
        List<StaffIItem> staffIItems = staffPort.getAllStaff();
        System.out.println(staffIItems.size());
        for(StaffIItem staffIItem: staffIItems){
            System.out.println(staffIItem.getName()+" "+staffIItem.getGroupId()+" "+staffIItem.getUsername()+" "+staffIItem.getPassword());
        }
        System.out.println(staffPort.getStaffByUsername("tongling").getName());
        System.out.println(staffPort.verify("tongling","123123"));

        OrderService orderService=new OrderService();
        Order orderPort=orderService.getOrderPort();
        List<OrderItem> orderItems=orderPort.getAllOrders();
        System.out.println(orderItems.size());
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem.getOrderNumber()+" "+orderItem.getMaterialCode()+" "+orderItem.getQuantity()+" "+orderItem.getDeliveryDate());
        }
    }
}