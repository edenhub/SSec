package DBPersistence.FileCompile;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

/**
 * Created by lab on 2014/12/12.
 */
public class JavaSourceFromString extends SimpleJavaFileObject {
    private String code;

    protected JavaSourceFromString(String name,String code){
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }

    public static void main(String[] args){
        String name = "DBPersistence.FileCompile.JavaSource";
        System.out.println(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension));
    }
}
