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
    private Map<Organization.OrganizationType, Organization> vaccineHistory;
    private Date proDate;
    private Date expDate;

//    public enum OrganizationType {
//        DoPH, Factory, Warehouse, HealthDepartment, Doctor
//    };

    public Vaccine(String vaccineType, int id, Date proDate, Date expDate) {
        this.vaccineType = vaccineType;
        this.id = id;
        this.proDate = proDate;
        this.expDate = expDate;
        vaccineHistory = new HashMap<>();
    }

}
