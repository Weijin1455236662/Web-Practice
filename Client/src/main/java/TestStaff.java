import orderservice.order.Order;
import orderservice.order.OrderItem;
import orderservice.order.OrderService;
import staffservice.staff.Staff;
import staffservice.staff.StaffIItem;
import staffservice.staff.StaffService;

import java.util.List;

public class TestStaff {
    public static void main(String[] args) {
        StaffService staffService = new StaffService();
        Staff staffPort = staffService.getStaffPort();
        List<StaffIItem> staffIItems = staffPort.getAllStaff();
        System.out.println(staffIItems.size());
        for(StaffIItem staffIItem: staffIItems){
            System.out.println(staffIItem.getName()+" "+staffIItem.getGroupId()+" "+staffIItem.getUsername()+" "+staffIItem.getPassword());
        }
        System.out.println(staffPort.getStaffByUsername("tongling").getName());
        System.out.println(staffPort.verify("tongling","123123"));
    }
}