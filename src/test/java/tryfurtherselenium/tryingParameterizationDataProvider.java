package tryfurtherselenium;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 4/01/17.
 */
public class tryingParameterizationDataProvider {


    @Test(dataProvider="countProvider")
    public static void exampleFunctionToTest(@Optional("1") int startnumber, @Optional("1") int addition) {
        System.out.println("adding "+startnumber+" + "+addition+" = "+(startnumber+addition));
    }


    @DataProvider(name="countProvider")
    public  Object[][] getDataFromDataprovider(){

        return new Object[][] {

                { 1, 1 },

                { 2, 5 },

                { 1000, 234 }

        };

    }


}


