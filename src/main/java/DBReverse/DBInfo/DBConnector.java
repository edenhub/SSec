package DBReverse.DBInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lab on 2014/11/19.
 */
public class DBConnector implements DBAuth, DBDriverName {
    private DBInfo dbInfo;
    private Connection connection;

    public DBConnector() {
        dbInfo = new DBInfo();
    }

    @Override
    public boolean isReadable() throws SQLException {
        if (connection!=null && connection.isClosed()) {
            return false;
        }
        return true;
    }

    @Override
    public Connection conn(String url, String username, String password, DBType dbType, String driverName)
            throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName(driverName).newInstance();
        connection = DriverManager.getConnection(url, username, password);

        setDbInfo(url,username,password,dbType,driverName);

        return connection;
    }

    @Override
    public Connection conn(String url, String username, String password, DBType dbType)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String driverName = selectDriverName(dbType);

        return conn(url, username, password, dbType, driverName);
    }

    @Override
    public Connection conn(DBInfo dbInfo)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return conn(dbInfo.getUrl(),dbInfo.getUsername(),
                dbInfo.getPassword(),dbInfo.getType(),dbInfo.getDriverClassName());
    }

    public String selectDriverName(DBType dbType) throws ClassNotFoundException {
        switch (dbType) {
            case MYSQL:
                return driver_mysql;
            case ORACLE:
                return driver_oracle;

        }

        throw new ClassNotFoundException("没有找到该类型的驱动");
    }

    public void setDbInfo(String url, String username, String password, DBType dbType, String driverName){
        dbInfo.setUsername(username);
        dbInfo.setPassword(password);
        dbInfo.setType(dbType);
        dbInfo.setDriverClassName(driverName);
        dbInfo.setUrl(url);
    }

    public DBInfo getDbInfo() throws SQLException {
        if (isReadable())
            return this.dbInfo;
        return null;
    }

    public void setDbInfo(DBInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
