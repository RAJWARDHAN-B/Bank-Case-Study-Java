package ASSIGNMENT9;

import java.time.LocalDate;



// USER DEFINED EXCEPTIONS
class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidWithdrawalException extends Exception{
    public InvalidWithdrawalException(String message) {
        super(message);
    }
}

class InvalidMobileException extends Exception{
    public InvalidMobileException(String message) {
        super(message);
    }
}

class InvalidAdhaarException extends Exception{
    public InvalidAdhaarException(String message) {
        super(message);
    }
}

class InvalidMailException extends Exception{
    public InvalidMailException(String message) {
        super(message);
    }
}

public abstract class Account {

    protected double dailyWithdrawnAmount = 0.0;
    protected LocalDate lastWithdrawalDate;

    private long balance;
    private String accountType;
    private String pin;

    // CONSTRUCTOR

    public Account(long balance, String accountType,String pin) {
        this.balance = balance;
        this.accountType = accountType;
        this.pin = pin;
    }

    // GETTERS AND SETTERS


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // ABSTRACT METHODS

    abstract void withdraw(long money, String type) throws InsufficientFundsException,InvalidWithdrawalException;
    abstract void deposit(long money);
    protected abstract String generateAccountNo();
    protected abstract String generatePin();

    protected void resetDailyLimit() {
        LocalDate currentDate = LocalDate.now();
        if (!lastWithdrawalDate.equals(currentDate)) {
            dailyWithdrawnAmount = 0.0;
            lastWithdrawalDate = currentDate;
        }
    }


}
