package StaffService.Staff;

public class StaffIItem {
    private String name;
    private String groupId;
    private String username;
    private String password;

    public StaffIItem(String name, String groupId, String username, String password) {
        this.name = name;
        this.groupId = groupId;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
