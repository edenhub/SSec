package DIP;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lab on 2014/12/22.
 */
public class ImageWrapper {
    protected String imgPath;
    protected BufferedImage bufferedImage;
    protected int width,height;

    public ImageWrapper(){}

    public ImageWrapper(String imgPath){
        this.imgPath = imgPath;
    }

    public void init() throws IOException {
        bufferedImage = ImageIO.read(new File(imgPath));
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
