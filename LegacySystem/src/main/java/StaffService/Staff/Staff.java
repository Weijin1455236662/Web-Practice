package StaffService.Staff;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.io.*;
import java.util.ArrayList;

@WebService(
        name = "Staff",
        endpointInterface = "StaffService.Staff.Staff"
)
public class Staff {
    private ArrayList<StaffIItem> readCSV() {
        ArrayList<StaffIItem> staffIItems = new ArrayList<StaffIItem>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src//main//resources//csv//staffInfo.csv"), "gbk"));
            reader.readLine();//读出标题行,没有则注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(",");
                StaffIItem staffIItem = new StaffIItem(item[0], item[1], item[2], item[3]);
                staffIItems.add(staffIItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staffIItems;
    }

    @WebMethod
    public ArrayList<StaffIItem> getAllStaff() {
        ArrayList<StaffIItem> staffIItems = readCSV();
        return staffIItems;
    }

    @WebMethod
    public StaffIItem getStaffByUsername(String username) {
        ArrayList<StaffIItem> staffIItems = readCSV();
        for(int i=0;i<staffIItems.size();i++){
            if(staffIItems.get(i).getUsername().equals(username)){
                return staffIItems.get(i);
            }
        }
        return null;
    }

    @WebMethod
    public boolean Verify(String username,String password) {
        ArrayList<StaffIItem> staffIItems = readCSV();
        for(int i=0;i<staffIItems.size();i++){
            if(staffIItems.get(i).getUsername().equals(username)&&staffIItems.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
