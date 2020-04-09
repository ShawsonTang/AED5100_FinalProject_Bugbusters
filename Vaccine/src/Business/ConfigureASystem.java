package Business;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 * @author shawson
 */
public class ConfigureASystem {
    
    public static EcoSystem configure(){
        
        EcoSystem system = EcoSystem.getInstance();
        
        //Create a network
        system.createAndAddNetwork("Massachusetts");
        system.createAndAddNetwork("California");
        system.createAndAddNetwork("Texas");
        //create an enterprise
        system.getNetworkList().get(0).getEnterpriseDirectory().createAndAddEnterprise("Walgreen001", Enterprise.EnterpriseType.Provider);
        //initialize some organizations
        
        //have some employees 
        
        //create user account
        Employee employee = system.getEmployeeDirectory().createEmployee("Shawson");        
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("s", "s", employee, new SystemAdminRole());
        
        return system;
    }
    
}
