package ASSIGNMENT9;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.Random;
import java.util.regex.*;

public class SavingsAccount extends Account{

    private final double dailyWithdrawalLimit = 40000.0;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // LIST OF SAVINGS ACCOUNTS
    public static List<SavingsAccount> saccountList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();

    Scanner s = new Scanner(System.in);


    public static final long minBalance = 3000;
    public static final long cminBalance = 10000;

    public static long withdrawLimit = 10000;

    // CONSTRUCTOR
    public SavingsAccount(long balance, String accountType, String pin) {
        super(balance, accountType,pin);
    }

    // OVERRIDDEN WITHDRAW AND DEPOSIT METHOD

    @Override
    void deposit(long money){
        while(money <0){
            System.out.print("Enter the money to be deposited: ₹");
            money = s.nextLong();
        }
        long newBalance = getBalance() + money;
        setBalance(newBalance);
    }

    @Override
    void withdraw(long money,String type) throws InsufficientFundsException{

        resetDailyLimit(); // Reset daily limit if needed

        if (type.equals("Current")){
            withdrawLimit = getBalance();
        }

        while(money <0){
            System.out.print("Enter the money to be withdrawn: ₹");
            money = s.nextLong();
        }
        if (money > getBalance()){
            throw new InsufficientFundsException("Insufficient Funds");


        }
        else if (dailyWithdrawnAmount + money > dailyWithdrawalLimit && type.equals("Savings")) {
            System.out.println("Daily withdrawal limit exceeded. Withdrawal failed.");
            return;
        }
        else if (money < withdrawLimit && type.equals("Savings")){
            System.out.println("Withdrawal Limit is "+withdrawLimit);
            return;
        }

        setBalance(getBalance()+money);

    }

    @Override
    public String generateAccountNo() {
        // STRING BUILDER FOR MUTABLE STRING
        StringBuilder acno = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<8;i++){
            acno.append(random.nextInt(10));
        }

