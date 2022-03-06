package serializers.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.json.JsonMapper;
import serializers.Deserializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.InputStream;

public class JsonDeserializer<T> implements Deserializer<T> {
    private static final JsonMapper jsonMapper = new JsonMapper();

    private final InputStream inputStream;
    private final Class<T> clazz;

    public JsonDeserializer(InputStream inputStream, Class<T> clazz) {
        this.inputStream = inputStream;
        this.clazz = clazz;
        jsonMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }

    @Override
    public T deserialize() throws IOException {
        return jsonMapper.readValue(inputStream, clazz);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public String getName() {
        return SerializeType.JSON.name();
    }
}
