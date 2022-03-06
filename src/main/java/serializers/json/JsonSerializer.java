package serializers.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.json.JsonMapper;
import serializers.Serializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.OutputStream;

public class JsonSerializer<T> implements Serializer<T> {
    private static final JsonMapper jsonMapper;
    static {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        jsonMapper = new JsonMapper(jsonFactory);
    }

    private final OutputStream outputStream;

    public JsonSerializer(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void serialize(T obj) throws IOException {
        jsonMapper.writeValue(outputStream, obj);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String getName() {
        return SerializeType.JSON.name();
    }
}
