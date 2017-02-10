package Try;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexanderboffin on 1/12/16.
 */
public class TryingCSV02 {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/WerkmapCSVstandaard3.csv"));
        List<String[]> li=reader.readAll();
        Iterator<String[]>i1=li.iterator();

        while (i1.hasNext()){
            String[] str=i1.next();
            System.out.println("values are");
            for (int i=0;i<str.length;i++){
                System.out.print(" "+str[i]);
            }
            System.out.println("");
        }




    }
}
