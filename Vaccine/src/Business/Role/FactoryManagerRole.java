/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.FactoryOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;
import userinterface.FactoryManagerRole.FactoryManagerWorkAreaJPanel;
import userinterface.SignIn;


/**
 *
 * @author shawson
 */
public class FactoryManagerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, SignIn frame, UserAccount account, Organization organization, Enterprise enterprise, Network network, EcoSystem business) {
        return new FactoryManagerWorkAreaJPanel(userProcessContainer, frame, account, (FactoryOrganization) organization, enterprise, business);
    }
    
}
