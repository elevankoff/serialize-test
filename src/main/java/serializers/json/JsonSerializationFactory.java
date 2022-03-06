package serializers.json;

import serializers.SerializationFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JsonSerializationFactory<T> implements SerializationFactory<T> {
    private final Class<T> clazz;

    public JsonSerializationFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public JsonSerializer<T> createSerializer(String outputFileName) throws FileNotFoundException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            return new JsonSerializer<>(bufferedOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public JsonDeserializer<T> createDeserializer(String inputFileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return new JsonDeserializer<>(bufferedInputStream, clazz);
    }
}
