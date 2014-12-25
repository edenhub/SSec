package DBPersistence.Sql;

/**
 * Created by lab on 2014/12/25.
 */

/**
 * SQL中每个{i}对应的数据情况，如：insert into users(name,age) value(\'{0}\',{1});
 * 包括
 * i:valIndex
 * i所对应的类型:itemType
 * i所对应的值:itemValue
 * i所对应字段所属的表
 */
public class ItemMapping implements SqlItemPresentator{
    private int valIndex;
    private String itemName;
    private String itemType;
    private String itemValue;
    private String originalTable;

    public ItemMapping(){}

    public ItemMapping(int valIndex){
        this.valIndex = valIndex;
    }

    public ItemMapping(int valIndex, String itemName,
                       String itemType, String itemValue,
                       String originalTable) {
        this.valIndex = valIndex;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.originalTable = originalTable;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getValIndex() {
        return valIndex;
    }

    public void setValIndex(int valIndex) {
        this.valIndex = valIndex;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getOriginalTable() {
        return originalTable;
    }

    public void setOriginalTable(String originalTable) {
        this.originalTable = originalTable;
    }

    @Override
    public String toString() {
        return "ItemMapping{" +
                "valIndex=" + valIndex +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemValue='" + itemValue + '\'' +
                ", originalTable='" + originalTable + '\'' +
                '}';
    }

    @Override
    public String itemPreSentate() {
        StringBuffer resSB = new StringBuffer();
        if (itemType.equals("varchar") || itemType.equals("text") || itemType.equals("string")){
            resSB.append("\\\"").append("{").append(valIndex).append("}").append("\\\"");
        }else{
            resSB.append("{").append(valIndex).append("}");
        }
            return resSB.toString();

    }

    @Override
    public String itemPreSentate(boolean withCommoa) {
        return itemPreSentate()+",";
    }
}
