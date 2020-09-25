package AttendanceService.Schedule;

public class ScheduleItem {
    //资源代码,资源名称,日期/星期,班次,备注;
    private int id;
    private String name;
    private String workDay;
    private String workTime;
    private String remarks;

    public ScheduleItem(int id, String name, String workDay, String workTime, String remarks) {
        this.id = id;
        this.name = name;
        this.workDay = workDay;
        this.workTime = workTime;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
