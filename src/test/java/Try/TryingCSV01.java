package Try;


import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexanderboffin on 1/12/16.
 */
public class TryingCSV01 {
    public static void CSVLine(String FileCSV,int TheLine) throws IOException {
        //probeer en manier te vinden om aan het element te komen
        //Geprobeerd hiermee een functie te maken die terug kan opgeroepen worden en dan een bepaalde lijn geven
        CSVReader reader = new CSVReader(new FileReader(FileCSV));
        String[] csvCELL = new String[0];
        for (int ink=1;ink<=TheLine; ink++){
            csvCELL = reader.readNext();

        }
        for(String el:csvCELL) {
            System.out.println(el+" ");
        }
        }

    public static void main(String[] args) throws IOException {
        CSVLine("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/WerkmapCSVstandaard3.csv",3);
    }
}

