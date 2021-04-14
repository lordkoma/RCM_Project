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
import javafx.scene.control.Button;
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
public class ContractManagementController implements Initializable {

    Insert i = new Insert();
    @FXML
    private TextField txtSd;
    @FXML
    private TextField txtApaId;
    @FXML
    private TextField txtEd;
    @FXML
    private TextField txtUid;
    @FXML
    private TableView<ContractTable> ContractTable;
    @FXML
    private TableColumn<ContractTable, String> col_ContractNum;
    @FXML
    private TableColumn<ContractTable, String> col_StartDate;
    @FXML
    private TableColumn<ContractTable, String> col_EndDate;
    @FXML
    private TableColumn<ContractTable, Integer> coll_ApartmentNum;
    @FXML
    private TableColumn<ContractTable, String> col_CustomerName;
    @FXML
    private TextField textSearch;

    Insert con = new Insert();
    ObservableList<ContractTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_ContractNum.setCellValueFactory(new PropertyValueFactory<>("ContractNum"));
        col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        coll_ApartmentNum.setCellValueFactory(new PropertyValueFactory<>("ApartmentNum"));
        col_CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));

        try {

            con.ViewContractTable(oblist);
            ContractTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ContractManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContractTable.setItems(oblist);

    }

    @FXML
    private void buttback(MouseEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void buttAdd(ActionEvent event) throws SQLException {
        i.insertContract(txtSd.getText(), txtEd.getText(), txtApaId.getText(), txtUid.getText());
        con.ViewContractTable(oblist);
        ContractTable.setItems(oblist);
    }

    @FXML
    private void SearchContract(MouseEvent event) {

        col_ContractNum.setCellValueFactory(new PropertyValueFactory<>("ContractNum"));
        col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        coll_ApartmentNum.setCellValueFactory(new PropertyValueFactory<>("ApartmentNum"));
        col_CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));

        try {

            con.searchContractTable(oblist, textSearch.getText());
            ContractTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ContractManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContractTable.setItems(oblist);

    }

    @FXML
    private void buttUP(ActionEvent event) throws SQLException {
        ContractTable selectedItem = ContractTable.getSelectionModel().getSelectedItem();
        String startD = txtSd.getText();
        String endD = txtEd.getText();
        String apaID = txtApaId.getText();
        
        String Cid=selectedItem.getContractNum();

        con.updateContract(Cid,startD ,endD ,apaID);
        con.ViewContractTable(oblist);
        ContractTable.setItems(oblist);
    }

    @FXML
    private void buttDlete(ActionEvent event) throws SQLException {
        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            ContractTable selectedItem = ContractTable.getSelectionModel().getSelectedItem();
            con.deleteContract(selectedItem.getContractNum());

            con.ViewContractTable(oblist);
            ContractTable.setItems(oblist);
        }
    }

    @FXML
    private void buttClean(ActionEvent event) {
        txtSd.setText("");
        txtEd.setText("");
        txtApaId.setText("");
        txtUid.setText("");
    }

    @FXML
    private void onMP(MouseEvent event) {
        ContractTable selectedItem = ContractTable.getSelectionModel().getSelectedItem();

        txtSd.setText(selectedItem.getStartDate());
        txtEd.setText(selectedItem.getEndDate() + "");
        txtApaId.setText(selectedItem.getApartmentNum() + "");
        txtUid.setText(selectedItem.getCustomerName() + "");

    }

}
