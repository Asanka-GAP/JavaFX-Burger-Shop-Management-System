package controller;

import db.OrderList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateOrderFormController implements Initializable {
    public ComboBox<String> orderStatusComboBox;
    public TextField txtFieldOrderId;
    public Text lblCustomerId;
    public Text lblName;
    public TextField txtFieldQTY;
    public Text lblTotal;

    OrderList list = OrderList.getInstance();
    private String[] status = {"PENDING","DELIVERED","CANCEL"};

    public void btnExitOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure want to exit..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            System.exit(0);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderStatusComboBox.getItems().addAll(status);
    }

    public void orderIdKeyReleased(KeyEvent keyEvent) {
        boolean isExist = false;
        for (int i=0;i<list.size();i++){
            if (txtFieldOrderId.getText().equals(list.get(i).getOrderId())){
                isExist =true;
                if (list.get(i).getOrderStatus().equals("CANCEL")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Order has been cancelled");
                    alert.setContentText("This order already cancelled..!!!");
                    Optional<ButtonType> result = alert.showAndWait();

                    lblCustomerId.setText("");
                        lblName.setText("");
                        txtFieldQTY.setText("");
                        lblTotal.setText("");
                        txtFieldOrderId.setText("");
                        return;
                }
                if (list.get(i).getOrderStatus().equals("DELEVERD")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Order has been Delivered");
                    alert.setContentText("This order already delivered..!!!");
                    Optional<ButtonType> result = alert.showAndWait();

                    lblCustomerId.setText("");
                    lblName.setText("");
                    txtFieldQTY.setText("");
                    lblTotal.setText("");
                    txtFieldOrderId.setText("");
                    return;

                }
                lblCustomerId.setText(list.get(i).getCustomerId());
                lblName.setText(list.get(i).getCustomerName());
                txtFieldQTY.setText(Integer.toString(list.get(i).getOrderQTY()));
                lblTotal.setText(Double.toString(list.get(i).getOrderValue()));

            }
        }
        if (!isExist){
            lblCustomerId.setText("");
            lblName.setText("");
            txtFieldQTY.setText("");
            lblTotal.setText("");
        }
    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        try {

            if (Integer.parseInt(txtFieldQTY.getText()) > 0) {
                double price = Integer.parseInt(txtFieldQTY.getText()) * 500.00;
                lblTotal.setText(Double.toString(price) + "0");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Quantity must be greater than 0");
                Optional<ButtonType> result = alert.showAndWait();
                lblTotal.setText("");
                txtFieldQTY.setText("");

            }

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid Quantity..!!!");
            Optional<ButtonType> result = alert.showAndWait();
            txtFieldQTY.setText("");
            lblTotal.setText("");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (txtFieldOrderId.getText().equals("") || txtFieldQTY.getText().equals("") || lblName.getText().equals("") || lblCustomerId.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Something Missing..!!!");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        for (int i=0;i<list.size();i++){
                if (list.get(i).getOrderId().equals(txtFieldOrderId.getText())){
                    list.get(i).setOrderQTY(Integer.parseInt(txtFieldQTY.getText()));
                    list.get(i).setOrderValue(Double.parseDouble(lblTotal.getText()));

                    String status = orderStatusComboBox.getValue();

                    switch (status){
                        case "PENDING":
                            list.get(i).setOrderStatus(1);
                            break;
                        case "CANCEL":
                            list.get(i).setOrderStatus(2);
                            break;
                        case "DELIVERED":
                            list.get(i).setOrderStatus(3);
                            break;
                    }

                }
            }
        txtFieldOrderId.setText("");
        txtFieldQTY.setText("");
        lblName.setText("");
        lblTotal.setText("0000.00");
        lblCustomerId.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Updated..!!!");
        alert.setContentText("Order updated successfully..!!!");
        Optional<ButtonType> result = alert.showAndWait();
        }
    }

