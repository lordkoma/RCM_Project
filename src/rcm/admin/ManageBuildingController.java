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
 * @author ho0o0
 */
public class ManageBuildingController implements Initializable {

    Insert i = new Insert();
    @FXML
    private TextField txtBa;
    @FXML
    private TextField txtNa;
    @FXML
    private TextField txtOid;
    @FXML
    private TableView<BuildingTable> BuildingTable;
    @FXML
    private TableColumn<BuildingTable, String> col_BulNum;
    @FXML
    private TableColumn<BuildingTable, String> col_NumOfApa;
    @FXML
    private TableColumn<BuildingTable, String> col_BuilAddr;
    @FXML
    private TableColumn<BuildingTable, String> col_OwnerName;
    @FXML
    private TextField textSearch;

    Insert con = new Insert();
    ObservableList<BuildingTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_BulNum.setCellValueFactory(new PropertyValueFactory<>("buildingID"));
        col_NumOfApa.setCellValueFactory(new PropertyValueFactory<>("numOfApartemnts"));
        col_BuilAddr.setCellValueFactory(new PropertyValueFactory<>("buildingAddress"));
        col_OwnerName.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));

        try {

            con.ViewBuildingTable(oblist);
            BuildingTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ManageBuildingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuildingTable.setItems(oblist);

    }

    @FXML
    private void buttback(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManageResidentialComplex.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttAdd(ActionEvent event) throws SQLException {
        i.insertBuilding(txtNa.getText(), txtBa.getText(), txtOid.getText());
        con.ViewBuildingTable(oblist);
        BuildingTable.setItems(oblist);
        con.ViewBuildingTable(oblist);
        BuildingTable.setItems(oblist);
    }

    @FXML
    private void BuildingSearch(MouseEvent event) {
        col_BulNum.setCellValueFactory(new PropertyValueFactory<>("buildingID"));
        col_NumOfApa.setCellValueFactory(new PropertyValueFactory<>("numOfApartemnts"));
        col_BuilAddr.setCellValueFactory(new PropertyValueFactory<>("buildingAddress"));
        col_OwnerName.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));

        try {

            con.searchBuildingTable(oblist, textSearch.getText());
            BuildingTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ManageBuildingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuildingTable.setItems(oblist);
    }

    @FXML
    private void onM(MouseEvent event) {
    BuildingTable selectedItem = BuildingTable.getSelectionModel().getSelectedItem();

        txtNa.setText(selectedItem.getNumOfApartemnts()+ "");
        txtBa.setText(selectedItem.getBuildingAddress() + "");
        txtOid.setText(selectedItem.getOwnerName());
       
    }

    @FXML
    private void buttUp(ActionEvent event) throws SQLException {
         BuildingTable selectedItem = BuildingTable.getSelectionModel().getSelectedItem();
        String nofA = txtNa.getText();
        String bA = txtBa.getText();
        
        
        String bid=selectedItem.getBuildingID();

        con.updateBuildings(bid,nofA ,bA );
        con.ViewBuildingTable(oblist);
        BuildingTable.setItems(oblist);
    }

    @FXML
    private void buttDelet(ActionEvent event) throws SQLException {
        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            BuildingTable selectedItem = BuildingTable.getSelectionModel().getSelectedItem();
            con.deleteBuildings(selectedItem.getBuildingID());

            con.ViewBuildingTable(oblist);
            BuildingTable.setItems(oblist);

        }
    }

    @FXML
    private void buttClean(ActionEvent event) {
        txtNa.setText("");
        txtBa.setText("");
        txtOid.setText("");
    }

}
