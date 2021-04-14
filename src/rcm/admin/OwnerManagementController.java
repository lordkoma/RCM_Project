/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author oke
 */
 
public class OwnerManagementController implements Initializable {
Insert i=new Insert();

    @FXML
    private TextField txtp;
    @FXML
    private TextField txtn;
    @FXML
    private TextField txtEmail;
    private TextField txtPassword;
    @FXML
    private TextField txtID;
    @FXML
    private TableView<OwnerTable> OwnerTable;
    @FXML
    private TableColumn<OwnerTable,String> col_id;
    @FXML
    private TableColumn<OwnerTable,String> col_name;
    @FXML
    private TableColumn<OwnerTable,String> col_Phone;
    @FXML
    private TableColumn<OwnerTable,String> col_email;
    @FXML
    private TextField textSearch;
    
    Insert con = new Insert();
    ObservableList<OwnerTable> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        col_Phone.setCellValueFactory(new PropertyValueFactory<>("wonerPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("ownerEmail"));
       

        try {

            con.ViewOwnerTable(oblist);
            OwnerTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OwnerTable.setItems(oblist);
    }    

    @FXML
    private void buttback(MouseEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttAdd(ActionEvent event) throws SQLException {
        i.insertOwner(txtID.getText(), txtn.getText(), txtp.getText(), txtEmail.getText());
         con.ViewOwnerTable(oblist);
            OwnerTable.setItems(oblist);
    }

    @FXML
    private void OwnerSearch(MouseEvent event) {
         col_id.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        col_Phone.setCellValueFactory(new PropertyValueFactory<>("wonerPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("ownerEmail"));
        

        try {

            con.searchOwnerTable(oblist,textSearch.getText());
            OwnerTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OwnerTable.setItems(oblist);
    }

    @FXML
    private void buttUp(ActionEvent event) throws SQLException {
        
        
        String id = txtID.getText();
        String name = txtn.getText();
        String phon = txtp.getText();
        String email = txtEmail.getText();
        
        con.updateOwners(id,name ,phon ,email);
        con.ViewOwnerTable(oblist);
        OwnerTable.setItems(oblist);
}

    @FXML
    private void onP(MouseEvent event) {
        OwnerTable selectedItem = OwnerTable.getSelectionModel().getSelectedItem();

        txtID.setText(selectedItem.getOwnerId());
        txtn.setText(selectedItem.getOwnerName() + "");
        txtp.setText(selectedItem.getWonerPhone() + "");
        txtEmail.setText(selectedItem.getOwnerEmail() + "");
    }

    @FXML
    private void Clean(ActionEvent event) {
         txtID.setText("");
        txtn.setText("");
        txtp.setText("");
        txtEmail.setText("");
    }

    @FXML
    private void buttDelet(ActionEvent event) throws SQLException {
         int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            OwnerTable selectedItem = OwnerTable.getSelectionModel().getSelectedItem();
            con.deleteOwners(selectedItem.getOwnerId());

            con.ViewOwnerTable(oblist);
            OwnerTable.setItems(oblist);
        }
    }
}
