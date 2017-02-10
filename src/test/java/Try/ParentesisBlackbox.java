package Try;

/**
 * Created by alexanderboffin on 12/12/16.
 */
public class ParentesisBlackbox {
    public static void main(String[] args) {
        String wordone="word ";
        int numberone=10;
        int numbertwo=20;
        String wordttwo=" word";

        System.out.println("all after each other gives: "+wordone+numberone+numbertwo+wordttwo);
        System.out.println("using parentheses gives: "+wordone+(numberone+numbertwo)+wordttwo);
    }
}
