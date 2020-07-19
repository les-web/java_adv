package threadsCoordination;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// utworz dwa watki o nazwach
// producer 1 - wypisuje na ekran zawartosc List<String>
//  producer 2 - wypisuje na ekran zawartosc Array<Integer>
// producer  moze wypisywac dopiero gdy producer 1 skonczy dzialanie (join)
// po zakonczeniu pracy obu watkow
// program main niech wypisze KONIEC
public class threadsController {
    List<String> names = new ArrayList<>(Arrays.asList("Adam", "Jan", "Anna", "Ola", "Iga"));
    int[] numbers = {22, 12, 33, 44, 7, 2, 99, 1, 1, 1, 1, 0, 0, 1};
    Thread thread1;
    Thread thread2;

    public void printNames() {
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < names.size()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " : " + names.get(i));
                        Thread.currentThread().sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }, "Producer 1");
        thread1.start();
    }

    public void printNumbers() {
        thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;
                int i = 0;
                while (i < numbers.length) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " : " + String.valueOf(numbers[i]));
                        Thread.currentThread().sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }, "Producer 2");
        thread2.start();
    }

    public static void main(String[] args) {
        threadsController tc = new threadsController();
        tc.printNames();
        tc.printNumbers();
        try {
            tc.thread1.join();
            tc.thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("KONIEC");
    }
}
