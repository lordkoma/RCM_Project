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

/**
 * FXML Controller class
 *
 * @author oke
 */
public class CustomerSearchController implements Initializable {

    @FXML
    private TextField textSearch;
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

    /**
     * Initializes the controller class.
     */
    ObservableList<ApartmentTable> oblist = FXCollections.observableArrayList();
    SQLmethods con = new SQLmethods();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        col_ApaId.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        col_ApaType.setCellValueFactory(new PropertyValueFactory<>("apartmentType"));
        col_ApaRoomNum.setCellValueFactory(new PropertyValueFactory<>("apartmentRoomNo"));
        col_ApaDescription.setCellValueFactory(new PropertyValueFactory<>("apartmentDescription"));
        co_ApaPricePerY.setCellValueFactory(new PropertyValueFactory<>("apartmentPricePerYear"));
        col_ApaBuilding_FK.setCellValueFactory(new PropertyValueFactory<>("buildingId"));

        try {

            con.VwieApartmentTable(oblist);
            ApartmentTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ApartmentTable.setItems(oblist);
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
    private void bookRoom(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("custumerRCInfo.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SearchContract(MouseEvent event) {
        col_ApaId.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        col_ApaType.setCellValueFactory(new PropertyValueFactory<>("apartmentType"));
        col_ApaRoomNum.setCellValueFactory(new PropertyValueFactory<>("apartmentRoomNo"));
        col_ApaDescription.setCellValueFactory(new PropertyValueFactory<>("apartmentDescription"));
        co_ApaPricePerY.setCellValueFactory(new PropertyValueFactory<>("apartmentPricePerYear"));
        col_ApaBuilding_FK.setCellValueFactory(new PropertyValueFactory<>("buildingId"));

        try {

            con.searchApartmentTable(oblist, textSearch.getText());
            ApartmentTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);

        }

        ApartmentTable.setItems(oblist);
    }

}
