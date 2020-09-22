package ERPService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.io.*;
import java.util.ArrayList;

@WebService(
        name = "Goods",
        endpointInterface = "ERPService.Goods"
)
public class Goods {
    private ArrayList<GoodsItem> readCSV() {
        ArrayList<GoodsItem> goodsItems = new ArrayList<GoodsItem>();

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/item.csv"), "gbk"));
            reader.readLine();
            String line = null;
            while((line = reader.readLine()) != null) {
                String[] item = line.split(",");
                if (item.length == 9) {
                    GoodsItem goods = new GoodsItem(item[0], item[1].trim(), item[2], item[3], item[4], item[5], item[6], item[7], item[8]," ");
                    goodsItems.add(goods);
                } else if (item.length == 10) {
                    String temp = item[1] + "," + item[2];
                    temp = temp.replaceAll("\"", "").trim();
                    GoodsItem goods = new GoodsItem(item[0], temp, item[3], item[4], item[5], item[6], item[7], item[8], item[9], " ");
                    goodsItems.add(goods);
                } else if (item.length == 11) {
                    String temp = item[1] + "," + item[2] + "," + item[3];
                    temp = temp.replaceAll("\"", "").trim();
                    GoodsItem goods = new GoodsItem(item[0], temp, item[4], item[5], item[6], item[7], item[8], item[9], item[10], " ");
                    goodsItems.add(goods);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodsItems;
    }

    @WebMethod
    public ArrayList<GoodsItem> getAllGoods() {
        ArrayList<GoodsItem> goodsItems = readCSV();
        return goodsItems;
    }
}
