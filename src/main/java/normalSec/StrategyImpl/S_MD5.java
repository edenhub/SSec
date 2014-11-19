package NormalSec.StrategyImpl;

import NormalSec.EncryptStrategy;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public class S_MD5 implements EncryptStrategy {
    @Override
    public String encrypt(String password, String encodeType) throws UnsupportedEncodingException {
        return new String(DigestUtils.md5Hex(password.getBytes(encodeType)));
    }
}
