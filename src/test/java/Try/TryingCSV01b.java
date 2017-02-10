package Try;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by alexanderboffin on 1/12/16.
 */
public class TryingCSV01b {
    public static String[] CSVLine(String FileCSV, int TheLine) throws IOException {
        //probeer en manier te vinden om aan het element te komen
        //Geprobeerd hiermee een functie te maken die terug kan opgeroepen worden en dan een bepaalde lijn geven
        CSVReader reader = new CSVReader(new FileReader(FileCSV));
        String[] csvCELL = new String[0];
        for (int ink=1;ink<=TheLine; ink++){
            csvCELL = reader.readNext();

        }
        return csvCELL;

        }

    public static void main(String[] args) throws IOException {
        String[] WhatWeNeed=CSVLine("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/WerkmapCSVstandaard3.csv",3);
        for(String el:WhatWeNeed) {
            System.out.println(el+" ");
        }
        System.out.println("is this right? :" +WhatWeNeed[1]);
    }
}

