package DBReverse;

/**
 * Created by lab on 2014/11/20.
 */

/**
 * 用于键值对
 * @param <K>
 * @param <V>
 */
public class MapPair<K,V> {
    private K key;
    private V value;

    public MapPair(K key,V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapPair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

      /*
    ================================================================

                        GetterAndSetter

    ================================================================
     */
}
