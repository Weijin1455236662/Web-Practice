package ERPService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebService(
        name = "ResourceService",
        endpointInterface = "ERPService.ResourceService"
)
public class ResourceService {
    private ArrayList<ResourceItem> getCSV(){
        ArrayList<ResourceItem> resourceItems = new ArrayList<ResourceItem>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\csv\\resource.csv"), "gbk"));
            reader.readLine();//读出标题行,没有则注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");
                ResourceItem human = new ResourceItem(Integer.parseInt(item[0]),item[1],item[2],item[3],item[4],Integer.parseInt(item[5]));
                resourceItems.add(human);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resourceItems;
    }

    @WebMethod
    public ArrayList<ResourceItem> getHumanResource(){
        ArrayList<ResourceItem> resourceItems = getCSV();
        ArrayList<ResourceItem> humans = new ArrayList<ResourceItem>();

        for (ResourceItem resourceItem: resourceItems){
            if (resourceItem.getSubtype().equals("班组")){
                humans.add(resourceItem);
            }
        }

        return humans;
    }

    @WebMethod
    public ArrayList<ResourceItem> getLineResource(){
        ArrayList<ResourceItem> resourceItems = getCSV();
        ArrayList<ResourceItem> lines = new ArrayList<ResourceItem>();

        for (ResourceItem resourceItem: resourceItems){
            if (resourceItem.getSubtype().equals("线体")){
                lines.add(resourceItem);
            }
        }

        return lines;
    }
}
