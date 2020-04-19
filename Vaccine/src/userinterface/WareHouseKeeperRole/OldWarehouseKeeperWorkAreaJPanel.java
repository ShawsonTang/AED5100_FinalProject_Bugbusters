/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.WareHouseKeeperRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.FactoryOrganization;
import Business.Organization.HealthDepartmentOrganization;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.WorkQueue.VaccineProduceRequest;
import Business.WorkQueue.VaccinePurchaseRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userinterface.SignIn;

/**
 *
 * @author Jasmine
 */
public class OldWarehouseKeeperWorkAreaJPanel extends javax.swing.JPanel {
    private JPanel container;
    private WarehouseOrganization organization;
    private Enterprise enterprise;
    private Network network;            
    private UserAccount userAccount;
    private EcoSystem system;
    /**
     * Creates new form WareHouseManagerJPanel
     */
    public OldWarehouseKeeperWorkAreaJPanel(JPanel container, UserAccount userAccount, WarehouseOrganization organization, Enterprise enterprise, Network network, EcoSystem system) {
        initComponents();
        this.container = container;
        this.organization = organization;
        this.enterprise = enterprise;
        this.network = network;
        this.userAccount = userAccount;
        this.system = system;
        populateTextField();
        populateVaccineInfoTable();
        populateTotalTable();
        populateRequestTable();
        populateVaccinePurchaseTable();
    }
    void populateTextField() {
        nameText.setText(userAccount.getEmployee().getName());
        phoneNumText.setText(userAccount.getEmployee().getPhoneNum());
        photoLabel.setIcon(userAccount.getEmployee().getPhoto());
//        System.out.println(userAccount.getEmployee().getPhoto());
        usernameText.setText(userAccount.getUsername());
        passwordText.setText(userAccount.getPassword());
    }
     
