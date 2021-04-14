/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 *
 * @author oke
 */
public class SQLmethods {

    private Connection connect() throws SQLException {
        Connection con = null;
        String url = "jdbc:sqlite:E:\\Desktop\\rcmwithTable\\RCM.db";
        con = DriverManager.getConnection(url);
        System.out.println("Connection stablished...");
        return con;

    }

    public void add(String id, String name, String email, String phone, String password) throws SQLException {
        String sql = "INSERT INTO Users(id,name,phoneNo,email,password) VALUES('" + id + "','" + name + "','" + phone + "','" + email + "','" + password + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The user added...");

    }

    public int login(String email, String password) throws SQLException {
        String sql = "SELECT id,email, password, UserType "
                + "From Users "
                + "where email = '" + email + "' ;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean check = false;
        if (rs.next()) {
            String e = rs.getString("email");
            String p = rs.getString("password");
            String type = rs.getString("UserType");
            String ids = rs.getString("id");

            if (email.equals(e) && password.equals(p)) {
                check = true;
                if (type.equals("admin")) {
                    st.close();
                    con.close();
                    return 1;
                } else {
                    new session(ids);
                    st.close();
                    con.close();
                    return 2;
                }
            } else {
                st.close();
                con.close();
                return 3;
            }
        } else {
            st.close();
            con.close();
            return 3;
        }

    }

    //----------------the View and Search for ApartmentTable------------------------
    public void VwieApartmentTable(ObservableList oblist) throws SQLException {
        String sql = "SELECT * FROM Apartments "
                + "where Availability ='T'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ApartmentTable(rs.getInt("apa_id"), rs.getString("apa_type"), rs.getInt("apa_NoRoom"), rs.getString("apa_Description"), rs.getInt("apa_PricePerY"), rs.getInt("b_id")));
        }
    }

    public void searchApartmentTable(ObservableList oblist, String search) throws SQLException {
        oblist.clear();
        String sql = "SELECT * FROM Apartments "
                + "where apa_id like'%" + search + "%' AND Availability ='T'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ApartmentTable(rs.getInt("apa_id"), rs.getString("apa_type"), rs.getInt("apa_NoRoom"), rs.getString("apa_Description"), rs.getInt("apa_PricePerY"), rs.getInt("b_id")));
        }

    }

    public void updateAvailability(String a_id) throws SQLException {
        String sql = "update Apartments set Availability='F'"
                + "where apa_id = '" + a_id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The Availability Has Been Updated");

    }

    public void addContract(String apa_id, String StartDate, String endDate, String u_id) throws SQLException {
        String sql = "INSERT INTO Contracts(con_StartDate,con_EndDate,apa_id,User_id) VALUES('" + StartDate + "','" + endDate + "','" + apa_id + "','" + u_id + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The Contract added...");

    }

    public void addalert(String From, String Date, String descrption, String state, String U_id) throws SQLException {
        String sql = "INSERT INTO alerts(al_From,Date,al_Description,al_State,User_id) VALUES('" + From + "','" + Date + "','" + descrption + "','" + state + "','" + U_id + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("The alert added...");

    }
    //-----------------------The table viwe for contracts-----------------------------

    public void VwieContractTable(ObservableList oblist, String u_id) throws SQLException {
        String sql = "SELECT c.con_id , c.con_StartDate , c.con_EndDate,c.apa_id,u.name\n"
                + "from Contracts c ,Users u \n"
                + "where  c.User_id = u.id and c.User_id ='" + u_id + "'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ContractTable(rs.getInt("con_id"), rs.getInt("apa_id"), rs.getString("con_StartDate"), rs.getString("con_EndDate"), rs.getString("name")));
        }
    }

    public void searchContractTable(ObservableList oblist, String search, String u_id) throws SQLException {
        oblist.removeAll(oblist);
        String sql = "SELECT c.con_id , c.con_StartDate , c.con_EndDate, c.apa_id, u.name "
                + "from Contracts c ,Users u "
                + "where  c.User_id = u.id and u_id = "+u_id+"c.con_id like " + (search.isEmpty() ? "'%'" : "'" + search + "%'");

        Connection con = this.connect();
        Statement st = con.createStatement();
//        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new ContractTable(rs.getInt("con_id"), rs.getInt("apa_id"), rs.getString("con_StartDate"), rs.getString("con_EndDate"), rs.getString("name")));
        }
    }

    //-----------------------The table viwe for alerts-----------------------------
    public void VwieAlertsTable(ObservableList oblist, String u_id) throws SQLException {
        String sql = "select  al_id,al_From ,Date, al_Description,al_State from alerts Where User_id = '" + u_id + "'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new alertsTable(rs.getString("al_id"), rs.getString("al_From"), rs.getString("Date"), rs.getString("al_Description"), rs.getString("al_State")));
        }
    }

    public void searchAlertsTable(ObservableList oblist, String search, String u_id) throws SQLException {
        oblist.removeAll(oblist);
        String sql = "select  al_id,al_From ,Date, al_Description,al_State from alerts "
                + "Where al_id like " + (search.isEmpty() ? "'%'" : "'" + search + "%'") + " and User_id='" + u_id + "'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            oblist.add(new alertsTable(rs.getString("al_id"), rs.getString("al_From"), rs.getString("Date"), rs.getString("al_Description"), rs.getString("al_State")));
        }
    }

    //-----------------------The view user profile -----------------------------
    public ArrayList<String> viewProfile(String u_id) throws SQLException {
        ArrayList<String> profile = new ArrayList<String>();
        String sql = "select name,phoneNo,email from Users "
                + "where id ='" + u_id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
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

    //----------------------- chcek avalilbity for an apartment--------------------
    public int checkAva(String RoomNo) throws SQLException {
        String sql
                = "SELECT Availability "
                + "From Apartments "
                + "where apa_id = '" + RoomNo + "' ;";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            
            
            String ava = rs.getString("Availability");
            if (ava.equals("T")) {

                st.close();
                con.close();
                return 1;

            } else {
                st.close();
                con.close();
                return 2;
            }
        } else {
            st.close();
            con.close();
            return 3;
        }

    }

    

}
