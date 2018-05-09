package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AitcaScreen implements Initializable {


    @FXML
    private AnchorPane isseupanel;

    @FXML
    private ImageView refreshissue;

    @FXML
    private ImageView inmainlogo;

    @FXML
    private Label emailadd;

    @FXML
    private Label walletadd;

    @FXML
    void createAsset(ActionEvent event) {
        System.out.println("dd");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
