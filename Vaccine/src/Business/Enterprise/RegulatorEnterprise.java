/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Enterprise.Enterprise.EnterpriseType;
import Business.Organization.DoPHOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class RegulatorEnterprise extends Enterprise {
    public RegulatorEnterprise(String name){
        super(name, EnterpriseType.Regulator);
    }

    @Override
    public ArrayList<Organization.OrganizationType> getSupportedOrganizations() {
        ArrayList<Organization.OrganizationType> organizations = new ArrayList();
        organizations.add(Organization.OrganizationType.DoPH);        
        return organizations;
    }
}
