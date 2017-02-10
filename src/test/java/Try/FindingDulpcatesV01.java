package Try;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexanderboffin on 7/12/16.
 */
public class FindingDulpcatesV01 {
    public static List UpdateDictionaryList(List alfa, String newelement, String todo) {
        /*Here I'm making something to make a list behave as a list in python
        *The structure given as string must be variable,number in both cases
        *if last == add: will see if it is a dublicate of a already added eleemnt. If not
        * if ==update, it will increase the number of the elemnt in it
        * =>is written for doing the checking of the quality of the animals on the end
        * */
        if (todo.equals("add")) {
            //seeing if we we can add the new element to the list
            int numsie = alfa.indexOf(newelement);

            //Niet in de lijst(-1)=>add it to the list (new animal in the bag)
            if (numsie == -1) {

                alfa.add(newelement);
            }
            return alfa;
        }
        else if (todo.equals("update")){
            //if it is in the list=>update it. But we are goning from the assumpion its the first time
            String IntheList="No";

            //seeing if it is in the list
            for (Object element:alfa){
                //indien zelfde element=>moet de Qualiteit vermeerderen met +1
                String Newthing=element.toString().split(",")[0];

                if (Newthing.equals(newelement)){
                    //indien hij erin staat=>quantiteit vermeerderen met 1
                    String numberofanimals=element.toString().split(",")[1];
                    int number=Integer.parseInt(numberofanimals)+1;

                    //voor op de juiste index terugzetten
                    int Num=alfa.indexOf(element);

                    //nieuwe zetten op plaats van de oude
                    String element2=newelement+","+number;
                    alfa.set(Num,newelement+","+number);
                    IntheList="Yes";
                    break;

                }

            }
            //indien het de eerste keer is dat we het tegenkomen geven we het een quantiteit van 1
            if(IntheList.equals("No")){
                alfa.add(newelement+",1");
            }

        }
        return alfa;
    }






    public static void main(String[] args) {
        List a = new ArrayList();
        //a.add("not in here");

        int numbar=a.indexOf("not in here");
        System.out.println(numbar);

        List b= new ArrayList();
        b.add("a,1");
        b.add("b,2");
        b.add("c,3");
        String Newthing="d,4";

        b=UpdateDictionaryList(b,Newthing,"add");
        b=UpdateDictionaryList(b,"a,1","add");

        b=UpdateDictionaryList(b,"X","update");
        b=UpdateDictionaryList(b,"a","update");

        System.out.print("Did it work?????");
    }
}
