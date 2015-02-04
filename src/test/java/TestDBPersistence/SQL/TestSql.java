package TestDBPersistence.SQL;

import DBPersistence.Sql.persistence.DefPersistentor;
import DBPersistence.Sql.persistence.Persistence;
import DBPersistence.Sql.persistence.data.DataWrapper;
import DBPersistence.Sql.persistence.data.Sql_Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by lab on 2015/1/27.
 */
public class TestSql {

    @Test
    public void testSql(){
        String insert1 = "insert into t_users values({1},{10},{29},{13});";
        String insert2 = "insert into t_users values({2},{9},{19},{23});";

        Sql_Item item11 = new Sql_Item(1,"username","string","adam2");
        Sql_Item item12 = new Sql_Item(10,"password","string","test2");
        Sql_Item item13 = new Sql_Item(29,"age","int","23");
        Sql_Item item14 = new Sql_Item(13,"addrr","string","SYSU2");

        Sql_Item item21 = new Sql_Item(2,"us","string","jack2");
        Sql_Item item22 = new Sql_Item(9,"pw","string","test22");
        Sql_Item item23 = new Sql_Item(19,"a","int","2");
        Sql_Item item24 = new Sql_Item(23,"s","string","SS2");

        DataWrapper data = new DataWrapper();
        data.addItem(item11).addItem(item12).addItem(item13).addItem(item14)
                .addItem(item21).addItem(item22).addItem(item23).addItem(item24);

        Persistence persistence = new DefPersistentor();
        String driverName = "com.mysql.jdbc.Driver";
        String Url = "jdbc:mysql://localhost:3306/dmail";
        String username = "root";
        String password = "12345";

        Properties properties = new Properties();
        properties.put("db.driverName",driverName);
        properties.put("db.Url",Url);
        properties.put("db.username",username);
        properties.put("db.password",password);

        persistence.init(properties);

        List<String> sqls = new ArrayList<String>();
        sqls.add(insert1);
        sqls.add(insert2);

        boolean res = persistence.persistenceSQL(sqls,data);
        assert res == true;
    }
}
