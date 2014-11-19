package DBReverse;

import DBReverse.DBInfo.DBConnector;
import DBReverse.DBInfo.DBType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lab on 2014/11/19.
 */
public class DBReverser {
    private DBConnector dbConnector;
    private List<TableStc> tableStcs;
    private DBType dbType;

    public DBReverser(){
        dbConnector = new DBConnector();
        tableStcs = new ArrayList<TableStc>();
    }

    public void init(String url, String username, String password, DBType dbType, String driverName)
            throws Exception{
        this.dbType = dbType;
        if (!dbConnector.isReadable())
            dbConnector.conn(url,username,password,dbType,driverName);
    }

    public void init(String url, String username, String password, String dbType, String driverName)
            throws Exception{
        this.dbType = DBType.selectType(dbType);
        init(url, username, password, this.dbType, driverName);
    }

    public void init(String url, String username, String password, DBType dbType)
            throws Exception{
        if (!dbConnector.isReadable())
            dbConnector.conn(url,username,password,dbType);
    }

    public void init(String url,String username,String password,String dbType)
            throws Exception{
        this.dbType = DBType.selectType(dbType);
        init(url, username, password, this.dbType);
    }

    public void buildData() throws Exception {
        reverseTables(dbConnector.getConnection());
    }

    public void reverseTables(Connection connection) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tableSets = databaseMetaData.getTables(connection.getCatalog(),"%","%",new String[]{"TABLE"});
        if (tableSets!=null && tableSets.first()){
            do{
                String tableName = tableSets.getString("TABLE_NAME");
                TableStc tableStc = new TableStc();
                Statement statement = connection.createStatement();

                tableStc.setTableName(tableName);
                reversePrimaryKeys(connection,databaseMetaData, tableName, tableStc);
                reverseTableStruct(statement,tableName,tableStc);
                reverseForiegnKeys(databaseMetaData, tableName,tableStc);

                tableStcs.add(tableStc);
                statement.close();
            }while(tableSets.next());
        }

        tableSets.close();

    }

    public void reversePrimaryKeys(Connection connection,DatabaseMetaData databaseMetaData,
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

    public void reverseTableStruct(Statement statement,String tableName,TableStc tableStc) throws SQLException {
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

    public void reverseForiegnKeys(DatabaseMetaData databaseMetaData,String tableName,TableStc tableStc){
//        2014-11-19 未完成
    }

    private TableStructSelectSQL selectType(DBType dbType){
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

    ================================================================
     */
    public interface TableStructSelectSQL{
        public String selectSQL(String tableName);
    }

    public static class NormalSelectSQL implements TableStructSelectSQL{

        @Override
        public String selectSQL(String tableName) {
            return "select * from "+tableName;
        }
    }

    public static class MYSQLSelectSQL implements TableStructSelectSQL{

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
