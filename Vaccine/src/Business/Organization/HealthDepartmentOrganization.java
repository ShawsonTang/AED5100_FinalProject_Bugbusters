/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.VaccineManagerRole;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class HealthDepartmentOrganization extends Organization {
    private boolean registered = false;
    
    public HealthDepartmentOrganization() {
        super(Organization.OrganizationType.HealthDepartment.getValue(), Organization.OrganizationType.HealthDepartment);
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    
    
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new VaccineManagerRole());
        return roles;
    }
    
}
