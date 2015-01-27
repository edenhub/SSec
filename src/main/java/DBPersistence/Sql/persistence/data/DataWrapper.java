package DBPersistence.Sql.persistence.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lab on 2015/1/27.
 */
public class DataWrapper {
    private Map<Integer , Item> iterms;

    public DataWrapper(){
        iterms = new HashMap<Integer, Item>();
    }

    public DataWrapper(int capability){
        iterms = new HashMap<Integer, Item>(capability);
    }

    public Map<Integer, Item> getIterms() {
        return iterms;
    }

    public Item getItem(int itemId){
        return iterms.get(itemId);
    }

    public DataWrapper addItem(Item iterm){
        iterms.put(iterm.getItemId(),iterm);
        return this;
    }

    public DataWrapper removeItem(Item item){
        removeItem(item.getItemId());
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
