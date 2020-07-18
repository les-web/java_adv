package threadAccount;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@AllArgsConstructor
@Data

public class Client implements Runnable {

    private Account account;


    @Override
    public void run() {
        while (account.getAccontSaldo() > 0) {
            try {
                Thread.sleep(500);
                int amount = new Random().nextInt(1000) + 1;
                System.out.println("Watek : " + Thread.currentThread().getName());
                System.out.println("Wyplacam z rachunku %s kwote: %2zł\n");
                account.getOutcome(amount);
                System.out.printf("Aktualne saldo dla rachunku %s : %.2fzł\n",
                        account.getAccountNumber(),
                        Double.valueOf(account.getAccontSaldo()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
