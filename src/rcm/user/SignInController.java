/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oke
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Label lblerr;
    @FXML
    private Label lblsec;
    SQLmethods sql = new SQLmethods();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void backStart(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void customerRegister(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("customerRegister.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        int user = sql.login(txtuser.getText(), txtpass.getText());

        switch (user) {
            case 1:

                Parent root = FXMLLoader.load(getClass().getResource("/rcm/admin/HomePage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;

            case 2:
                root = FXMLLoader.load(getClass().getResource("customerHomePage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case 3:
                lblerr.setText("USERNAME/PASSWORD IS WRONG");
                break;
        }

    }


}
