import erpservice.ResourceItem;
import erpservice.Resource;
import erpservice.ResourceService;

import java.util.List;

public class TestERP {
    public static void main(String[] args) {
        ResourceService service=new ResourceService();
        Resource port=service.getResourcePort();
        List<ResourceItem> resourceItems = port.getHumanResource();
        for (ResourceItem resourceItem: resourceItems){
            System.out.println(resourceItem.getId() + " " + resourceItem.getName());
        }
    }
}