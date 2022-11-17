import flatbuf.start.FlatBuffersRunner;
import protobuf.start.ProtobufRunner;
import xml.start.XmlRunner;

import java.io.IOException;

/**
 * 简单测试各序列化协议花费耗时
 */
public class SerializationProtocolSimpleTest {

    public static void main(String[] args) throws IOException {
        ProtobufRunner.runProtobuf();
        FlatBuffersRunner.runFlatBuffers();
        XmlRunner.runningXml();
    }
}
