/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;
import userinterface.SignIn;
import userinterface.WareHouseKeeperRole.WarehouseKeeperWorkAreaJPanel;

/**
 *
 * @author shawson
 */
public class WarehouseKeeperRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, SignIn frame, UserAccount account, Organization organization, Enterprise enterprise, Network network, EcoSystem business) {
        return new WarehouseKeeperWorkAreaJPanel(userProcessContainer, frame, account, (WarehouseOrganization) organization, enterprise, network, business);
    }

      
}
