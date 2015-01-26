package TestRegrex;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lab on 2015/1/26.
 */
public class TestSqlRegrex {

    private Properties pros;

    {
        pros = new Properties();
        try {
            pros.load(TestSqlRegrex.class.getResourceAsStream("/sqls.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01(){
        final String sql = pros.getProperty("test.regrex");
//        System.out.println(sql);
        String regrex = "\\{(\\w+\\.){3}+\\d+\\}";

        Pattern pattern = Pattern.compile(regrex);
        Matcher matcher = pattern.matcher(sql);
        while(matcher.find()){
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(sql.substring(matcher.start()+1,matcher.end()-1));
            System.out.println("----");
        }

    }

    @Test
    public void test02(){
        final String sql = pros.getProperty("test.itemIdRegrex");
        final String regrex = "\\{\\d+\\}";
        Pattern pattern = Pattern.compile(regrex);
        Matcher matcher = pattern.matcher(sql);
        while(matcher.find()){
            System.out.println(sql.substring(matcher.start()+1,matcher.end()-1));
        }
    }
}
