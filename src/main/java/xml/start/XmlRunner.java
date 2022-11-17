package xml.start;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;
import com.supermap.sps.core.serializer.WorkflowKeys;
import com.supermap.sps.core.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xml.message.Input;
import xml.message.Output;
import xml.message.Process;

import java.util.ArrayList;
import java.util.List;

public class XmlRunner {

    private static final String FILE_NAME = "process.xml";

    public static void main(String[] args) {
        runningXml();
    }

    private static void initProcess(Process process) {
        process.setFactory("iobjects_modelingtools");
        process.setNamespace("com.supermap.modelingtools.iterator");
        process.setName("fileiterator");
        process.setTitle("迭代文件");
        process.setId("fileiterator");
        process.setVersion("1.0");
        process.setLoopmode("EACH_MATCH");
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


    public static void runningXml() {
        System.out.println("xml序列化和反序列化测试");
        long startSerialization = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        Process process = new Process();
        initProcess(process);
        Document emptyDoc = XmlUtils.getEmptyDocument();
        Element processE = emptyDoc.createElement("process");
        XmlUtils.appendChild(processE, WorkflowKeys.FACTORY, process.getFactory());
        XmlUtils.appendChild(processE, WorkflowKeys.NAMESPACE, process.getNamespace());
        XmlUtils.appendChild(processE, WorkflowKeys.NAME, process.getName());
        XmlUtils.appendChild(processE, WorkflowKeys.TITLE, process.getTitle());
        XmlUtils.appendChild(processE, WorkflowKeys.ID, process.getId());
        XmlUtils.appendChild(processE, WorkflowKeys.VERSION, process.getVersion());
        XmlUtils.appendChild(processE, WorkflowKeys.LOOP_MODE, process.getLoopmode());
        Element inputsE = createInputsElements(emptyDoc, process);
        processE.appendChild(inputsE);

        Element outputsE = createOutputsElements(emptyDoc, process);
        processE.appendChild(outputsE);
        emptyDoc.appendChild(processE);
        XmlUtil.toFile(emptyDoc, FILE_NAME);
        System.out.println("xml序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - startSerialization));

        // 反序列化
        long deserializeCostTime = System.currentTimeMillis();
        Document document = XmlUtils.getDocument("C:\\code\\Serialization-Learning\\target\\classes\\process.xml");
        Process deserializeProcess = new Process();
        Element processElements = XmlUtils.getChildElementNodeByName(document, WorkflowKeys.PROCESS);
        deserializeProcess.setFactory(XmlUtils.getContent(processElements, "factory"));
        deserializeProcess.setNamespace(XmlUtils.getContent(processElements, "namespace"));
        deserializeProcess.setName(XmlUtils.getContent(processElements, "name"));
        deserializeProcess.setTitle(XmlUtils.getContent(processElements, "title"));
        deserializeProcess.setId(XmlUtils.getContent(processElements, "id"));
        deserializeProcess.setVersion(XmlUtils.getContent(processElements, "version"));
        deserializeProcess.setLoopmode(XmlUtils.getContent(processElements, "loopMode"));
        initInput(processElements, deserializeProcess);
        initOutput(processElements, deserializeProcess);
        System.out.println("xml反序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - deserializeCostTime));
        System.out.println(deserializeProcess.factory);
        System.out.println("xml序列化和反序列化花费时间:" + DateUtil.formatBetween(System.currentTimeMillis() - time));
    }

    public static Element createOutputsElements(Document emptyDoc, Process process) {
        Element outputsE = emptyDoc.createElement("outputs");
        List<Output> outputs = process.getOutputs();
        for (Output output : outputs) {
            Element outputE = emptyDoc.createElement("output");
            XmlUtils.appendChild(outputE, "id", output.getId());
            XmlUtils.appendChild(outputE, "outputame", output.getOutputname());
            XmlUtils.appendChild(outputE, "title", output.getTitle());
            outputsE.appendChild(outputE);
        }
        return outputsE;
    }

    public static void initInput(Element document, Process process) {
        Element inputsE = XmlUtils.getChildElementNodeByName(document, WorkflowKeys.INPUTS);
        Element[] elements =  XmlUtils.getChildElementNodesByName(inputsE, WorkflowKeys.INPUT);
        List<Input> inputs = new ArrayList<>();
        for (Element element : elements) {
            Input input = new Input();
            input.setInputName(XmlUtils.getContent(element, "inputName"));
            input.setValue(XmlUtils.getContent(element, "value"));
            inputs.add(input);
        }
        process.setInputs(inputs);
    }

    public static void initOutput(Element document, Process process) {
        Element outputE = XmlUtils.getChildElementNodeByName(document, WorkflowKeys.OUTPUTS);
        Element[] elements =  XmlUtils.getChildElementNodesByName(outputE, WorkflowKeys.OUTPUT);
        List<Output> outputs = new ArrayList<>();
        for (Element element : elements) {
            Output output = new Output();
            output.setOutputname(XmlUtils.getContent(element, "outputName"));
            output.setId(XmlUtils.getContent(element, "id"));
            output.setTitle(XmlUtils.getContent(element, "title"));
            outputs.add(output);
        }
        process.setOutputs(outputs);
    }

    public static Element createInputsElements( Document emptyDoc, Process process) {
        Element inputsElement = emptyDoc.createElement(WorkflowKeys.INPUTS);
        List<Input> inputs = process.getInputs();
        for (Input input : inputs) {
            Element inputElement = emptyDoc.createElement(WorkflowKeys.INPUT);
            XmlUtils.appendChild(inputElement, "inputname", input.getInputName());
            XmlUtils.appendChild(inputElement, "value", input.getValue());
            inputsElement.appendChild(inputElement);
        }
        return inputsElement;
    }
}
