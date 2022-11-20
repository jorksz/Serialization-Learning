package json.start;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import json.message.Input;
import json.message.Output;
import json.message.Process;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonRunner {
    private static final String FILE_NAME = "process.json";

    public static void main(String[] args) {
        jsonRunner();
    }

    public static void jsonRunner() {
        Process process = new Process();
        initProcess(process);
        long time = System.currentTimeMillis();
        String jsonString = JSON.toJSONString(process);
        System.out.println("fastJson 序列化时间: " + DateUtil.formatBetween(System.currentTimeMillis() - time));
        long writeJsonTime = System.currentTimeMillis();
        FileUtil.writeBytes(jsonString.getBytes(StandardCharsets.UTF_8), FILE_NAME);
        System.out.println("fastJson 持久化时间: " + DateUtil.formatBetween(System.currentTimeMillis() - writeJsonTime));

        long deserializeCostTime = System.currentTimeMillis();
        String processJsonString = FileUtil.readString(FILE_NAME, StandardCharsets.UTF_8);
        Process newProcess = JSON.parseObject(processJsonString, Process.class);
        System.out.println("fastJson 反序列化时间: " + DateUtil.formatBetween(System.currentTimeMillis() - deserializeCostTime));
        System.out.println("fastJson 序列化&持久化&反序列化时间: " + DateUtil.formatBetween(System.currentTimeMillis() - time));


    }

    private static void initProcess(Process process) {
        process.setFactory("iobjects_modelingtools");
        process.setNamespace("com.supermap.modelingtools.iterator");
        process.setName("fileiterator");
        process.setTitle("迭代文件");
        process.setId("fileiterator");
        process.setVersion(1.0);
        process.setLoopMode("EACH_MATCH");
        List<Input> inputs = new ArrayList<>();
        Input input1 = new Input("recursion","true");
        inputs.add(input1);
        Input input2 = new Input("pattern","");
        inputs.add(input2);
        process.setInputs(inputs);
        List<Output> outputs = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Output output = new Output("iteratorResult","文件路径集合", "fileiterator.iteratorResult");
            outputs.add(output);
        }
        process.setOutputs(outputs);
    }
}
