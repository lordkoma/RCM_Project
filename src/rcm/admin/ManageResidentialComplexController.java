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
import javafx.scene.control.TextArea;
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
public class ManageResidentialComplexController implements Initializable {

    Insert i = new Insert();
    @FXML
    private TextField txtNr;
    @FXML
    private TextField txtAt;
    @FXML
    private TextField txtAp;
    @FXML
    private TextArea txtAd;
    @FXML
    private TextField txtBid;
    @FXML
    private TableView<ApartmentTable> ApartmentTable;
    @FXML
    private TableColumn<ApartmentTable, String> col_ApaId;
    @FXML
    private TableColumn<ApartmentTable, String> col_ApaType;
    @FXML
    private TableColumn<ApartmentTable, String> col_ApaRoomNum;
    @FXML
    private TableColumn<ApartmentTable, String> col_ApaDescription;
    @FXML
    private TableColumn<ApartmentTable, String> co_ApaPricePerY;
    @FXML
    private TableColumn<ApartmentTable, String> col_ApaBuilding_FK;
    @FXML
    private TextField textSearch;

    Insert con = new Insert();
    ObservableList<ApartmentTable> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ApartmentTable, String> col_Available;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_ApaId.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        col_ApaType.setCellValueFactory(new PropertyValueFactory<>("apartmentType"));
        col_ApaRoomNum.setCellValueFactory(new PropertyValueFactory<>("apartmentRoomNo"));
        col_ApaDescription.setCellValueFactory(new PropertyValueFactory<>("apartmentDescription"));
        co_ApaPricePerY.setCellValueFactory(new PropertyValueFactory<>("apartmentPricePerYear"));
        col_Available.setCellValueFactory(new PropertyValueFactory<>("apartmentAvailable"));
        col_ApaBuilding_FK.setCellValueFactory(new PropertyValueFactory<>("buildingId"));

        try {

            con.ViewApartmentTable(oblist);
            ApartmentTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ManageResidentialComplexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ApartmentTable.setItems(oblist);
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
    private void buttBuilding(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManageBuilding.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttAdd(ActionEvent event) throws SQLException {
        i.insertResidentialComplex(txtAt.getText(), txtNr.getText(), txtAd.getText(), txtAp.getText(), txtBid.getText());
        con.ViewApartmentTable(oblist);
        ApartmentTable.setItems(oblist);
    }

    @FXML
    private void ApartmentSearch(MouseEvent event) {
        col_ApaId.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        col_ApaType.setCellValueFactory(new PropertyValueFactory<>("apartmentType"));
        col_ApaRoomNum.setCellValueFactory(new PropertyValueFactory<>("apartmentRoomNo"));
        col_ApaDescription.setCellValueFactory(new PropertyValueFactory<>("apartmentDescription"));
        co_ApaPricePerY.setCellValueFactory(new PropertyValueFactory<>("apartmentPricePerYear"));
        col_Available.setCellValueFactory(new PropertyValueFactory<>("apartmentAvailable"));
        col_ApaBuilding_FK.setCellValueFactory(new PropertyValueFactory<>("buildingId"));

        try {

            con.searchApartmentTable(oblist, textSearch.getText());
            ApartmentTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(ManageResidentialComplexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ApartmentTable.setItems(oblist);
    }

    @FXML
    private void buttDelet(ActionEvent event) throws SQLException {

        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            ApartmentTable selectedItem = ApartmentTable.getSelectionModel().getSelectedItem();
            con.deleteApartments(selectedItem.getApartmentId());

            con.ViewApartmentTable(oblist);
            ApartmentTable.setItems(oblist);
        }
    }

    @FXML
    private void buttClean(ActionEvent event) {
         txtAt.setText("");
        txtNr.setText("");
        txtAd.setText("");
        txtAp.setText("");
        txtBid.setText("");
    }

    @FXML
    private void buttUp(ActionEvent event) throws SQLException {
        ApartmentTable selectedItem = ApartmentTable.getSelectionModel().getSelectedItem();
         String aid=selectedItem.getApartmentId();
        String At = txtAt.getText();
        String Nr = txtNr.getText();
        String Ad = txtAd.getText();
        String Ap = txtAp.getText();
        String Bid = txtBid.getText();

        con.updateApartments(aid, At, Nr, Ad, Ap,Bid);
        con.ViewApartmentTable(oblist);
        ApartmentTable.setItems(oblist);
    }

    @FXML
    private void onP(MouseEvent event) {
        ApartmentTable selectedItem = ApartmentTable.getSelectionModel().getSelectedItem();
        txtAt.setText(selectedItem.getApartmentType()+ "");
        txtNr.setText(selectedItem.getApartmentRoomNo() + "");
        txtAd.setText(selectedItem.getApartmentDescription());
        txtAp.setText(selectedItem.getApartmentPricePerYear()+ "");
        txtBid.setText(selectedItem.getBuildingId()+ "");
       
    }
}
