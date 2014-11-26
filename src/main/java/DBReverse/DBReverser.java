package DBReverse;

import DBReverse.DBInfo.DBConnector;
import DBReverse.DBInfo.DBType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 用于连接数据库，获取表信息
 */
public class DBReverser {
    private DBConnector dbConnector;
    //表结构列表
    private List<TableStc> tableStcs;
    private DBType dbType;

    public DBReverser(){
        dbConnector = new DBConnector();
        tableStcs = new ArrayList<TableStc>();
    }

    /**
     * 初始化，获得数据库连接，必须操作
     * @param url 数据库连接符
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类型
     * @param driverName 驱动类
     * @throws Exception
     */
    public void init(String url, String username, String password, DBType dbType, String driverName)
            throws Exception{
        this.dbType = dbType;
        if (!dbConnector.isReadable())
            dbConnector.conn(url,username,password,dbType,driverName);
    }

    /**
     * 初始化，获得数据库连接，必须操作
     * @param url 数据库连接符
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类型
     * @param driverName 驱动类
     * @throws Exception
     */
    public void init(String url, String username, String password, String dbType, String driverName)
            throws Exception{
        this.dbType = DBType.selectType(dbType);
        init(url, username, password, this.dbType, driverName);
    }

    /**
     * 初始化，获得数据库连接，必须操作，使用默认驱动
     * @param url 数据库连接符
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类型
     * @throws Exception
     */
    public void init(String url, String username, String password, DBType dbType)
            throws Exception{
        this.dbType = dbType;
        if (!dbConnector.isReadable())
            dbConnector.conn(url,username,password,dbType);
    }

    /**
     * 初始化，获得数据库连接，必须操作，使用默认驱动
     * @param url 数据库连接符
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类型
     * @throws Exception
     */
    public void init(String url,String username,String password,String dbType)
            throws Exception{
        this.dbType = DBType.selectType(dbType);
        init(url, username, password, this.dbType);
    }

    /**
     * 执行反向工程
     * @throws Exception
     */
    public void buildData() throws Exception {
        assert(dbConnector.getConnection()!=null);
        reverseTables(dbConnector.getConnection());
    }

    /**
     * 反向表工程
     * @param connection
     * @throws SQLException
     */
    protected void reverseTables(Connection connection) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tableSets = databaseMetaData.getTables(connection.getCatalog(),"%","%",new String[]{"TABLE"});
        if (tableSets!=null && tableSets.first()){
            do{
                String tableName = tableSets.getString("TABLE_NAME");
                TableStc tableStc = new TableStc();
                Statement statement = connection.createStatement();

                tableStc.setTableName(tableName);
                reversePrimaryKeys(connection,databaseMetaData, tableName, tableStc);
                reverseTableStructe(statement,tableName,tableStc);
                reverseForeignKeys(connection,databaseMetaData, tableName,tableStc);

                tableStcs.add(tableStc);
                statement.close();
            }while(tableSets.next());
        }

        tableSets.close();

    }

    /**
     * 反向主键工程
     * @param connection
     * @param databaseMetaData
     * @param tableName
     * @param tableStc
     * @throws SQLException
     */
    protected void reversePrimaryKeys(Connection connection,DatabaseMetaData databaseMetaData,
                                   String tableName,TableStc tableStc)
            throws SQLException {
        ResultSet primarySet = databaseMetaData.getPrimaryKeys(connection.getCatalog(),"",tableName);

        if (primarySet!=null && primarySet.first()){
            do {
                tableStc.getPrimaryKeys().add(primarySet.getString("COLUMN_NAME"));
            }while (primarySet.next());
        }

        primarySet.close();
    }

    /**
     * 反向字段工程
     * @param statement
     * @param tableName
     * @param tableStc
     * @throws SQLException
     */
    protected void reverseTableStructe(Statement statement,String tableName,TableStc tableStc) throws SQLException {
        TableStructSelectSQL selectSQL = selectType(this.dbType);
        String queryStr = selectSQL.selectSQL(tableName);
        ResultSet resultSet = statement.executeQuery(queryStr);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        tableStc.setColumnNum(columnCount);

        for(int i=1;i<=columnCount;i++){
            tableStc.getFields().put(resultSetMetaData.getColumnLabel(i),resultSetMetaData.getColumnTypeName(i));
        }

        resultSet.close();
    }

    /**
     * 反向外键工程
     * @param connection
     * @param databaseMetaData
     * @param tableName
     * @param tableStc
     * @throws SQLException
     */
    protected void reverseForeignKeys(Connection connection,DatabaseMetaData databaseMetaData,
                                   String tableName,TableStc tableStc)
            throws SQLException {
        ResultSet importSet = databaseMetaData.getImportedKeys(connection.getCatalog(),"",tableName);
        if (importSet!=null && importSet.first()){
            do {
                String sourceKey = importSet.getString("PKCOLUMN_NAME");
                String fkTable = importSet.getString("PKTABLE_NAME");
                String fkName = importSet.getString("FKCOLUMN_NAME");
                tableStc.getForeignTables().add(fkTable);
                tableStc.getForeignKeys().add(fkName);
                tableStc.getGeneralizations().put(sourceKey,new MapPair<String, String>(fkTable,fkName));
            }while (importSet.next());
        }
        importSet.close();
    }

     /*
    ================================================================

                        根据数据库情况再增加

    ================================================================
     */

    protected TableStructSelectSQL selectType(DBType dbType){
        switch (dbType){
            case MYSQL:
                return new MYSQLSelectSQL();
        }

        return new NormalSelectSQL();
    }

     /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public List<TableStc> getTableStcs() {
        return tableStcs;
    }

    public void setTableStcs(List<TableStc> tableStcs) {
        this.tableStcs = tableStcs;
    }

       /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    /*
    ================================================================

                        TableStructSelectSQL
     不同数据库有不同实现，在进行字段的反向时需要查询一便数据集，
     可以根据不同的数据库进行不同的优化，默认的方式是获取全部数据集

    ================================================================
     */
    public interface TableStructSelectSQL{
        /**
         * 返回数据库查询SQL语句
         * @param tableName 表名
         * @return SQL查询语句
         */
        public String selectSQL(String tableName);
    }

    /**
     * 默认的实现
     */
    public static class NormalSelectSQL implements TableStructSelectSQL{

        /**
         * 默认返回所有的数据集
         * @param tableName 表名
         * @return
         */
        @Override
        public String selectSQL(String tableName) {
            return "select * from "+tableName;
        }
    }

    /**
     * 根据MYSQL优化
     */
    public static class MYSQLSelectSQL implements TableStructSelectSQL{

        /**
         * MYSQL有Limit语句，只用返回1条数据
         * @param tableName 表名
         * @return
         */
        @Override
        public String selectSQL(String tableName) {
            return "select * from "+tableName+" limit 0,1";
        }
    }

     /*
    ================================================================

                        TableStructSelectSQL

    ================================================================
     */
}
