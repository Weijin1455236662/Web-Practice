package ERPService.Resource;

public class ResourceItem {
    private int id;
    private String name;
    private String subtype;
    private String belong;
    private String type;
    private int amount;

    public ResourceItem(int id, String name, String subtype, String belong, String type, int amount) {
        this.id = id;
        this.name = name;
        this.subtype = subtype;
        this.belong = belong;
        this.type = type;
        this.amount = amount;
    }

    public void show(){
        System.out.println("项目资源代码:"+id+"\t资源名称:"+name+"\t资源子类型:"+subtype+"\t所属资源:"+belong+"\t资源类型:"+type+"\t资源量:"+amount);
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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
