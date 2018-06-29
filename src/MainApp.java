import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/**
 * Main Class
 * @author Misha Yevtushenko 21CS
 * Code warnings:
 * 1.Classes BinaryButton and BinariGridPane extend respectively javafx.scene.control.Button and
 * javafx.scene.control.GridPane
 */

public class MainApp extends Application{

    private Stage startWindow;
    private int variables=2;
    private int column;
    private Set trueValuesArr = new TreeSet();

    @Override
    public void start(Stage primaryStage) throws Exception {
        startWindow = primaryStage;
        startWindow.setTitle("Boolean function`s minimization by Karno carts");
        startWindow.show();

        GridPane startGridPane = new GridPane();
        Scene startScene = new Scene(startGridPane,325,100);
        startScene.getStylesheets().add("css/style1.css");
        startWindow.setScene(startScene);

        TextField tfH = new TextField();
        tfH.setPromptText("Введіть кількість змінних функції");
        tfH.setPrefSize(200,100);

        TextField trueValuesField = new TextField();
        trueValuesField.setPrefSize(200,100);
        trueValuesField.setPromptText("Внесіть наступне значення");

        Button addValueBtn = new Button("Додати значення");
        addValueBtn.setPrefSize(120,100);

        /**
         * Filling an array with elements for which the function acquires positive values
         */
        addValueBtn.setOnAction(event -> {
//            if(event.equals(KeyCode.ENTER)){
//                trueValuesArr.add(Integer.parseInt(trueValuesField.getText()));
//                trueValuesField.clear();
//            }
            trueValuesArr.add(Integer.parseInt(trueValuesField.getText()));
            trueValuesField.clear();
        });


        Button btnStartMinimization = new Button("Мінімізувати");
        btnStartMinimization.setPrefSize(120,100);
        startGridPane.add(trueValuesField,0,0);
        startGridPane.add(addValueBtn,1,0);
        startGridPane.add(tfH,0,1);
        startGridPane.add(btnStartMinimization,1,1);
        startGridPane.setGridLinesVisible(true);

        btnStartMinimization.setOnAction(e-> {
            if (!tfH.getText().isEmpty()) {
                column = Integer.parseInt(tfH.getText());
                for(int i=0; i< column-1;i++){
                    variables*=2;
                }
            }
            GridPane gridPane = getNumbersTable();
            GridPane binariGridPane = getBinaryNumbersTable();
            BinaryGridPane xPane = getXsNumbersTable();
            BinaryGridPane trueValuesPane = getValuesNumbersTable();
            StringBuilder sb = new StringBuilder();
            sb.append("Функція набуває '1' при значеннях: ");
            Iterator iterator = trueValuesArr.iterator();
            while (iterator.hasNext()){
                sb.append(iterator.next());
                sb.append(", ");
            }

            HBox hBoxTop = new HBox();
            hBoxTop.setSpacing(10);
            hBoxTop.getChildren().addAll(gridPane,binariGridPane,xPane,trueValuesPane);
            VBox vBox = new VBox();
            vBox.getChildren().addAll(hBoxTop,new Label(sb.toString()),new Label(getDDNF(trueValuesArr)),new Label(minimization(xPane)));
            Scene scene = new Scene(vBox);
            startWindow.close();
            Stage mainStage = new Stage();
            mainStage.setTitle("Boolean function`s minimization by Karno carts");
            scene.getStylesheets().add("css/style1.css");
            mainStage.setScene(scene);
            mainStage.show();
            //System.out.println(minimization(trueValuesPane));

            //System.out.println(minimization(xPane));
            BinaryButton b1 = new BinaryButton(9,9,0,true);
            BinaryButton b2 = new BinaryButton(9,8,8,true);
            //System.out.println("reduced: "+toReduce(b1,b2));
        });
    }


