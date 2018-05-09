package sample;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.io.IOException;

public class AlertB {
    public  void display(String title, String message) {
        Stage window = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();
        }
        catch (IOException e){}

    }
}
