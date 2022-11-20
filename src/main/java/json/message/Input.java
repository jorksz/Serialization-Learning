package json.message;

public class Input {
    private String inputName;

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

    private String value;

    public Input(String inputName, String value) {
        this.inputName = inputName;
        this.value = value;
    }
}
