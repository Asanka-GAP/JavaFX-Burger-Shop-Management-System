package controller;

import db.OrderList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.OrderDetails;

import java.util.Optional;

public class SearchCustomerFormController {
    public Text lblName;
    public TextField txtFieldCustomerId;
    public TableView orderTable;
    public TableColumn colOrderId;
    public TableColumn colOty;
    public TableColumn ColTotal;

    OrderList list = OrderList.getInstance();

    public void searchCustomerKeyReleased(KeyEvent keyEvent) {
        ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();
        boolean isExists = false;
        for (int i=0; i<list.size();i++){
            if (txtFieldCustomerId.getText().equals(list.get(i).getCustomerId())){
                isExists = true;
                lblName.setText(list.get(i).getCustomerName());
                orderDetails.add(list.get(i));
            }
        }if (!isExists) lblName.setText("");
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOty.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("orderValue"));

        orderTable.setItems(orderDetails);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/searchForm.fxml"))));
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
