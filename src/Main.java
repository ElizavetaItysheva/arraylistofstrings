import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main( String[] args ) {
IntegerListimpl test = new IntegerListimpl();
// Верхняя граница рандомных чисел
//        int upperBound = 100000;
//        Integer[] arr = new Integer[100000];
//        Random random = new Random();
//        IntStream.range(0, 100000)
//                .forEach(index -> arr[index] = random.nextInt(upperBound));
//
//        long start = System.currentTimeMillis();
//        test.sortMethodChoice(arr);
//        System.out.println(System.currentTimeMillis() - start);
test.add(5);
test.add(1);
test.add(7);
test.add(2);
test.add(8);
test.add(6);
test.add(3);
test.add(4);
test.add(10);
test.add(9);
       test.show();
test.sort();
        System.out.println();
       test.show();

    }

}