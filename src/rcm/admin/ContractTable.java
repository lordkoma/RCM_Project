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
public class ContractTable {
    private int  ApartmentNum;

    private String StartDate, EndDate, CustomerName,ContractNum;

    public ContractTable(String ContractNum,int ApartmentNum ,String StartDate, String EndDate, String CustomerName) {
        this.ApartmentNum = ApartmentNum;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.CustomerName = CustomerName;
        this.ContractNum = ContractNum;
    }

    public int getApartmentNum() {
        return ApartmentNum;
    }

    public void setApartmentNum(int ApartmentNum) {
        this.ApartmentNum = ApartmentNum;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public void setContractNum(String ContractNum) {
        this.ContractNum = ContractNum;
    }

   
}
