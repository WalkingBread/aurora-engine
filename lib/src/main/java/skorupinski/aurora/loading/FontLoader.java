package skorupinski.aurora.loading;

import java.io.IOException;
import java.io.InputStream;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class FontLoader implements Loader {

    @Override
    public boolean canLoad(Class<?> type) {
        return type.isAssignableFrom(Font.class);
    }

    @Override
    public Object load(InputStream is) throws IOException {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }
    
}
