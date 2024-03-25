package controller;

import db.OrderList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class SearchOrderFormController {
    public Text lblCustomerId;
    public Text lblName;
    public Text lblQty;
    public Text lblTotal;
    public Text lblStatus;

OrderList list = OrderList.getInstance();
    public TextField txtFieldOrderId;

    public void searchOrderKeyReleased(KeyEvent keyEvent) {
        boolean isExists =false;
        for (int i=0;i<list.size();i++){
            if (txtFieldOrderId.getText().equals(list.get(i).getOrderId())){
                isExists =true;
                lblCustomerId.setText(list.get(i).getCustomerId());
                lblName.setText(list.get(i).getCustomerName());
                lblQty.setText(Integer.toString(list.get(i).getOrderQTY()));
                lblTotal.setText(Double.toString(list.get(i).getOrderValue()));
                lblStatus.setText(list.get(i).getOrderStatus());
            }
        }
        if (!isExists){
            lblCustomerId.setText("");
            lblName.setText("");
            lblQty.setText("");
            lblTotal.setText("");
            lblStatus.setText("");
        }
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/searchForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }
}
