package tryfurtherselenium;


import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 4/01/17.
 */
public class tryingParameterizationDataProviderContext {



    @Test(dataProvider="countProvider", groups = {"A"})
    public static void exampleSum(@Optional("1") int startnumber, @Optional("1") int addition) {
        System.out.println("adding "+startnumber+" + "+addition+" = "+(startnumber+addition));
    }

    @Test(dataProvider="countProvider", groups = {"B"})
    public static void exampleMultiply(@Optional("1") int todubble) {
        System.out.println("multiplaying "+todubble+" gives us: "+(2*todubble));
    }

    @DataProvider(name="countProvider")
    public  Object[][] getDataFromDataprovider(org.testng.ITestContext thegroups){
        System.out.println("inside the dataprovider and c is: "+thegroups);

        Object[][] groupArray = null;

        for (String group: thegroups.getIncludedGroups()){          //wilt hier precies niet evrder gaan!!!!!!!!!
            System.out.println("the group is: "+ group);

            if(group.equalsIgnoreCase("A")){
                System.out.println("\nTesting the SUM-method");

                groupArray= new Object[][] {

                        { 1, 1 },

                        { 2, 5 },

                        { 1000, 234 }
                };
                break;

            }
            else if(group.equalsIgnoreCase("B")){
                System.out.println("\nTesting the SUM-method");
                groupArray= new Object[][] {

                        { 1 },

                        { 2 },

                        { 1000 }
                };
                break;


            }


        }
        return groupArray;


    }

}



