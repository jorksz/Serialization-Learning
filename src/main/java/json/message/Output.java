package json.message;

public class Output {

    private String outputName;

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String title;
    private String id;

    public Output(String outputName, String title, String id) {
        this.outputName = outputName;
        this.title = title;
        this.id = id;
    }
}
