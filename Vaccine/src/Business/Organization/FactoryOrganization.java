/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;
import Business.Role.FactoryManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class FactoryOrganization extends Organization {
    public FactoryOrganization() {
        super(Organization.OrganizationType.Factory.getValue(), Organization.OrganizationType.Factory);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new FactoryManagerRole());
        return roles;
    }
    
}
