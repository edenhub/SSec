package java.TestDBReverse;

import DBReverse.DBInfo.DBType;
import DBReverse.DBReverser;
import DBReverse.MapPair;
import DBReverse.TableStc;
import com.google.common.collect.HashMultimap;
import org.junit.Test;

import java.util.List;

/**
 * Created by lab on 2014/11/20.
 */
public class DBReverserTest {
    private static final String connStr = "jdbc:mysql://localhost/easyform";
    private static final String username = "root";
    private static final String password = "12345";

    @Test
    public void test1() throws Exception {
        DBReverser dbReverser = new DBReverser();
        dbReverser.init(connStr,username,password, DBType.MYSQL);
        dbReverser.buildData();

        List<TableStc> tableStcs = dbReverser.getTableStcs();
        for(TableStc tableStc : tableStcs){
            System.out.println("表名 : "+tableStc.getTableName());
            System.out.println("属性列个数 : "+tableStc.getColumnNum());
            System.out.println("主键列表 ： "+tableStc.getPrimaryKeys());
            System.out.println("所有的属性类 : "+tableStc.getFields());
            System.out.println("外键列表 ==================================================>>>");
            System.out.println("所有的外键 ："+tableStc.getForeignKeys());
            System.out.println("所有的从表 : "+tableStc.getForeignTables());
            HashMultimap<String, MapPair<String,String>> foreingKeys = tableStc.getGeneralizations();
            for (String s : foreingKeys.keySet()){
                System.out.println("主表中键 : "+s+"对应如下外键 ：");
                System.out.println(foreingKeys.get(s));
            }
//            System.out.println(tableStc);
            System.out.println("属性列转JS数组形式 ： "+tableStc.toJSArray());
            System.out.println("<<<==========================================================");
        }
    }
}
