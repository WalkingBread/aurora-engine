package skorupinski.aurora.loading;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    
    private static final List<Loader> loaders = new ArrayList<>() {{
        add(new ImageLoader());
        add(new AudioLoader());
    }};

    private static Loader getLoaderFor(Class<?> type) {
        for (Loader loader : loaders) {
            if (loader.canLoad(type))
                return loader;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(Class<T> type, InputStream inputStream) throws IOException {
        Loader loader = getLoaderFor(type);
        Object resource = loader.load(inputStream);
        return (T) resource;
    }

    public static <T> T load(Class<T> type, Path path) throws IOException {
        String filename = "/" + path;
        InputStream is = ResourceManager.class.getResourceAsStream(filename);

        if (is == null) {
            throw new IOException("Resource not found: " + filename);
        }

        return load(type, is);
    }
}
