import java.util.Objects;

public class Main {
    public static void main( String[] args ) {
        StringListimpl test = new StringListimpl();
        test.add("ttt");
        test.add("rrr");
        test.add("mmm");

        StringListimpl test1 = new StringListimpl();
        test1.add("ttt");
        test1.add("rrr");
        test1.add("mmm");
// я не понимаю как сделать так, чтобы этот метод работал хоть как-нибудь ._.

        System.out.println(test1.equals(test));

    }
}