package DBReverse.DBInfo;

/**
 * Created by lab on 2014/11/19.
 */

/**
 * 数据库类型
 */
public enum DBType {
    MYSQL("DB_MYSQL"),ORACLE("DB_ORACLE");

    DBType(String value){
        this.value = value;
    }

    /**
     * 根据字符串返回enum
     * @param type
     * @return
     */
    public static DBType selectType(String type){
        if ("DB_MYSQL" == type)
            return DBType.MYSQL;
        if ("DB_ORACLE" == type)
            return DBType.ORACLE;

        return null;
    }

    private String value;
}
