package TestDBReverse;

import DBReverse.DBInfo.DBType;
import DBReverse.DBReverser;
import DBReverse.TableStc;
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
            System.out.println(tableStc);
            System.out.println(tableStc.toJSArray());
        }
    }
}
