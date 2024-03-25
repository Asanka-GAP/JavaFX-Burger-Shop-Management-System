package model;

public class OrderDetails {
    private String orderId;
    private String customerId;
    private String customerName;
    private int orderStatus;
    private int orderQTY;
    private double orderValue;


    public OrderDetails(String orderId,String customerId,String customerName,int orderStatus,int orderQTY,double orderValue){
        this.orderId=orderId;
        this.customerId=customerId;
        this.customerName=customerName;
        this.orderStatus=orderStatus;
        this.orderQTY=orderQTY;
        this.orderValue=orderValue;
    }

    public String getOrderId(){ return orderId; }
    public String getCustomerId(){ return customerId; }
    public String getCustomerName(){ return customerName; }
    public String getOrderStatus(){
        switch(orderStatus){
            case 1:return "PENDING";
            case 2:return "CANCEL";
            case 3:return "DELEVERD";

            default:return "NuN";
        }
    }
    public int getOrderQTY(){ return orderQTY; }
    public double getOrderValue(){ return orderValue; }

    public void setCustomerId(String customerId){ this.customerId=customerId; }
    public void setCustomerName(String customerName){ this.customerName=customerName; }
    public void setOrderStatus(int orderStatus){ this.orderStatus=orderStatus; }
    public void setOrderQTY(int orderQTY){ this.orderQTY=orderQTY; }
    public void setOrderValue(double orderValue){ this.orderValue=orderValue; }
    public String toString(){
        return "{ "+getOrderId()+" - "+getCustomerId()+" - "+getCustomerName()+" - "+getOrderStatus()+" - "+getOrderQTY()+" - "+getOrderValue()+" }";
    }
}
