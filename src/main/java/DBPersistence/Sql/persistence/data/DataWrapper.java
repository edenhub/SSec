package DBPersistence.Sql.persistence.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lab on 2015/1/27.
 */
public class DataWrapper {
    private Map<Integer , Sql_Item> iterms;

    public DataWrapper(){
        iterms = new HashMap<Integer, Sql_Item>();
    }

    public DataWrapper(int capability){
        iterms = new HashMap<Integer, Sql_Item>(capability);
    }

    public Map<Integer, Sql_Item> getIterms() {
        return iterms;
    }

    public Sql_Item getItem(int itemId){
        return iterms.get(itemId);
    }

    public DataWrapper addItem(Sql_Item iterm){
        iterms.put(iterm.getItemId(),iterm);
        return this;
    }

    public DataWrapper removeItem(Sql_Item sqlItem){
        removeItem(sqlItem.getItemId());
        return this;
    }

    public DataWrapper removeItem(int itemId){
        iterms.remove(itemId);
        return this;
    }

    @Override
    public String toString() {
        return "DataWrapper{" +
                "iterms=" + iterms +
                '}';
    }
}
