/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcm.user;



/**
 *
 * @author ho0o0
 */
public class ApartmentTable {
     private int apartmentId;
    private String apartmentType;
    private int apartmentRoomNo;
    private String apartmentDescription;
    private int apartmentPricePerYear;
    private int buildingId;

    public ApartmentTable(int apartmentId, String apartmentType, int apartmentRoomNo, String apartmentDescription, int apartmentPricePerYear, int buildingId) {
        this.apartmentId = apartmentId;
        this.apartmentType = apartmentType;
        this.apartmentRoomNo = apartmentRoomNo;
        this.apartmentDescription = apartmentDescription;
        this.apartmentPricePerYear = apartmentPricePerYear;
        this.buildingId = buildingId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
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

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
    
}
