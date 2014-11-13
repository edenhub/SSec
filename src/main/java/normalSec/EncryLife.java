package normalSec;

/**
 * Created by lab on 2014/11/13.
 */
public interface EncryLife {
    public void beforeEntry(String password);
    public String encry(String password);
    public void afterEntry(String password);
    public void updateEncryStrategy(EncryStrategy encryterStrategy);
}
