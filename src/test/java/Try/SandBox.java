package Try;

import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by alexanderboffin on 22/12/16.
 */
public class SandBox {

    public static void main(String[] args) {
        int numberone = 1 + 6 + 7 * 4 + 9 * 2 + 7 + 3;
        System.out.println(numberone);

        int numbertwo = 1 + 6 + (7 * 4) + (9 * 2) + 7 + 3;
        System.out.println(numbertwo);

    }

    public static void main2(String[] args) {

        try {
            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);

            String strline;
            while ((strline = Buffreader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strline, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if (!(val = token.nextToken()).equals("null")) {
                        expiry = new Date(val);
                    }
                    Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);

                    // This will add the stored cookie to your current session
                    //driver.manage().addCookie(ck);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}