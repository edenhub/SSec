package normalSec;

/**
 * Created by lab on 2014/11/13.
 */
public class EncryManager {
    private Encryter encryter;
    private EncryStrategy encryStrategy;

    public String encry(String password){
        encryter.updateEncryStrategy(encryStrategy);
        return encryter.encry(password);
    }

    public Encryter getEncryter() {
        return encryter;
    }

    public void setEncryter(Encryter encryter) {
        this.encryter = encryter;
    }

    public EncryStrategy getEncryStrategy() {
        return encryStrategy;
    }

    public void setEncryStrategy(EncryStrategy encryStrategy) {
        this.encryStrategy = encryStrategy;
    }
}
