package threads;

import java.sql.Time;
import java.util.*;

public class ThreadController {
    //  List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    Deque<Integer> numbers = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5));

    public void printNumber(Thread thread) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //           for (Integer number : numbers) {
                while (!numbers.isEmpty())
                    try {
                        Thread.currentThread().sleep(new Random().nextInt(6) * 1000);
                        synchronized (numbers) {
                            System.out.println("Watek " + Thread.currentThread().getName() + " - wartosc - " + numbers.removeFirst()
                                    + "aktualna zawartosc " + numbers);
                            System.out.println("jeszcze cos robie");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NoSuchElementException e) {
                        break;
                    }
            }


        });
        thread.start();


        public static void main (String[]args){
            ThreadController tc = new ThreadController();
            long timeStart = System.currentTimeMillis();
            Thread th1 = null, th2 = null, th3 = null;
            System.out.println("watek AAA " + Thread.currentThread().getName());
            tc.printNumber(th1);
            tc.printNumber(th2);
            tc.printNumber(th3);

//        System.out.println("watek  BBB    " + Thread.currentThread().getName());
//        tc.printNumber(th1);
//        tc.printNumber(th2);
//        tc.printNumber(th3);
            System.out.println("Czas trwania : " + (timeStart - System.currentTimeMillis()));
        }
    }

