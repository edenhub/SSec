package DIP.HW1;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by lab on 2014/12/22.
 */
public class TestImageUtils {

    @Test
    public void testReadImageRGBMatrix() throws IOException {
        String path = "C:\\Users\\lab\\Desktop\\dip\\60.png";

        int[][] imgMatrix = ImageUtils.readImageRGBMatrix(path);

        int width = imgMatrix.length;

        for (int x=0;x<width;x++)
            System.out.println(Arrays.toString(imgMatrix[x]));
    }
}
