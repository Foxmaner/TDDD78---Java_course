package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class ImgFetcher {
    public ImgFetcher() {
    }

    public BufferedImage fetchImage(final String imgInputPath){
        BufferedImage image = null;

        try{
            URL path = this.getClass().getResource("/resources/" + imgInputPath);

            assert path != null;
            image = ImageIO.read(path);

        }catch(IOException e){
            System.out.println("Error whilst loading image: " + e);
        }

        return image;
    }

}
