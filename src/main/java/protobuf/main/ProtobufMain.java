package protobuf.main;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.message.ProcessDes;

import java.nio.charset.StandardCharsets;

public class ProtobufMain {

    public static void main(String[] args) throws InvalidProtocolBufferException {
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
        byte[] bytes = process.build().toByteArray();
        FileUtil.writeBytes(bytes, "process.protobuf");

        // 读取文件
        long time = System.currentTimeMillis();
        byte[] result = FileUtil.readBytes("process.protobuf");
        String str = FileUtil.readString("process.protobuf", StandardCharsets.UTF_8);
        System.out.println(str);
        ProcessDes.Process resultProcess = ProcessDes.Process.parseFrom(result);
        System.out.println(DateUtil.formatBetween(System.currentTimeMillis() - time));
        System.out.println(resultProcess.getFactory());
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
