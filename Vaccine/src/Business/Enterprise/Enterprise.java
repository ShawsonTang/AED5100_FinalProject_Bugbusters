 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public abstract class Enterprise {
    
    private EnterpriseType enterpriseType;
    private String name;
    private OrganizationDirectory organizationDirectory;

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }
    
    public enum EnterpriseType {
        Regulator("Regulator"), Manufacturer("Manufacturer"), Provider("Provider");
        
        private String value;
        
        private EnterpriseType(String value){
            this.value=value;
        }
        public String getValue() {
            return value;
        }
        @Override
        public String toString(){
            return value;
        }
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract ArrayList<Organization.OrganizationType> getSupportedOrganizations();
    
    public Enterprise(String name, EnterpriseType type){
        this.name = name;
        this.enterpriseType=type;
        organizationDirectory=new OrganizationDirectory();
    }
    @Override
    public String toString() {
        return name;
    }
    
}
