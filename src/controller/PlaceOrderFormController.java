package controller;

import db.OrderList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.OrderDetails;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import  java.awt.*;

public class PlaceOrderFormController implements Initializable {
    public TextField txtOrderId;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtQTY;
    public Text lblTotal;



    public static double burgerPrice = 500;
    public Button btnPlaceOrder;

    private double total;

    public static OrderList list = OrderList.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        generateOrderId();
        generateCustomerId();
    }

    public  void generateOrderId() {
        if (list.size()==0){
            txtOrderId.setText("O0001");
        }else {
            String lastOrderId = list.get(list.size() - 1).getOrderId();
            int number = Integer.parseInt(lastOrderId.split("O")[1]); //1
            number++;//2
            txtOrderId.setText(String.format("O%04d", number)); //printf("",) //B0002
        }
    }

    public  void generateCustomerId() {
        if (list.size()==0){
            txtCustomerId.setText("C0001");
        }else {
            String lastCustomerId = list.get(list.size() - 1).getCustomerId();
            int number = Integer.parseInt(lastCustomerId.split("C")[1]); //1
            number++;//2
            txtCustomerId.setText(String.format("C%04d", number)); //printf("",) //B0002
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        if (txtCustomerName.getText().equals("") || txtQTY.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Something Missing..!!!");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        if (!txtCustomerName.getText().equals("") && !txtQTY.getText().equals("")){
            OrderDetails odObj= new OrderDetails(
                    txtOrderId.getText(),
                    txtCustomerId.getText(),
                    txtCustomerName.getText(),
                    1,
                    Integer.parseInt(txtQTY.getText()),
                    Double.parseDouble(lblTotal.getText())
            );
            list.add(odObj);
            generateOrderId();
            generateCustomerId();
            txtCustomerName.setText("");
            txtQTY.setText("");
            lblTotal.setText("0000.00");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed..!!!");
            alert.setContentText("Order placed successfully..!!!");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public void qtyKeyReleased(KeyEvent keyEvent) {

        try {
            int num = Integer.parseInt(txtQTY.getText());
            if (num > 0) {
                double price = num * 500.00;
                lblTotal.setText(Double.toString(price) + "0");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Quantity must be greater than 0");
                Optional<ButtonType> result = alert.showAndWait();
                lblTotal.setText("");
                txtQTY.setText("");

            }
        } catch (NumberFormatException ev) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid Quantity..!!!");
            Optional<ButtonType> result = alert.showAndWait();
txtQTY.setText("");
            lblTotal.setText("0000.00");

        }
    }
    private int index() {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getCustomerId().split("C")[1]) > Integer
                    .parseInt(list.get(index).getCustomerId().split("C")[1])) {
                index = i;
            }
        }
        return index;
    }
    public void customerIdKeyReleased(KeyEvent keyEvent) {
        boolean isExist=false;
        if (list.size()>0) {
                try {
                    if (txtCustomerId.getText().length()>5 || txtCustomerId.getText().length()<5 || txtCustomerId.getText().charAt(0) != 'C'
                            || Integer.parseInt(txtCustomerId.getText().split("C")[1]) == 0
                            || Integer.parseInt(list.get(index()).getCustomerId().split("C")[1]) + 1 < Integer
                            .parseInt(txtCustomerId.getText().split("C")[1])) {
                        btnPlaceOrder.setDisable(true);


                    }else{
                        txtCustomerName.setEditable(true);
                        btnPlaceOrder.setDisable(false);
                    }
                } catch (NumberFormatException er) {

                    btnPlaceOrder.setDisable(true);
                }



            }
            try {
                if (txtCustomerId.getText().length() == 5 && txtCustomerId.getText().charAt(0) == 'C'
                        && Integer.parseInt(txtCustomerId.getText().split("C")[1]) > 0
                        && Integer.parseInt(list.get(index()).getCustomerId().split("C")[1]) + 1 >= Integer
                        .parseInt(txtCustomerId.getText().split("C")[1])) {

                    for (int i = 0; i < list.size(); i++) {
                        if (txtCustomerId.getText().equals(list.get(i).getCustomerId())) {
                            txtCustomerName.setText(list.get(i).getCustomerName());
                            txtCustomerName.setEditable(false);
                            isExist = true;
                        }
                    }
                    if (!isExist) {

                        txtCustomerName.setText("");
                    }
                }

            } catch (Exception er) {

                // placeOrder.setEnabled(false);
            }



    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBordForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure want to exit..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            System.exit(0);
        }
    }
}
