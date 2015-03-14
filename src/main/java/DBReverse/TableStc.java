package DBReverse;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;

import java.util.*;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 表结构对象
 */
public class TableStc implements TableReverse {
//    用于表示图的id
    private int id;
//    表名
    private String tableName;
//    属性列数
    private int columnNum;
//    主键列表
    private List<String> primaryKeys;
//    属性列，包含所有的属性
    private Map<String,String> fields;
//    外键列表
    private Set<String> foreignKeys;
//    外表列表
    private Set<String> foreignTables;
//    主键和外表，外表键映射表 ： 主键 -> <外表名，外表哪个键>
    private HashMultimap<String, MapPair<String,String>> generalizations;

    public TableStc(){
        primaryKeys = new ArrayList<String>();
        fields = new HashMap<String, String>();
        foreignKeys = new LinkedHashSet<String>();
        foreignTables = new LinkedHashSet<String>();
//        generalizations = new HashMap<String, MapPair<String, String>>();
        generalizations = HashMultimap.create();
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

    public HashMultimap<String, MapPair<String, String>> getGeneralizations() {
        return generalizations;
    }

    public void setGeneralizations(HashMultimap<String, MapPair<String, String>> generalizations) {
        this.generalizations = generalizations;
    }

    public Set<String> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(Set<String> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public Set<String> getForeignTables() {
        return foreignTables;
    }

    public void setForeignTables(Set<String> foreignTables) {
        this.foreignTables = foreignTables;
    }

    //    public Map<String, MapPair<String, String>> getGeneralizations() {
//        return generalizations;
//    }

//    public void setGeneralizations(Map<String, MapPair<String, String>> generalizations) {
//        this.generalizations = generalizations;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
