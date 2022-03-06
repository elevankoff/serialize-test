package serializers.xml;

import com.thoughtworks.xstream.XStream;
import serializers.Deserializer;
import serializers.SerializeType;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;

public class XmlDeserializer<T> implements Deserializer<T> {
    private final InputStream inputStream;
    private final XStream xstream;

    public XmlDeserializer(InputStream inputStream, Class[] allowedTypes) {
        this.inputStream = inputStream;
        this.xstream = new XStream();
        xstream.allowTypes(allowedTypes);
    }

    @Override
    public T deserialize() {
        return (T) xstream.fromXML(inputStream);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public String getName() {
        return SerializeType.XML.name();
    }
}
