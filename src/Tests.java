import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Tests {
    public static String getXsBinaryNumder(int num, int lenght){

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

    public static String toReduce (String s1, String s2){
        StringBuilder stringBuilder = new StringBuilder();
        char ch1[]=s1.toCharArray();
        char ch2[]=s2.toCharArray();
        for (int i=0; i<ch1.length;i++){
            if(ch1[i]==ch2[i]){
                stringBuilder.append(ch1[i]);
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int i=5;
        int u=7;
        System.out.println(getXsBinaryNumder(i,3));
        System.out.println(getXsBinaryNumder(u,3));
        System.out.println(toReduce(getXsBinaryNumder(i,3),getXsBinaryNumder(u,3)));
//        while (i!=0) {
//            String s = getXsBinaryNumder(6, 3);
//            System.out.println(s);
//            i--;
//        }
//        String stringMain= "adsggfdsgsfd";
//        Queue<String>arrayMain = new LinkedList<>(Arrays.asList(stringMain.split("(?<=\\G.{1})")));
//        System.out.println(arrayMain.size());
//        Iterator<String> iterator = arrayMain.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        //BinaryButton binaryButton = new BinaryButton(1,2,32,true);
        //BinaryButton bb = new BinaryButton(1,1,29,true);
        //BinaryButton bb1 = new BinaryButton(1,2,28,true);
//        Iterator<String> iterator = bb.getQueue().iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        //MainApp mainApp = new MainApp();

        //mainApp.toReduce();
//        int decimalNumber1 = 4;
//        int decimalNumber2 = 5;
//
//        StringBuilder stringBuilder1 = new StringBuilder();
//        while(decimalNumber1!=0 ) {
//            int b = decimalNumber1%2;
//            stringBuilder1.append(b);
//            decimalNumber1 = decimalNumber1/2;
//        }
//        String str1 = new String(stringBuilder1);
//        str1 = new StringBuffer(str1).reverse().toString();
//        Queue queue1 = new LinkedList<>(Arrays.asList(str1.split("(?<=\\G.{1})")));
//
//        StringBuilder stringBuilder2 = new StringBuilder();
//        while(decimalNumber2!=0 ) {
//            int b = decimalNumber2%2;
//            stringBuilder2.append(b);
//            decimalNumber2 = decimalNumber2/2;
//        }
//        String str2 = new String(stringBuilder2);
//        str2 = new StringBuffer(str2).reverse().toString();
//        Queue queue2 = new LinkedList<>(Arrays.asList(str2.split("(?<=\\G.{1})")));
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        Iterator<String> iterator1 = queue1.iterator();
//        Iterator<String> iterator2 = queue2.iterator();
//        System.out.println("iterator1:");
//        while (iterator1.hasNext()){
//            System.out.println(iterator1.next());
//        }
//        System.out.println("iterator2:");
//        while (iterator2.hasNext()){
//            System.out.println(iterator2.next());
//        }
//        while (iterator1.hasNext()){
//            String s1 = iterator1.next().toString();
//            System.out.println(s1);
//            String s2 = iterator2.next().toString();
//            System.out.println(s2);
//            if(s1.equals(s2)){
//                stringBuilder.append(s1);
//            }
//            else {
//                stringBuilder.append("-");
//            }
//        }
//        System.out.println("minm"+stringBuilder.toString());

    }

}
