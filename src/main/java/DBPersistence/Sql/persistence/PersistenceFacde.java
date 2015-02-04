package DBPersistence.Sql.persistence;

import DBPersistence.Sql.persistence.data.DataWrapper;

import java.util.List;
import java.util.Properties;

/**
 * Created by lab on 2015/1/27.
 */
public class PersistenceFacde {

    private Persistence persistence;
    private DataWrapper data;

    public PersistenceFacde(){
        persistence = new DefPersistentor();
        data = new DataWrapper();
    }

    public boolean persistenceSQL(List<String> sqls){
        boolean res =  persistence.persistenceSQL(sqls,data);
        persistence.closeRemoteConnection();
        return res;
    }

    public boolean persistenceCode(String code){
        boolean res = persistence.persistenceCode(code,data);
        persistence.closeRemoteConnection();
        return res;
    }

    public void init(String driverName,String Url,String username,String password){
        if (!persistence.isInited()){
            Properties properties = new Properties();
            properties.put("db.driverName",driverName);
            properties.put("db.Url",Url);
            properties.put("db.username",username);
            properties.put("db.password",password);

            persistence.init(properties);
        }
    }

    public void init(Properties properties){
        if (!persistence.isInited())
            persistence.init(properties);
    }


    public void setData(DataWrapper data) {
        this.data = data;
    }
}
