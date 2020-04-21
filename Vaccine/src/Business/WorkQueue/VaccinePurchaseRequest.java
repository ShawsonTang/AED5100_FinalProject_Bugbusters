/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.WarehouseOrganization;
import Business.Vaccine.Vaccine;

/**
 *
 * @author shawson
 */
public class VaccinePurchaseRequest extends WorkRequest{
    private int dosesPurchase;
    private int doseForUpdate;
    private WarehouseOrganization receiver;
    private Vaccine vaccine;

    public VaccinePurchaseRequest() {
        super();
    }
    
    
    public int getDosesPurchase() {
        return dosesPurchase;
    }

    public void setDosesPurchase(int dosesPurchase) {
        this.dosesPurchase = dosesPurchase;
    }

    public int getDoseForUpdate() {
        return doseForUpdate;
    }

    public void setDoseForUpdate(int doseForUpdate) {
        this.doseForUpdate = doseForUpdate;
    }
        

    public WarehouseOrganization getReceiver() {
        return receiver;
    }

    public void setReceiver(WarehouseOrganization receiver) {
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
