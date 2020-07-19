package threadsCoordination;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// utworz dwa watki o nazwach
// producer 1 - wypisuje na ekran zawartosc List<String>
//  producer 2 - wypisuje na ekran zawartosc Array<Integer>
//
public class threadsController {
    List<String> names = new ArrayList<>(Arrays.asList("Adam", "Jan", "Anna", "Ola", "Iga"));
    int[] numbers = {22, 12, 33, 44, 7, 2, 99, 1, 1, 1, 1, 0, 0, 1};


    public void printNames() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < names.size()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "  " +    names.get(i));
                        Thread.currentThread().sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                i++;
                }
            }
        });
        thread.start();
    }

        public void printNumbers() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < numbers.length) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "  " +String.valueOf(numbers[i]));
                        Thread.currentThread().sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        });
        thread.start();
    }
    public static void main(String[] args) {
threadsController tc =new threadsController();
tc.printNames();
tc.printNumbers();
    }
}
