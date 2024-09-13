package ASSIGNMENT9;

public abstract class Account {

    private long balance;
    private String accountType;

    // CONSTRUCTOR

    public Account(long balance, String accountType) {
        this.balance = balance;
        this.accountType = accountType;
    }

    // GETTERS AND SETTERS


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

    // ABSTRACT METHOD

    abstract void displayInfo();


}
