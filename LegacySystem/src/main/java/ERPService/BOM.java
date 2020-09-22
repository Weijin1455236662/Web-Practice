package ERPService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.*;
import java.util.ArrayList;

@WebService(
        name = "BOM",
        endpointInterface = "ERPService.BOM"
)
public class BOM {
    private  ArrayList<BOMItem> readCSV() {
        ArrayList<BOMItem> bomItems = new ArrayList<BOMItem>();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/BOM.csv"), "gbk"));
            reader.readLine();
            String line = null;
            while((line = reader.readLine()) != null) {
                String item[] = line.split(",");
                BOMItem bomItem = new BOMItem(item[0], item[1], item[2], item[3], item[4].equals("") ? 0 :Double.parseDouble(item[4]), item[5], item[6], item[7], item[8], item[9].equals("") ? 0 : Double.parseDouble(item[9]), item[10], item[11].equals("") ? 0 : Integer.parseInt(item[11]),item[12], item[13].equals("") ? 0 :Integer.parseInt(item[13]));
                bomItems.add(bomItem);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bomItems;
    }

    @WebMethod
    public ArrayList<BOMItem> getAllBOM() {
        ArrayList<BOMItem> bomItems = readCSV();
        return bomItems;
    }
}
