
import erpservice.goods.Goods;
import erpservice.goods.GoodsItem;
import erpservice.goods.GoodsService;
import erpservice.resource.Resource;
import erpservice.resource.ResourceItem;
import erpservice.resource.ResourceService;

import java.util.List;

public class TestERP {
    public static void main(String[] args) {
        ResourceService resourceService=new ResourceService();
        Resource resourcePort=resourceService.getResourcePort();
        List<ResourceItem> resourceItems = resourcePort.getHumanResource();
        for (ResourceItem resourceItem: resourceItems){
            System.out.println(resourceItem.getId() + " " + resourceItem.getName());
        }

        resourceItems = resourcePort.getLineResource();
        for (ResourceItem resourceItem: resourceItems){
            System.out.println(resourceItem.getId() + " " + resourceItem.getName());
        }

        GoodsService goodsService=new GoodsService();
        Goods goodsPort=goodsService.getGoodsPort();
        List<GoodsItem> goodsItems = goodsPort.getAllGoods();
        for (GoodsItem goodsItem: goodsItems){
            System.out.println(goodsItem.getCode() + " " + goodsItem.getName());
        }
    }
}