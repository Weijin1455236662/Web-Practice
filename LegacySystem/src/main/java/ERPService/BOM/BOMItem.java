package ERPService.BOM;

public class BOMItem {
    private String materialCode;
    private String technology;
    private String BOM;
    private String property;
    private Double quantity;
    private String unit;
    private String resource;
    private String resourceType;
    private String resourceKind;
    private Double necessaryResource;
    private String standardCapacity;
    private int resourcePriority;
    private String switchingTime;
    private int personnelNumber;

    public BOMItem(String materialCode, String technology, String BOM, String property, Double quantity, String unit, String resource, String resourceType, String resourceKind, Double necessaryResource, String standardCapacity, int resourcePriority, String switchingTime, int personnelNumber) {
        this.materialCode = materialCode;
        this.technology = technology;
        this.BOM = BOM;
        this.property = property;
        this.quantity = quantity;
        this.unit = unit;
        this.resource = resource;
        this.resourceType = resourceType;
        this.resourceKind = resourceKind;
        this.necessaryResource = necessaryResource;
        this.standardCapacity = standardCapacity;
        this.resourcePriority = resourcePriority;
        this.switchingTime = switchingTime;
        this.personnelNumber = personnelNumber;
    }

    public void show() {
        System.out.println("物料编码: " + materialCode + "\t工艺: " + technology + "\t物品代码: " + BOM + "\t物料属性: " + property + "\t数量: " + quantity + "\t单位: " + unit + "\t资源: " + resource + "\t资源类型: " + resourceType + "\t资源种类: " + resourceKind + "\t必要资源量:" + necessaryResource + "\t标准产能: " + standardCapacity + "\t资源优先级: " + resourcePriority + "\t换线时间: " + switchingTime + "\t产品规定生产人员: " + personnelNumber );
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getBOM() {
        return BOM;
    }

    public void setBOM(String BOM) {
        this.BOM = BOM;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceKind() {
        return resourceKind;
    }

    public void setResourceKind(String resourceKind) {
        this.resourceKind = resourceKind;
    }

    public Double getNecessaryResource() {
        return necessaryResource;
    }

    public void setNecessaryResource(Double necessaryResource) {
        this.necessaryResource = necessaryResource;
    }

    public String getStandardCapacity() {
        return standardCapacity;
    }

    public void setStandardCapacity(String standardCapacity) {
        this.standardCapacity = standardCapacity;
    }

    public int getResourcePriority() {
        return resourcePriority;
    }

    public void setResourcePriority(int resourcePriority) {
        this.resourcePriority = resourcePriority;
    }

    public String getSwitchingTime() {
        return switchingTime;
    }

    public void setSwitchingTime(String switchingTime) {
        this.switchingTime = switchingTime;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }
}
