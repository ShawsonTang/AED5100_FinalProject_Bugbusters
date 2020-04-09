/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.VaccineManager;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class HealthDepartmentOrganization extends Organization {
    public HealthDepartmentOrganization() {
        super(Organization.OrganizationType.HealthDepartment.getValue(), Organization.OrganizationType.HealthDepartment);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new VaccineManager());
        return roles;
    }
    
}
