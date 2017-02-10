package Try;


import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * Created by alexanderboffin on 5/12/16.
 */
public class TRYjson01 {
    public static void main(String[] args) throws IOException, ParseException {
        //read the file itself
        org.json.simple.parser.JSONParser parser=new org.json.simple.parser.JSONParser();

        JSONObject obj = (JSONObject) parser.parse(new FileReader("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/doc6ex6d.json"));

        //Getting one of the variables (outside  an array []
        String name= (String) obj.get("Name");

        System.out.println(name);

        //Getting an element from an normal array
        org.json.simple.JSONArray arr= (org.json.simple.JSONArray) obj.get("AnimalTypes");

        System.out.println((String) arr.get(1));

        //getting elements out an arry with selectables
        org.json.simple.JSONArray arrSP= (org.json.simple.JSONArray) obj.get("TheHomepage");


        JSONObject objOfArray = (JSONObject) arrSP.get(1);//setting found element to new object

        String nameOfArrayElement=(String) objOfArray.get("subcategory");

        System.out.println(nameOfArrayElement);


        System.out.println("does everything work?");
    }

}
