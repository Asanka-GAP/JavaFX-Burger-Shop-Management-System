package model;

public class BestCustomerDetails {

    private String customerName;
    private double total;
    public BestCustomerDetails(String customerId, String customerName, double total) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.total = total;
    }

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



}
