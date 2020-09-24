package OrderService.Order;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebService(
        name = "Order",
        endpointInterface = "OrderService.Order.Order"
)
public class Order {

    @WebMethod
    public ArrayList<OrderItem> getAllOrders(){
        return readCSV();
    }



    private ArrayList<OrderItem> readCSV(){
        ArrayList<OrderItem> resourceItems = new ArrayList<OrderItem>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/order.csv"), "gbk"));
            reader.readLine();//读出标题行,没有则注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");
                DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
                Date deliveryDate=format.parse(item[3]);
                OrderItem human = new OrderItem(item[0],item[1],Integer.parseInt(item[2]),deliveryDate);
                resourceItems.add(human);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resourceItems;
    }


}
