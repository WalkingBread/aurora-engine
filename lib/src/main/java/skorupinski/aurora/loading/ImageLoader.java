package skorupinski.aurora.loading;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader implements Loader {

    @Override
    public boolean canLoad(Class<?> type) {
        return type.isAssignableFrom(BufferedImage.class);
    }

    @Override
    public Object load(InputStream is) throws IOException {
        return ImageIO.read(is);
    }
    
}
