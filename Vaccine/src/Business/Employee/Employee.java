/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.Vaccine.Vaccine;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author shawson
 */
public class Employee {
    
    private Vaccine vaccine;
    private String name;    
    private int id;
    private String phoneNum; 
    private String address;
    private String email;
    private ImageIcon photo;
    
    
    private static int count = 1;

//    public Employee() {
//        id = count;
//        count++;
//    }

    public Employee(String name, int id, String phoneNum) {
        this.name = name;
        this.id = id;
        this.phoneNum = phoneNum;
//        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ImageIcon getPhoto() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
