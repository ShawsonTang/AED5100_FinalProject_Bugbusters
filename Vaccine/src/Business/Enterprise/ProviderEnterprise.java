/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.DoPHOrganization;
import Business.Organization.DoctorOrganization;
import Business.Organization.HealthDepartmentOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class ProviderEnterprise extends Enterprise {
    
    public ProviderEnterprise(String name){
        super(name, EnterpriseType.Provider);
    }

    @Override
    public ArrayList<Organization.OrganizationType> getSupportedOrganizations() {
        ArrayList<Organization.OrganizationType> organizations = new ArrayList();
        organizations.add(Organization.OrganizationType.HealthDepartment);
        organizations.add(Organization.OrganizationType.Doctor);
        return organizations;
    }
    
}
