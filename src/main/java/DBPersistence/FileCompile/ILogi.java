package DBPersistence.FileCompile;

import java.sql.Connection;

/**
 * Created by lab on 2014/12/12.
 */
public interface ILogi {

    public void doHold(Connection connection,DataBean dataBean);
}