    /**
     * All Buttons are clickable
     * @return GridPane of Buttons with decimal numbers for visual representation of Karno`s Carts Array
     */
    public GridPane getNumbersTable(){
        GridPane gridPane = new GridPane();
        int j=0;
        int c=1;
        for (int i=0; i< variables;i++){
            if (i!=0 && (i%4)==0){
                c=1;
                j++;
                Button button = new BinaryButton(j,c,i,false);
                button.setText(""+i);
                button.setPrefSize(50,30);
                button.setStyle("-fx-background-color: #ccff99; ");
                button.setOnAction(event -> {
                    button.setStyle("-fx-background-color: #ff1100; ");
                });
                gridPane.add(button,c,j);
                c++;
            }
            else  if (c==3){
                Button button = new BinaryButton(j,c,i,false);
                button.setText(""+i);
                button.setPrefSize(50,30);
                button.setStyle("-fx-background-color: #ccff99; ");
                button.setOnAction(event -> {
                    button.setStyle("-fx-background-color: #ff1100; ");
                });
                gridPane.add(button,4,j);
                c++;
            }
            else  if (c==4){
                Button button = new BinaryButton(j,c,i,false);
                button.setText(""+i);
                button.setPrefSize(50,30);
                button.setStyle("-fx-background-color: #ccff99; ");
                button.setOnAction(event -> {
                    button.setStyle("-fx-background-color: #ff1100; ");
                });
                gridPane.add(button,3,j);
                c++;
            }
            else{
                Button button = new BinaryButton(j,c,i,false);
                button.setText(""+i);
                button.setPrefSize(50,30);
                button.setStyle("-fx-background-color: #ccff99; ");
                button.setOnAction(event -> {
                    button.setStyle("-fx-background-color: #ff1100; ");
                });
                gridPane.add(button,c,j);
                c++;
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


    /**
     * @return Object of BinaryGridPane class, which extends javafx.scene.control.GridPane and contains
     * array of booleans, which meets array of function`s true values
     */
    public BinaryGridPane getValuesNumbersTable(){
        BinaryGridPane gridPane = new BinaryGridPane();
        int j=0;
        int c=1;
        Boolean [][] booleansArr= new Boolean[variables/4][4];
        for (int i=0; i< variables;i++){
            if (i!=0 && (i%4)==0){
                if(ifBelongToArray(i,trueValuesArr)){
                    c=1;
                    j++;
                    booleansArr[j][c-1]=true;
                    BinaryButton button = new BinaryButton(j,c-1,i,true);
                    button.setText("1");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ccff99; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }
                else {
                    c=1;
                    j++;
                    booleansArr[j][c-1]=false;
                    BinaryButton button = new BinaryButton(j,c-1,i,false);
                    button.setText("0");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }

            }
            else  if (c==3){
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][3]=true;
                    BinaryButton button = new BinaryButton(j,3,i,true);
                    button.setText("1");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ccff99; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,4,j);
                    c++;
                }
                else {
                    booleansArr[j][3]=false;
                    BinaryButton button = new BinaryButton(j,3,i,false);
                    button.setText("0");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,4,j);
                    c++;
                }

            }
            else  if (c==4){
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][2]=true;
                    BinaryButton button = new BinaryButton(j,2,i,true);
                    button.setText("1");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ccff99; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,3,j);
                    c++;
                }
                else {
                    booleansArr[j][2]=false;
                    BinaryButton button = new BinaryButton(j,2,i,false);
                    button.setText("0");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,3,j);
                    c++;
                }
            }
            else{
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][c-1]=true;
                    BinaryButton button = new BinaryButton(j,c-1,i,true);
                    button.setText("1");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ccff99; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }
                else {
                    booleansArr[j][c-1]=false;
                    BinaryButton button = new BinaryButton(j,c-1,i,false);
                    button.setText("0");
                    button.setPrefSize(50,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }

            }
        }
        gridPane.setTrueValueArrayOfFunction(booleansArr);
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


    /**
     * @return GridPane of Buttons with Xs numbers for visual representation of Karno`s Carts Array
     */
    public BinaryGridPane getXsNumbersTable(){
        BinaryGridPane gridPane = new BinaryGridPane();
        int j=0;
        int c=1;
        String [][] stringsArr = new String[variables/4][4];
        Boolean [][] booleansArr= new Boolean[variables/4][4];
        int prefWidth = column*25;
        for (int i=0; i< variables;i++){
            if (i!=0 && (i%4)==0) {
                if (ifBelongToArray(i, trueValuesArr)) {
                    c = 1;
                    j++;
                    booleansArr[j][c - 1] = true;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][c-1]=button.getText();
                    button.setPrefSize(prefWidth, 30);
                    button.setStyle("-fx-background-color: #bb7fff; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button, c, j);
                    c++;
                } else {
                    c = 1;
                    j++;
                    booleansArr[j][c - 1] = false;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][c-1]=button.getText();
                    button.setPrefSize(prefWidth, 30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button, c, j);
                    c++;
                }
            }
            else  if (c==3){
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][3]=true;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][3]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #bb7fff; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,4,j);
                    c++;
                }
                else {
                    booleansArr[j][3]=false;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][3]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,4,j);
                    c++;
                }
            }
            else  if (c==4){
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][2]=true;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][2]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #bb7fff; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,3,j);
                    c++;
                }
                else {
                    booleansArr[j][2]=false;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][2]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,3,j);
                    c++;
                }
            }
            else{
                if(ifBelongToArray(i,trueValuesArr)){
                    booleansArr[j][c-1]=true;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][c-1]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #bb7fff; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }
                else {
                    booleansArr[j][c-1]=false;
                    Button button = new Button("" + getXsBinaryNumder(i, getBinaryLenght(variables)));
                    stringsArr[j][c-1]=button.getText();
                    button.setPrefSize(prefWidth,30);
                    button.setStyle("-fx-background-color: #ffe61b; ");
                    button.setOnAction(event -> {
                        button.setStyle("-fx-background-color: #ff1100; ");
                    });
                    gridPane.add(button,c,j);
                    c++;
                }

            }
        }
        gridPane.setBinaryValues(stringsArr);
        gridPane.setTrueValueArrayOfFunction(booleansArr);
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


    /**
     * @return GridPane of Buttons with binary numbers for visual representation of Karno`s Carts Array
     */
    public GridPane getBinaryNumbersTable(){
        GridPane gridPane = new GridPane();
        int j=0;
        int c=1;
        int prefWidth = column*18;
        for (int i=0; i< variables;i++){
            if (i!=0 && (i%4)==0){
                c=1;
                j++;
                Button button = new Button(""+getBinaryNumder(i,getBinaryLenght(variables)+1));
                button.setPrefSize(prefWidth,30);
                button.setStyle("-fx-background-color: #2de1ff; ");
                gridPane.add(button,c,j);
                c++;
            }
            else  if (c==3){
                Button button = new Button(""+getBinaryNumder(i,getBinaryLenght(variables)+1));
                button.setPrefSize(prefWidth,30);
                button.setStyle("-fx-background-color: #2de1ff; ");
                gridPane.add(button,4,j);
                c++;
            }
            else  if (c==4){
                Button button = new Button(""+getBinaryNumder(i,getBinaryLenght(variables)+1));
                button.setPrefSize(prefWidth,30);
                button.setStyle("-fx-background-color: #2de1ff; ");
                gridPane.add(button,3,j);
                c++;
            }
            else{
                Button button = new Button(""+getBinaryNumder(i,getBinaryLenght(variables)+1));
                button.setPrefSize(prefWidth,30);
                button.setStyle("-fx-background-color: #2de1ff; ");
                gridPane.add(button,c,j);
                c++;
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    /**
     * @param num number in the decimal system
     * @return number in the binary system
     */
    public String getXsBinaryNumder(int num, int lenght){

        int counter = lenght;
        StringBuilder stringBuilder = new StringBuilder();
        while(num!=0 ) {
            int b = num%2;
            if(b==0){
                stringBuilder.append(counter);
                counter--;
                stringBuilder.append("Ӿ");

            }
            else {
                stringBuilder.append(counter);
                counter--;
                stringBuilder.append("X");

            }
            num = num/2;
        }

        if(stringBuilder.length()<lenght*2){
            while (stringBuilder.length()!=(lenght*2)){
                stringBuilder.append(counter);
                counter--;
                stringBuilder.append("Ӿ");

            }
        }
        String str = new String(stringBuilder);
        str = new StringBuffer(str).reverse().toString();
        str=str.replace('0','₀');
        str=str.replace('1','₁');
        str=str.replace('2','₂');
        str=str.replace('3','₃');
        str=str.replace('4','₄');
        str=str.replace('5','₅');
        str=str.replace('6','₆');
        str=str.replace('7','₇');
        str=str.replace('8','₈');
        str=str.replace('9','₉');
        return str;
    }


    /**
     * @param num number in the decimal system
     * @return number in the binary system
     */
    public String getBinaryNumder(int num, int lenght){

        StringBuilder stringBuilder = new StringBuilder();
        while(num!=0 ) {
            int b = num%2;
            stringBuilder.append(b);
            num = num/2;
        }

        if(stringBuilder.length()<lenght){
            while (stringBuilder.length()!=lenght-1){
                stringBuilder.append("0");
            }
        }
        String str = new String(stringBuilder);
        str = new StringBuffer(str).reverse().toString();
        return str;
    }

    /**
     * @param num number in the decimal system
     * @return length of number in the binary system
     */
    public int getBinaryLenght(int num){

        StringBuilder stringBuilder = new StringBuilder();
        while(num!=0 ) {
            int b = num%2;
            stringBuilder.append(b);
            num = num/2;
        }

        String str = new String(stringBuilder);
        str = new StringBuffer(str).reverse().toString();
        return str.length()-1;
    }
    /**
     * @param a number in the decimal system
     * @param arr array of  some numbers
     * @return true if input number belong to input array, false if not belong
     */
    public Boolean ifBelongToArray (int a, Set arr){
        Iterator iterator = trueValuesArr.iterator();
        while (iterator.hasNext()){
            int num = Integer.parseInt(iterator.next().toString());
            if(a==num){
                return true;
            }
        }
        return false;
    }


    /**
     * @param s1 first button for reducing
     * @param s2 second button for reducing
     * @return String of two reduced button`s binary values, which consists of the same values in their binary code
     */
    public String toReduce (String s1,String s2){
        StringBuilder stringBuilder = new StringBuilder();
        char ch1[]=s1.toCharArray();
        char ch2[]=s2.toCharArray();
        for (int i=0; i<ch1.length;i++){
            if(ch1[i]==ch2[i]){
                stringBuilder.append(ch1[i]);
            }
        }
        return numCleaning(stringBuilder.toString());
    }

    /**
     * @param s1 first button for reducing
     * @param s2 second button for reducing
     * @param s3 third button for reducing
     * @param s4 fourth button for reducing
     * @return String of four reduced button`s binary values, which consists of the same values in their binary code
     */
    public String quadraReduce (String s1,String s2,String s3, String s4){
        StringBuilder stringBuilder = new StringBuilder();
        char ch1[]=s1.toCharArray();
        char ch2[]=s2.toCharArray();
        char ch3[]=s3.toCharArray();
        char ch4[]=s4.toCharArray();
        for (int i=0; i<ch1.length;i++){
            if(ch1[i]==ch2[i] && ch1[i]==ch3[i] && ch1[i]==ch4[i] && ch2[i]==ch3[i] && ch2[i]==ch4[i] && ch3[i]==ch4[i]){
                stringBuilder.append(ch1[i]);
            }
        }
        return numCleaning(stringBuilder.toString());
    }

    public Boolean isANumber (char ch){
        String s = new String("0123456789");
        char chArr []= s.toCharArray();
        for (int i =0;i<chArr.length-1;i++){
            if(ch==chArr[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * This method finds all buttons for reducing and reduce them
     * @param binaryGridPane  of buttons for reduce
     * @return string of reduced binary function
     * @warnings find out if qudra reduce makes values false
     */
    public String minimization (BinaryGridPane binaryGridPane){

        Boolean[][] bArr = binaryGridPane.getTrueValueArrayOfFunction();
        Boolean[][] boolArr2 = binaryGridPane.getTrueValueArrayOfFunction();
        System.out.println(showArray(boolArr2));
        String [][] sArr = binaryGridPane.getBinaryValues();
        System.out.println(showStringArray(sArr));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("F=");
        for (int i=0;i<bArr.length-1;i++) {
            for (int j = 0; j < bArr[i].length-1; j++) {
                //quadra reduce
                if(bArr[i][j]==true && bArr[i][j+1]==true && bArr[i+1][j]==true && bArr[i+1][j+1]==true){
                    stringBuilder.append("("+i+","+j+" - "+i+","+(j+1)+" - "+(i+1)+","+j+" - "+(i+1)+","+(j+1)+")");
                    stringBuilder.append(quadraReduce(sArr[i][j],sArr[i][j+1],sArr[i+1][j], sArr[i+1][j+1]));
                    stringBuilder.append("∪");
//                    bArr[i][j]=false;
//                    bArr[i][j+1]=false;
//                    bArr[i+1][j+1]=false;
//                    bArr[i+1][j]=false;
                    boolArr2[i][j]=false;
                    boolArr2[i][j+1]=false;
                    boolArr2[i+1][j+1]=false;
                    boolArr2[i+1][j]=false;
                }
                else if (bArr[i][0]==true && bArr[i][3]==true && bArr[i+1][0]==true && bArr[i+1][3]==true){
                    stringBuilder.append("("+i+","+"0"+" - "+i+","+"3"+" - "+(i+1)+","+"0"+" - "+(i+1)+","+"3"+")");
                    stringBuilder.append(quadraReduce(sArr[i][0],sArr[i][3],sArr[i+1][0], sArr[i+1][3]));
                    stringBuilder.append("∪");
//                    bArr[i][0]=false;
//                    bArr[i][3]=false;
//                    bArr[i+1][0]=false;
//                    bArr[i+1][3]=false;
                    boolArr2[i][0]=false;
                    boolArr2[i][3]=false;
                    boolArr2[i+1][3]=false;
                    boolArr2[i+1][0]=false;
                }
            }
        }

        //HReduce
        for (int i=0;i<bArr.length;i++){
            if(bArr[i][0]==true && bArr[i][3]==true){
                stringBuilder.append("("+i+",0"+"-"+i+",3"+")");
                stringBuilder.append(toReduce(sArr[i][0],sArr[i][3]));
                stringBuilder.append("∪");
                boolArr2[i][0]=false;
                boolArr2[i][3]=false;
                bArr[i][0]=true;
                bArr[i][3]=true;
            }
            for (int j=0;j<bArr[i].length-1;j++){
                if(bArr[i][j]==true && bArr[i][j+1]==true){
                    stringBuilder.append("("+i+","+j+"-"+i+","+(j+1)+")");
                    stringBuilder.append(toReduce(sArr[i][j],sArr[i][j+1]));
                    stringBuilder.append("∪");
                    boolArr2[i][j]=false;
                    boolArr2[i][j+1]=false;
                    bArr[i][j]=true;
                    bArr[i][j+1]=true;
                    System.out.println(showArray(bArr));
                }
            }
        }

        //Vreduce
        for (int i=0;i<bArr.length-1;i++) {
            for (int j = 0; j < bArr[i].length; j++) {
                if(bArr[i][j]==true && bArr[i+1][j]==true){
                    stringBuilder.append("("+i+","+j+"-"+(i+1)+","+j+")");
                    stringBuilder.append(toReduce(sArr[i][j],sArr[i+1][j]));
                    stringBuilder.append("∪");
                    boolArr2[i][j]=false;
                    boolArr2[i+1][j]=false;
                }

            }
        }
        //not reduced adding
        for (int i=0;i<boolArr2.length;i++) {
            for (int j = 0; j < boolArr2[i].length; j++) {
                if(boolArr2[i][j]==true){
                    stringBuilder.append(sArr[i][j]);
                    stringBuilder.append("∪");
                }
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }


    /**
     * @param arr boolean two-dimensional array
     * @return String-table of input two-dimensional array
     */
    public String showArray(Boolean[][] arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                stringBuilder.append(arr[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    /**
     * @param arr string two-dimensional array
     * @return String-table of input two-dimensional array
     */
    public String showStringArray(String[][] arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                stringBuilder.append(arr[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }


    /**
     * This method used in numCleaning
     * @param ch character
     * @param chars array of characters
     * @return true if character  belong to array of characters, and false if not belong
     */
    public Boolean ifBelongToCharArray (char ch, char[]chars){
        for (int i=0;i<chars.length;i++){
            if(ch==chars[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * This method clean all the superfluous nums in the String
     * @param s String for cleaning
     * @return string without superfluous nums
     */
    public String numCleaning (String s){
        StringBuffer sb= new StringBuffer(s);
        String numbers = new String("₀₁₂₃₄₅₆₇₈₉");
        String x = new String("ӾX");
        char [] n1 = numbers.toCharArray();
        char [] x2 = x.toCharArray();
        char [] main = s.toCharArray();
        if (ifBelongToCharArray(main[0],n1)){
            sb.delete(0,1);
        }
        for (int i=1;i<main.length-1;i++){
            if (ifBelongToCharArray(main[i],n1).equals(ifBelongToCharArray(main[i+1],n1))){
                sb.append("");
                sb.delete(i+1,i+2);
            }
        }
        s=sb.toString();
        return s;
    }

    /**
     * @param arr set of values for which function has true answer
     * @return string of disjunctive normal form for function
     */
    public String getDDNF (Set arr){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Досконала Диз'юнктивна нормальна форма (ДДНФ):");
        stringBuilder.append('\n');
        stringBuilder.append("F= ");
        for (int i=0; i< variables;i++){
            if(ifBelongToArray(i,trueValuesArr)){
                stringBuilder.append(getXsBinaryNumder(i,getBinaryLenght(variables)));
                stringBuilder.append("∪");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
}
