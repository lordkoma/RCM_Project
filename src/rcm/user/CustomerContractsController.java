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
public class CustomerContractsController implements Initializable {

    @FXML
    private TableView<ContractTable> ContractTable;
    @FXML
    private TableColumn<ContractTable,String> col_ContractNum;
    @FXML
    private TableColumn<ContractTable,String> col_StartDate;
    @FXML
    private TableColumn<ContractTable,String> col_EndDate;
    @FXML
    private TableColumn<ContractTable,String> coll_ApartmentNum;
    @FXML
    private TableColumn<ContractTable,String> col_CustomerName;
    @FXML
    private TextField textSearch;

        SQLmethods con = new SQLmethods();
    ObservableList<ContractTable> oblist = FXCollections.observableArrayList();
       String id= session.id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         col_ContractNum.setCellValueFactory(new PropertyValueFactory<>("ContractNum"));
         col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
         col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
         coll_ApartmentNum.setCellValueFactory(new PropertyValueFactory<>("ApartmentNum"));
         col_CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
         
      
         try {
             ContractTable.getItems().clear();
            con.VwieContractTable(oblist,id);
            ContractTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerContractsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContractTable.setItems(oblist);
        
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
    private void SearchContract(MouseEvent event) {
        
         col_ContractNum.setCellValueFactory(new PropertyValueFactory<>("ContractNum"));
         col_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
         col_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
         coll_ApartmentNum.setCellValueFactory(new PropertyValueFactory<>("ApartmentNum"));
         col_CustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
         
         
            ContractTable.setItems(oblist);
         try {

            con.searchContractTable(oblist, textSearch.getText(), id);
            ContractTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContractTable.setItems(oblist);
        
    }
    
}
