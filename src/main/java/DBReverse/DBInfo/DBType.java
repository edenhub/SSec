package DBReverse.DBInfo;

/**
 * Created by lab on 2014/11/19.
 */
public enum DBType {
    MYSQL("DB_MYSQL"),ORACLE("DB_ORACLE");

    DBType(String value){
        this.value = value;
    }

    public static DBType selectType(String type){
        if ("DB_MYSQL" == type)
            return DBType.MYSQL;
        if ("DB_ORACLE" == type)
            return DBType.ORACLE;

        return null;
    }

    private String value;
}
