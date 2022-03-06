package serializers;

import java.io.IOException;
import java.io.OutputStream;

public interface Serializer<T> {
    void serialize(T obj) throws IOException;
    void flush() throws IOException;
    void close() throws IOException;
    OutputStream getOutputStream();
    String getName();
}
