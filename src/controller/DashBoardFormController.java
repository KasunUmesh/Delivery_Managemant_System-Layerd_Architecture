package controller;

import animatefx.animation.FadeIn;
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

    public void initialize(){

        try {
            StackPane pane = FXMLLoader.load(this.getClass().getResource("../view/HomePageForm.fxml"));
            rootHome.getChildren().setAll(pane);
            pane.setMaxWidth(1634);
            pane.setMaxHeight(1010);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(this.getClass().getResource("../view/HomePageForm.fxml"));
        rootHome.getChildren().setAll(pane);
//        pane.setMaxWidth(1634);
//        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(this.getClass().getResource("../view/EmployeeForm.fxml"));
        rootHome.getChildren().setAll(pane);

        new FadeIn(rootHome).play();
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(this.getClass().getResource("../view/VehicleForm.fxml"));
        rootHome.getChildren().setAll(pane);

        new FadeIn(rootHome).play();
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
        stage.show();

    }
}
