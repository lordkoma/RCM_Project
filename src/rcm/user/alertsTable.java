/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;

/**
 *
 * @author Mohammed
 */
public class alertsTable {
    private String t_from ,t_date ,t_desc ,t_state,al_id;

    public String getAl_id() {
        return al_id;
    }

    public void setAl_id(String al_id) {
        this.al_id = al_id;
    }

 

    public String getT_from() {
        return t_from;
    }

    public void setT_from(String t_from) {
        this.t_from = t_from;
    }

    public String getT_date() {
        return t_date;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }

    public String getT_desc() {
        return t_desc;
    }

    public void setT_desc(String t_desc) {
        this.t_desc = t_desc;
    }

    public String getT_state() {
        return t_state;
    }

    public void setT_state(String t_state) {
        this.t_state = t_state;
    }

    public alertsTable(String al_id,String t_from, String t_date, String t_desc, String t_state ) {

        this.al_id = al_id;     
        this.t_from = t_from;
        this.t_date = t_date;
        this.t_desc = t_desc;
        this.t_state = t_state;
    }

  
}
