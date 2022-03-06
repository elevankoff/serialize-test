package serializers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface SerializationFactory<T> {
    Serializer<T> createSerializer(String outputFileName) throws FileNotFoundException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    Deserializer<T> createDeserializer(String inputFileName) throws FileNotFoundException, IOException;
}
