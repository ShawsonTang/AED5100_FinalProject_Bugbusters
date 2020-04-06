/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import com.db4o.collections.ActivatableArrayList;
import java.util.ArrayList;

/**
 *
 * @author shawson
 */
public class VaccineDirectory {
    private ArrayList<Vaccine> vaccineList;

    public VaccineDirectory() {
        this.vaccineList = new ActivatableArrayList<Vaccine>();
    }

    public ArrayList<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }
    
    
    
    
}
