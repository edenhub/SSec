package normalSec;

/**
 * Created by lab on 2014/11/13.
 */
public abstract class Encryter implements EncryLife{
    private EncryStrategy strategy;

    @Override
    public abstract void beforeEntry(String password);
    @Override
    public String encry(String password) {
        String res = null;
        beforeEntry(password);
        res = strategy.encry(password);
        afterEntry(password);

        return res;
    }

    @Override
    public void updateEncryStrategy(EncryStrategy encryterStrategy) {
        this.strategy = encryterStrategy;
    }

    public EncryStrategy getEncryStrategy(){
        return this.strategy;
    }

    @Override
    public abstract void afterEntry(String password);
}
