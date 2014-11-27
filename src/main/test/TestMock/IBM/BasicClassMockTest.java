package TestMock.IBM;

/**
 * Created by adam on 2014/11/27.
 */
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Example of how to use <code>org.easymock.classextension.EasyMock</code>
 *
 * @author Henri Tremblay
 */
public class BasicClassMockTest extends EasyMockSupport {

    /**
     * Our nice class that is allowed to print
     */
    public static class Document {

        private final Printer printer;

        private String content;

        public Document(final Printer printer) {
            this.printer = printer;
        }

        public String getContent() {
            return content;
        }

        public void setContent(final String content) {
            this.content = content;
        }

        public void print() {
            printer.print(content);
        }
    }

    /**
     * The terrible 3rd party class that is not an interface but that we really
     * want to mock.
     */
    public static abstract class Printer {
        public abstract void print(String toPrint);
    }

    private Printer printer;

    private Document document;

    @Before
    public void setUp() {
        printer = createMock(Printer.class);
        document = new Document(printer);
    }

    @After
    public void tearDown() {
        printer = null;
        document = null;
    }

    @Test
    public void testPrintContent() {
        printer.print("Hello world");
        replayAll();

        document.setContent("Hello world");
        document.print();

        verifyAll(); // make sure Printer.print was called
    }

    @Test
    public void testPrintEmptyContent() {
        printer.print("");
        replayAll();

        document.setContent("");
        document.print();

        verifyAll(); // make sure Printer.print was called
    }
}
