package tryfurtherselenium;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by alexanderboffin on 4/01/17.
 */
public class tryingParameterization {


    @Test
    @Parameters({"start","addition"})
    public static void exampleFunctionToTest(@Optional("1") int startnumber, @Optional("1") int addition) {
        System.out.println("adding "+startnumber+" + "+addition+" = "+(startnumber+addition));
    }
}


