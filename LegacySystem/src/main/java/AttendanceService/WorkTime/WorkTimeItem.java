package AttendanceService.WorkTime;

import java.util.Date;

public class WorkTimeItem {
    private String id;
    private String name;
    private String time;

    public WorkTimeItem(String code, String name, String time) {
        this.id = code;
        this.name = name;
        this.time = time;
    }
    
    public String getCode() {
        return id;
    }

    public void setCode(String code) {
        this.id = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
