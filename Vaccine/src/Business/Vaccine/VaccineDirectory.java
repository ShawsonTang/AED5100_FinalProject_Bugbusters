/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import com.db4o.collections.ActivatableArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shawson
 */
public class VaccineDirectory {
    private ArrayList<Vaccine> vaccineList;
//    private ArrayList<Vaccine> emptyList;
    private Map<Vaccine, List<Vaccine>> vaccineMap;

    public VaccineDirectory() {
        this.vaccineList = new ArrayList<>();
//        this.emptyList = new ArrayList<>();
        this.vaccineMap = new HashMap<>();
//        Calendar pro = Calendar.getInstance();
//        Calendar exp = Calendar.getInstance();
//        pro.set(2020, 4, 01, 8, 0);
//        exp.set(2021, 4, 01, 8, 0);
//        Date covid19Pro = pro.getTime();
//        Date covid19Exp = exp.getTime();
        
        Vaccine covid19 = new Vaccine("COVID19");
        covid19.setDescription("KEEP CORONAVIRUS FROM YOURSELF!");
        Vaccine hib = new Vaccine("Hib");
        hib.setDescription("1-4 doses; the number of doses is determined by vaccine product and age the series begins");
        Vaccine dtap = new Vaccine("DTap");
        dtap.setDescription("4 doses");
        Vaccine polio = new Vaccine("Polio");
        polio.setDescription("3 doses");
        Vaccine hepatitisB = new Vaccine("Hepatitis B");
        hepatitisB.setDescription("3 doses; laboratory evidence of immunity acceptable");
        Vaccine mmr = new Vaccine("MMR");
        mmr.setDescription("1 dose; must be given on or after the 1st birthday; laboratory evidence of immunity acceptable");
        Vaccine varicella = new Vaccine("Varicella"); 
        varicella.setDescription("1 dose; must be given on or after the 1st birthday; a reliable history of chickenpox* or laboratory evidence of\n" +
        "immunity acceptable");
        vaccineList.add(covid19);
        vaccineList.add(hib);
        vaccineList.add(dtap);
        vaccineList.add(polio);
        vaccineList.add(hepatitisB);
        vaccineList.add(mmr);
        vaccineList.add(varicella); 
        
        vaccineMap.put(covid19, new ArrayList<>());
        vaccineMap.put(hib, new ArrayList<>());
        vaccineMap.put(dtap, new ArrayList<>());
        vaccineMap.put(polio, new ArrayList<>());
        vaccineMap.put(hepatitisB, new ArrayList<>());
        vaccineMap.put(mmr, new ArrayList<>());
        vaccineMap.put(varicella, new ArrayList<>());
    }

    public ArrayList<Vaccine> getVaccineList() {
        return vaccineList;
    }    
    
    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

//    public ArrayList<Vaccine> getEmptyList() {
//        return emptyList;
//    }
//
//    public void setEmptyList(ArrayList<Vaccine> emptyList) {
//        this.emptyList = emptyList;
//    }

    public Map<Vaccine, List<Vaccine>> getVaccineMap() {
        return vaccineMap;
    }

    public void setVaccineMap(Map<Vaccine, List<Vaccine>> vaccineMap) {
        this.vaccineMap = vaccineMap;
    }
    
    
    
    
}
