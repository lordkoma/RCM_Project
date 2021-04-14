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
public class ApartmentTable {
     private String apartmentId;
    private String apartmentType;
    private int apartmentRoomNo;
    private String apartmentDescription;
    private int apartmentPricePerYear;
     private String apartmentAvailable;
    private int buildingId;

    public ApartmentTable(String apartmentId, String apartmentType, int apartmentRoomNo, String apartmentDescription, int apartmentPricePerYear, String apartmentAvailable, int buildingId) {
        this.apartmentId = apartmentId;
        this.apartmentType = apartmentType;
        this.apartmentRoomNo = apartmentRoomNo;
        this.apartmentDescription = apartmentDescription;
        this.apartmentPricePerYear = apartmentPricePerYear;
        this.apartmentAvailable = apartmentAvailable;
        this.buildingId = buildingId;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public int getApartmentRoomNo() {
        return apartmentRoomNo;
    }

    public void setApartmentRoomNo(int apartmentRoomNo) {
        this.apartmentRoomNo = apartmentRoomNo;
    }

    public String getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(String apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }

    public int getApartmentPricePerYear() {
        return apartmentPricePerYear;
    }

    public void setApartmentPricePerYear(int apartmentPricePerYear) {
        this.apartmentPricePerYear = apartmentPricePerYear;
    }

    public String getApartmentAvailable() {
        return apartmentAvailable;
    }

    public void setApartmentAvailable(String apartmentAvailable) {
        this.apartmentAvailable = apartmentAvailable;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
     

    
}
