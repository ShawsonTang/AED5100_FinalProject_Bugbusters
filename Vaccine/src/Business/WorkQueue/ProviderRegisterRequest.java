/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.DoPHOrganization;
import Business.Organization.HealthDepartmentOrganization;

/**
 *
 * @author shawson
 */
public class ProviderRegisterRequest extends WorkRequest {
    private DoPHOrganization receiver;
    private HealthDepartmentOrganization sender;

    public ProviderRegisterRequest() {
        super();
    }        
    
    public DoPHOrganization getReceiver() {
        return receiver;
    }

    public void setReceiver(DoPHOrganization receiver) {
        this.receiver = receiver;
    }   

    public HealthDepartmentOrganization getSender() {
        return sender;
    }

    public void setSender(HealthDepartmentOrganization sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return sender.getName();
    }
    
    
}
