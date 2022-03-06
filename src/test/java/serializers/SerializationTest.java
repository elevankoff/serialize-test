package serializers;

import common.MillisFormatUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fortest.SerializationTestClass;
import proto.serializers.SerializationTestClassOuterClass;
import serializers.json.JsonSerializationFactory;
import serializers.natives.NativeSerializationFactory;
import serializers.protobuf.ProtobufSerializationFactory;
import serializers.xml.XmlSerializationFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SerializationTest {
    private static final String TEMP_FILE_NAME = "temp.xml";
    private static final int LOOPS = 50000;
    private static final int STRUCTURES_SIZE = 100;

    private static final SerializationTestClass TEST_OBJECT = new SerializationTestClass(
            """
            Lorem ipsum dolor sit amet, consectetur adipiscing
             elit. Mauris adipiscing adipiscing placerat.
             Vestibulum augue augue,
             pellentesque quis sollicitudin id, adipiscing.""",
            new ArrayList<>() {{
               for (int i = 0; i < STRUCTURES_SIZE; i++) {
                   add(Integer.toString(i));
               }
            }},
            new HashMap<>() {{
                for (int i = 0; i < STRUCTURES_SIZE; i++) {
                    put(Integer.toString(i), 5);
                }
            }},
            100,
            100.123456f);

    @Test
    public void nativeSerializeTest() {
        NativeSerializationFactory<SerializationTestClass> nativeSerializationFactory = new NativeSerializationFactory<>();
        serializeTest(nativeSerializationFactory, TEST_OBJECT);
    }

    @Test
    public void xmlSerializeTest() {
        XmlSerializationFactory<SerializationTestClass> xmlSerializationFactory = new XmlSerializationFactory<>();
        serializeTest(xmlSerializationFactory, TEST_OBJECT);
    }

    @Test
    public void jsonSerializeTest() {
        JsonSerializationFactory<SerializationTestClass> jsonSerializationFactory
                = new JsonSerializationFactory<>(SerializationTestClass.class);
        serializeTest(jsonSerializationFactory, TEST_OBJECT);
    }

    @Test
    public void protobufSerializeTest() {
        ProtobufSerializationFactory<SerializationTestClass, SerializationTestClassOuterClass.SerializationTestClass>
                protobufSerializationFactory = new ProtobufSerializationFactory<>(
                        SerializationTestClassProtobufSerializationInstance.INSTANCE);
        serializeTest(protobufSerializationFactory, TEST_OBJECT);
    }

    @Test
    public void nativeSerializationPerformanceTest() throws IOException {
        NativeSerializationFactory<SerializationTestClass> nativeSerializationFactory = new NativeSerializationFactory<>();
        testSerializationPerformance(nativeSerializationFactory, TEST_OBJECT, LOOPS);
    }

    @Test
    public void xmlSerializationPerformanceTest() throws IOException {
        XmlSerializationFactory<SerializationTestClass> xmlSerializationFactory = new XmlSerializationFactory<>();
        testSerializationPerformance(xmlSerializationFactory, TEST_OBJECT, LOOPS);
    }

    @Test
    public void jsonSerializationPerformanceTest() throws IOException {
        JsonSerializationFactory<SerializationTestClass> jsonSerializationFactory
                = new JsonSerializationFactory<>(SerializationTestClass.class);
        testSerializationPerformance(jsonSerializationFactory, TEST_OBJECT, LOOPS);
    }

    @Test
    public void protobufSerializationPerformanceTest() throws IOException {
        ProtobufSerializationFactory<SerializationTestClass, SerializationTestClassOuterClass.SerializationTestClass>
                protobufSerializationFactory = new ProtobufSerializationFactory<>(
                        SerializationTestClassProtobufSerializationInstance.INSTANCE);
        testSerializationPerformance(protobufSerializationFactory, TEST_OBJECT, LOOPS);
    }

    private <T> void serializeTest(SerializationFactory<T> serializationFactory, T testObj) {
        try {
            Serializer<T> serializer = serializationFactory.createSerializer(TEMP_FILE_NAME);
            serializer.serialize(testObj);
            serializer.close();
            File file = new File(TEMP_FILE_NAME);
            System.out.printf("Result file size for %s = %d bytes\n", serializer.getName(), file.length());
            Deserializer<T> deserializer = serializationFactory.createDeserializer(TEMP_FILE_NAME);
            T deserializedObject = deserializer.deserialize();
            deserializer.close();
            Assertions.assertEquals(testObj, deserializedObject);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void testSerializationPerformance(
            SerializationFactory<T> serializationFactory,
            T testObj,
            int number) throws IOException
    {
        Serializer<T> serializer;
        Deserializer<T> deserializer;
        try {
            serializer = serializationFactory.createSerializer(TEMP_FILE_NAME);
            deserializer = serializationFactory.createDeserializer(TEMP_FILE_NAME);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.printf("[Start encoding for %s]\n", serializer.getName());
        long totalSerializationTime = 0;
        long totalDeserializationTime = 0;
        for (int i = 0; i < number; i++) {
            long startSerializationTime = System.currentTimeMillis();
            Assertions.assertDoesNotThrow(() -> serializer.serialize(testObj));
            totalSerializationTime += System.currentTimeMillis() - startSerializationTime;
            long startDeserializationTime = System.currentTimeMillis();
            Assertions.assertDoesNotThrow(deserializer::deserialize);
            totalDeserializationTime += System.currentTimeMillis() - startDeserializationTime;
        }
        serializer.close();
        System.out.printf("[Total serialization time %s]: %s\n", serializer.getName(), MillisFormatUtils.getFormattedMillis(totalSerializationTime));
        System.out.printf("[Total deserilization time %s]: %s\n", serializer.getName(), MillisFormatUtils.getFormattedMillis(totalDeserializationTime));
    }
}