        // RETURNS UNIQUE ACCOUNT NUMBER.
        for(Customer customer: customerList){
            if(customer.getAccno().contentEquals(acno)){
                return generateAccountNo();
            }
        }
        return acno.toString();
    }

    @Override
    public String generatePin(){

        StringBuilder pin = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<4;i++){
            pin.append(random.nextInt(10));
        }

        for(SavingsAccount acc: saccountList){
            if(acc.getPin().equals(pin.toString())){
                return generatePin();
            }
        }
        return pin.toString();
    }

    // ACCOUNT CREATION METHOD
    // METHOD OVERLOADING

    public void createSavings(String name,int age) throws InvalidAdhaarException, InvalidMailException,InvalidMobileException{
        int atype = 0;
        while(atype != 1 && atype != 2) {
            System.out.println("Choose 1 for Savings account \nEnter 2 for Current account: ");
            atype = s.nextInt();
        }
        System.out.println("Enter Phone number: ");
        String pnum = s.next();
        if(pnum.length()!=10){
            throw new InvalidMobileException("Please Enter correct Phone number.");
        }
        System.out.println("Enter your Adhaar number: ");
        String anum = s.next();
        if(anum.length()!=12){
            throw new InvalidAdhaarException("Enter correct Adhaar.");
        }
        System.out.println("Enter Email: ");
        String mail = s.next();
        // USING REGEX TO VERIFY EMAIL.
        if(Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",mail.toUpperCase())){
            System.out.println("Correct Email.");
        }
        else{
            throw new InvalidMailException("Enter correct email.");
        }

        if (atype == 1){

            for(Customer customer: customerList){
                if (customer instanceof Customer && customer.getAdhaar().equals(anum) && customer.isSavings()){
                    System.out.println(ANSI_RED+"A Savings account with the same adhaar number already exists."+ANSI_RESET);
                    return;
                }

                else if (customer instanceof Customer && customer.getPhone().equals(pnum) && customer.isSavings()){
                    System.out.println(ANSI_RED+"A Savings account with the same Phone number already exists."+ANSI_RESET);
                    return;
                }
                else if (customer != null && customer.getMail().equals(mail) && customer.isSavings()){
                    System.out.println(ANSI_RED+"A Savings account with the same email number already exists."+ANSI_RESET);
                    return;
                }
            }

            long indep = 0;

            while(indep < minBalance){
                System.out.print(ANSI_YELLOW+"Enter initial Deposit: ₹"+ANSI_RESET);
                indep = s.nextLong();
                if (indep<0){
                    System.out.println(ANSI_RED+"Please enter valid amount."+ANSI_RESET);
                }
                else if (indep < minBalance){
                    System.out.println("Minimum balance is "+minBalance);
                }
            }
            String accnum = generateAccountNo();
            String pin = generatePin();
            SavingsAccount acc = new SavingsAccount(indep,"Savings",pin);
            Customer cust = new Customer(accnum,name,age,mail,pnum,anum,false,true);
            saccountList.add(acc);
            customerList.add(cust);
            System.out.println();
            System.out.println(ANSI_GREEN+"Account created successfully..."+ANSI_RESET);
            System.out.println("Here is your complementary credit card: ");
            Customer.generateAtm(accnum,name);
            System.out.println(ANSI_GREEN+"Your account number is: "+accnum+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Your PIN is: "+pin+ANSI_RESET);
            System.out.println(ANSI_RED+"DO NOT SHARE YOUR PIN WITH ANYONE !"+ANSI_RESET);
            System.out.println();

        }
        else{

            for(Customer customer: customerList){
                if (customer instanceof Customer && customer.getAdhaar().equals(anum) && customer.isCurrent()){
                    System.out.println(ANSI_RED+"A current account with the same adhaar number already exists."+ANSI_RESET);
                    return;
                }

                else if (customer instanceof Customer && customer.getPhone().equals(pnum) && customer.isCurrent()){
                    System.out.println(ANSI_RED+"A current account with the same Phone number already exists."+ANSI_RESET);
                    return;
                }
                else if (customer != null && customer.getMail().equals(mail) && customer.isCurrent()){
                    System.out.println(ANSI_RED+"A current account with the same email number already exists."+ANSI_RESET);
                    return;
                }
            }

            long indep = 0;

            while(indep < cminBalance){
                System.out.print(ANSI_YELLOW+"Enter initial Deposit: ₹"+ANSI_RESET);
                indep = s.nextLong();
                if (indep<0){
                    System.out.println(ANSI_RED+"Please enter valid amount."+ANSI_RESET);
                }
                else if (indep < cminBalance){
                    System.out.println("Minimum balance is "+cminBalance);
                }
            }
            String accnum = generateAccountNo();
            String pin = generatePin();
            SavingsAccount acc = new SavingsAccount(indep,"Current",pin);
            Customer cust = new Customer(accnum,name,age,mail,pnum,anum,true,false);
            saccountList.add(acc);
            customerList.add(cust);
            System.out.println();
            System.out.println(ANSI_GREEN+"Account created successfully..."+ANSI_RESET);
            Customer.generateAtm(accnum,name);
            System.out.println(ANSI_GREEN+"Your account number is: "+accnum+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Your PIN is: "+pin+ANSI_RESET);
            System.out.println(ANSI_RED+"DO NOT SHARE YOUR PIN WITH ANYONE !"+ANSI_RESET);
            System.out.println();
        }




    }

    public void createSavings(String name, String gname, int age) throws InvalidAdhaarException, InvalidMailException,InvalidMobileException{

        System.out.println(ANSI_YELLOW + "\nThe further details to be filled by the guardian.\n" + ANSI_RESET);
        System.out.println("Enter Phone number: ");
        String pnum = s.next();
        if(pnum.length()!=10){
            throw new InvalidMobileException("Please Enter correct Phone number.");
        }
        System.out.println("Enter your Adhaar number: ");
        String anum = s.next();
        if(anum.length()!=12){
            throw new InvalidAdhaarException("Enter correct Adhaar.");
        }
        System.out.println("Enter Email: ");
        String mail = s.next();
        // USING REGEX TO VERIFY EMAIL.
        if(Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",mail.toUpperCase())){
            System.out.println("Correct Email.");
        }
        else{
            throw new InvalidMailException("Enter correct email.");
        }

        for(Customer customer: customerList){
            if (customer instanceof Customer && customer.getAdhaar().equals(anum) && customer.isSavings()){
                System.out.println(ANSI_RED+"A Savings account with the same adhaar number already exists."+ANSI_RESET);
                return;
            }

            else if (customer instanceof Customer && customer.getPhone().equals(pnum) && customer.isSavings()){
                System.out.println(ANSI_RED+"A Savings account with the same Phone number already exists."+ANSI_RESET);
                return;
            }
            else if (customer != null && customer.getMail().equals(mail) && customer.isSavings()){
                System.out.println(ANSI_RED+"A Savings account with the same email number already exists."+ANSI_RESET);
                return;
            }
        }

        long indep = 0;

        while(indep < minBalance){
            System.out.print(ANSI_YELLOW+"Enter initial Deposit: ₹"+ANSI_RESET);
            indep = s.nextLong();
            if (indep<0){
                System.out.println(ANSI_RED+"Please enter valid amount."+ANSI_RESET);
            }
            else if (indep < minBalance){
                System.out.println("Minimum balance is "+minBalance);
            }
        }
        String accnum = generateAccountNo();
        String pin = generatePin();
        SavingsAccount acc = new SavingsAccount(indep,"Savings",pin);
        Customer cust = new Customer(accnum,name,age,mail,pnum,anum,false,true);
        saccountList.add(acc);
        customerList.add(cust);
        System.out.println();
        System.out.println(ANSI_GREEN+"Account created successfully..."+ANSI_RESET);
        Customer.generateAtm(accnum,name);
        System.out.println(ANSI_GREEN+"Your account number is: "+accnum+ANSI_RESET);
        System.out.println(ANSI_GREEN+"Your PIN is: "+pin+ANSI_RESET);
        System.out.println(ANSI_RED+"DO NOT SHARE YOUR PIN WITH ANYONE !"+ANSI_RESET);
        System.out.println();

    }


}

//BOLD COLORS
//\e[1;30m	Black
//\e[1;31m	Red
//\e[1;32m	Green
//\e[1;33m	Yellow
//\e[1;34m	Blue
//\e[1;35m	Purple
//\e[1;36m	Cyan
//\e[1;37m	White
