package flatbuf.start;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.google.flatbuffers.FlatBufferBuilder;
import flatbuf.message.Input;
import flatbuf.message.Output;
import flatbuf.message.Process;

import java.io.*;
import java.nio.ByteBuffer;

public class FlatBuffersRunner {
    private static final String FILE_NAME = "process.flatbuf";

    public static void runFlatBuffers() throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int root = Process.createProcess(builder,
                builder.createString("iobjects_modelingtools"),
                builder.createString("com.supermap.modelingtools.iterator"),
                builder.createString("fileiterator"),
                builder.createString("fileiterator"),
                1.0,
                builder.createString("EACH_MATCH"),
                builder.createVectorOfTables(initOut(builder)),
                builder.createVectorOfTables(initInput(builder)),
                builder.createString("迭代文件"));
        builder.finish(root);
        System.out.println("flatbuf 开始序列化和反序列化测试");
        long time = System.currentTimeMillis();
        // [2] 序列化 Process 对象为字节码
        byte[] buffer = builder.sizedByteArray();
        System.out.println("flatbuf 序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - time));
        long writeFile = System.currentTimeMillis();
        FileUtil.writeBytes(buffer, FILE_NAME);
        System.out.println("flatbuf 字节持久化耗时:" + DateUtil.formatBetween(System.currentTimeMillis() - writeFile));
        // [3] 保存字节码到文件，也可以进行网络传输
        FileUtil.writeBytes(buffer, FILE_NAME);
        /*FileOutputStream out = new FileOutputStream(FILE_NAME);
        out.write(buffer);
        out.close();*/


       /* File file = new File(FILE_NAME);
        RandomAccessFile f = new RandomAccessFile(file, "r");
        byte[] data = new byte[(int)f.length()];
        f.readFully(data);
        f.close();*/
        long deserializationTime = System.currentTimeMillis();
        byte[] data = FileUtil.readBytes(FILE_NAME);
        ByteBuffer bb = ByteBuffer.wrap(data);
        Process.getRootAsProcess(bb);
        System.out.println("flatbuf 反序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - deserializationTime));
        System.out.println("flatbuf 序列化&持久化&反序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - time));
    }

    public static void main(String[] args) throws IOException {
        runFlatBuffers();
    }

    public static int[] initInput(FlatBufferBuilder builder) {
        int len = 2;
        int[] times = new int[len];
        times[0] = Input.createInput(builder, builder.createString("recursion"), builder.createString("true"));
        times[1] = Input.createInput(builder, builder.createString("pattern"), builder.createString(""));
        return times;
    }

    public static int[] initOut(FlatBufferBuilder builder) {
        int len = 1;
        int[] times = new int[len];
        for (int i = 0; i < len; i++) {
            times[i] = Output.createOutput(builder,
                    builder.createString("iteratorResult"),
                    builder.createString("文件路径集合"),
                    builder.createString("fileiterator.iteratorResult"));
        }
        return times;
    }
}
