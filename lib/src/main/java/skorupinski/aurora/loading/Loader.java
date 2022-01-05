package skorupinski.aurora.loading;

import java.io.IOException;
import java.io.InputStream;

public interface Loader {

    boolean canLoad(Class<?> type);
    
    Object load(InputStream is) throws IOException;
}
