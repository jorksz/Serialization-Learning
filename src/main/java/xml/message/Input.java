package xml.message;

public class Input {

    public Input() {

    }

    public Input(String inputName, String value) {
        this.inputName = inputName;
        this.value = value;
    }

    public String inputName;

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String value;
}
