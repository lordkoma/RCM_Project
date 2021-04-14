/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author ho0o0
 */
public class Insert {

    private Connection connect() throws SQLException {

        Connection con = null;
        String url = "jdbc:sqlite:E:\\Desktop\\rcmwithTable\\RCM.db";
        con = DriverManager.getConnection(url);
        System.out.println("con establoshed...");
        return con;
    }

    public void insertContract(String con_StartDate, String con_EndDate, String apa_id, String User_id) throws SQLException {

        String sql = "INSERT INTO Contracts(con_StartDate,con_EndDate,apa_id,User_id) VALUES ('" + con_StartDate + "','" + con_EndDate + "','" + apa_id + "','" + User_id + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("the Contract added");

    }

    public void insertBuilding(String nomberOfApartments, String b_Address, String o_id) throws SQLException {

        String sql = "INSERT INTO Buildings(nomberOfApartments,b_Address,o_id) VALUES ('" + nomberOfApartments + "','" + b_Address + "','" + o_id + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("the Buildings added");

    }

    public void insertResidentialComplex(String apa_type, String apa_NoRoom, String apa_Description, String apa_PricePerY, String b_id) throws SQLException {

        String sql = "INSERT INTO Apartments(apa_type,apa_NoRoom,apa_Description,apa_PricePerY,b_id) VALUES ('" + apa_type + "','" + apa_NoRoom + "','" + apa_Description + "','" + apa_PricePerY + "','" + b_id + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("the Apartments added");

    }

    public void insertOwner(String o_id, String o_name, String o_phoneNo, String o_email) throws SQLException {

        String sql = "INSERT INTO Owners(o_id,o_name,o_phoneNo,o_email) VALUES ('" + o_id + "','" + o_name + "','" + o_phoneNo + "','" + o_email + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("the Owners added");

    }

    public void insertUsers(String id, String name, String phoneNo, String email, String password) throws SQLException {

        String sql = "INSERT INTO Users(id,name,phoneNo,email,password) VALUES ('" + id + "','" + name + "','" + phoneNo + "','" + email + "','" + password + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("the Users added");

    }

