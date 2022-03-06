package serializers.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import serializers.Deserializer;
import serializers.SerializationFactory;
import serializers.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProtobufSerializationFactory<T, TProto extends GeneratedMessageV3> implements SerializationFactory<T> {
    private final ProtobufSerializationInstance<T, TProto> protobufSerializationInstance;

    public ProtobufSerializationFactory(
            ProtobufSerializationInstance<T, TProto> protobufSerializationInstance)
    {
        this.protobufSerializationInstance = protobufSerializationInstance;
    }

    @Override
    public Serializer<T> createSerializer(String outputFileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        return new ProtobufSerializer<>(bufferedOutputStream, protobufSerializationInstance);
    }

    @Override
    public Deserializer<T> createDeserializer(String inputFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return new ProtobufDeserializer<>(bufferedInputStream, protobufSerializationInstance);
    }
}
