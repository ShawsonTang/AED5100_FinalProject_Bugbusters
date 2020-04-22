/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.VaccineManagerRole;
import Business.Role.WarehouseKeeperRole;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class WarehouseOrganization extends Organization {
    public WarehouseOrganization() {
        super(Organization.OrganizationType.Warehouse.getValue(), Organization.OrganizationType.Warehouse);
    }
    
    @Override
    public ArrayList<Role.RoleType> getSupportedRole() {
        ArrayList<Role.RoleType> roles = new ArrayList();
        roles.add(Role.RoleType.WarehouseKeeper);
        return roles;
    }
    
}
