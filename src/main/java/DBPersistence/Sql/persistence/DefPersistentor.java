package DBPersistence.Sql.persistence;

import DBPersistence.Sql.DBUtils.DBManager;
import DBPersistence.Sql.DBUtils.DBToolkit;
import DBPersistence.Sql.DBUtils.StatementTemplate;
import DBPersistence.Sql.persistence.data.DataWrapper;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lab on 2015/1/27.
 */
public class DefPersistentor implements Persistence {
    private Connection connection;
    private DBManager dbManager;
    private boolean isInited;

    private final String regrex = "\\{\\d+\\}";

    private static Logger logger = Logger.getLogger(DefPersistentor.class);

    public DefPersistentor(){
        dbManager = new DBManager();
        isInited = false;
    }

    @Override
    public boolean init(Properties dbPros) {
        boolean res = dbManager.conn(dbPros);
        if (res){
            this.connection = dbManager.getConnection();
        }

        isInited = res;
        return res;
    }

    @Override
    public boolean isInited() {
        return isInited;
    }

    @Override
    public boolean persistenceSQL(final List<String> sqls,final DataWrapper data) {
        assert connection!= null;
        StatementTemplate<Boolean> statementTemplate = new StatementTemplate<Boolean>(connection);
        Boolean resO = statementTemplate.executeStmSQL(statementTemplate.new StatementExecutor() {
            @Override
            public Boolean execute(Statement stm) throws SQLException {
                Pattern pattern = Pattern.compile(regrex);
                for(String sql : sqls){
                    Matcher matcher = pattern.matcher(sql);
                    StringBuffer sb = new StringBuffer();
                    int index = 0;
                    while(matcher.find()){
                        int start = matcher.start();
                        int end = matcher.end();
                        int itemId = Integer.parseInt(sql.substring(start + 1, end - 1));
                        if (end < sql.length()){
                            sb.append(sql.substring(index, start));
                            sb.append(data.getItem(itemId).itemPreSentate());
                        }
                        index = end;
                    }
                    sb.append(sql.substring(index));
                    System.out.println(sb.toString());
                    stm.addBatch(sb.toString());
                }

                int resCnt = stm.executeBatch().length;
                Boolean res = resCnt == sqls.size() ? true : false;
                return res;
            }
        });

        return resO != null ? true : false;
    }

    /**
     * 使用Velocity替换比较安全
     * @param code
     * @param data
     * @return
     */
    @Override
    public boolean persistenceCode(String code, DataWrapper data) {
        throw new NotImplementedException();
    }

    @Override
    public boolean closeRemoteConnection() {
        if (!DBToolkit.isConnectionClosed(connection))
            try {
                DBToolkit.closeDBConnection(connection);
            } catch (SQLException e) {
                logger.warn("无法关闭远程数据库连接",e);
                return false;
            }
        return true;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
