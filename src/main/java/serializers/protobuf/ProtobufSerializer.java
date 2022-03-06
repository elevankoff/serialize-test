package serializers.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import serializers.SerializeType;
import serializers.Serializer;

import java.io.IOException;
import java.io.OutputStream;

public class ProtobufSerializer<T, TProto extends GeneratedMessageV3> implements Serializer<T> {
    private final OutputStream outputStream;
    private final ProtobufSerializationInstance<T, TProto> protobufSerializationInstance;

    public ProtobufSerializer(
            OutputStream outputStream,
            ProtobufSerializationInstance<T, TProto> protobufSerializationInstance)
    {
        this.outputStream = outputStream;
        this.protobufSerializationInstance = protobufSerializationInstance;
    }

    @Override
    public void serialize(T obj) throws IOException {
        protobufSerializationInstance.serialize(obj).writeTo(outputStream);
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
        return SerializeType.PROTOBUF.name();
    }
}
