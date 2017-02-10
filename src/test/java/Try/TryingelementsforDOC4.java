package Try;

import org.junit.Assert;

/**
 * Created by alexanderboffin on 30/11/16.
 */
public class TryingelementsforDOC4 {
    public static void main(String[] args) {
        //int to float en gewone
        int a=2;
        int b=3;
        int c=a+b;
        double d=Math.round(a+b);
        System.out.println("c is: "+c+"\nd is: "+d);

        //gemengd
        int numie1=12;
        float numie2= 12.50f;
        double num=numie1+numie2;
        System.out.println("num is: "+num);
        float numA=Float.parseFloat("12.50");
        System.out.println("numA is: "+numA);
        System.out.println(numie2==numA);
    }

}
