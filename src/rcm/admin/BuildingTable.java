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
public class BuildingTable {
    private String buildingID;
    private int numOfApartemnts;
    private String buildingAddress;
    private String OwnerName;

    public BuildingTable(String buildingID, int numOfApartemnts, String buildingAddress, String OwnerName) {
        this.buildingID = buildingID;
        this.numOfApartemnts = numOfApartemnts;
        this.buildingAddress = buildingAddress;
        this.OwnerName = OwnerName;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public int getNumOfApartemnts() {
        return numOfApartemnts;
    }

    public void setNumOfApartemnts(int numOfApartemnts) {
        this.numOfApartemnts = numOfApartemnts;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }
    
    
    
}
