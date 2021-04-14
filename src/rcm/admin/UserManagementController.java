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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ho0o0
 */
public class UserManagementController implements Initializable {

    Insert i = new Insert();

    @FXML
    private TextField txtP;
    @FXML
    private TextField txtN;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtID;
    @FXML
    private TableView<UserTable> UserTable;
    @FXML
    private TableColumn<UserTable, String> col_UserId;
    @FXML
    private TableColumn<UserTable, String> col_Username;
    @FXML
    private TableColumn<UserTable, String> col_PhoneNo;
    @FXML
    private TableColumn<UserTable, String> col_Email;
    @FXML
    private TableColumn<UserTable, String> col_userPassword;
    @FXML
    private TableColumn<UserTable, String> col_UserType;
    @FXML
    private TextField textSearch;

    Insert con = new Insert();
    ObservableList<UserTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_UserId.setCellValueFactory(new PropertyValueFactory<>("Userid"));
        col_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_PhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNO"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_userPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_UserType.setCellValueFactory(new PropertyValueFactory<>("UserType"));

        try {

            con.ViewUserTable(oblist);
            UserTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        UserTable.setItems(oblist);
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
        i.insertUsers(txtID.getText(), txtN.getText(), txtP.getText(), txtEmail.getText(), txtPassword.getText());
        con.ViewUserTable(oblist);
        UserTable.setItems(oblist);
    }

    @FXML
    private void buttUP(ActionEvent event) throws SQLException {
        String id = txtID.getText();
        String name = txtN.getText();
        String phone = txtP.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        con.updateUser(id, name, phone, email, password);
        con.ViewUserTable(oblist);
        UserTable.setItems(oblist);
    }

    private void UserSearch(MouseEvent event) {
        UserTable.getItems().clear();
        oblist.removeAll(oblist);
        

        try {

            con.searchUserTable(oblist, textSearch.getText());
            UserTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_UserId.setCellValueFactory(new PropertyValueFactory<>("Userid"));
        col_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_PhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNO"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_userPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_UserType.setCellValueFactory(new PropertyValueFactory<>("UserType"));

        UserTable.setItems(oblist);
    }

    @FXML
    private void buttDelet(ActionEvent event) throws SQLException {

        int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (des == 0) {
            UserTable selectedItem = UserTable.getSelectionModel().getSelectedItem();
            con.deleteUser(selectedItem.getUserid());

            con.ViewUserTable(oblist);
            UserTable.setItems(oblist);

        }
    }

    @FXML
    private void userTablemouseP(MouseEvent event) {
        UserTable selectedItem = UserTable.getSelectionModel().getSelectedItem();

        txtID.setText(selectedItem.getUserid());
        txtN.setText(selectedItem.getUsername() + "");
        txtP.setText(selectedItem.getPhoneNO());
        txtEmail.setText(selectedItem.getEmail() + "");
        txtPassword.setText(selectedItem.getPassword() + "");
    }

    @FXML
    private void buttClean(ActionEvent event) {
        txtID.setText("");
        txtN.setText("");
        txtP.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    @FXML
    private void SearchUserKey(KeyEvent event) {
        col_UserId.setCellValueFactory(new PropertyValueFactory<>("Userid"));
        col_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_PhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNO"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_userPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_UserType.setCellValueFactory(new PropertyValueFactory<>("UserType"));

        try {

            con.searchUserTable(oblist, textSearch.getText());
            UserTable.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        UserTable.setItems(oblist);
    }

}
