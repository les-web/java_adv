package threadsAccount;

import lombok.AllArgsConstructor;
import lombok.Data;

// klasa modelu
@Data
@AllArgsConstructor
public class Account {
    private String accountNumber;
    private double accountSaldo;

    public void addIncome(double amount){
        System.out.println(Thread.currentThread().getName() + " +wplata na konto " + amount);
        this.accountSaldo += amount;
        System.out.println(Thread.currentThread().getName() + " aktualne saldo " + accountSaldo);
    }
    public void getOutcome(double amount){
        if(amount <= accountSaldo) {
            System.out.println(Thread.currentThread().getName() +   " -Wyplata  " + amount + " zł");
            this.accountSaldo -= amount;
        } else {
            System.out.println("Operacja niemożliwa do zrealizowania:");
  //          System.out.println(Thread.currentThread().getName() +  " Aktualne saldo: " + accountSaldo);
        }
        System.out.println(Thread.currentThread().getName() +  " Aktualne saldo: " + accountSaldo);

    }
}