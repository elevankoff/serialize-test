package serializers.xml;

import com.thoughtworks.xstream.XStream;
import fortest.SerializationTestClass;
import serializers.Serializer;
import serializers.SerializeType;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

public class XmlSerializer<T> implements Serializer<T> {
    private final OutputStream outputStream;
    private final XStream xstream;

    public XmlSerializer(OutputStream outputStream, Class[] allowedTypes) {
        this.outputStream = outputStream;
        this.xstream = new XStream();
        xstream.allowTypes(allowedTypes);
    }

    @Override
    public void serialize(T obj) {
        xstream.toXML(obj, outputStream);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.flush();
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String getName() {
        return SerializeType.XML.name();
    }
}
