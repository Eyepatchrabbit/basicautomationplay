package tryfurtherselenium;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 4/01/17.
 */
public class tryingParameterizationDataProviderM {


    @Test(dataProvider="countProvider")
    public static void exampleSum(@Optional("1") int startnumber, @Optional("1") int addition) {
        System.out.println("adding "+startnumber+" + "+addition+" = "+(startnumber+addition));
    }

    @Test(dataProvider="countProvider")
    public static void exampleMultiply(@Optional("1") int todubble) {
        System.out.println("multiplaying "+todubble+" gives us: "+(2*todubble));
    }

    @DataProvider(name="countProvider")
    public  Object[][] getDataFromDataprovider(java.lang.reflect.Method m){
        if(m.getName().equalsIgnoreCase("exampleSum")){
            System.out.println("\nTesting the SUM-method");

            return new Object[][] {

                    { 1, 1 },

                    { 2, 5 },

                    { 1000, 234 }

            };}
        else{
            System.out.println("\nTesting the SUM-method");
            return new Object[][] {

                    { 1 },

                    { 2 },

                    { 1000 }
        };

    }


}
}


