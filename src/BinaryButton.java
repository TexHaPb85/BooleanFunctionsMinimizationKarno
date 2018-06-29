
import javafx.scene.control.Button;

import java.util.*;

public class BinaryButton extends Button{
    private int i;
    private int j;
    private int decimalNumber;
    private Boolean isTrueValue;
    private String queue = new String();


    public BinaryButton(int i, int j, int decimalNumber, Boolean bull) {
        this.i = i;
        this.j = j;
        this.decimalNumber = decimalNumber;
        this.isTrueValue=bull;
        StringBuilder stringBuilder = new StringBuilder();
        while(decimalNumber!=0 ) {
            int b = decimalNumber%2;
            stringBuilder.append(b);
            decimalNumber = decimalNumber/2;
        }
        //String str = new String(stringBuilder);
        this.queue= new String(stringBuilder);//new StringBuffer(str).reverse().toString();
        //this.queue = new LinkedList<>(Arrays.asList(str.split("(?<=\\G.{1})")));

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getDecimalNumber() {
        return decimalNumber;
    }

    public void setDecimalNumber(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public Boolean isTrue() { return isTrueValue; }

    public void setTrueValue(Boolean trueValue) {
        isTrueValue = trueValue;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    //public void setBinariQueue(String str){
//        this.queue = new LinkedList<>(Arrays.asList(str.split("(?<=\\G.{1})")));
//    }

}
