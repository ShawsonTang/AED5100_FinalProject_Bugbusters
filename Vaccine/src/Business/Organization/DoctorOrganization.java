/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DoctorRole;
import Business.Role.Role;
import java.util.ArrayList;
import javax.swing.Action;

/**
 *
 * @author shawson
 */
public class DoctorOrganization extends Organization {
    public DoctorOrganization() {
        super(Organization.OrganizationType.Doctor.getValue(), Organization.OrganizationType.Doctor);
    }
    
    @Override
    public ArrayList<Role.RoleType> getSupportedRole() {
        ArrayList<Role.RoleType> roles = new ArrayList();
        roles.add(Role.RoleType.Doctor);
        return roles;
    }
    
}
