/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.admin;

/**
 *
 * @author ho0o0
 */
public class OwnerTable {
    private String ownerId,ownerName,ownerEmail;
    private String wonerPhone;

    public OwnerTable(String ownerId, String ownerName,String wonerPhone ,String ownerEmail  ) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
       
        this.wonerPhone = wonerPhone;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }


    public String getWonerPhone() {
        return wonerPhone;
    }

    public void setWonerPhone(String wonerPhone) {
        this.wonerPhone = wonerPhone;
    }
    
}
