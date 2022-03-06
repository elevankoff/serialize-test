package serializers.natives;

import serializers.Deserializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class NativeDeserializer<T extends Serializable> implements Deserializer<T> {
    private final ObjectInputStream objectInputStream;

    public NativeDeserializer(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public T deserialize() throws IOException, ClassNotFoundException {
        return (T) objectInputStream.readObject();
    }

    @Override
    public void close() throws IOException {
        objectInputStream.close();
    }

    @Override
    public String getName() {
        return SerializeType.NATIVE.name();
    }
}
