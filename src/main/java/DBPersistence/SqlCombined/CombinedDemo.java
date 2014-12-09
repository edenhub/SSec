package DBPersistence.SqlCombined;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by adam on 2014/12/9.
 */
public class CombinedDemo {
    public static final String addUserSqlDemo = "insert into t_users(username,password,address) values(\"{0}\",\"{1}\",\"{2}\")";
    public static final String addGoodSqlDemo = "insert into t_goods(goodname,price,belongto) values(\"{0}\",{1},\"{2}\")";


    public static final String selectUsersGoodSqlDemo = "select from t_users u ,t_goods g where u.username = \"{0}\" g.belongto = u.username";

    private Logger logger = Logger.getLogger(CombinedDemo.class.getName());

//    数据库唯一实例
    private Connection connection = null;
    private static CombinedDemo instace = null;

    private CombinedDemo(){

    }

    public static CombinedDemo getInstance(){
        if (instace == null)
            instace = new CombinedDemo();

        return instace;
    }

//    util工具类，生成数据库的唯一实例
    public Connection getConnection(String url,String username,String password,String driverName) throws SQLException, ClassNotFoundException {
        assert connection == null;

        Class.forName(driverName);
        return DriverManager.getConnection(url,username,password);
    }

//    对外提供的服务模板，不对外抛异常，只告诉外部是否完成
    public boolean executeBatchTemplate(final String[] exeSql,final Object[][] values){
        assert exeSql.length == values.length;

        assert connection!=null;

        Statement stm = null;
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            stm = connection.createStatement();

            result = execute(stm,exeSql,values);

            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.WARNING,"操作失误",e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return false;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }

        try {
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

//    隐藏的内部实现，只执行逻辑，不处理打开，关闭等操作，遇到异常就抛出，让上层处理
    private boolean execute(Statement stm,final String[] exeSql,final Object[][] values) throws Exception{
        /**
         * ====================================================
         * 下面部分可以做出模板，如果只是单纯的执行逻辑，只要定义好了位置和值的关系
         */
        int len = exeSql.length;

        for(int i=0;i<len;i++){
            String sql = MessageFormat.format(exeSql[i], values[i]);
            logger.log(Level.INFO,"exeSql "+i+" : "+sql);

            stm.addBatch(sql);
//            stm.executeBatch();
//            stm.clearBatch();
        }


        /**
         * ======================================================
         */

        /**
         * =======================================================
         * 下面这部分也可以做成模板，如果需要
         */
        int[] result = stm.executeBatch();
        if (result.length != len)
            return false;
        return true;

        /**
         *  =======================================================
         */
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
