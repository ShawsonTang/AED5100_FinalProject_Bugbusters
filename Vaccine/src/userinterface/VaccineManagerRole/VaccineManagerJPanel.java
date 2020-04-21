/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.VaccineManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoPHOrganization;
import Business.Organization.HealthDepartmentOrganization;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.WorkQueue.ProviderRegisterRequest;
import Business.WorkQueue.VaccineDoctorRequest;
import Business.WorkQueue.VaccinePurchaseRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import userinterface.SignIn;

/**
 *
 * @author shawson
 */
public class VaccineManagerJPanel extends javax.swing.JPanel {
    private JPanel container;
    private HealthDepartmentOrganization organization;
    private Enterprise enterprise;
    private Network network;
    private UserAccount userAccount;
    private EcoSystem system;
    private SignIn frame;

    /**
     * Creates new form Demo
     */
    public VaccineManagerJPanel(JPanel container, SignIn frame, UserAccount userAccount, HealthDepartmentOrganization organization, Enterprise enterprise, Network network, EcoSystem system) {
        initComponents();
        this.container = container;
        this.frame = frame;
        this.organization = organization;
        this.enterprise = enterprise;
        this.network = network;
        this.userAccount = userAccount;
        this.system = system;
        this.setSize(1300, 697);
        setTableProperty();
        populateTextField();
        populateDoctorRequestTable();
        populateVaccineTotalTable();
        populatePurchaseTable();
        showRegisterLabelContent();
        populateRegulatorComboBox();
        populateWarehouseComboBox();
        vaccineText.setEditable(false);
        saveBtn.setEnabled(false);
        if (this.organization.isRegistered() == false) {
            requestAreaBtn.setEnabled(false);
            purchaseAreaBtn.setEnabled(false);
        }
    }
    
    private void setTableProperty() {
        doctorRequestTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        doctorRequestTable.getTableHeader().setOpaque(false);
        doctorRequestTable.getTableHeader().setBackground(new Color(120, 168, 252));
        doctorRequestTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccinePurchaseTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccinePurchaseTable.getTableHeader().setOpaque(false);
        vaccinePurchaseTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccinePurchaseTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccineTotalTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineTotalTable.getTableHeader().setOpaque(false);
        vaccineTotalTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineTotalTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        
        
    }
    void populateTextField() {
        nameText.setText(userAccount.getEmployee().getName());
        phoneNumText.setText(userAccount.getEmployee().getPhoneNum());
        addressText.setText(userAccount.getEmployee().getAddress());
        emailText.setText(userAccount.getEmployee().getEmail());
        photoLabel.setIcon(userAccount.getEmployee().getPhoto());
        //photo needs to implement 
        //photoLabel1.setIcon(userAccount.getEmployee().getPhoto());
//        System.out.println(userAccount.getEmployee().getPhoto());
        usernameText.setText(userAccount.getUsername());
        passwordText.setText(userAccount.getPassword());    
    }
    
