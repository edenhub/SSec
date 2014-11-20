package DBReverse.DBInfo;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 用于数据库连接和判断现在是否可读
 */
public interface DBAuth {
    /**
     * 判断数据库现在是否可用
     * @return true可用，flase不可用
     * @throws SQLException
     */
    public boolean isReadable() throws SQLException;

    /**
     * 获得数据库连接
     * @param url 数据库连接符，到数据库名
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类别
     * @param driverName 驱动类名
     * @return Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Connection conn(String url,String username,String password,DBType dbType,String driverName)
            throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException;
    /**
     * 获得数据库连接，使用默认驱动
     * @param url 数据库连接符，到数据库名
     * @param username 用户名
     * @param password 密码
     * @param dbType 数据库类别
     * @return Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public Connection conn(String url,String username,String password,DBType dbType)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    /**
     * 获得数据库连接，使用DBInfo对象
     * @return Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Connection conn(DBInfo dbInfo)
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
