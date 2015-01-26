package java.HW1;

import DIP.ImageWrapper;

/**
 * Created by lab on 2014/12/22.
 */
public class Scaling extends ImageWrapper{
    public ScalingSize newScalingSize(int width,int height){
        return this.new ScalingSize(width,height);
    }

    public class ScalingSize{
        private int width,height;

        public ScalingSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
