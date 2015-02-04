package DBPersistence.Sql.persistence.data;

/**
 * Created by lab on 2015/1/27.
 */
public class Sql_Item implements ItemPresentator{
    private int itemId;
    private String descriptor;
    private String type;
    private String value;

    public Sql_Item(){}

    public Sql_Item(int itemId, String descriptor, String type, String value){
        this.itemId = itemId;
        this.descriptor = descriptor;
        this.type = type;
        this.value = value;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String itemPreSentate() {
        StringBuffer resSB = new StringBuffer();
        if (type.equals("varchar") || type.equals("text") || type.equals("string")){
            resSB.append("\"").append(value).append("\"");
        }else{
            resSB.append(value);
        }
        return resSB.toString();

    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", descriptor='" + descriptor + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public String itemPreSentate(boolean withCommoa) {
        return itemPreSentate()+",";
    }
}
