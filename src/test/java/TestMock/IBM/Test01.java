package java.TestMock.IBM;

import org.easymock.EasyMock;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Created by adam on 2014/11/27.
 */
public class Test01 {

    @Test
    public void test01(){
        ResultSet resultSetMock = EasyMock.createMock(ResultSet.class);
    }
}
