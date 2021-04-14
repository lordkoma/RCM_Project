/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oke
 */
public class CustumerRCInfoController implements Initializable {

    @FXML
    private TextField textApartemId;
    @FXML
    private TextField textEndDate;
    @FXML
    private TextField textStartDat;
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
    String id = session.id;

    @FXML
    private void backSearch(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AddCountret(MouseEvent event) throws SQLException {
        int checkavailabilty = sql.checkAva(textApartemId.getText());
        if (!(textApartemId.getText().isEmpty() || textEndDate.getText().isEmpty() || textStartDat.getText().isEmpty())) {
            if (checkavailabilty == 1) {
                SQLmethods s1 = new SQLmethods();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
                LocalDateTime now = LocalDateTime.now();

                s1.addContract(textApartemId.getText(), textStartDat.getText(), textEndDate.getText(), id);
                s1.addalert("System", dtf.format(now), "New Contract for Appartment nubmer " + textApartemId.getText() + " ", "New", id);
                s1.updateAvailability(textApartemId.getText());
                lblerr.setText("");
                lblsec.setText("You're apartment booked successfully");
            } else if (checkavailabilty == 2) {
                lblsec.setText("");
                lblerr.setText("Apartment is booked!!");
            }else if(checkavailabilty == 3){
                lblsec.setText("");
                lblerr.setText("Please enter a valid apaetment number!!");
            }
        } else {
            lblsec.setText("");
            lblerr.setText("PLEASE CHECK EVERY TEXTFILD!!");
        }
    }

}
