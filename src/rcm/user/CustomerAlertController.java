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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oke
 */
public class CustomerAlertController implements Initializable {

    @FXML
    private TableView<alertsTable> t_alerts;
    @FXML
    private TableColumn<alertsTable, String> t_form;
    @FXML
    private TableColumn<alertsTable, String> t_date;
    @FXML
    private TableColumn<alertsTable, String> t_desc;
    @FXML
    private TableColumn<alertsTable, String> t_state;
    @FXML
    private TextField textSearch;

    SQLmethods con = new SQLmethods();
    ObservableList<alertsTable> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<alertsTable, String> t_id;
    
    String id= session.id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t_id.setCellValueFactory(new PropertyValueFactory<>("al_id"));
        t_form.setCellValueFactory(new PropertyValueFactory<>("t_from"));
        t_date.setCellValueFactory(new PropertyValueFactory<>("t_date"));
        t_desc.setCellValueFactory(new PropertyValueFactory<>("t_desc"));
        t_state.setCellValueFactory(new PropertyValueFactory<>("t_state"));

        try {

            con.VwieAlertsTable(oblist, id);
            t_alerts.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAlertController.class.getName()).log(Level.SEVERE, null, ex);
        }

        t_alerts.setItems(oblist);

    }

    @FXML
    private void backHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void alerts_search(MouseEvent event) {
        
        t_alerts.getItems().clear();
        
        t_id.setCellValueFactory(new PropertyValueFactory<>("al_id"));
        t_form.setCellValueFactory(new PropertyValueFactory<>("t_from"));
        t_date.setCellValueFactory(new PropertyValueFactory<>("t_date"));
        t_desc.setCellValueFactory(new PropertyValueFactory<>("t_desc"));
        t_state.setCellValueFactory(new PropertyValueFactory<>("t_state"));

        try {

            con.searchAlertsTable(oblist, textSearch.getText(), id);
            t_alerts.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAlertController.class.getName()).log(Level.SEVERE, null, ex);
        }

        t_alerts.setItems(oblist);

    }

}
