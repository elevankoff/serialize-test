package serializers.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import serializers.Deserializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.InputStream;

public class ProtobufDeserializer<T, TProto extends GeneratedMessageV3> implements Deserializer<T> {
    private final InputStream inputStream;
    private final ProtobufSerializationInstance<T, TProto> protobufSerializationInstance;

    public ProtobufDeserializer(
            InputStream inputStream,
            ProtobufSerializationInstance<T, TProto> protobufSerializationInstance)
    {
        this.inputStream = inputStream;
        this.protobufSerializationInstance = protobufSerializationInstance;
    }

    @Override
    public T deserialize() throws IOException {
        return protobufSerializationInstance.deserialize(inputStream);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public String getName() {
        return SerializeType.PROTOBUF.name();
    }
}
