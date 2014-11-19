package DBReverse.DBInfo;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lab on 2014/11/19.
 */
public interface DBAuth {
    public boolean isReadable() throws SQLException;
    public Connection conn(String url,String username,String password,DBType dbType,String driverName)
            throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException;
    public Connection conn(String url,String username,String password,DBType dbType)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
    public Connection conn(DBInfo dbInfo)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
