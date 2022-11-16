import flatbuf.start.FlatBuffersRunner;
import protobuf.start.ProtobufRunner;

import java.io.IOException;

/**
 * 相同一份 schema 数据，看看 protobuf 和 flatbuf 序列化耗时各自是多少
 */
public class protobufVsFlatbuf {

    public static void main(String[] args) throws IOException {
        ProtobufRunner.runProtobuf();
        FlatBuffersRunner.runFlatBuffers();
    }
}
