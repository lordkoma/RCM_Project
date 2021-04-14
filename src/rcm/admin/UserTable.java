/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.admin;

/**
 *
 * @author gg
 */
public class UserTable {
    private String Userid;
    private String Username;
    private String phoneNO;
    private String email;
    private String password;
    private String UserType ;

    public UserTable(String Userid, String Username, String phoneNO, String email, String password, String UserType) {
        this.Userid = Userid;
        this.Username = Username;
        this.phoneNO = phoneNO;
        this.email = email;
        this.password = password;
        this.UserType = UserType;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }
    
    
}
