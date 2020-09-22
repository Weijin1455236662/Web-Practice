package ERPService.Goods;

public class GoodsItem {
    private String code;
    private String name;
    private String property;
    private String measurementUnit;
    private String color;
    private String length;
    private String hardness;
    private String otherSpecification;
    private String materialPreparation;
    private String minimumPackingQuantity;

    public GoodsItem(String code, String name, String property, String measurementUnit, String color, String length, String hardness, String otherSpecification, String materialPreparation, String minimumPackingQuantity) {
        this.code = code;
        this.name = name;
        this.property = property;
        this.measurementUnit = measurementUnit;
        this.color = color;
        this.length = length;
        this.hardness = hardness;
        this.otherSpecification = otherSpecification;
        this.materialPreparation = materialPreparation;
        this.minimumPackingQuantity = minimumPackingQuantity;
    }

    public void show() {
        System.out.println("物料编码: " + code + "\t物料描述: " + name + "\t物品属性: " + property + "\t计量单位: " + measurementUnit + "\t颜色: " + color + "\t长度: " + length + "\t硬度: " + hardness + "\t其他需要分区的规格: " + otherSpecification + "\t备料方式: " + materialPreparation + "\t最小包装量: " + minimumPackingQuantity);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getOtherSpecification() {
        return otherSpecification;
    }

    public void setOtherSpecification(String otherSpecification) {
        this.otherSpecification = otherSpecification;
    }

    public String getMaterialPreparation() {
        return materialPreparation;
    }

    public void setMaterialPreparation(String materialPreparation) {
        this.materialPreparation = materialPreparation;
    }

    public String getMinimumPackingQuantity() {
        return minimumPackingQuantity;
    }

    public void setMinimumPackingQuantity(String minimumPackingQuantity) {
        this.minimumPackingQuantity = minimumPackingQuantity;
    }

}
