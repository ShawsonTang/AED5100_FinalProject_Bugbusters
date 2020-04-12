/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import Business.Employee.WarehouseKeeper;
import Business.Enterprise.Enterprise;
import Business.Enterprise.ManufacturerEnterprise;
import Business.Enterprise.RegulatorEnterprise;
import Business.Organization.DoPHOrganization;
import Business.Organization.HealthDepartmentOrganization;
import Business.Organization.Organization;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shawson
 */
public class Vaccine {

    private String vaccineType;
    private int id;
    private int doseProdeced;
    private int doseInStock;
    private Map<Organization.OrganizationType, Organization> vaccineHistory;
    private Date proDate;
    private Date expDate;

//    public enum OrganizationType {
//        DoPH, Factory, Warehouse, HealthDepartment, Doctor
//    };

    public Vaccine(String vaccineType) {
        this.vaccineType = vaccineType;        
    }
    
    
    public Vaccine(String vaccineType, int id) {
        this.vaccineType = vaccineType;
        this.id = id;        
//        this.proDate = proDate;
//        this.expDate = expDate;
        vaccineHistory = new HashMap<>();
    }

    public Vaccine(String vaccineType, int id, int doseProdeced, int doseInStock, Date proDate, Date expDate) {
        this.vaccineType = vaccineType;
        this.id = id;
        this.doseProdeced = doseProdeced;
        this.doseInStock = doseInStock;
        this.proDate = proDate;
        this.expDate = expDate;
    }
    
    

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoseProdeced() {
        return doseProdeced;
    }

    public void setDoseProdeced(int doseProdeced) {
        this.doseProdeced = doseProdeced;
    }

    public int getDoseInStock() {
        return doseInStock;
    }

    public void setDoseInStock(int doseInStock) {
        this.doseInStock = doseInStock;
    }



    public Map<Organization.OrganizationType, Organization> getVaccineHistory() {
        return vaccineHistory;
    }

    public void setVaccineHistory(Map<Organization.OrganizationType, Organization> vaccineHistory) {
        this.vaccineHistory = vaccineHistory;
    }

    public Date getProDate() {
        return proDate;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
    @Override
    public String toString() {
        return vaccineType;
    }
}
