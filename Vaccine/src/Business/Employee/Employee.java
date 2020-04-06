/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import java.awt.image.BufferedImage;

/**
 *
 * @author shawson
 */
public class Employee {
    
    private String name;
    private String phoneNum;
    private BufferedImage photo;
    
    private int id;
    private static int count = 1;

    public Employee() {
        id = count;
        count++;
    }

    public Employee(String name, String phoneNum, BufferedImage photo) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.photo = photo;
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

    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
