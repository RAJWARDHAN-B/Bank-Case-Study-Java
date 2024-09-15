package ASSIGNMENT9;
import java.util.*;

public class Assignment9 {



    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
//    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int ch=3;

        System.out.println(ANSI_CYAN + "Welcome to Organ Chase and Co.\n" + ANSI_RESET);

        while(ch != 0) {
            System.out.println("Enter 1 to create a new account\nEnter 2 for Existing account\nEnter 0 to exit: ");
            ch = s.nextInt();
            if(ch == 0){
                System.out.println(ANSI_RESET + ANSI_GREEN + "Thank you for visiting !" + ANSI_RESET);
            }
            if(ch != 1 && ch != 2 && ch != 0){
                System.out.println(ANSI_RESET + ANSI_RED +  "Wrong choice" + ANSI_RESET);
            }
            else if(ch == 1){
                System.out.println("Please go through the Account creation process\n");
                int age = 0;
                while(age<=0) {
                    System.out.println("Enter your age: ");
                    age = s.nextInt();
                    if(age <= 0){
                        System.out.println(ANSI_RED + "Enter valid age" + ANSI_RESET);
                    }

                    else if(age <12){
                        System.out.println("You cannot create an account");
                    }
                    else if(age < 18){

                        System.out.println(ANSI_YELLOW + "You will have to create a Savings Minor account.\nPlease enter the details" + ANSI_RESET);
                        System.out.println("Enter Your name: ");
                        String sname = s.next();
                        System.out.println("Enter your Guardian's name: ");
                        String gname = s.next();
                        SavingsAccount saveaccount = new SavingsAccount(0,"Savings","0");
                        try {
                            saveaccount.createSavings(sname, gname,age);
                        }catch(Exception e){
                            System.out.println(e);
                            System.out.println("Please Enter correct details.");
                        }

                    }
                    else{
                        System.out.println(ANSI_GREEN + "You can create an account " + ANSI_RESET);
                        System.out.println("Enter Your name: ");
                        String sname = s.next();
                        SavingsAccount saveaccount = new SavingsAccount(0,"Savings","0");
                        try {
                            saveaccount.createSavings(sname,age);
                        }catch(Exception e){
                            System.out.println(e);
                            System.out.println("Please Enter correct details.");
                        }

                    }
                }

            }
            else if(ch == 2){
                int ch1 = 10;

                while(ch1!=0){
                    System.out.println(ANSI_CYAN+"Welcome back to Organ Chase and Co."+ANSI_RESET);
                    System.out.println("Enter 1 to withdraw money\nEnter 2 to deposit money\nEnter 3 to Display details\nEnter 0 to exit: ");

                    ch1 = s.nextInt();
                    if(ch1==0){
                        break;
                    }
                    System.out.println("Enter the Account number: ");
                    String acn = s.next();
                    int Flag = 0;

                    for (Customer customer: SavingsAccount.customerList){
                        if (customer.getAccno().equals(acn)){
                            Flag = 1;
                            System.out.println("Enter the PIN: ");
                            String pin = s.next();
                            for(SavingsAccount acc : SavingsAccount.saccountList){
                                if (acc.getPin().equals(pin)){
                                    Flag = 2;
                                    try {
                                        if (ch1 == 1){
                                            System.out.print("Enter the money to withdraw: Rs.");
                                            long money = s.nextLong();
                                            acc.withdraw(money, acc.getAccountType());
                                            // NOW WITHDRAW SHOULD BE IMPLEMENTED, VERIFY ACC NO. WITH PIN AND CURRENT SHOULD NOT HAVE WITHDRAWAL LIMIT. ALSO IMPLEMENT THE TIMER.
                                        }
                                        else if (ch1 == 2){
                                            System.out.print("Enter the money to deposit: Rs.");
                                            long money = s.nextLong();
                                            acc.deposit(money);
                                        }
                                        else if(ch1==3){

                                            // DISPLAY METHOD

                                            Customer.displayInfo(acn);

                                        }
                                        //acc.withdraw(money, acc.getAccountType());
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(ANSI_RED+"Insufficient funds. "+ANSI_RESET);
                                    }
                                }
                            }
                        }
                    }
                    if(Flag == 0){
                        System.out.println(ANSI_RED+"Account not found."+ANSI_RESET);
                    }
                    else if (Flag == 1){
                        System.out.println(ANSI_RED+"Incorrect PIN"+ANSI_RESET);
                    }


                }
            }
        }

    }
}
