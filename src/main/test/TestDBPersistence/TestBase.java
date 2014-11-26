package TestDBPersistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

/**
 * Created by lab on 2014/11/26.
 */
public class TestBase {
    private static final String url = "jdbc:mysql://localhost/easyform";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "12345";

    private static Connection connection = null;

    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        connection = DriverManager.getConnection(url,username,password);
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        connection.close();
    }

    @Test
    public void test01() throws SQLException {
        String insertRecordSQL = "insert into record (value) value('kkkkk')";
        String insertUserSQL = "insert into user (username,type) value('adamT','employee')";
        PreparedStatement preparedStatement;
//        PreparedStatement preparedStatement = connection.prepareStatement(insertRecordSQL);
//        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(insertUserSQL);
        preparedStatement.execute();

        closeSataement(preparedStatement);
    }

    void closeSataement(Statement statement) throws SQLException {
        statement.close();
    }
}
