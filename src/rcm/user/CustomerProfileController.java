/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oke
 */
public class CustomerProfileController implements Initializable {

    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNumber;
    SQLmethods sql = new SQLmethods();
    String id = session.id;
    ArrayList<String> profile = new ArrayList<String>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            profile = sql.viewProfile(id);
            lblName.setText(profile.get(0));
            lblNumber.setText(profile.get(1));
            lblEmail.setText(profile.get(2));
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
