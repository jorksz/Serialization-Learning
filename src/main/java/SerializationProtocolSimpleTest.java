import flatbuf.start.FlatBuffersRunner;
import json.start.JsonRunner;
import protobuf.start.ProtobufRunner;
import xml.start.XmlRunner;

import java.io.IOException;

/**
 * 简单测试各序列化协议花费耗时
 */
public class SerializationProtocolSimpleTest {

    public static void main(String[] args) throws IOException {
        ProtobufRunner.runProtobuf();
        System.out.println(" ");
        FlatBuffersRunner.runFlatBuffers();
        System.out.println(" ");
        XmlRunner.runningXml();
        System.out.println(" ");
        JsonRunner.jsonRunner();
    }
}
