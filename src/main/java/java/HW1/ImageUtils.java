package java.HW1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lab on 2014/12/22.
 */
public class ImageUtils {

    public static int[][] readImageRGBMatrix(String imgPath) throws IOException {
        File img = new File(imgPath);
        if (!img.exists())
            throw new RuntimeException("文件不存在，请检查路径");
        BufferedImage bufferedImage = ImageIO.read(img);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[][] imgMatrix = new int[width][height];

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                int rgb = bufferedImage.getRGB(x,y);
                imgMatrix[x][y] = rgb;
            }
        }

        return imgMatrix;
    }
}
