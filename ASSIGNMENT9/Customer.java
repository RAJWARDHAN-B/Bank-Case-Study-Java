package ASSIGNMENT9;

public class Customer {

    private String customerName;
    private String customerAge;

    // CONSTRUCTOR


    public Customer(String customerName, String customerAge) {
        this.customerName = customerName;
        this.customerAge = customerAge;
    }

    // GETTERS AND SETTERS


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }


}
