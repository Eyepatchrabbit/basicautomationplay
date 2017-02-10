package tryfurtherselenium;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 5/01/17.
 */
public class tryingModefiedGuru {
    @Test(dataProvider="SearchProvider",groups="A")

    public void testMethodA(String author,String searchKey) {

        {
            System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        }

    }



    @Test(dataProvider="SearchProvider",groups="B")

    public void testMethodB(String searchKey) throws InterruptedException{

        {
            System.out.println("Welcome ->Unknown user Your search key is->"+searchKey);
        }

    }

    /**

     * Here the DAtaProvider will provide Object array on the basis on ITestContext

     * @param c

     * @return

     */

    @DataProvider(name="SearchProvider")

    public Object[][] getDataFromDataprovider(ITestNGMethod c){



        Object[][] groupArray = null;

        for (String group : c.getGroups()) {

            if(group.equalsIgnoreCase("A")){

                groupArray = new Object[][] {

                        { "Guru99", "India" },

                        { "Krishna", "UK" },

                        { "Bhupesh", "USA" }

                };

                break;

            }

            else if(group.equalsIgnoreCase("B"))

            {

                groupArray = new Object[][] {

                        { "Canada" },

                        { "Russia" },

                        { "Japan" }

                };

            }

            break;

        }



        return groupArray;

    }

}


