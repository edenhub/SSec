package normalSec.StrategyImpl;

import normalSec.EncryptStrategy;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public class S_Base64 implements EncryptStrategy {
    @Override
    public String encrypt(String password, String encodeType) throws UnsupportedEncodingException {
        Base64 base64 = new Base64();

        return new String(base64.encode(password.getBytes(encodeType)));
    }
}
