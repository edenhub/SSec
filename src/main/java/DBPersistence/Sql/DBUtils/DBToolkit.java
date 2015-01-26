package DBPersistence.Sql.DBUtils;

import java.sql.*;

/**
 * Created by adam on 2014/12/5.
 */
public class DBToolkit {

    public static void closeDBConnection(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void closeStatment(Statement statement) throws SQLException {
        if (statement!=null)
            statement.close();
    }

    public static void closePreparedStatment(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement!=null)
            preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet!=null)
            resultSet.close();
    }

    public static boolean isConnectionClosed(Connection connection){
        if(connection!=null)
            try {
                return connection.isClosed();
            } catch (SQLException e) {
//                e.printStackTrace();
            }
        return true;
    }

    public static boolean isStatmentClosed(Statement statement){
        if(statement!=null)
            try {
                return statement.isClosed();
            } catch (SQLException e) {
//                e.printStackTrace();
            }
        return false;
    }

    public static boolean isPreparedStatementClosed(PreparedStatement preparedStatement){
        if(preparedStatement!=null)
            try {
                return preparedStatement.isClosed();
            } catch (SQLException e) {
//                e.printStackTrace();
            }
        return false;
    }
}