import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage primaryStage) {

        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/dashBordForm.fxml"))));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

        }catch (Exception e){

        }
    }
}
