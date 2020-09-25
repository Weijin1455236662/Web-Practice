package AttendanceService.Schedule;

import ERPService.Resource.ResourceItem;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebService(
        name = "Schedule",
        endpointInterface = "AttendanceService.Schedule.Schedule"
)
public class Schedule {
    private ArrayList<ScheduleItem> getCSV(){
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/arrangement.csv"), "gbk"));
            reader.readLine();//读出标题行,没有则注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String[] item = line.split(",");
                if(item.length<5){
                    ScheduleItem scheduleItem = new ScheduleItem(Integer.parseInt(item[0]),item[1],item[2],item[3],"");
                    scheduleItems.add(scheduleItem);
                }else{
                    ScheduleItem scheduleItem = new ScheduleItem(Integer.parseInt(item[0]),item[1],item[2],item[3],item[4]);
                    scheduleItems.add(scheduleItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scheduleItems;
    }

    @WebMethod
    public ArrayList<ScheduleItem> getAllSchedule(){
        return getCSV();
    }
}
