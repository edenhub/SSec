package DBPersistence.Sql.persistence;

import DBPersistence.Sql.persistence.data.DataWrapper;

import java.util.List;
import java.util.Properties;

/**
 * Created by lab on 2015/1/27.
 */
public interface Persistence {
    public boolean init(Properties dbPros);
    public boolean isInited();
    public boolean persistenceSQL(List<String> sqls,DataWrapper data);
    public boolean persistenceCode(String code,DataWrapper data);
    public boolean closeRemoteConnection();
}
