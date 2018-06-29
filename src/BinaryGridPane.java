import javafx.scene.layout.GridPane;

public class BinaryGridPane extends GridPane {
    private Boolean[][] trueValueArrayOfFunction;
    private String [][] binaryValues;

    public BinaryGridPane(Boolean[][] trueValueArrayOfFunction) {
        this.trueValueArrayOfFunction = trueValueArrayOfFunction;
    }

    public BinaryGridPane(){

    }

    public Boolean[][] getTrueValueArrayOfFunction() {
        return trueValueArrayOfFunction;
    }

    public void setTrueValueArrayOfFunction(Boolean[][] trueValueArrayOfFunction) {
        this.trueValueArrayOfFunction = trueValueArrayOfFunction;
    }

    public String[][] getBinaryValues() {
        return binaryValues;
    }

    public void setBinaryValues(String[][] binaryValues) {
        this.binaryValues = binaryValues;
    }
}
