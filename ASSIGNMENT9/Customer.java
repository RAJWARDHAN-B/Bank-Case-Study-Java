package ASSIGNMENT9;

public class Customer {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_RESET = "\u001B[0m";

    // CUSTOMER DETAILS

    private String customerName;
    private int customerAge;
    private String mail;
    private String phone;
    private String adhaar;
    private boolean current;
    private static boolean savings;
    private String accno;

    // CONSTRUCTOR


    public Customer(String accno, String customerName, int customerAge, String mail, String phone, String adhaar, boolean current, boolean savings) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.mail = mail;
        this.phone = phone;
        this.adhaar = adhaar;
        this.current = current;
        this.savings = savings;
        this.accno = accno;
    }

    // GETTERS AND SETTERS


    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public static boolean isSavings() {
        return savings;
    }

    public void setSavings(boolean savings) {
        this.savings = savings;
    }

    public static void generateAtm(String accnum, String name){
        System.out.println("____________________________________________________"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|  ___________                         "+ANSI_BLACK+">o)"+"         |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|  "+ ANSI_RESET+ANSI_BLACK+ANSI_YELLOW_BACKGROUND+"| /  |  / |"+ ANSI_RESET+ANSI_CYAN_BACKGROUND+"        "+ANSI_CYAN_BACKGROUND+ANSI_BLACK+"ORGAN CHASE      (_>"+ ANSI_RESET+ANSI_CYAN_BACKGROUND+"         |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|  "+ ANSI_RESET+ANSI_BLACK+ANSI_YELLOW_BACKGROUND+"|__|---/\\ |"+ ANSI_RESET+ANSI_CYAN_BACKGROUND+"                                     |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|  "+ ANSI_RESET+ANSI_BLACK+ANSI_YELLOW_BACKGROUND+"|_________|"+ ANSI_RESET+ANSI_CYAN_BACKGROUND+"                                     |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|                                                  |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|                                                  |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|       "+ANSI_BLACK+accnum+ ANSI_RESET+ANSI_CYAN_BACKGROUND+"                                   |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|                                                  |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|                                                  |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|                                                  |"+ ANSI_RESET);
        System.out.println(ANSI_CYAN_BACKGROUND+"|__________________________________________________|"+ ANSI_RESET);

    }

    public static void displayInfo(String accno){
        int temp =0,i = 0;
        for(Customer cust :SavingsAccount.customerList){
            if (cust.getAccno().equals(accno)){
                temp = i;
                System.out.println("____________________________________");
                if (isSavings()) {
                    System.out.println("Account Type: Savings");
                }
                else{
                    System.out.println("Account Type: Current");
                }
                System.out.println("Account number: " +accno);
                System.out.println("Account Holder name: "+cust.getCustomerName());
                System.out.println("Balance: "+ SavingsAccount.saccountList.get(temp).getBalance());
                System.out.println("____________________________________");
            }
            i++;
        }

    }
}
