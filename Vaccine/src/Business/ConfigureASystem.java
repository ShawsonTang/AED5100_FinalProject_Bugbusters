package Business;

import Business.Employee.AssistantSecretary;
import Business.Employee.Doctor;
import Business.Employee.Employee;
import Business.Employee.FactoryManager;
import Business.Employee.SystemAdmin;
import Business.Employee.VaccineManager;
import Business.Employee.WarehouseKeeper;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.AssistantSecretaryRole;
import Business.Role.DoctorRole;
import Business.Role.FactoryManagerRole;
import Business.Role.SystemAdminRole;
import Business.Role.VaccineManagerRole;
import Business.Role.WarehouseKeeperRole;
import Business.UserAccount.UserAccount;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import jdk.nashorn.internal.objects.NativeDebug;

/**
 *
 * @author shawson
 */
public class ConfigureASystem {
    
    public static EcoSystem configure(){
        
        EcoSystem system = EcoSystem.getInstance();
        
        //Create some networks
        system.createAndAddNetwork("Massachusetts");
        system.createAndAddNetwork("California");
        system.createAndAddNetwork("Texas");
        
        //create some enterprises
        //Create a manufacturer and a provider in MA
        for (Network n : system.getNetworkList()) {
            if (n.getName().equals("Massachusetts")) {
                n.getEnterpriseDirectory().createAndAddEnterprise("Merck001", Enterprise.EnterpriseType.Manufacturer);
                n.getEnterpriseDirectory().createAndAddEnterprise("Walgreen001", Enterprise.EnterpriseType.Provider);
                n.getEnterpriseDirectory().createAndAddEnterprise("DoPH001", Enterprise.EnterpriseType.Regulator);
            }
        }                       
        
        //initialize some organizations
        //Initialize a factory organization and warehouse organization in Merck001, 
        //and a doctor organization in Walgreen001
        Organization factoryOrganization1 = null;
        Organization warehouseOrganization1 = null;
        Organization doctorOrganization1 = null;
        Organization healthOrganization1 = null;
        Organization dophOrganization1 = null;
        for (Network n : system.getNetworkList()) {
            if (n.getName().equals("Massachusetts")) {
                for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                    if (e.getName().equalsIgnoreCase("Merck001")) {
                        factoryOrganization1 = e.getOrganizationDirectory().createOrganization(Organization.OrganizationType.Factory);
                        factoryOrganization1.setName("Factory001");
                        warehouseOrganization1 = e.getOrganizationDirectory().createOrganization(Organization.OrganizationType.Warehouse);
                        warehouseOrganization1.setName("Warehouse001");
                    }
                    if (e.getName().equalsIgnoreCase("Walgreen001")) {
                       doctorOrganization1 = e.getOrganizationDirectory().createOrganization(Organization.OrganizationType.Doctor);
                       doctorOrganization1.setName("DoctorOrg");
                       healthOrganization1 = e.getOrganizationDirectory().createOrganization(Organization.OrganizationType.HealthDepartment);
                       healthOrganization1.setName("VcManager");
                    }
                    if (e.getName().equalsIgnoreCase("Doph001")) {
                        dophOrganization1 = e.getOrganizationDirectory().createOrganization(Organization.OrganizationType.DoPH);
                        dophOrganization1.setName("Department of Public Health in Suffolk");
                    }
                }
            } 
        }
//        System.out.println(factoryOrganization1.getVaccineDirectory().getVaccineList().size());
         
        //have some employees 
        //Create a system admin employee
        Employee admin = new SystemAdmin("shawson", 1027900, "6175167197");
        system.getEmployeeDirectory().getEmployeeList().add(admin);
        
        //Create a factory manager employee
        Employee factoryManager1 = new FactoryManager("A", 1027901, "6175167190");
        system.getEmployeeDirectory().getEmployeeList().add(factoryManager1);
        
        //Create a warehouse keeper emplyee
        Employee warehouseKeeper1 = new WarehouseKeeper("B", 1027441, "6175564423");
        
        //Create a doctor employee
        Employee doctor1 = new Doctor("Angela", 001, "6175167797");
        ImageIcon doctor1Photo = new ImageIcon("src/image/doctor1.png");
        
        //Create a vaccine manager employee
        Employee vcManager = null;
        vcManager = new VaccineManager("32", 005, "6175797");
        system.getEmployeeDirectory().getEmployeeList().add(vcManager);
        
        //Create a assistant employee
        Employee assistant1 = new AssistantSecretary("Boss", 102899, "6175167199");
        system.getEmployeeDirectory().getEmployeeList().add(assistant1);
                       
        doctor1.setPhoto(doctor1Photo);
        system.getEmployeeDirectory().getEmployeeList().add(doctor1);
        

        //create user account
        //Create a system admin user account       
        system.getUserAccountDirectory().createUserAccount("s", "s", admin, new SystemAdminRole());
        
        //Create a factory user account in factory oragnization1
        UserAccount factoryManagerUser1 = factoryOrganization1.getUserAccountDirectory().createUserAccount("f", "f", factoryManager1, new FactoryManagerRole());
//        factoryOrganization1.getUserAccountDirectory().getUserAccountList().add(factoryManagerUser1);
        
        //Create a warehouse keeper account in warehouse oragization1
        UserAccount warehouseKeeperUser1 = warehouseOrganization1.getUserAccountDirectory().createUserAccount("w", "w", warehouseKeeper1, new WarehouseKeeperRole());
        UserAccount vaccineMangerAccount = healthOrganization1.getUserAccountDirectory().createUserAccount("v", "v", vcManager, new VaccineManagerRole());
//        warehouseOrganization1.getUserAccountDirectory().getUserAccountList().add(warehouseKeeperUser1);
        
        //Create a doctor user account in doctor organization1
        UserAccount doctor1User = doctorOrganization1.getUserAccountDirectory().createUserAccount("d", "d", doctor1, new DoctorRole());
//        doctorOrganization1.getUserAccountDirectory().getUserAccountList().add(doctor1User);

        //Create an assistant user account in doph organization1
        UserAccount assistantUser1 = dophOrganization1.getUserAccountDirectory().createUserAccount("a", "a", assistant1, new AssistantSecretaryRole());
        ArrayList<Organization> deafaultOrganizations = new ArrayList<Organization>();
        deafaultOrganizations.add(healthOrganization1);
        deafaultOrganizations.add(warehouseOrganization1);
        deafaultOrganizations.add(factoryOrganization1);
        deafaultOrganizations.add(doctorOrganization1);
        deafaultOrganizations.add(dophOrganization1);
        system.setDefaultOrganizationList(deafaultOrganizations);
        return system;
    }
    
}
