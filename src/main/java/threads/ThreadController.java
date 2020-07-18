package threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadController {
    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 6, 3, 8, 9, 7));

    public void printNumber() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer number : numbers) {
                    try {
                        Thread.currentThread().sleep(1000);
                        System.out.println("Watek " + Thread.currentThread().getName());
                        System.out.println(number);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        });
        thread.start();
    }

    public static void main(String[] args) {
        ThreadController tc = new ThreadController();
        System.out.println("watek  " + Thread.currentThread().getName());
        tc.printNumber();
        System.out.println("watek     " + Thread.currentThread().getName());

    }
}
