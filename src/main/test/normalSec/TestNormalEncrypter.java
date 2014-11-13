package normalSec;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * Created by lab on 2014/11/13.
 */
public class TestNormalEncrypter {

    @Test
    public void test01() throws UnsupportedEncodingException {
        String[] pathConfig = new String[]{"applicationContext.xml","applicationBeans.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(pathConfig);

        EncryptManager manager = (EncryptManager) applicationContext.getBean("encrypterManager");
        System.out.println(manager.encrypt("helloworld"));
    }
}
