package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public StackPane rootFullPage;
    public StackPane rootHome;

    public void btnHomeOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnItemOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnItemStockOnAction(ActionEvent actionEvent) {
    }

    public void btnSoldDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = new Stage();
        Stage stage1 = (Stage) this.rootFullPage.getScene().getWindow();
        stage1.close();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }
}
