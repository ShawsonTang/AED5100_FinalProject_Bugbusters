/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Employee.Employee;
import Business.Role.Role;
import Business.Vaccine.VaccineDirectory;
import Business.WorkQueue.WorkQueue;

/**
 *
 * @author shawson
 */
public class UserAccount {
    
    private String username;
    private String password;
    private Employee employee;
    private Role role;
    private WorkQueue workQueue;
    private VaccineDirectory vaccineDirectory;
    public UserAccount() {
        workQueue = new WorkQueue();
        vaccineDirectory = new VaccineDirectory();
    }
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public VaccineDirectory getVaccineDirectory() {
        return vaccineDirectory;
    }

    public void setVaccineDirectory(VaccineDirectory vaccineDirectory) {
        this.vaccineDirectory = vaccineDirectory;
    }

    
    
    @Override
    public String toString() {
        return username;
    }
    
    
    
}