    private void populateDoctorRequestTable() {
        DefaultTableModel model = (DefaultTableModel) doctorRequestTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccineDoctorRequest) {
                row[0] = r;
                row[1] = r.getId();
                row[2] = ((VaccineDoctorRequest) r).getDosesRequest();
                row[3] = r.getSender();
                row[4] = r.getStatus();
                model.addRow(row);
            }
        }
    }
    
    void populatePurchaseTable() {
        DefaultTableModel model = (DefaultTableModel) vaccinePurchaseTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccinePurchaseRequest) {
                row[0] = r;
                row[1] = r.getId();
                row[2] = ((VaccinePurchaseRequest) r).getDosesPurchase();
                row[3] = r.getReceiver();
                row[4] = r.getStatus();
                model.addRow(row);
            }
        }
    }
    

    void populateVaccineTotalTable() {         
//        Map<Vaccine, List<Vaccine>> vaccineMap = organization.getVaccineDirectory().getVaccineMap();
//        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
//            if (r instanceof VaccinePurchaseRequest) {
//                for (Vaccine keyV : vaccineMap.keySet()) {
//                    if (((VaccinePurchaseRequest) r).getVaccine().getVaccineType().equals(keyV.getVaccineType())) {
//                        List<Vaccine> vaccineList = vaccineMap.get(keyV);
//                        vaccineList.add(((VaccinePurchaseRequest) r).getVaccine());
//                    }
//                }
//            }
//        }
        DefaultTableModel totalModel = (DefaultTableModel) vaccineTotalTable.getModel();
        totalModel.setRowCount(0);
        
        for (Vaccine keyV : organization.getVaccineDirectory().getVaccineMap().keySet()) {
            int count = 0;            
            for (Vaccine v : organization.getVaccineDirectory().getVaccineMap().get(keyV)) {                
                count += v.getDoseInStock();
            }    
            Object[] row = new Object[3];
            row[0] = keyV;
            row[1] = count; 
            row[2] = count == 0 ? "OUT OF STOCK" : null;            
            totalModel.addRow(row);
        }
//        for (int i = 0; i < totalModel.getRowCount(); i++) {
//            if (totalModel.getValueAt(0, 1).equals("OUT OF STOCK")) {
//                
//            }
//        }
    }
    
     private void populateRegulatorComboBox() {
       regulatorComboBox.removeAllItems();
         for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
             for (Organization o : e.getOrganizationDirectory().getOrganizationList())
                if (o.getOrganizationType() == Organization.OrganizationType.DoPH) {
                    regulatorComboBox.addItem(o);
                }
         } 
    }
     
     private void populateWarehouseComboBox() {
        warehouseComboBox.removeAllItems();
        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            for (Organization f : e.getOrganizationDirectory().getOrganizationList()) {
                System.out.println(f);
                if (f.getOrganizationType().equals(Organization.OrganizationType.Warehouse)) {
                    warehouseComboBox.addItem(f);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoJPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        usernameText = new javax.swing.JTextField();
        passwordText = new javax.swing.JTextField();
        phoneNumText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        editBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        photoLabel1 = new javax.swing.JLabel();
        leftJPanel = new javax.swing.JPanel();
        requestPanelArea = new javax.swing.JPanel();
        requestAreaBtn = new javax.swing.JButton();
        purchasePanelArea = new javax.swing.JPanel();
        purchaseAreaBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        topJPanel = new javax.swing.JPanel();
        registerLabel = new javax.swing.JLabel();
        workAreaJPanel = new javax.swing.JPanel();
        requestJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        doctorRequestTable = new javax.swing.JTable();
        approveBtn = new javax.swing.JButton();
        declineBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        vaccinePurchaseTable = new javax.swing.JTable();
        tableDesLabel = new javax.swing.JLabel();
        tableDesLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        vaccinePurchaseJPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        vaccineTotalTable = new javax.swing.JTable();
        selectBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        vaccineText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        doseRequestText = new javax.swing.JTextField();
        warehouseComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        purchaseBtn = new javax.swing.JButton();
        tableDesLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        registerRequestJPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        regulatorComboBox = new javax.swing.JComboBox<>();
        registerBtn = new javax.swing.JButton();
        backgroundLabel1 = new javax.swing.JLabel();
        backgroundLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        photoLabel = new javax.swing.JLabel();
        uploadLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1300, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoJPanel.setBackground(new java.awt.Color(71, 120, 197));

        jLabel3.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PHONE");

        nameText.setEditable(false);
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        addressText.setEditable(false);
        addressText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextActionPerformed(evt);
            }
        });

        usernameText.setEditable(false);
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        passwordText.setEditable(false);
        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        phoneNumText.setEditable(false);
        phoneNumText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumTextActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NAME");

        jLabel5.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADDRESS");

        jLabel6.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("USERNAME");

        jLabel7.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PASSWORD");

        jLabel8.setFont(new java.awt.Font("Al Bayan", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("EMAIL");

        emailText.setEditable(false);
        emailText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-calendar.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-sign_mail.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-cell_phone.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-address.png"))); // NOI18N

        editBtn.setBackground(new java.awt.Color(51, 153, 255));
        editBtn.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Edit");
        editBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBtnMouseEntered(evt);
            }
        });
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        saveBtn.setBackground(new java.awt.Color(51, 153, 255));
        saveBtn.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveBtnMouseEntered(evt);
            }
        });
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        photoLabel1.setBackground(new java.awt.Color(255, 255, 255));
        photoLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/getimmunized.gif"))); // NOI18N

        javax.swing.GroupLayout infoJPanelLayout = new javax.swing.GroupLayout(infoJPanel);
        infoJPanel.setLayout(infoJPanelLayout);
        infoJPanelLayout.setHorizontalGroup(
            infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(infoJPanelLayout.createSequentialGroup()
                        .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(infoJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoJPanelLayout.createSequentialGroup()
                        .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phoneNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(infoJPanelLayout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(passwordText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usernameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(infoJPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(photoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        infoJPanelLayout.setVerticalGroup(
            infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(phoneNumText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(saveBtn))
                .addGap(33, 33, 33)
                .addComponent(photoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        add(infoJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 300, 540));

        leftJPanel.setBackground(new java.awt.Color(23, 35, 51));

        requestPanelArea.setBackground(new java.awt.Color(41, 57, 80));

        requestAreaBtn.setBackground(new java.awt.Color(41, 57, 80));
        requestAreaBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        requestAreaBtn.setForeground(new java.awt.Color(255, 255, 255));
        requestAreaBtn.setText("Request");
        requestAreaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        requestAreaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requestAreaBtn.setPreferredSize(new java.awt.Dimension(70, 24));
        requestAreaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                requestAreaBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                requestAreaBtnMouseEntered(evt);
            }
        });
        requestAreaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestAreaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout requestPanelAreaLayout = new javax.swing.GroupLayout(requestPanelArea);
        requestPanelArea.setLayout(requestPanelAreaLayout);
        requestPanelAreaLayout.setHorizontalGroup(
            requestPanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelAreaLayout.createSequentialGroup()
                .addComponent(requestAreaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        requestPanelAreaLayout.setVerticalGroup(
            requestPanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestAreaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        purchasePanelArea.setBackground(new java.awt.Color(23, 35, 51));

        purchaseAreaBtn.setBackground(new java.awt.Color(41, 57, 80));
        purchaseAreaBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        purchaseAreaBtn.setForeground(new java.awt.Color(255, 255, 255));
        purchaseAreaBtn.setText("Purchase");
        purchaseAreaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        purchaseAreaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        purchaseAreaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                purchaseAreaBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                purchaseAreaBtnMouseEntered(evt);
            }
        });
        purchaseAreaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseAreaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout purchasePanelAreaLayout = new javax.swing.GroupLayout(purchasePanelArea);
        purchasePanelArea.setLayout(purchasePanelAreaLayout);
        purchasePanelAreaLayout.setHorizontalGroup(
            purchasePanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelAreaLayout.createSequentialGroup()
                .addComponent(purchaseAreaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        purchasePanelAreaLayout.setVerticalGroup(
            purchasePanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(purchaseAreaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        logOutBtn.setBackground(new java.awt.Color(51, 153, 255));
        logOutBtn.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        logOutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logOutBtn.setText("Log out");
        logOutBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        logOutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logOutBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logOutBtnMouseEntered(evt);
            }
        });
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftJPanelLayout = new javax.swing.GroupLayout(leftJPanel);
        leftJPanel.setLayout(leftJPanelLayout);
        leftJPanelLayout.setHorizontalGroup(
            leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftJPanelLayout.createSequentialGroup()
                .addGroup(leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(purchasePanelArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(requestPanelArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        leftJPanelLayout.setVerticalGroup(
            leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftJPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(requestPanelArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(purchasePanelArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(leftJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 697));

        topJPanel.setBackground(new java.awt.Color(71, 120, 197));

        registerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerLabel.setText("Dynamic Label Text....");
        registerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout topJPanelLayout = new javax.swing.GroupLayout(topJPanel);
        topJPanel.setLayout(topJPanelLayout);
        topJPanelLayout.setHorizontalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );
        topJPanelLayout.setVerticalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        add(topJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 1170, 50));

        workAreaJPanel.setLayout(new java.awt.CardLayout());

        requestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        doctorRequestTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        doctorRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "VACCINE", "REQUEST ID", "DOSE REQUEST", "SENDER", "STATUS"
            }
        ));
        doctorRequestTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        doctorRequestTable.setShowVerticalLines(false);
        jScrollPane4.setViewportView(doctorRequestTable);

        approveBtn.setBackground(new java.awt.Color(102, 102, 102));
        approveBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        approveBtn.setText("Approve");
        approveBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        approveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        approveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                approveBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                approveBtnMouseEntered(evt);
            }
        });
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });

        declineBtn.setBackground(new java.awt.Color(102, 102, 102));
        declineBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        declineBtn.setText("Decline");
        declineBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        declineBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        declineBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                declineBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                declineBtnMouseEntered(evt);
            }
        });
        declineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineBtnActionPerformed(evt);
            }
        });

        vaccinePurchaseTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccinePurchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "VACCINE", "REQUEST ID", "DOSE PURCHASE", "RECEIVER", "STATUS"
            }
        ));
        vaccinePurchaseTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccinePurchaseTable.setShowVerticalLines(false);
        jScrollPane6.setViewportView(vaccinePurchaseTable);

        tableDesLabel.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel.setText("Doctor Request");
        tableDesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableDesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableDesLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableDesLabelMouseEntered(evt);
            }
        });

        tableDesLabel2.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel2.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel2.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel2.setText("Vaccine Purchase Request");
        tableDesLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableDesLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesLabel2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableDesLabel2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableDesLabel2MouseEntered(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vaccine005.gif"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(8, 41, 87));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(8, 41, 87));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout requestJPanelLayout = new javax.swing.GroupLayout(requestJPanel);
        requestJPanel.setLayout(requestJPanelLayout);
        requestJPanelLayout.setHorizontalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(declineBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(requestJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(requestJPanelLayout.createSequentialGroup()
                                .addGap(590, 590, 590)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(requestJPanelLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableDesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableDesLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        requestJPanelLayout.setVerticalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tableDesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(declineBtn)
                    .addComponent(approveBtn))
                .addGap(19, 19, 19)
                .addComponent(tableDesLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        workAreaJPanel.add(requestJPanel, "card2");

        vaccinePurchaseJPanel.setBackground(new java.awt.Color(255, 255, 255));
        vaccinePurchaseJPanel.setPreferredSize(new java.awt.Dimension(1300, 697));

        vaccineTotalTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccineTotalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "VACCINE", "STOCK", "ALERT"
            }
        ));
        vaccineTotalTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccineTotalTable.setShowVerticalLines(false);
        jScrollPane5.setViewportView(vaccineTotalTable);

        selectBtn.setBackground(new java.awt.Color(102, 102, 102));
        selectBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        selectBtn.setText("Select");
        selectBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectBtnMouseEntered(evt);
            }
        });
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel10.setText("Vaccine");

        vaccineText.setEditable(false);
        vaccineText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vaccineText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vaccineText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaccineTextActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel11.setText("Doses");

        doseRequestText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doseRequestTextActionPerformed(evt);
            }
        });

        warehouseComboBox.setBackground(new java.awt.Color(120, 168, 252));
        warehouseComboBox.setFont(new java.awt.Font("Menlo", 2, 18)); // NOI18N
        warehouseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel12.setText("Warehouse");

        purchaseBtn.setBackground(new java.awt.Color(102, 102, 102));
        purchaseBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        purchaseBtn.setText("Purchase");
        purchaseBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        purchaseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        purchaseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                purchaseBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                purchaseBtnMouseEntered(evt);
            }
        });
        purchaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseBtnActionPerformed(evt);
            }
        });

        tableDesLabel1.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel1.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel1.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel1.setText("Vaccine Stock");
        tableDesLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableDesLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesLabel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableDesLabel1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableDesLabel1MouseEntered(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gotvaccineblue.gif"))); // NOI18N

        javax.swing.GroupLayout vaccinePurchaseJPanelLayout = new javax.swing.GroupLayout(vaccinePurchaseJPanel);
        vaccinePurchaseJPanel.setLayout(vaccinePurchaseJPanelLayout);
        vaccinePurchaseJPanelLayout.setHorizontalGroup(
            vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                .addGap(705, 705, 705)
                .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(doseRequestText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(warehouseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232)
                        .addComponent(purchaseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel19))
        );
        vaccinePurchaseJPanelLayout.setVerticalGroup(
            vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(doseRequestText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(vaccinePurchaseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(purchaseBtn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(vaccinePurchaseJPanelLayout.createSequentialGroup()
                        .addComponent(warehouseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        workAreaJPanel.add(vaccinePurchaseJPanel, "card3");

        registerRequestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("American Typewriter", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Please Select a Local Department of Public Health ");

        jLabel9.setFont(new java.awt.Font("American Typewriter", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("to Process Your Registration");

        regulatorComboBox.setBackground(new java.awt.Color(26, 172, 147));
        regulatorComboBox.setFont(new java.awt.Font("Menlo", 2, 14)); // NOI18N
        regulatorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        registerBtn.setBackground(new java.awt.Color(102, 102, 102));
        registerBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        registerBtn.setText("Register");
        registerBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerBtnMouseEntered(evt);
            }
        });
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        backgroundLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/officialsealblack.jpg"))); // NOI18N

        backgroundLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dophbackground.png"))); // NOI18N

        javax.swing.GroupLayout registerRequestJPanelLayout = new javax.swing.GroupLayout(registerRequestJPanel);
        registerRequestJPanel.setLayout(registerRequestJPanelLayout);
        registerRequestJPanelLayout.setHorizontalGroup(
            registerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerRequestJPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(backgroundLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backgroundLabel1)
                .addGap(18, 18, 18))
            .addGroup(registerRequestJPanelLayout.createSequentialGroup()
                .addGroup(registerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerRequestJPanelLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(regulatorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerRequestJPanelLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(registerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registerRequestJPanelLayout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        registerRequestJPanelLayout.setVerticalGroup(
            registerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerRequestJPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(registerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backgroundLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backgroundLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(regulatorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );

        workAreaJPanel.add(registerRequestJPanel, "card4");

        add(workAreaJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 870, 650));

        jPanel3.setBackground(new java.awt.Color(120, 168, 252));

        photoLabel.setBackground(new java.awt.Color(255, 255, 255));

        uploadLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-upload.png"))); // NOI18N
        uploadLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uploadLabel.setEnabled(false);
        uploadLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uploadLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uploadLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(uploadLabel)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(uploadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 300, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void showRegisterLabelContent() {
        if (this.organization.isRegistered() == false) {
            registerLabel.setText("Please register with a regulator before processing your work");
            registerLabel.setFont(new Font("Serif", Font.ITALIC, 18));
            registerLabel.setForeground(Color.RED);
            registerLabel.setBackground(Color.RED);
        }
        else {
            registerLabel.setEnabled(false);            
            registerLabel.setText("Vaccine Manager: " + userAccount.getEmployee().getName());
            registerLabel.setBackground(new Color(51, 153, 255));
            registerLabel.setForeground(Color.BLACK);
            registerLabel.setFont(new Font("Serif", Font.BOLD, 18));
        }
    }
    
    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseClicked
//        RegistrationJPanel registrationJPanel = new RegistrationJPanel(container, organization, network);
    if (this.organization.isRegistered() == false) {
        vaccinePurchaseJPanel.setVisible(false);
        requestJPanel.setVisible(false);
        registerRequestJPanel.setVisible(true);
    }
//        container.add("registrationJPanel", registrationJPanel);
//        CardLayout layout = (CardLayout) container.getLayout();
//        layout.next(container);
    }//GEN-LAST:event_registerLabelMouseClicked

    private void registerLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseExited
        registerLabel.setBorder(null);
    }//GEN-LAST:event_registerLabelMouseExited

    private void registerLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseEntered
        if (this.organization.isRegistered() == false) {
            Border labelBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED);
            registerLabel.setBorder(labelBorder);
        }
    }//GEN-LAST:event_registerLabelMouseEntered

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void addressTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextActionPerformed

    private void phoneNumTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumTextActionPerformed

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        int selectedRow = doctorRequestTable.getSelectedRow();
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }       
        VaccineDoctorRequest vaccineDoctorRequest = (VaccineDoctorRequest) doctorRequestTable.getValueAt(selectedRow, 0);
        Vaccine selectedVaccine = vaccineDoctorRequest.getVaccine();
        DefaultTableModel model = (DefaultTableModel) vaccineTotalTable.getModel();
        for (int count = 0; count < model.getRowCount(); count++) {
            Vaccine target = (Vaccine) model.getValueAt(count, 0);
            if (selectedVaccine.getVaccineType().equals(target.getVaccineType())) {
                int total = 0;            
                for (Vaccine v : organization.getVaccineDirectory().getVaccineMap().get(target)) {                
                    total += v.getDoseInStock();
                } 
                int requestV = vaccineDoctorRequest.getDosesRequest();
//                System.out.println(total);
                if (total < requestV) {
                    JOptionPane.showMessageDialog(null,"This vaccine is deficient, please check the stock.","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    vaccineDoctorRequest.setStatus("Approved");                    
                    target.setDoseInStock(total - requestV); 
                    vaccineDoctorRequest.setResolveDate(new Date());
                    JOptionPane.showMessageDialog(null, "Approve Request Successfully!");
                    populateDoctorRequestTable();
                    populateVaccineTotalTable();
                }
                break;
            }
        }
        
    }//GEN-LAST:event_approveBtnActionPerformed

    private void emailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextActionPerformed

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }        
        int selectedRow = vaccineTotalTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Vaccine selectedVaccine = (Vaccine) vaccineTotalTable.getValueAt(selectedRow, 0);
        vaccineText.setText(selectedVaccine.getVaccineType());
        vaccineText.setEditable(false);
    }//GEN-LAST:event_selectBtnActionPerformed

    private void declineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineBtnActionPerformed
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = doctorRequestTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccineDoctorRequest vaccineDoctorRequest = (VaccineDoctorRequest) doctorRequestTable.getValueAt(selectedRow, 0);
        vaccineDoctorRequest.setStatus("Declined");
        populateDoctorRequestTable();
    }//GEN-LAST:event_declineBtnActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        ProviderRegisterRequest request = new ProviderRegisterRequest();
        DoPHOrganization selected = (DoPHOrganization) regulatorComboBox.getSelectedItem();
        request.setReceiver(selected);
        request.setSender(organization);
        request.setStatus("pending");
        organization.getWorkQueue().getWorkRequestList().add(request);
        system.getWorkQueue().getWorkRequestList().add(request);
        selected.getWorkQueue().getWorkRequestList().add(request);
        JOptionPane.showMessageDialog(null, "Registration Request Process Successfully!");
    }//GEN-LAST:event_registerBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        container.remove(this);
        frame.setJFrameVisible();
        frame.getB4OUtil().storeSystem(system);
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void vaccineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccineTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaccineTextActionPerformed

    private void doseRequestTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doseRequestTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doseRequestTextActionPerformed

    private void purchaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseBtnActionPerformed
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = vaccineTotalTable.getSelectedRow();
        if (selectedRow < 0 || vaccineText.getText().equals("") || vaccineText.getText() == null) {
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (doseRequestText.getText() == null || doseRequestText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Invalid quantity!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Vaccine selectedVaccine = (Vaccine) vaccineTotalTable.getValueAt(selectedRow, 0);
        VaccinePurchaseRequest request = new VaccinePurchaseRequest();
        int dose = Integer.parseInt(doseRequestText.getText());
        WarehouseOrganization warehouseOrganization = (WarehouseOrganization) warehouseComboBox.getSelectedItem();        
        request.setVaccine(selectedVaccine);
        request.setDosesPurchase(dose);
        request.setDoseForUpdate(dose);
        request.setReceiver(warehouseOrganization);
        request.setSender(organization);        
        request.setStatus("Pending");
        organization.getWorkQueue().getWorkRequestList().add(request);
        system.getWorkQueue().getWorkRequestList().add(request);
        warehouseOrganization.getWorkQueue().getWorkRequestList().add(request);
        populatePurchaseTable();
        JOptionPane.showMessageDialog(null,"Vaccine Purchase Request Created Successfully!");
        vaccineText.setText("");
        doseRequestText.setText("");
    }//GEN-LAST:event_purchaseBtnActionPerformed

    private void logOutBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseEntered
        logOutBtn.setBackground(new Color(80, 148, 240));
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
        logOutBtn.setBorder(btnBorder);
    }//GEN-LAST:event_logOutBtnMouseEntered

    private void logOutBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseExited
        logOutBtn.setBackground(new Color(51, 153, 255));
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        logOutBtn.setBorder(btnBorder);
    }//GEN-LAST:event_logOutBtnMouseExited

    private void requestAreaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestAreaBtnActionPerformed
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        else {
            activeColor(requestPanelArea);
            inactiveColor(purchasePanelArea);
            vaccinePurchaseJPanel.setVisible(false);
            requestJPanel.setVisible(true);
            registerRequestJPanel.setVisible(false);
        }
    }//GEN-LAST:event_requestAreaBtnActionPerformed

    private void purchaseAreaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseAreaBtnActionPerformed
        if (this.organization.isRegistered() == false){
            JOptionPane.showMessageDialog(null,"Please register with a regulator before processing your work","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        else {
            activeColor(purchasePanelArea);
            inactiveColor(requestPanelArea);
            vaccinePurchaseJPanel.setVisible(true);
            requestJPanel.setVisible(false);
            registerRequestJPanel.setVisible(false);
        }
    }//GEN-LAST:event_purchaseAreaBtnActionPerformed

    private void requestAreaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestAreaBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        requestAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestAreaBtnMouseEntered

    private void requestAreaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestAreaBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        requestAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestAreaBtnMouseExited

    private void purchaseAreaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseAreaBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        purchaseAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_purchaseAreaBtnMouseEntered

    private void purchaseAreaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseAreaBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        purchaseAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_purchaseAreaBtnMouseExited

    private void editBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        editBtn.setBorder(btnBorder);
    }//GEN-LAST:event_editBtnMouseExited

    private void editBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
        editBtn.setBorder(btnBorder);
    }//GEN-LAST:event_editBtnMouseEntered

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        saveBtn.setEnabled(true);
        editBtn.setEnabled(false);
        uploadLabel.setEnabled(true);
        nameText.setEditable(true);
        phoneNumText.setEditable(true);
        addressText.setEditable(true);
        emailText.setEditable(true);
        usernameText.setEditable(true);
        passwordText.setEditable(true);
        
    }//GEN-LAST:event_editBtnActionPerformed

    private void saveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        saveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_saveBtnMouseExited

    private void saveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
        saveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_saveBtnMouseEntered

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        
        String name = nameText.getText();
        String phone = phoneNumText.getText();
        String address = addressText.getText();
        String email = emailText.getText();
        String ua = usernameText.getText();
        String pa = passwordText.getText();
        if (name.equals("") || phone.equals("") || address.equals("") || email.equals("") || ua.equals("") || pa.equals("")) {
            JOptionPane.showMessageDialog(null,"Text area can not be empty","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        saveBtn.setEnabled(false);
        editBtn.setEnabled(true);
        uploadLabel.setEnabled(false);
        userAccount.getEmployee().setName(name);
        userAccount.getEmployee().setPhoneNum(phone);
        userAccount.getEmployee().setAddress(address);
        userAccount.getEmployee().setEmail(email);
        userAccount.setUsername(ua);
        userAccount.setPassword(pa);
        
        nameText.setEditable(false);
        phoneNumText.setEditable(false);
        addressText.setEditable(false);
        emailText.setEditable(false);
        usernameText.setEditable(false);
        passwordText.setEditable(false);
        JOptionPane.showMessageDialog(null,"Modify personal information successfully");
    }//GEN-LAST:event_saveBtnActionPerformed

    private void declineBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseEntered

    private void declineBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseExited

    private void approveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        approveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_approveBtnMouseExited

    private void approveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        approveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_approveBtnMouseEntered

    private void selectBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        selectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_selectBtnMouseEntered

    private void selectBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        selectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_selectBtnMouseExited

    private void purchaseBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        purchaseBtn.setBorder(btnBorder);
    }//GEN-LAST:event_purchaseBtnMouseEntered

    private void purchaseBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        purchaseBtn.setBorder(btnBorder);
    }//GEN-LAST:event_purchaseBtnMouseExited

    private void registerBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        registerBtn.setBorder(btnBorder);
    }//GEN-LAST:event_registerBtnMouseEntered

    private void registerBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        registerBtn.setBorder(btnBorder);
    }//GEN-LAST:event_registerBtnMouseExited

    private void uploadLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadLabelMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        uploadLabel.setBorder(btnBorder);
    }//GEN-LAST:event_uploadLabelMouseEntered

    private void uploadLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadLabelMouseExited
        uploadLabel.setBorder(null);
    }//GEN-LAST:event_uploadLabelMouseExited

    private void uploadLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadLabelMouseClicked
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new ImageFilter());
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showSaveDialog(null);
        if (chooser.getSelectedFile() != null) {
            File f = chooser.getSelectedFile();
            photoLabel.setIcon(new ImageIcon(f.toString()));
        }
        userAccount.getEmployee().setPhoto((ImageIcon)photoLabel.getIcon());
     
    }//GEN-LAST:event_uploadLabelMouseClicked

    private void tableDesLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseClicked

    private void tableDesLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseExited

    private void tableDesLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseEntered

    private void tableDesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseClicked

    private void tableDesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseExited

    private void tableDesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseEntered

    private void tableDesLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel2MouseClicked

    private void tableDesLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel2MouseExited

    private void tableDesLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel2MouseEntered
    
    public void inactiveColor(JPanel panel) {
        panel.setBackground(new Color(23, 35, 51));
    }
    
    public void activeColor(JPanel panel) {
        panel.setBackground(new Color(41, 57, 80));
    }

    
    
    class ImageFilter extends FileFilter {
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String fileName = f.getName();
            int i = f.getName().lastIndexOf('.');
            return fileName.substring(i + 1).equals("jpg") || fileName.substring(i + 1).equals("png");
        }
        @Override
        public String getDescription() {
            return "please select .jpg and .png file";
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressText;
    private javax.swing.JButton approveBtn;
    private javax.swing.JLabel backgroundLabel1;
    private javax.swing.JLabel backgroundLabel3;
    private javax.swing.JButton declineBtn;
    private javax.swing.JTable doctorRequestTable;
    private javax.swing.JTextField doseRequestText;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField emailText;
    private javax.swing.JPanel infoJPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel leftJPanel;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField passwordText;
    private javax.swing.JTextField phoneNumText;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JLabel photoLabel1;
    private javax.swing.JButton purchaseAreaBtn;
    private javax.swing.JButton purchaseBtn;
    private javax.swing.JPanel purchasePanelArea;
    private javax.swing.JButton registerBtn;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JPanel registerRequestJPanel;
    private javax.swing.JComboBox<Object> regulatorComboBox;
    private javax.swing.JButton requestAreaBtn;
    private javax.swing.JPanel requestJPanel;
    private javax.swing.JPanel requestPanelArea;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JLabel tableDesLabel;
    private javax.swing.JLabel tableDesLabel1;
    private javax.swing.JLabel tableDesLabel2;
    private javax.swing.JPanel topJPanel;
    private javax.swing.JLabel uploadLabel;
    private javax.swing.JTextField usernameText;
    private javax.swing.JPanel vaccinePurchaseJPanel;
    private javax.swing.JTable vaccinePurchaseTable;
    private javax.swing.JTextField vaccineText;
    private javax.swing.JTable vaccineTotalTable;
    private javax.swing.JComboBox<Object> warehouseComboBox;
    private javax.swing.JPanel workAreaJPanel;
    // End of variables declaration//GEN-END:variables
}
