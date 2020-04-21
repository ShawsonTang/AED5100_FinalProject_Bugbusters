/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class EcoSystem extends Organization {
    
    private static EcoSystem business;
    private ArrayList<Network> networkList;
    private ArrayList<Organization> defaultOrganizationList;

    public void setDefaultOrganizationList(ArrayList<Organization> defaultOrganizationList) {
        this.defaultOrganizationList = defaultOrganizationList;
    }

    public ArrayList<Organization> getDefaultOrganizationList() {
        return defaultOrganizationList;
    }
    public static EcoSystem getInstance(){
        if(business==null){
            business=new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork(String name){
        Network network=new Network(name);
        networkList.add(network);
        return network;
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    private EcoSystem(){
        super(null, null);
        networkList=new ArrayList<Network>();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }
    
    public void deleteNetwork(String value) {
//        for(Network network : networkList){
//            if(value.equals(network.getName())){
//                networkList.remove(network);
//            }
//        }
            for( int i = 0; i < networkList.size(); i++ )
            {
                String temp = networkList.get(i).getName();
                if(temp.equals(value))
                {
                     networkList.remove(i);
                     i--; 
                }  
            }
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }
    
    public boolean checkIfUserIsUnique(String userName){
        if(!this.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
            return false;
        }
        for(Network network:networkList){
            
        }
        return true;
    }
  
}
