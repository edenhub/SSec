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
        int index = 0;
        StringBuffer sb = new StringBuffer();
        System.out.println(sql);
        while(matcher.find()){
//            System.out.println(matcher.start());
//            System.out.println(matcher.end());
//            System.out.println(sql.substring(matcher.start()+1,matcher.end()-1));
//            System.out.println("----");
            int start = matcher.start();
            int end = matcher.end();
            sb.append(sql.substring(index, index + start - 1));
            sb.append("-value-");
            index = end+1;
        }
        System.out.println(sb.toString());

    }

    @Test
    public void test02(){
        final String sql = pros.getProperty("test.itemIdRegrex2");
        final String regrex = "\\{\\d+\\}";
        Pattern pattern = Pattern.compile(regrex);
        Matcher matcher = pattern.matcher(sql);
        StringBuffer sb = new StringBuffer();
        System.out.println(sql);
        int index = 0;
        while(matcher.find()){
//            System.out.println(matcher.start());
//            System.out.println(matcher.end());
            int itemId = Integer.parseInt(sql.substring(matcher.start()+1,matcher.end()-1));
            System.out.println(sql.substring(matcher.start()+1,matcher.end()-1));
            System.out.println("----");
            int start = matcher.start();
            int end = matcher.end();
            if (end < sql.length()){
                sb.append(sql.substring(index,start));
                sb.append("-value-");
            }
//            else{
////                sb.append("-value-");
//                sb.append(sql.substring(index));
//            }
            index = end;
        }
        sb.append(sql.substring(index));
        System.out.println(sb.toString());
//        while(matcher.find()){
//            System.out.println(sql.substring(matcher.start()+1,matcher.end()-1));
//        }
    }
}
