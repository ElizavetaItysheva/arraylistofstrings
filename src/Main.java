import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main( String[] args ) {
IntegerListimpl test = new IntegerListimpl();
// Верхняя граница рандомных чисел
        int upperBound = 100000;
        Integer[] arr = new Integer[100000];
        Random random = new Random();
        IntStream.range(0, 100000)
                .forEach(index -> arr[index] = random.nextInt(upperBound));

        long start = System.currentTimeMillis();
        test.sortMethodChoice(arr);
        System.out.println(System.currentTimeMillis() - start);



    }
}