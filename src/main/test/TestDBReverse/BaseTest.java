package TestDBReverse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.sql.*;

import static org.junit.Assert.*;

/**
 * Created by lab on 2014/11/19.
 */
public class BaseTest {

    private static final String connStr = "jdbc:mysql://localhost/easyform?user=root&password=12345";
    private Connection connection;
    @Before
    public void before() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connStr);

        Assert.notNull(connection);
    }

    @Test
    public void testGetTables() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tableSets = databaseMetaData.getTables(connection.getCatalog(),"%","%",new String[]{"TABLE"});

        if (tableSets!=null && tableSets.first()){
            do{
                System.out.println("==============================================");

                String tableName = tableSets.getString("TABLE_NAME");
                System.out.println("表名 "+tableName);
                testGetPrimaryKey(databaseMetaData, tableName);
                testGetTableStruct(tableName,connection.createStatement());
                testGetImportKeys(databaseMetaData,tableName);
            }while(tableSets.next());
        }

        tableSets.close();
    }

    public void testGetPrimaryKey(DatabaseMetaData metaData,String tableName) throws SQLException {
        ResultSet primarySet = metaData.getPrimaryKeys(connection.getCatalog(),"",tableName);

        if (primarySet!=null && primarySet.first()){
            do {
                        System.out.println("主键 "+primarySet.getString("COLUMN_NAME"));
            }while (primarySet.next());
        }
//        primarySet.first();
//        System.out.println("主键 "+primarySet.getString("COLUMN_NAME"));

        primarySet.close();
    }

    public void testGetTableStruct(String tableName,Statement statement) throws SQLException {
        String queryStr = "select * from "+tableName+" limit 0,1";
//        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryStr);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("属性列行数 : "+columnCount);

        for(int i=1;i<=columnCount;i++){
            System.out.println("属性 "+resultSetMetaData.getColumnLabel(i)+" 的类型 "+resultSetMetaData.getColumnTypeName(i));
        }

        resultSet.close();

    }

    public void testGetImportKeys(DatabaseMetaData metaData,String tableName) throws SQLException {
        ResultSet importSet = metaData.getImportedKeys(connection.getCatalog(),"",tableName);
        while(importSet.next()){
            System.out.println("外表名 "+importSet.getString("PKTABLE_NAME"));
        }

        importSet.close();

        System.out.println("==============================================");
    }

    @After
    public void after() throws SQLException {
        connection.close();
    }
}
