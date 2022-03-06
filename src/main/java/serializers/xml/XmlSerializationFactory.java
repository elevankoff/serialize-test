package serializers.xml;

import fortest.SerializationTestClass;
import serializers.SerializationFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class XmlSerializationFactory<T extends Serializable> implements SerializationFactory<T> {
    @Override
    public XmlSerializer<T> createSerializer(String outputFileName)
            throws FileNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        return new XmlSerializer<>(bufferedOutputStream, getAllowedTypes());
    }

    @Override
    public XmlDeserializer<T> createDeserializer(String inputFileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        return new XmlDeserializer<>(bufferedInputStream, getAllowedTypes());
    }

    private static Class[] getAllowedTypes() {
        return new Class[] { SerializationTestClass.class };
    }
}
