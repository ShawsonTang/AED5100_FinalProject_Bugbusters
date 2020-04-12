/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.FactoryOrganization;
import Business.Vaccine.Vaccine;



/**
 *
 * @author shawson
 */
public class VaccineProduceRequest extends WorkRequest {
    private int dosesRequest;
    private FactoryOrganization receiver;
    private Vaccine vaccine;
    
    public int getDosesRequest() {
        return dosesRequest;
    }

    public void setDosesRequest(int dosesRequest) {
        this.dosesRequest = dosesRequest;
    }

    @Override
    public FactoryOrganization getReceiver() {
        return receiver;
    }

    public void setReceiver(FactoryOrganization receiver) {
        this.receiver = receiver;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
    
    @Override
    public String toString() {
        return this.vaccine.getVaccineType();
    }
}
