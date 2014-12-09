package DBPersistence.FileCompile;

import javax.tools.*;
import java.io.File;
import java.util.Arrays;

/**
 * Created by adam on 2014/12/9.
 */
public class Base2 {

    public static void main(String[] args) throws ClassNotFoundException {
        File file = new File("d:\\CalculatorTest.txt");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);

        JavaFileObject fileObject = new MySimple(file);
        Iterable unit = Arrays.asList(fileObject);

        boolean result = compiler.getTask(null,fileManager,null,null,null,unit).call();

        if (result){
            Class clazz = Class.forName("CalculatorTest");

            assert clazz!=null;
        }
    }
}
