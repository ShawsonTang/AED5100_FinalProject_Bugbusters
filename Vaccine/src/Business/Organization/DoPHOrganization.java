/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.AssistantSecretaryRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class DoPHOrganization extends Organization {
    public DoPHOrganization() {
        super(Organization.OrganizationType.DoPH.getValue(), Organization.OrganizationType.DoPH);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
       ArrayList<Role> roles = new ArrayList();
        roles.add(new AssistantSecretaryRole());
        return roles;
    }
    
}
