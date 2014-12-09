package DBPersistence.SqlCombined;

import java.sql.SQLException;

/**
 * Created by adam on 2014/12/9.
 */
public class DemoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /**
         * 1
         * 用户提供DB连接数据
         */
        String driverName = "com.mysql.jdbc.Driver";
        String Url = "jdbc:mysql://localhost:3306/dmail";
        String username = "root";
        String password = "12345";
        CombinedDemo demo = CombinedDemo.getInstance();
        demo.setConnection(demo.getConnection(Url,username,password,driverName));

//        Demo可以的话，正式写代码的时候要考虑多种情况，如一条sql执行多次；这里一条sql只执行一次

        /**
         *

        select也一样，不过select会出现数据集，要用户执行其他逻辑，不是单纯的数据库操作，所以，这里没demo
                如果要的话，要提供接口，用户实现该接口，我们执行该接口的内容，不过这样就跟后面说的提供代码书写器一样冲突了
                我觉得，表单只有单纯的提交的话可以用下面来实现
        **/

        /**
         * 2
         * 用户提供需要执行的sql和多对应的数据
         */
        String[] insertUserSql = new String[]{CombinedDemo.addUserSqlDemo,CombinedDemo.addGoodSqlDemo,};
        Object[][] userValue = new Object[][]{new Object[]{"user1","test","SYSU"},new Object[]{"good1",12,"user1"}};

        String[] insertGoodSql = new String[]{CombinedDemo.addGoodSqlDemo};
        Object[][] goodValue = new Object[][]{new Object[]{"good2",12,"user1"}};


        boolean result1 = demo.executeBatchTemplate(insertUserSql,userValue);
        assert result1 == true;
        boolean result2 = demo.executeBatchTemplate(insertGoodSql,goodValue);
        assert result2 == true;
    }
}
