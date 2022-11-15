package flatbuf.main;

import com.google.flatbuffers.FlatBufferBuilder;
import flatbuf.message.Process;
import protobuf.message.ProcessDes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlatBuffersTest {
    private static final String FILE_NAME = "process.model";

    public static void main(String[] args) throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int root = Process.createProcess(builder,
                builder.createString("iobjects_modelingtools"),
                builder.createString("com.supermap.modelingtools.iterator"),
                builder.createString("fileiterator"),
                builder.createString("fileiterator"),
                1.0,
                builder.createString("EACH_MATCH"),
                builder.createString("fileiterator"),
                builder.createString("fileiterator"),
                builder.createString("迭代文件"));
        builder.finish(root);
        // [2] 序列化 Process 对象为字节码
        byte[] buffer = builder.sizedByteArray();
        // [3] 保存字节码到文件，也可以进行网络传输
        FileOutputStream out = new FileOutputStream(FILE_NAME);
        out.write(buffer);
        out.close();
    }

    public static void initInput(FlatBufferBuilder builder) {
        String str = "recursion";
        for (int i = 0; i < 2; i++) {
            builder.createString("recursion");
            builder.createString("true");
        }
    }

    public static void initOut( ProcessDes.Process.Builder process) {
        for (int i = 0; i < 1; i++) {
            ProcessDes.Process.Output.Builder output = new ProcessDes.Process.Output.Builder();
            output.setInputName("iteratorResult");
            output.setId("fileiterator.iteratorResult");
            output.setTitle("文件路径集合");
            process.addOutputs(output);
        }
    }
}
