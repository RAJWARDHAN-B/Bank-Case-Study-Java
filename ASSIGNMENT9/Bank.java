package ASSIGNMENT9;
import java.util.Scanner;

public class Bank extends Account{

    // CONSTRUCTOR

    public Bank(long balance, String accountType) {
        super(balance, accountType);
    }

    // OTHER METHODS

    public void createAccount(){

    }

    @Override
    public void displayInfo(){

        System.out.println("Account Type: " + getAccountType());
        System.out.println("Account number: " );
        System.out.println("Account Holder name: ");
        System.out.println("Balance: ");


    }
}

/*
Add functionalities for minor major account, by method overloading and then also display credit card
or debit card.

Also, both current and savings account should be implemented.

Read about the four pillars of oop with examples and implement them.

Account number should be random and should not have been assigned to anyone earlier.

Implement daily withdrawal limit by using date time etc.

check aadhaar number length and phone number, also could do email verification through regex.

The credit/Debit card should be perfect rectangle.

Also, there should be a unique pin.

Create new account

Existing account
 */
