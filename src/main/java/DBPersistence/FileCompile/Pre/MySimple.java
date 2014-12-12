package DBPersistence.FileCompile.Pre;

import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.util.Scanner;

/**
 * Created by adam on 2014/12/9.
 */
public class MySimple extends SimpleJavaFileObject {
    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     *
     * @param uri  the URI for this file object
     * @param kind the kind of this file object
     */
    private File file;

    public MySimple(File file){
        super(file.toURI(),Kind.SOURCE);
        this.file = file;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        InputStream in = new FileInputStream(file);
        Scanner scanner = new Scanner(in);

        String out = "";

        while(scanner.hasNext()){
            out += scanner.nextLine();
        }

//        System.out.println("My : "+out);

        return out;
    }
}
