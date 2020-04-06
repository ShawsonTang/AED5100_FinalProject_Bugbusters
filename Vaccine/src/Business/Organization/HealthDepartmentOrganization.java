/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class HealthDepartmentOrganization extends Organization {
    public HealthDepartmentOrganization() {
        super(Organization.OrganizationType.HealthDepartment.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {

        return null;
    }
    
}
