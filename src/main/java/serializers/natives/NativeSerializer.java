package serializers.natives;

import serializers.Serializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class NativeSerializer<T extends Serializable> implements Serializer<T> {
    private final ObjectOutputStream objectOutputStream;

    public NativeSerializer(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void serialize(T obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }

    @Override
    public void flush() throws IOException {
        objectOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        objectOutputStream.close();
    }

    @Override
    public OutputStream getOutputStream() {
        return objectOutputStream;
    }

    @Override
    public String getName() {
        return SerializeType.NATIVE.name();
    }
}
