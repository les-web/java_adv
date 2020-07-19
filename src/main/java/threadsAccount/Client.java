package threadsAccount;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@AllArgsConstructor
@Data
public class Client implements Runnable {
    private Account account;

    @Override
    public void run() {
        //       while (account.getAccountSaldo() > 0) {
        int i = 30;
        while (i >= 0) {

            try {
                Thread.sleep(new Random().nextInt(1000));
                int amount = new Random().nextInt((10) + 1) * 10;
                int decision = new Random().nextInt(2);
                if (decision == 0) {

                    account.getOutcome(amount);
                } else {
                    account.addIncome(amount / 2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }
}