package threadAccount;

public class Main {
    public static void main(String[] args) {
        // jeden rachunek i jedna karta
        Account a1 = new Account("1111-2222-3333", 10000);
   //     Client c1 = new Client(a1);
        Thread th1 = new Thread(new Client(a1));
        Thread th2 = new Thread(new Client(a1));

        th1.start();
        th2.start();
    }
}
