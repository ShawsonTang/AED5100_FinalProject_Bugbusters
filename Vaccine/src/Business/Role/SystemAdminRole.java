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
import userinterface.SystemAdminWorkArea.SystemAdminWorkAreaJPanel;
import javax.swing.JPanel;
import userinterface.SignIn;
import userinterface.SystemAdminWorkArea.ChooseNetworkJPanel;


/**
 *
 * @author raunak
 */
public class SystemAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, SignIn frame, UserAccount account, Organization organization, Enterprise enterprise, Network network, EcoSystem system) {
        return new SystemAdminWorkAreaJPanel(userProcessContainer, frame, account, system);
    }
    
}
