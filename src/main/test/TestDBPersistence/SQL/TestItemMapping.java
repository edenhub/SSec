package TestDBPersistence.SQL;

import DBPersistence.Sql.ItemMapping;
import DBPersistence.SqlCombined.CombinedDemo;
import org.junit.Test;

/**
 * Created by lab on 2014/12/25.
 */
public class TestItemMapping {

    @Test
    public void testPresentation(){
        ItemMapping itemMapping = new ItemMapping(0,"name","varchar","adam","user");
        System.out.println(itemMapping.itemPreSentate());
        ItemMapping itemMapping1 = new ItemMapping(1,"age","int","age","user");
        System.out.println(itemMapping1.itemPreSentate(true));

        System.out.println(CombinedDemo.addGoodSqlDemo);
    }
}
