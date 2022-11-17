package xml.message;

public class Output {

    public Output() {

    }

    public Output(String outputname, String title, String id) {
        this.id = id;
        this.outputname = outputname;
        this.title = title;
    }

    public String getOutputname() {
        return outputname;
    }

    public void setOutputname(String outputname) {
        this.outputname = outputname;
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

    public String outputname;
    public String title;
    public String id;
}