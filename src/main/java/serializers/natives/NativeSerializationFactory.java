package serializers.natives;

import serializers.SerializationFactory;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NativeSerializationFactory<T extends Serializable> implements SerializationFactory<T> {
    @Override
    public NativeSerializer<T> createSerializer(String outputFileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        return new NativeSerializer<>(objectOutputStream);
    }

    @Override
    public NativeDeserializer<T> createDeserializer(String inputFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return new NativeDeserializer<>(objectInputStream);
    }
}
