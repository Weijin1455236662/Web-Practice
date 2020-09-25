package AttendanceService.WorkTime;

import ERPService.Resource.ResourceItem;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebService(
        name = "WorkTime",
        endpointInterface = "AttendanceService.WorkTime.WorkTime"
)
public class WorkTime {
    private ArrayList<WorkTimeItem> getCSV(){
        ArrayList<WorkTimeItem> workTimeItems = new ArrayList<WorkTimeItem>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/workTime.csv"), "gbk"));
            reader.readLine();//读出标题行,没有则注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");
                if(item[0].equals("休息")){
                    WorkTimeItem workTimeItem = new WorkTimeItem(item[0],"无","无");
                    workTimeItems.add(workTimeItem);
                }else{
                    WorkTimeItem workTimeItem = new WorkTimeItem(item[0],item[1],item[2]);
                    workTimeItems.add(workTimeItem);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workTimeItems;
    }

    @WebMethod
    public ArrayList<WorkTimeItem> getAllWorkTime(){
        return getCSV();
    }
}
