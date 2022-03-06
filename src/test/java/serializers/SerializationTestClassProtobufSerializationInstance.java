package serializers;

import fortest.SerializationTestClass;
import proto.serializers.SerializationTestClassOuterClass;
import serializers.protobuf.ProtobufSerializationInstance;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author elevankoff
 */
public class SerializationTestClassProtobufSerializationInstance
        extends ProtobufSerializationInstance<SerializationTestClass, SerializationTestClassOuterClass.SerializationTestClass>
{
    public static final SerializationTestClassProtobufSerializationInstance INSTANCE
            = new SerializationTestClassProtobufSerializationInstance();

    @Override
    public SerializationTestClassOuterClass.SerializationTestClass serialize(SerializationTestClass obj) {
        SerializationTestClassOuterClass.SerializationTestClass.Builder builder
                = SerializationTestClassOuterClass.SerializationTestClass.newBuilder();
        builder.setWords(obj.getWords());
        builder.addAllList(obj.getList());
        builder.putAllMap(obj.getMap());
        builder.setInteger(obj.getInteger());
        builder.setFloatNumber(obj.getFloatNumber());
        return builder.build();
    }

    @Override
    public SerializationTestClass deserialize(InputStream inputStream) throws IOException {
        SerializationTestClassOuterClass.SerializationTestClass proto
                = SerializationTestClassOuterClass.SerializationTestClass.parseFrom(inputStream);
        return new SerializationTestClass(
                proto.getWords(),
                proto.getListList(),
                proto.getMapMap(),
                proto.getInteger(),
                proto.getFloatNumber());
    }
}
