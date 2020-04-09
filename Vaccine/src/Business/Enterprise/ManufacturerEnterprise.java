/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.FactoryOrganization;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class ManufacturerEnterprise extends Enterprise {
    
    public ManufacturerEnterprise(String name){
        super(name, EnterpriseType.Manufacturer);
    }

    @Override
    public ArrayList<Organization.OrganizationType> getSupportedOrganizations() {
        ArrayList<Organization.OrganizationType> organizations = new ArrayList();
        organizations.add(Organization.OrganizationType.Factory);
        organizations.add(Organization.OrganizationType.Warehouse);
        return organizations;
    }

    
    
    
}
