package serializers.protobuf;

import com.google.protobuf.GeneratedMessageV3;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author elevankoff
 */
public abstract class ProtobufSerializationInstance<T, TProto extends GeneratedMessageV3> {
    public abstract TProto serialize(T obj);
    public abstract T deserialize(InputStream inputStream) throws IOException;
}
