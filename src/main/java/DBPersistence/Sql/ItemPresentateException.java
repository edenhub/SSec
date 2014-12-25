package DBPersistence.Sql;

/**
 * Created by lab on 2014/12/25.
 */
public class ItemPresentateException extends RuntimeException {

    public ItemPresentateException(){
        super();
    }

    public ItemPresentateException(String msg){
        super(msg);
    }

    public ItemPresentateException(Throwable throwable){
        super(throwable);
    }
}
