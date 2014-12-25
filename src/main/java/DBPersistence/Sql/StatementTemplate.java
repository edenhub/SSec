package DBPersistence.Sql;

import DBPersistence.Sql.DBUtils.DBToolkit;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by adam on 2014/12/5.
 */

/**
 * 数据库执行模板操作，对Statement和PreparedStatement提供模板
 * 具体的执行操作由对应的抽象类实现
 * @param <R> 模板执行语句的返回值
 */
public class StatementTemplate<R> {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private static Logger logger = Logger.getLogger(StatementTemplate.class);

    public StatementTemplate(Connection connection){
        this.connection = connection;
    }

    public R executeStmSQL(StatementExecutor statmentExecutor) {
        R result = null;

        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            statement = connection.createStatement();
            result = statmentExecutor.execute(statement);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            logger.error("无法提交数据库操作", e);
            try {
                connection.rollback();
                DBToolkit.closeStatment(statement);
                return null;
            } catch (SQLException e1) {
                logger.error("无法回滚", e);
                return null;
            }
        }
        return result;
    }

    public R executePreparedSQL(PreparedStatementExecutor preparedExecutor,String exeSQL) {
        R result = null;
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            preparedStatement = connection.prepareStatement(exeSQL);
            result = preparedExecutor.execute(preparedStatement, exeSQL);
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("无法提交数据库操作", e);
            try {
                connection.rollback();
                DBToolkit.closePreparedStatment(preparedStatement);
                return null;
            } catch (SQLException e1) {
                logger.error("无法回滚", e);
                return null;
            }
        }

        return result;

    }

    public abstract class StatementExecutor {
        public abstract R execute(Statement stm) throws SQLException;
    }

    public abstract class PreparedStatementExecutor {
        public abstract R execute(PreparedStatement preStatement, String exeSQL) throws SQLException;
    }
}