package DBReverse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 表结构对象
 */
public class TableStc implements TableReverse {
    private String tableName;
    private int columnNum;
    private List<String> primaryKeys;
    private Map<String,String> fields;
    private Map<String,MapPair<String,String>> generalizations;

    public TableStc(){
        primaryKeys = new ArrayList<String>();
        fields = new HashMap<String, String>();
        generalizations = new HashMap<String, MapPair<String, String>>();
    }

       /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public Map<String, MapPair<String, String>> getGeneralizations() {
        return generalizations;
    }

    public void setGeneralizations(Map<String, MapPair<String, String>> generalizations) {
        this.generalizations = generalizations;
    }

       /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    @Override
    public String toXML() {
        return null;
    }

    @Override
    public String toJSArray() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int count = 0;
        Map.Entry<String,String> lastEntry = null;

        for(Map.Entry<String,String> e : fields.entrySet()){
            if(++count > (columnNum-1)){
                lastEntry = e;
                break;
            }
            builder.append("\'").append(e.getKey()).append(":").append(e.getValue()).append("\'").append(",");
        }
        builder.append("\'").append(lastEntry.getKey()).append(":").append(lastEntry.getValue()).append("\'");
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "TableStc{" +
                "tableName='" + tableName + '\'' +
                ", columnNum=" + columnNum +
                ", primaryKeys=" + primaryKeys +
                ", fields=" + fields +
                ", generalizations=" + generalizations +
                '}';
    }
}
