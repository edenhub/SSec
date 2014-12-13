package TestDBPersistence;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lab on 2014/12/13.
 */
public class TestSql {

    @Test
    public void test02(){
        String rerigion = "insert into t_users(username,password,address) values(\"{0}\",\"{1}\",\"{2}\");    insert into t_goods(goodname,price,belongto) values(\"{0}\",{1},\"{2}\");";
        String[] sqls = rerigion.trim().split("(^insert)(.*)(;$)");
        System.out.println(sqls.length);
        Pattern pattern = Pattern.compile("((^insert)(.*)(;$))");
        Matcher matcher = pattern.matcher(rerigion);
        Scanner scanner = new Scanner(new BufferedReader(new StringReader(rerigion)));
        while(matcher.find())
            System.out.println(matcher.group());
        while (scanner.hasNext(Pattern.compile("(^insert)(.*)(;$)"))){
            System.out.println(scanner.next());
        }

        System.out.println("===================");
    }
}
