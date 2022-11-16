package protobuf.start;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.message.ProcessDes;

import java.io.File;

public class ProtobufRunner {

    public static final String FILE_NAME = "process.protobuf";

    public static void runProtobuf() throws InvalidProtocolBufferException {
        // 填充值
        ProcessDes.Process.Builder process = ProcessDes.Process.newBuilder();
        process.setFactory("iobjects_modelingtools");
        process.setNamespace("com.supermap.modelingtools.iterator");
        process.setName("fileiterator");
        process.setTitle("迭代文件");
        process.setId("fileiterator");
        process.setVersion(1.0);
        process.setLoopMode("EACH_MATCH");
        initInput(process);
        initOut(process);
        // 写出文件
        System.out.println("protobuf 开始序列化和反序列化测试");
        long time = System.currentTimeMillis();
        byte[] bytes = process.build().toByteArray();
        FileUtil.writeBytes(bytes, FILE_NAME);

        // 读取文件
        byte[] result = FileUtil.readBytes(FILE_NAME);
        ProcessDes.Process resultProcess = ProcessDes.Process.parseFrom(result);
        System.out.println("protobuf序列化和反序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - time));

        System.out.println("protobuf 持久化文件process.protobuf大小:" + FileUtil.size(new File(FILE_NAME)));
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
       runProtobuf();
    }

    public static void initInput( ProcessDes.Process.Builder process) {
        for (int i = 0; i < 2; i++) {
            ProcessDes.Process.Input.Builder input = new ProcessDes.Process.Input.Builder();
            input.setInputName("recursion");
            input.setValue("true");
            process.addInputs(input);
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
