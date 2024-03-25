package controller;

import db.OrderList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.OrderDetails;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProcessingOrderFormController implements Initializable {
    public TableView processingOrderTable;
    public TableColumn colOrderId;
    public TableColumn colName;
    public TableColumn colQTY;
    public TableColumn colCustomerId;
    public TableColumn colTotal;

    OrderList list = OrderList.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("orderValue"));

        processingOrderTable.setItems(getOrderDetails());


    }

    ObservableList<OrderDetails> getOrderDetails(){
        ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();

        for (int i=0;i<list.size();i++){
            if (list.get(i).getOrderStatus().equals("PENDING")){
                orderDetails.add(list.get(i));
            }
        }
        return orderDetails;
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

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/viewOrderForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }
}