    //-----------------------The table viwe-----------------------------
    public void ViewContractTable(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sql = "SELECT c.con_id , c.con_StartDate , c.con_EndDate,c.apa_id,u.name  "
                + "from Contracts c ,Users u  "
                + "where  c.User_id = u.id ";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ContractTable(rs.getString("con_id"), rs.getInt("apa_id"), rs.getString("con_StartDate"), rs.getString("con_EndDate"), rs.getString("name")));
        }
        st.close();
        con.close();
    }

    public void searchContractTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT c.con_id , c.con_StartDate , c.con_EndDate,c.apa_id,u.name\n"
                + "from Contracts c ,Users u \n"
                + "where  c.User_id = u.id "
                + "and con_id like'%" + search + "%'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ContractTable(rs.getString("con_id"), rs.getInt("apa_id"), rs.getString("con_StartDate"), rs.getString("con_EndDate"), rs.getString("name")));
        }
        st.close();
        con.close();
    }

    //----------------the Vwice and Search for OwnerTable------------------------
    public void ViewOwnerTable(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Owners;";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new OwnerTable(rs.getString("o_id"), rs.getString("o_name"), rs.getString("o_phoneNo"), rs.getString("o_email")));
        }
        st.close();
        con.close();
    }

    public void searchOwnerTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Owners "
                + "where o_id like'%" + search + "%'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new OwnerTable(rs.getString("o_id"), rs.getString("o_name"), rs.getString("o_phoneNo"), rs.getString("o_email")));
        }
        st.close();
        con.close();
    }

    //----------------the Vwice and Search for ApartmentTable------------------------
    public void ViewApartmentTable(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Apartments;";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ApartmentTable(rs.getString("apa_id"), rs.getString("apa_type"), rs.getInt("apa_NoRoom"), rs.getString("apa_Description"), rs.getInt("apa_PricePerY"), rs.getString("Availability"), rs.getInt("b_id")));
        }
        st.close();
        con.close();
    }

    public void searchApartmentTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Apartments "
                + "where apa_id like'%" + search + "%'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ApartmentTable(rs.getString("apa_id"), rs.getString("apa_type"), rs.getInt("apa_NoRoom"), rs.getString("apa_Description"), rs.getInt("apa_PricePerY"), rs.getString("Availability"), rs.getInt("b_id")));
        }
        st.close();
        con.close();
    }

    //----------------the Vwice and Search for BuildingTable------------------------
    public void ViewBuildingTable(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sql = "SELECT b.b_id,b.nomberOfApartments,b.b_Address,o.o_name\n"
                + "from Buildings b , Owners o \n"
                + "where b.o_id = o.o_id ";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new BuildingTable(rs.getString("b_id"), rs.getInt("nomberOfApartments"), rs.getString("b_Address"), rs.getString("o_name")));
        }
        st.close();
        con.close();
    }

    public void searchBuildingTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT b.b_id,b.nomberOfApartments,b.b_Address,o.o_name\n"
                + "from Buildings b , Owners o \n"
                + "where b.o_id = o.o_id "
                + "and b_id like'%" + search + "%'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new BuildingTable(rs.getString("b_id"), rs.getInt("nomberOfApartments"), rs.getString("b_Address"), rs.getString("o_name")));
        }
        st.close();
        con.close();
    }

    //----------------the Vwice and Search for UserTable------------------------
    public void ViewUserTable(ObservableList oblist) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Users;";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new UserTable(rs.getString("id"), rs.getString("name"), rs.getString("phoneNo"), rs.getString("email"), rs.getString("password"), rs.getString("UserType")));
        }
        st.close();
        con.close();
    }

    public void searchUserTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Users "
                + "where id like " + (search.isEmpty() ? "'%'" : "'" + search + "%'");

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new UserTable(rs.getString("id"), rs.getString("name"), rs.getString("phoneNo"), rs.getString("email"), rs.getString("password"), rs.getString("UserType")));
        }
        st.close();
        con.close();
    }
    //----------------DELET MTHEOD------------------------

    public void deleteUser(String ID) throws SQLException {
        String sql = "DELETE FROM Users WHERE id = " + ID + ";";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public void deleteContract(String ID) throws SQLException {
        String sql = "DELETE FROM Contracts WHERE con_id = '" + ID + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public void deleteBuildings(String b_id) throws SQLException {
        String sql = "DELETE FROM Buildings WHERE b_id = " + b_id + ";";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public void deleteApartments(String apa_id) throws SQLException {
        String sql = "DELETE FROM Apartments WHERE apa_id = '" + apa_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }

    public void deleteOwners(String o_id) throws SQLException {
        String sql = "DELETE FROM Owners WHERE o_id = '" + o_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();

    }
    //----------------Updat MTHEOD------------------------

    public void updateUser(String id, String name, String phoneNo, String email, String password) throws SQLException {
        String sql = "UPDATE Users set id = '" + id + "', name='" + name + "' , phoneNo='" + phoneNo + "', email='" + email + "' , password='" + password + "' "
                + "WHERE id='" + id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
      
    }

    public void updateContract(String con_id, String con_StartDate, String con_EndDate, String apa_id) throws SQLException {
        String sql = "UPDATE Contracts set con_id = '" + con_id + "', con_StartDate='" + con_StartDate + "' , con_EndDate='" + con_EndDate + "', apa_id='" + apa_id + "'  "
                + "WHERE con_id='" + con_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();

    }

    public void updateBuildings(String b_id, String nomberOfApartments, String b_Address) throws SQLException {
        String sql = "UPDATE Buildings set b_id = '" + b_id + "', nomberOfApartments='" + nomberOfApartments + "' , b_Address='" + b_Address + "'"
                + " WHERE b_id='" + b_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();

    }
//   

    public void updateApartments(String apa_id, String apa_type, String apa_NoRoom, String apa_Description, String apa_PricePerY, String b_id) throws SQLException {
        String sql = "UPDATE Apartments set apa_id = '" + apa_id + "', apa_type='" + apa_type + "' , apa_NoRoom='" + apa_NoRoom + "', apa_Description='" + apa_Description + "' , apa_PricePerY='" + apa_PricePerY + "', b_id='" + b_id + "' "
                + "WHERE apa_id='" + apa_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();

    }

    public void updateOwners(String o_id, String o_name, String o_phoneNo, String o_email) throws SQLException {
        String sql = "UPDATE Owners set o_id = '" + o_id + "', o_name='" + o_name + "' , o_phoneNo='" + o_phoneNo + "', o_email='" + o_email + "' "
                + "WHERE o_id='" + o_id + "';";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();

    }
    
    //-------------------------------- view admin profile ---------------------------

    public ArrayList<String> viewProfile() throws SQLException {
        ArrayList<String> profile = new ArrayList<String>();
        String sql = "select name,phoneNo,email from Users "
                + "where id ='123456789'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String name = rs.getString("name");
        String phone = rs.getString("phoneNo");
        String email = rs.getString("email");
        st.close();
        con.close();
        System.out.println("USER NOW CAN VIEW HIS INFORMATION");
        profile.add(name);
        profile.add(phone);
        profile.add(email);

        return profile;
    }
}
