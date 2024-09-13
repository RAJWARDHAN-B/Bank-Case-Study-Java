package ASSIGNMENT9;

public class SavingsAccount extends Account{

    public static final long minBalance = 3000;

    public static final long withdrawLimit = 10000;

    public SavingsAccount(long balance, String accountType) {
        super(balance, accountType);
    }

    @Override
    public void displayInfo(){

    }


}
