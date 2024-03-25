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
import model.BestCustomerDetails;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BestCustomerFormController implements Initializable {
    public TableColumn colCustomerId;
    public TableColumn colName;
    public TableColumn colTotal;

    OrderList list = OrderList.getInstance();


    public TableView bestCustomerTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        bestCustomerTable.setItems(getBestCustomer());

    }

    ObservableList<BestCustomerDetails> getBestCustomer() {
        ObservableList<BestCustomerDetails> bestCustomer = FXCollections.observableArrayList();


        String[] sortCustomerIdArray = new String[0];
        String[] sortCustomerName = new String[0];
        double[] customerTotalBuyingArray = new double[0];

        for (int i = 0; i < list.size(); i++) {
            boolean isExist = false;
            for (int j = 0; j < sortCustomerIdArray.length; j++) {
                if (sortCustomerIdArray[j].equals(list.get(i).getCustomerId())) {
                    if (!list.get(i).getOrderStatus().equals("CANCEL")) {
                        customerTotalBuyingArray[j] += list.get(i).getOrderValue();
                        isExist = true;
                    }


                }
            }
            if (!isExist && !list.get(i).getOrderStatus().equals("CANCEL")) {
                String[] tempSortCustomerArray = new String[sortCustomerIdArray.length + 1];
                String[] tempSortCustomerName = new String[sortCustomerName.length + 1];
                double[] tempCustomerTotalBuyingArray = new double[customerTotalBuyingArray.length + 1];
                for (int j = 0; j < sortCustomerIdArray.length; j++) {
                    tempSortCustomerArray[j] = sortCustomerIdArray[j];
                    tempSortCustomerName[j] = sortCustomerName[j];
                    tempCustomerTotalBuyingArray[j] = customerTotalBuyingArray[j];
                }
                sortCustomerIdArray = tempSortCustomerArray;
                sortCustomerName = tempSortCustomerName;
                customerTotalBuyingArray = tempCustomerTotalBuyingArray;


                sortCustomerIdArray[sortCustomerIdArray.length - 1] = list.get(i).getCustomerId();
                sortCustomerName[sortCustomerName.length - 1] = list.get(i).getCustomerName();
                customerTotalBuyingArray[customerTotalBuyingArray.length - 1] = list.get(i).getOrderValue();
            }
        }
        // sort
        for (int i = 1; i < sortCustomerIdArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (customerTotalBuyingArray[j] < customerTotalBuyingArray[i]) {
                    String temp = sortCustomerIdArray[j];
                    sortCustomerIdArray[j] = sortCustomerIdArray[i];
                    sortCustomerIdArray[i] = temp;
                    temp = sortCustomerName[j];
                    sortCustomerName[j] = sortCustomerName[i];
                    sortCustomerName[i] = temp;
                    double tempd = customerTotalBuyingArray[j];
                    customerTotalBuyingArray[j] = customerTotalBuyingArray[i];
                    customerTotalBuyingArray[i] = tempd;
                }
            }
        }


        for (int i = 0; i < sortCustomerIdArray.length; i++) {

            bestCustomer.add(new BestCustomerDetails(sortCustomerIdArray[i],sortCustomerName[i],customerTotalBuyingArray[i]));

        }

        return bestCustomer;

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
