import orderservice.order.Order;
import orderservice.order.OrderItem;
import orderservice.order.OrderService;

import java.util.List;

public class TestOrder {
    public static void main(String[] args) {
        OrderService orderService=new OrderService();
        Order orderPort=orderService.getOrderPort();
        List<OrderItem> orderItems=orderPort.getAllOrders();
        System.out.println(orderItems.size());
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem.getOrderNumber()+" "+orderItem.getMaterialCode()+" "+orderItem.getQuantity()+" "+orderItem.getDeliveryDate());
        }
    }
}
