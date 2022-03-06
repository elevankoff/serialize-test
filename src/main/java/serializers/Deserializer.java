package serializers;

import java.io.IOException;
import java.io.InputStream;

public interface Deserializer<T> {
    T deserialize() throws IOException, ClassNotFoundException;
    void close() throws IOException;
    String getName();
}
