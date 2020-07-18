package threadAccount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private double accontSaldo;

    public void addIncome(double amount) {
        this.accontSaldo += amount;

    }

    public void getOutcome(double amount) {
        if (amount <= accontSaldo) {
            this.accontSaldo -= amount;
        } else {
            System.out.println("Operacja niemozliwa do realizacji");
            System.out.println("Aktualne saldo  " + accontSaldo);
        }
    }
}
