package DBReverse;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 表转化为指定格式
 */
public interface TableReverse {

    /**
     * 表结构转化为XML
     * @return
     */
    public String toXML();

    /**
     * 表结构转化为JS
     * @return
     */
    public String toJSArray();
}
