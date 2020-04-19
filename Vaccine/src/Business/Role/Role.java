/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;
import userinterface.SignIn;

/**
 *
 * @author shawson
 */
public abstract class Role {
    
    public enum RoleType {
        Admin("Admin"),
        FactoryManager("Factory Manager"),
        WarehouseKeeper("Warehouse Keeper"),
        AssistantSecretary("Assistant Secretary"),
        VaccineManager("Vaccine Manager"),
        Doctor("Doctor");
       
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel userProcessContainer, SignIn frame,
            UserAccount account, 
            Organization organization, 
            Enterprise enterprise, 
            Network network, EcoSystem business);

    @Override
    public String toString() {        
        return this.getClass().getName();
    }
    
    
}