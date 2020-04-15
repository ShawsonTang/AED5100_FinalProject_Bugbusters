/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.FactoryOrganization;
import Business.Organization.HealthDepartmentOrganization;
import Business.Organization.WarehouseOrganization;
import Business.Vaccine.Vaccine;

/**
 *
 * @author kim
 */
public class VaccineDoctorRequest extends WorkRequest{
    private int dosesRequest;
    private HealthDepartmentOrganization receiver;
    private Vaccine vaccine;

    public int getDosesRequest() {
        return dosesRequest;
    }

    public HealthDepartmentOrganization getHealthDepartmentOrganization() {
        return receiver;
    }
     @Override
    public HealthDepartmentOrganization getReceiver() {
        return receiver;
    }
    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setDosesRequest(int dosesRequest) {
        this.dosesRequest = dosesRequest;
    }

    public void setReceiver(HealthDepartmentOrganization receiver) {
        this.receiver = receiver;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
    @Override
    public String toString() {
        return this.vaccine.getVaccineType();
    }
    
    
}
