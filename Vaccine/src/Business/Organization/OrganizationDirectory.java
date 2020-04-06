/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.OrganizationType;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(OrganizationType type){
        Organization organization;
        if (type.getValue().equals(OrganizationType.Factory.getValue())){
            organization = new FactoryOrganization();
            organizationList.add(organization); 
        }
        else if (type.getValue().equals(OrganizationType.Warehouse.getValue())){
            organization = new WarehouseOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(OrganizationType.DoPH.getValue())){
            organization = new DoPHOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(OrganizationType.Doctor.getValue())){
            organization = new DoctorOrganization();
            organizationList.add(organization);
        } else {
            organization = new HealthDepartmentOrganization();
            organizationList.add(organization);
        }        
        return organization;
    }
}