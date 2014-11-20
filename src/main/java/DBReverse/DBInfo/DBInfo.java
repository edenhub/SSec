package DBReverse.DBInfo;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 数据库的连接信息
 */
public class DBInfo {
    private DBType type;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

     /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    public DBType getType() {
        return type;
    }

    public void setType(DBType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
