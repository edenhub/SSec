package DBPersistence.FileCompile;

import DBPersistence.FileCompile.ncompiler.CharSequenceCompiler;
import DBPersistence.FileCompile.ncompiler.CharSequenceCompilerException;
import com.mysql.jdbc.NotUpdatable;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by lab on 2014/12/12.
 */
public class CompileAction {

    public boolean compileObjectFromString(String name,String code){
        JavaFileObject stringObject = new JavaSourceFromString(name,code);
        Iterable files = Arrays.asList(stringObject);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null,fileManager,null,null,null,files
        );

        return compilationTask.call();
    }

    public String clazzTemplate(String clazzName,String coreAction,String... imports){
        String packagz = "package DBPersistence.FileCompile;\n";
        String front   = "import java.sql.Connection;\n" +
                         "public class "+ clazzName+ " implements ILogi{\n" +
                         "\n" +
                         "    @Override\n" +
                         "    public void doHold(Connection connection, DataBean dataBean) {\n";
        String reer =    "    }\n" +
                         "}";

        StringBuffer clazz = new StringBuffer();

        clazz.append(packagz);
        for(String impor : imports){
            clazz.append(impor);
        }
        clazz.append(front).append(coreAction).append(reer);

        return clazz.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, CharSequenceCompilerException, IllegalAccessException, InstantiationException {
        test03();
    }

    public static void test03() throws CharSequenceCompilerException, IllegalAccessException, InstantiationException {
        CompileAction compileAction = new CompileAction();
        CharSequenceCompiler<ILogi> compiler =
                new CharSequenceCompiler<ILogi>(compileAction.getClass().getClassLoader(), null);
        String action1 = "package DBPersistence.FileCompile;import java.sql.Connection;public class UserId_1 implements ILogi{    @Override    public void doHold(Connection connection, DataBean dataBean) {        System.out.println();    }}\n" +
                "\t";
        Class<ILogi> compiledLogi = compiler.compile("DBPersistence.FileCompile.UserId_1",action1,null,new Class<?>[]{ILogi.class});
        ILogi logi = compiledLogi.newInstance();
        logi.doHold(null,null);
    }



    public static void test02() throws CharSequenceCompilerException, IllegalAccessException, InstantiationException {
        CompileAction compileAction = new CompileAction();
        CharSequenceCompiler<ILogi> compiler =
                new CharSequenceCompiler<ILogi>(compileAction.getClass().getClassLoader(), null);
        String action1 = compileAction.clazzTemplate("ActionTemplate2","System.out.println(\"Hello world>>>>>>>>\");");
        Class<ILogi> compiledLogi = compiler.compile("DBPersistence.FileCompile.ActionTemplate2",action1,null,new Class<?>[]{ILogi.class});
        ILogi logi = compiledLogi.newInstance();
        logi.doHold(null,null);
    }

    public static void test01() throws ClassNotFoundException {
        CompileAction compileAction = new CompileAction();
        String action1 = compileAction.clazzTemplate("ActionTemplate2","System.out.println(\"Hello world\");");
//        System.out.println(action1);
        boolean result = compileAction.compileObjectFromString("DBPersistence.FileCompile.ActionTemplate2",action1);
        System.out.println(result);

//        System.load("d:\\ActionTemplate2.class");
        if (result){
            Class clazz = Class.forName("d:\\DBPersistence.FileCompile.ActionTemplate2");
            assert clazz!=null;
        }
    }
}
