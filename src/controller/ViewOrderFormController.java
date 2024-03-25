package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ViewOrderFormController {

    public void btnProcessingOrderOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/processingOrderForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }

    public void btnDeliveredOrderOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/deliveredOrderForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }

    public void btnCancelledOrderOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/cancelledOrderForm.fxml"))));
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

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Stage stage =new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashBordForm.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }catch (Exception e){}
    }
}
