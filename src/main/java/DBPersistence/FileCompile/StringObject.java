package DBPersistence.FileCompile;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

/**
 * Created by adam on 2014/12/9.
 */
public class StringObject extends SimpleJavaFileObject {
    private String contents = null;
    public StringObject(String className, String contents) throws Exception {
        super(URI.create("string:///" + className.replace('.', '/')
                + Kind.SOURCE.extension), Kind.SOURCE);
        this.contents = contents;
    }
    public CharSequence getCharContent(boolean ignoreEncodingErrors)
            throws IOException {
        return contents;
    }
}
