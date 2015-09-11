
/**
 * Created by Thomas Hodges on 9/9/2015.
 * Project: HodgesCMIS242Project2
 * Filename: Account.java
 *
 * Course: CMIS 242
 * Professor: Ioan Salomie
 * Assignment: Project 2
 *
 * Platform: Windows 10, IntelliJ IDEA 14.1.4
 * Compiler: jdk1.8.0_45
 */
public class Account {

    private double balance;

    public Account() {

    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public class Checking extends Account {

        public Checking() {
            
        }
    }

    public class Savings extends Account {

        public Savings() {

        }

    }

    public void withdraw(double withdrawAmount) throws InsufficientFunds {

        if (this.balance - withdrawAmount < 0) {
            throw new InsufficientFunds();
        }

        this.balance = this.balance - withdrawAmount;
    }

    public void deposit(double depositAmount) {
        this.balance = this.balance + depositAmount;
    }

    public void transferTo(double transferAmount) {
        this.balance = this.balance + transferAmount;
    }

    public void transferFrom(double transferAmount) throws InsufficientFunds {
        if (this.balance - transferAmount < 0) {
            throw new InsufficientFunds();
        }

        this.balance = this.balance - transferAmount;
    }
}