     void populateVaccineInfoTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineInfoTable.getModel();
        model.setRowCount(0);        
        for (Vaccine keyV : organization.getVaccineDirectory().getVaccineMap().keySet()) {
            for (Vaccine v : organization.getVaccineDirectory().getVaccineMap().get(keyV)) {
                Object[] row = new Object[5];
                row[0] = v;
                row[1] = v.getId();
                row[2] = v.getDoseInStock();
                row[3] = v.getProDate();
                row[4] = v.getExpDate();
                model.addRow(row);
            }            
        }
     }
     void populateVaccinePurchaseTable() {
        DefaultTableModel model = (DefaultTableModel) vaccinePurchaseTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccinePurchaseRequest) {
                row[0] = r;
                row[1] = r.getId();
                row[2] = ((VaccinePurchaseRequest) r).getDosesPurchase();
                row[3] = r.getSender();
                row[4] = r.getStatus();
                model.addRow(row);
            }
        }
     }
     
     void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineRequestTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccineProduceRequest) {
                row[0] = r;
                row[1] = r.getId();
                row[2] = ((VaccineProduceRequest) r).getDosesRequest();
                row[3] = r.getReceiver();
                row[4] = r.getStatus();
                model.addRow(row);
            }
        }
        
     }
     
     void populateTotalTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineTotalTable.getModel();
        model.setRowCount(0);
        
        for (Vaccine keyV : organization.getVaccineDirectory().getVaccineMap().keySet()) {
            int count = 0;            
            for (Vaccine v : organization.getVaccineDirectory().getVaccineMap().get(keyV)) {                
                count += v.getDoseInStock();
            }    
            Object[] row = new Object[3];
            row[0] = keyV;
            row[1] = count; 
            row[2] = count == 0 ? "Out of Stock" : null;
            model.addRow(row);
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

        nameText = new javax.swing.JTextField();
        phoneNumText = new javax.swing.JTextField();
        selectBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vaccineRequestTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        declineBtn = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        requestBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        passwordText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        photoLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vaccineInfoTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        vaccinePurchaseTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        vaccineTotalTable = new javax.swing.JTable();
        logOutBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        vaccineText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        doseText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        expText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        manuText = new javax.swing.JTextField();
        confirmBtn = new javax.swing.JButton();
        vaccineIDComboBox = new javax.swing.JComboBox<>();

        nameText.setEditable(false);
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        phoneNumText.setEditable(false);
        phoneNumText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumTextActionPerformed(evt);
            }
        });

        selectBtn.setText("Select");
        selectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtnMouseClicked(evt);
            }
        });
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Password:");

        vaccineRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vaccine", "Request ID", "Doses Request", "Receiver", "Status"
            }
        ));
        jScrollPane1.setViewportView(vaccineRequestTable);

        jLabel8.setText("Username:");

        jLabel7.setText("Phone Number:");

        usernameText.setEditable(false);
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        jLabel2.setText("Name:");

        jLabel6.setText("Address:");

        declineBtn.setText("Decline");
        declineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineBtnActionPerformed(evt);
            }
        });

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        enterpriseLabel.setText("Ware House Keeper");

        requestBtn.setText("Request");
        requestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Photo:");

        passwordText.setEditable(false);
        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        addressText.setEditable(false);
        addressText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextActionPerformed(evt);
            }
        });

        vaccineInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vaccine", "Vaccine ID", "Doses", "Manufacture Date", "Expiration Date"
            }
        ));
        jScrollPane2.setViewportView(vaccineInfoTable);
        if (vaccineInfoTable.getColumnModel().getColumnCount() > 0) {
            vaccineInfoTable.getColumnModel().getColumn(1).setHeaderValue("Vaccine ID");
            vaccineInfoTable.getColumnModel().getColumn(3).setHeaderValue("Manufacture Date");
        }

        vaccinePurchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vaccine", "Request ID", "Dose Purchase", "Sender", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(vaccinePurchaseTable);
        if (vaccinePurchaseTable.getColumnModel().getColumnCount() > 0) {
            vaccinePurchaseTable.getColumnModel().getColumn(3).setResizable(false);
        }

        vaccineTotalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Vaccine", "Stock", "Alert"
            }
        ));
        jScrollPane4.setViewportView(vaccineTotalTable);

        logOutBtn.setText("Log Out");
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Vaccine");

        vaccineText.setEditable(false);
        vaccineText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaccineTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Vaccine ID");

        jLabel5.setText("Dose");

        doseText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doseTextActionPerformed(evt);
            }
        });

        jLabel11.setText("Expiration Date ");

        expText.setEditable(false);
        expText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expTextActionPerformed(evt);
            }
        });

        jLabel12.setText("Manufacture Date");

        manuText.setEditable(false);
        manuText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuTextActionPerformed(evt);
            }
        });

        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        vaccineIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaccineIDComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(declineBtn)
                        .addGap(18, 18, 18)
                        .addComponent(selectBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(logOutBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(requestBtn)
                                .addGap(294, 294, 294)))))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(319, 319, 319)
                            .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(483, 483, 483)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(465, 465, 465)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(vaccineIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(expText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(manuText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(confirmBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutBtn))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(phoneNumText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectBtn)
                            .addComponent(declineBtn))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(expText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(manuText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vaccineIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmBtn))
                        .addGap(25, 25, 25)
                        .addComponent(requestBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void phoneNumTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumTextActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextActionPerformed

    private void addressTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextActionPerformed

    private void requestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = vaccineTotalTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Vaccine selectedVaccine = (Vaccine) vaccineTotalTable.getValueAt(selectedRow, 0);
        VaccineProduceRequestJPanel vaccineProduceRequestJPanel = new VaccineProduceRequestJPanel(container, organization, enterprise, selectedVaccine);
        container.add("vaccineProduceJPanel", vaccineProduceRequestJPanel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);
        
    }//GEN-LAST:event_requestBtnActionPerformed

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        int selectedRow = vaccinePurchaseTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccinePurchaseRequest vaccinePurchaseRequest = (VaccinePurchaseRequest) vaccinePurchaseTable.getValueAt(selectedRow, 0);
        
        Vaccine selectedVaccine = vaccinePurchaseRequest.getVaccine();
        DefaultTableModel totalModel = (DefaultTableModel) vaccineTotalTable.getModel();
        for (int count = 0; count < totalModel.getRowCount(); count++) {
            Vaccine target = (Vaccine) totalModel.getValueAt(count, 0);
            if (selectedVaccine.getVaccineType().equals(target.getVaccineType())) {
                int total = 0;            
                for (Vaccine v : organization.getVaccineDirectory().getVaccineMap().get(target)) {                
                    total += v.getDoseInStock();
                } 
                int purchase = vaccinePurchaseRequest.getDosesPurchase();
//                System.out.println(total);
                if (total < purchase) {
                    JOptionPane.showMessageDialog(null,"This vaccine is deficient, please check the stock.","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    vaccineText.setText(selectedVaccine.getVaccineType());
                    DefaultTableModel infoModel = (DefaultTableModel) vaccineInfoTable.getModel();
                    populateIDComboBox(infoModel, selectedVaccine);
                    vaccineIDComboBox.addActionListener((e) -> {
                        String id = (String) vaccineIDComboBox.getSelectedItem();
                        for (int i = 0; i < infoModel.getRowCount(); i++) {
                            Vaccine sameIDVaccine = (Vaccine)infoModel.getValueAt(i, 0);
                            if (sameIDVaccine.getId().equals(id)) {
                                doseText.setText(""+ sameIDVaccine.getDoseInStock());
                                expText.setText(""+ sameIDVaccine.getExpDate());
                                manuText.setText("" + sameIDVaccine.getProDate());
                            }
                        }
                    });
                }
                break;
            }
        }
        populateTotalTable();
        populateVaccinePurchaseTable();
    }//GEN-LAST:event_selectBtnActionPerformed
    
    private void populateIDComboBox(DefaultTableModel model, Vaccine v) {
        vaccineIDComboBox.removeAllItems();
        for (int i = 0; i < model.getRowCount(); i++) { 
            Vaccine target = (Vaccine) model.getValueAt(i, 0);
            if (v.getVaccineType().equals(target.getVaccineType())) {
                String id = target.getId();
                vaccineIDComboBox.addItem(id);
            }
        }
    }
    
    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
//        container.removeAll();
//        JPanel blankJP = new JPanel();
//        container.add("blank", blankJP);
//        CardLayout crdLyt = (CardLayout) container.getLayout();
//        crdLyt.next(container);
        this.setVisible(false);
        new SignIn().setVisible(true);
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void vaccineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccineTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaccineTextActionPerformed

    private void doseTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doseTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doseTextActionPerformed

    private void expTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expTextActionPerformed

    private void manuTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manuTextActionPerformed

    private void selectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_selectBtnMouseClicked

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        int selectedRow = vaccinePurchaseTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccinePurchaseRequest vaccinePurchaseRequest = (VaccinePurchaseRequest) vaccinePurchaseTable.getValueAt(selectedRow, 0);
        Vaccine purchaseVaccine = vaccinePurchaseRequest.getVaccine();
        String id = "" + vaccineIDComboBox.getSelectedItem();
        int dose = Integer.parseInt(doseText.getText());
//            SimpleDateFormat formatter = new SimpleDateFormat();
//            Date manuDate = formatter.parse(manuText.getText());
//            Date expDate = formatter.parse(expText.getText());
        purchaseVaccine.setId(id);
        purchaseVaccine.setDoseInStock(dose);

        vaccinePurchaseRequest.setStatus("Approved");            
        JOptionPane.showMessageDialog(null,"Approve vaccine purchase successfully.");

        DefaultTableModel infoModel = (DefaultTableModel) vaccineInfoTable.getModel();           
        for (int i = 0; i < infoModel.getRowCount(); i++) {
            Vaccine sameIDVaccine = (Vaccine)infoModel.getValueAt(i, 0);
            if (sameIDVaccine.getId().equals(id)) {
                if (dose < sameIDVaccine.getDoseInStock()) {
                    sameIDVaccine.setDoseInStock(0);
                    purchaseVaccine.setExpDate(sameIDVaccine.getExpDate());
                    purchaseVaccine.setProDate(sameIDVaccine.getProDate());
                    JOptionPane.showMessageDialog(null,"The vaccine under this id is deficient, please split with another one!","Warning",JOptionPane.WARNING_MESSAGE);                    
                }
                else {
                    sameIDVaccine.setDoseInStock(sameIDVaccine.getDoseInStock() - dose);
                    purchaseVaccine.setExpDate(sameIDVaccine.getExpDate());
                    purchaseVaccine.setProDate(sameIDVaccine.getProDate());
                }
            }
        }
        
        //Update the vaccine map in health department org.
        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                if (o.getName().equals(vaccinePurchaseRequest.getSender().getName())) {
                    for (Vaccine keyV : o.getVaccineDirectory().getVaccineMap().keySet()) {
                        if (keyV.getVaccineType().equals(purchaseVaccine.getVaccineType())) {
                            o.getVaccineDirectory().getVaccineMap().get(keyV).add(purchaseVaccine);
                        }
                    }
                }
            }
        }
        
        populateTotalTable();
        populateVaccineInfoTable();
        populateVaccinePurchaseTable();
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void vaccineIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccineIDComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaccineIDComboBoxActionPerformed

    private void declineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_declineBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressText;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JButton declineBtn;
    private javax.swing.JTextField doseText;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JTextField expText;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JTextField manuText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField passwordText;
    private javax.swing.JTextField phoneNumText;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JButton requestBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JTextField usernameText;
    private javax.swing.JComboBox<String> vaccineIDComboBox;
    private javax.swing.JTable vaccineInfoTable;
    private javax.swing.JTable vaccinePurchaseTable;
    private javax.swing.JTable vaccineRequestTable;
    private javax.swing.JTextField vaccineText;
    private javax.swing.JTable vaccineTotalTable;
    // End of variables declaration//GEN-END:variables
}
