package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.print.DocFlavor;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    Button Singin,Singup;
    JFXTextField email,emailup;
    JFXPasswordField password,passwordup,passwordupc;
    Label sin,sup,errorcodesin,gotowebsite,errorcodesup;
    ImageView logo,cover,mainlogo,refreshissue;
    AnchorPane a1,a2;
    Scene mainscreen,first_scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        Parent main = FXMLLoader.load(getClass().getResource("aitca_screen.fxml"));
        primaryStage.setTitle("Hello World");
        Security.addProvider(new BouncyCastleProvider());

        first_scene=new Scene(root);
        //first scene lookup
        sin=(Label)first_scene.lookup("#gosignup");
        sup=(Label)first_scene.lookup("#gosignin");
        gotowebsite=(Label)first_scene.lookup("#gotowebsite");
        errorcodesin=(Label) first_scene.lookup("#errorcodesin");
        errorcodesup=(Label) first_scene.lookup("#errorcodesup");
        logo=(ImageView)first_scene.lookup("#logo");
        //mainlogo=(ImageView)first_scene.lookup("#inmainlogo");
        cover=(ImageView)first_scene.lookup("#coverscr");
        //refreshissue=(ImageView)first_scene.lookup("#refreshissue");
        a1=(AnchorPane)first_scene.lookup("#Signuplayout");
        a2=(AnchorPane)first_scene.lookup("#Signinlayout");
        email=(JFXTextField) first_scene.lookup("#emailsin");
        emailup=(JFXTextField) first_scene.lookup("#emailsup");
        password=(JFXPasswordField) first_scene.lookup("#passwordsin");
        passwordup=(JFXPasswordField) first_scene.lookup("#passwordsup");
        passwordupc=(JFXPasswordField) first_scene.lookup("#passwordsupc");
        Singin=(Button) first_scene.lookup("#Singin");
        Singup=(Button) first_scene.lookup("#Singup");

        //----------------------------------

        //fisrt sceen visibility
        Image logoimg=new Image(Main.class.getResource("../resources/aitcalogo.png").toURI().toString());
        logo.setImage(logoimg);

        //Image logoimg1=new Image(Main.class.getResource("../resources/aitcalogo.png").toURI().toString());
        //mainlogo.setImage(logoimg1);

        Image coverimg=new Image(Main.class.getResource("../resources/loginscreen.png").toURI().toString());
        cover.setImage(coverimg);

        sin.setOnMouseClicked(event ->{a1.setVisible(true);a2.setVisible(false);});
        sup.setOnMouseClicked(event ->{a1.setVisible(false);a2.setVisible(true);});

        gotowebsite.setOnMouseClicked(event -> {
           // getHostServices().showDocument("https://aitca.net/");
            mainscreen=new Scene(main);
            primaryStage.setScene(mainscreen);

        });

        //---------------------------

        //login action
        Singin.setOnAction(event -> {
            String emails=email.getText();
            String passwords=password.getText();
            if(Myutile.emptyfield(emails,passwords)&&Myutile.isValid(emails))
            {
                JSONObject tosend=Myutile.makejsonobj(emails,passwords);
                try {
                    URL u = new URL(UrlT.URL_SIN);
                    HttpsURLConnection conn = new Estaplish(Main.class, u).conn();
                    tosend=Myutile.getResault(conn,tosend);

                    boolean login = tosend.getBoolean("login");
                    boolean validate = tosend.getBoolean("validate");
                    String address=tosend.getString("address");
                    if(!login)
                        errorcodesin.setText("You email or password are incorrect");
                    else
                        if(!validate)
                            errorcodesin.setText("your email is not validated yet");
                        else
                        if(login&&validate) {
                            errorcodesin.setText("connect");
                        }
                    System.out.println(tosend.toString());
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }

        });
        //----------------------------

        //sign up action
        Singup.setOnAction(event -> {
            String emailfup=emailup.getText();
            String passwordfup=passwordup.getText();
            String passwordfupc=passwordupc.getText();
            System.out.println(emailfup+passwordfup+passwordfupc);
            if(!Myutile.emptyfield(emailfup,passwordfup,passwordfupc)) {
                errorcodesup.setText("Fill the fields");
                return;
            }
            if(!Myutile.isValid(emailfup)) {
                errorcodesup.setText("The email you entered in not valid");
                return;
            }


            JSONObject tosend=Myutile.makejsonobj(emailfup,passwordfup);
            try {
                URL u = new URL(UrlT.URL_SUP);
                HttpsURLConnection conn = new Estaplish(Main.class, u).conn();
                tosend=Myutile.getResault(conn,tosend);

                boolean created = tosend.getBoolean("created");
                boolean exist = tosend.getBoolean("exist");
                boolean validate = tosend.getBoolean("validate");
                if(exist&&validate)
                    errorcodesup.setText("This email already exist");
                else
                if(!validate&&!created)
                    errorcodesup.setText("Please check your email to validate your account");
                else
                if(created) {
                    errorcodesup.setText("Please, Check you Email for confirmation");
                }
            }catch (Exception e){
                System.out.println(e.toString());
            }

        });

        //----------------------------


        primaryStage.setScene(first_scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
