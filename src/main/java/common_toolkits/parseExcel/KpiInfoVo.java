package common_toolkits.parseExcel;

/**
 * @author luzj
 * @description:
 * 1.描述Excel解析的结果的对象
 * @date 2018/4/10
 */
public class KpiInfoVo {
    private String kpiName;//指标名称
    private String kpiCode;//指标code
    private String unit;//指标单位
    private int isAdd;//是否是作为新增
    private double ord;//重新排序ord
    private int level;//在指标中的层级 分为：1 2 3
    private String parentKpiCode;//父指标，只有isAdd==1是，才可能有父指标

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentKpiCode() {
        return parentKpiCode;
    }

    public void setParentKpiCode(String parentKpiCode) {
        this.parentKpiCode = parentKpiCode;
    }

    public int getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(int isAdd) {
        this.isAdd = isAdd;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getOrd() {
        return ord;
    }

    public void setOrd(double ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "kpiName:"+kpiName+",\tkpiCode:"+kpiCode+",\tunit:"+unit+",\tisAdd:"+isAdd+
                ",\tord:"+ord+",\tlevel:"+level+",\tparentKpiCode:"+parentKpiCode+"\t\t";
    }
}
