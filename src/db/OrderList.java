package db;

import javafx.collections.*;
import model.OrderDetails;

public class OrderList {

    private static OrderList instance;
    private Node first;

    private OrderList(){}

    public static OrderList getInstance(){
        if (instance==null){
            return instance = new OrderList();
        }
        return instance;
    }
    public void add(int index, OrderDetails data){
        Node n1=new Node(data);
        if(index>=0 && index<=size()){
            if(index==0){
                n1.next=first;
                first=n1;
            }else{
                int count=0;
                Node temp=first;
                while(count<index-1){
                    temp=temp.next;
                    count++;
                }
                n1.next=temp.next;
                temp.next=n1;
            }
        }
    }
    public void add(OrderDetails data){
        Node n1=new Node(data);
        if(isEmpty()){
            first=n1;
        }else{
            Node lastNode=first;
            while(lastNode.next!=null){
                lastNode=lastNode.next;
            }
            lastNode.next=n1;
        }
    }
    public void remove(){ //remove the first element
        if(first!=null){
            first=first.next;
        }
    }
    public int search(OrderDetails data){ //search with orderId
        Node temp=first;
        int index=0;
        while(temp!=null){
            if(temp.data.getOrderId().equals(data.getOrderId())){
                return index;
            }
            index++;
            temp=temp.next;
        }
        return -1;
    }
    public boolean contains(OrderDetails data){
        return search(data)!=-1;
    }
    public int size(){ //size of the node
        int count=0;
        Node temp=first;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }
    private boolean isEmpty(){
        return first==null;
    }
    public void clear(){
        first=null;
    }
    public void printList(){
        Node temp=first;
        System.out.print("[");
        if(first==null){
            System.out.println("[empty]");
        }else{
            while(temp!=null){
                System.out.println(temp.data.toString());
                temp=temp.next;
            }
        }
    }
    public OrderDetails get(int index){
        if(index>=0 && index<size()){
            int count=0;
            Node temp=first;
            while(count<index){
                temp=temp.next;
                count++;
            }
            return temp.data;
        }
        return null;
    }

    public ObservableList<OrderDetails> toArray(){
        OrderDetails[] tempArray=new OrderDetails[size()];

        ObservableList<OrderDetails> obLIst= FXCollections.observableArrayList();

        int i=0;
        Node temp=first;
        while(temp!=null){
            tempArray[i++]=temp.data;
            obLIst.add(temp.data);
            temp=temp.next;
        }
        return obLIst;
    }



    class Node{
        private OrderDetails data;
        private Node next;
        Node(OrderDetails data){
            this.data=data;
        }
    }
}
