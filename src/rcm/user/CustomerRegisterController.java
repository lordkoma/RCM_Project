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
public class CustomerRegisterController implements Initializable {

    @FXML
    private Label lblerr;
    @FXML
    private Label lblsec;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtphone;
    @FXML
    private PasswordField txtpass;
    @FXML
    private PasswordField txtpassconf;
    
    SQLmethods sql = new SQLmethods();
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backLogin(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void signup(ActionEvent event) throws IOException, SQLException {
        if (txtname.getText().isEmpty() || txtemail.getText().isEmpty() || txtphone.getText().isEmpty() || txtpass.getText().isEmpty() || txtpassconf.getText().isEmpty()) {
            lblerr.setText("Please fill all the txtfilds");
            lblsec.setText("");
        } else if (!txtpass.getText().equals(txtpassconf.getText())) {
            lblsec.setText("");
            lblerr.setText("paswords doesn't match");
        } else {
            lblerr.setText("");
            lblsec.setText("You've been registered successfully");
            sql.add(id.getText(),txtname.getText(), txtemail.getText(), txtphone.getText(), txtpass.getText());
        }
    }

}
