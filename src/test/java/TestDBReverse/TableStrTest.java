package java.TestDBReverse;

import DBReverse.DBInfo.DBType;
import DBReverse.TableStc;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lab on 2014/11/19.
 */
public class TableStrTest {

    @Test
    public void test01(){
        TableStc tableStc = new TableStc();
        tableStc.setColumnNum(3);
        tableStc.setTableName("user");
        Map<String,String> fileds = new HashMap<String, String>(3);
        fileds.put("id","int");
        fileds.put("code","string");
        fileds.put("name","string");
        tableStc.setFields(fileds);

        System.out.println(tableStc.toJSArray());
    }

    @Test
    public void test02(){
        System.out.println(DBType.MYSQL);
//        toS("DB_MYSQL");
    }

    public void toS(DBType dbType){
        System.out.println(dbType);
    }
}
