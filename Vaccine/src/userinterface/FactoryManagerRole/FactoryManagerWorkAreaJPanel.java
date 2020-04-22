/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.FactoryManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.FactoryOrganization;
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.WorkQueue.VaccineProduceRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
public class FactoryManagerWorkAreaJPanel extends javax.swing.JPanel {
    private JPanel container;
    private FactoryOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem system;
    private SignIn frame;
    private final static Set<String> idSet = new HashSet<>();
   
    /**
     * Creates new form NewFactoryManagerWorkAreaJPanel
     */
    public FactoryManagerWorkAreaJPanel(JPanel container, SignIn frame, UserAccount userAccount, FactoryOrganization organization, Enterprise enterprise, EcoSystem system) {
        initComponents();
        this.container = container;
        this.frame = frame;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = userAccount;
        this.system = system;
//        idSet = new HashSet<>();
        populateTextField();
        setTableProperty();
        populateVaccineProduceTable();
        populateVaccineRequestTable();
        populateWarehouseComboBox();
    }
    
    private void setTableProperty() {
        vaccineProduceTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineProduceTable.getTableHeader().setOpaque(false);
        vaccineProduceTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineProduceTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccineRequestTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineRequestTable.getTableHeader().setOpaque(false);
        vaccineRequestTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineRequestTable.getTableHeader().setForeground(new Color(0, 0, 0));                                      
    }
    
    void populateTextField() {
        headLabel.setEnabled(false);            
        headLabel.setText("Factory Manager: " + userAccount.getEmployee().getName());
        headLabel.setBackground(new Color(51, 153, 255));
        headLabel.setForeground(Color.BLACK);
        headLabel.setFont(new Font("Serif", Font.BOLD, 18));
                
        nameText.setText(userAccount.getEmployee().getName());
        phoneNumText.setText(userAccount.getEmployee().getPhoneNum());
        addressText.setText(userAccount.getEmployee().getAddress());
        emailText.setText(userAccount.getEmployee().getEmail());
        photoLabel.setIcon(userAccount.getEmployee().getPhoto());
        usernameText.setText(userAccount.getUsername());
        passwordText.setText(userAccount.getPassword());  
    }
    
    void populateVaccineProduceTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineProduceTable.getModel();
        model.setRowCount(0);        
        for (Vaccine v : organization.getVaccineDirectory().getVaccineList()) {
            Object[] row = new Object[5];
            row[0] = v;            
            row[1] = v.getId() == null ? "" : v.getId();
            row[2] = v.getDoseProdeced();
            row[3] = v.getProDate();
            row[4] = v.getExpDate();
            model.addRow(row);
        }
     }
     
     void populateVaccineRequestTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineRequestTable.getModel();
        model.setRowCount(0);        
        Object[] row = new Object[5];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccineProduceRequest) {
                row[0] = r;                
                row[1] = r.getId();
                row[2] = ((VaccineProduceRequest) r).getDosesRequest();
                row[3] = r.getSender();
                row[4] = r.getStatus();
                model.addRow(row);
            }
        }
     }
     
     void populateWarehouseComboBox() {
         warehouseComboBox.removeAllItems();
         for (Organization o : enterprise.getOrganizationDirectory().getOrganizationList()) {
             if (o.getOrganizationType() == Organization.OrganizationType.Warehouse) {
                 warehouseComboBox.addItem(o);
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

        topJPanel = new javax.swing.JPanel();
        headLabel = new javax.swing.JLabel();
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
        producePanelArea = new javax.swing.JPanel();
        produceAreaBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        workAreaJPanel = new javax.swing.JPanel();
        requestJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        vaccineRequestTable = new javax.swing.JTable();
        approveBtn = new javax.swing.JButton();
        declineBtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        tableDesLabel1 = new javax.swing.JLabel();
        vaccineProduceJPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        vaccineProduceTable = new javax.swing.JTable();
        produceBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        vaccineText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        doseText = new javax.swing.JTextField();
        warehouseComboBox = new javax.swing.JComboBox<>();
        storeBtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        idText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        manuDateText = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        expDateText = new javax.swing.JTextField();
        selectBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tableDesLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tableDesLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        photoLabel = new javax.swing.JLabel();
        uploadLabel = new javax.swing.JLabel();

        topJPanel.setBackground(new java.awt.Color(71, 120, 197));

        headLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLabel.setText("Dynamic Label Text....");
        headLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        headLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                headLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                headLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                headLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout topJPanelLayout = new javax.swing.GroupLayout(topJPanel);
        topJPanel.setLayout(topJPanelLayout);
        topJPanelLayout.setHorizontalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(headLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );
        topJPanelLayout.setVerticalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(headLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        producePanelArea.setBackground(new java.awt.Color(23, 35, 51));

        produceAreaBtn.setBackground(new java.awt.Color(41, 57, 80));
        produceAreaBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        produceAreaBtn.setForeground(new java.awt.Color(255, 255, 255));
        produceAreaBtn.setText("Produce");
        produceAreaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        produceAreaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        produceAreaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produceAreaBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produceAreaBtnMouseEntered(evt);
            }
        });
        produceAreaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produceAreaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout producePanelAreaLayout = new javax.swing.GroupLayout(producePanelArea);
        producePanelArea.setLayout(producePanelAreaLayout);
        producePanelAreaLayout.setHorizontalGroup(
            producePanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(producePanelAreaLayout.createSequentialGroup()
                .addComponent(produceAreaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        producePanelAreaLayout.setVerticalGroup(
            producePanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(produceAreaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
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
                    .addComponent(producePanelArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(producePanelArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        workAreaJPanel.setLayout(new java.awt.CardLayout());

        requestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        vaccineRequestTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccineRequestTable.setModel(new javax.swing.table.DefaultTableModel(
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
        vaccineRequestTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccineRequestTable.setShowVerticalLines(false);
        jScrollPane4.setViewportView(vaccineRequestTable);

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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gotvaccineblue.gif"))); // NOI18N

        tableDesLabel1.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel1.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel1.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel1.setText("Vaccine Manufacture Information");
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

        javax.swing.GroupLayout requestJPanelLayout = new javax.swing.GroupLayout(requestJPanel);
        requestJPanel.setLayout(requestJPanelLayout);
        requestJPanelLayout.setHorizontalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestJPanelLayout.createSequentialGroup()
                        .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(requestJPanelLayout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jLabel19))
                            .addGroup(requestJPanelLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 154, Short.MAX_VALUE))
                    .addGroup(requestJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(declineBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane4)
        );
        requestJPanelLayout.setVerticalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveBtn)
                    .addComponent(declineBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        workAreaJPanel.add(requestJPanel, "card2");

        vaccineProduceJPanel.setBackground(new java.awt.Color(255, 255, 255));
        vaccineProduceJPanel.setPreferredSize(new java.awt.Dimension(1300, 697));

        vaccineProduceTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccineProduceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "VACCINE", "REQUEST ID", "VACCINE ID", "DOSE", "MANU DATE", "EXP DATE"
            }
        ));
        vaccineProduceTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccineProduceTable.setShowVerticalLines(false);
        jScrollPane5.setViewportView(vaccineProduceTable);
        if (vaccineProduceTable.getColumnModel().getColumnCount() > 0) {
            vaccineProduceTable.getColumnModel().getColumn(1).setHeaderValue("REQUEST ID");
        }

        produceBtn.setBackground(new java.awt.Color(102, 102, 102));
        produceBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        produceBtn.setText("Produce");
        produceBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        produceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        produceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produceBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produceBtnMouseEntered(evt);
            }
        });
        produceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produceBtnActionPerformed(evt);
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

        doseText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        doseText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doseTextActionPerformed(evt);
            }
        });

        warehouseComboBox.setBackground(new java.awt.Color(120, 168, 252));
        warehouseComboBox.setFont(new java.awt.Font("Menlo", 2, 18)); // NOI18N
        warehouseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        storeBtn.setBackground(new java.awt.Color(102, 102, 102));
        storeBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        storeBtn.setText("Store");
        storeBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        storeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        storeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                storeBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                storeBtnMouseEntered(evt);
            }
        });
        storeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeBtnActionPerformed(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vaccine005.gif"))); // NOI18N

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel12.setText("ID");

        idText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel17.setText("MANU Date");

        manuDateText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        manuDateText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        manuDateText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuDateTextActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel18.setText("EXP Date");

        expDateText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        expDateText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expDateTextActionPerformed(evt);
            }
        });

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

        jPanel1.setBackground(new java.awt.Color(8, 41, 87));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableDesLabel.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel.setText("Vaccine Manufacture Information");
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

        jPanel2.setBackground(new java.awt.Color(8, 41, 87));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableDesLabel2.setBackground(new java.awt.Color(8, 41, 87));
        tableDesLabel2.setFont(new java.awt.Font("American Typewriter", 1, 18)); // NOI18N
        tableDesLabel2.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel2.setText("Please select a warehouse and the vaccine you just produced to store");
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

        javax.swing.GroupLayout vaccineProduceJPanelLayout = new javax.swing.GroupLayout(vaccineProduceJPanel);
        vaccineProduceJPanel.setLayout(vaccineProduceJPanelLayout);
        vaccineProduceJPanelLayout.setHorizontalGroup(
            vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(tableDesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(manuDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(expDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(produceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(warehouseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(storeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                                .addGap(590, 590, 590)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tableDesLabel2)))
                .addGap(430, 430, 430))
        );
        vaccineProduceJPanelLayout.setVerticalGroup(
            vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(tableDesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(manuDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(expDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produceBtn))
                .addGap(19, 19, 19)
                .addComponent(tableDesLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warehouseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vaccineProduceJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(storeBtn)))
                .addGap(17, 17, 17)
                .addGroup(vaccineProduceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        workAreaJPanel.add(vaccineProduceJPanel, "card3");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(leftJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(topJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(infoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(workAreaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(leftJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(topJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(110, 110, 110)
                                    .addComponent(infoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(workAreaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void headLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseClicked

    }//GEN-LAST:event_headLabelMouseClicked

    private void headLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseExited
        headLabel.setBorder(null);
    }//GEN-LAST:event_headLabelMouseExited

    private void headLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseEntered

    }//GEN-LAST:event_headLabelMouseEntered

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

    private void emailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextActionPerformed

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

    private void requestAreaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestAreaBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        requestAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestAreaBtnMouseExited

    private void requestAreaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestAreaBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        requestAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestAreaBtnMouseEntered

    private void requestAreaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestAreaBtnActionPerformed

        activeColor(requestPanelArea);
        inactiveColor(producePanelArea);
        vaccineProduceJPanel.setVisible(false);
        requestJPanel.setVisible(true);               
    }//GEN-LAST:event_requestAreaBtnActionPerformed

    private void produceAreaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produceAreaBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        produceAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_produceAreaBtnMouseExited

    private void produceAreaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produceAreaBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        produceAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_produceAreaBtnMouseEntered

    private void produceAreaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produceAreaBtnActionPerformed
        
        activeColor(producePanelArea);
        inactiveColor(requestPanelArea);
        vaccineProduceJPanel.setVisible(true);
        requestJPanel.setVisible(false);
        
        
    }//GEN-LAST:event_produceAreaBtnActionPerformed

    private void logOutBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseExited
        logOutBtn.setBackground(new Color(51, 153, 255));
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        logOutBtn.setBorder(btnBorder);
    }//GEN-LAST:event_logOutBtnMouseExited

    private void logOutBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseEntered
        logOutBtn.setBackground(new Color(80, 148, 240));
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
        logOutBtn.setBorder(btnBorder);
    }//GEN-LAST:event_logOutBtnMouseEntered

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        container.remove(this);
        frame.setJFrameVisible();
        frame.getB4OUtil().storeSystem(system);
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void approveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        approveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_approveBtnMouseExited

    private void approveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        approveBtn.setBorder(btnBorder);
    }//GEN-LAST:event_approveBtnMouseEntered

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        int selectedRow = vaccineRequestTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        VaccineProduceRequest vaccineProduceRequest = (VaccineProduceRequest) vaccineRequestTable.getValueAt(selectedRow, 0);        
        if (vaccineProduceRequest.getStatus().equalsIgnoreCase("produced and stored")) {
            JOptionPane.showMessageDialog(null,"This work request has completed yet!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        vaccineProduceRequest.setStatus("Producing");
        Vaccine selectedVaccine = vaccineProduceRequest.getVaccine();
        DefaultTableModel model = (DefaultTableModel) vaccineProduceTable.getModel();
        for (int count = 0; count < model.getRowCount(); count++) {
            Vaccine target = (Vaccine) model.getValueAt(count, 0);
            if (selectedVaccine.getVaccineType().equals(target.getVaccineType())) {
                target.setDoseProdeced(vaccineProduceRequest.getDosesRequest());
//                System.out.println(vaccineProduceRequest.getId());
                populateVaccineProduceTable();
                populateVaccineRequestTable();
//                vaccineProduceTable.setValueAt((Integer)vaccineProduceRequest.getId(), count, 1);
//                model.fireTableCellUpdated(count, 1);
                System.out.println(model.getValueAt(count, 1));
                break;
            }
            
        }
        

    }//GEN-LAST:event_approveBtnActionPerformed

    private void declineBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseExited

    private void declineBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseEntered

    private void declineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineBtnActionPerformed

        int selectedRow = vaccineRequestTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        VaccineProduceRequest vaccineProduceRequest = (VaccineProduceRequest) vaccineRequestTable.getValueAt(selectedRow, 0);
        if (vaccineProduceRequest.getStatus().equalsIgnoreCase("produced and stored")) {
            JOptionPane.showMessageDialog(null,"This work request has completed yet!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        vaccineProduceRequest.setStatus("Declined");
    }//GEN-LAST:event_declineBtnActionPerformed

    private void produceBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produceBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        produceBtn.setBorder(btnBorder);
    }//GEN-LAST:event_produceBtnMouseExited

    private void produceBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produceBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        produceBtn.setBorder(btnBorder);
    }//GEN-LAST:event_produceBtnMouseEntered

    private void produceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produceBtnActionPerformed
        int selectedRow = vaccineProduceTable.getSelectedRow();
        if (selectedRow < 0 || vaccineText.getText().equals("") || vaccineText.getText() == null) {
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (doseText.getText() == null || doseText.getText().equals("") || idText.getText() == null || idText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Text field can not be empty","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Vaccine selectedVaccine = (Vaccine) vaccineProduceTable.getValueAt(selectedRow, 0);
        if (selectedVaccine.getProDate() != null) {
            JOptionPane.showMessageDialog(null,"Please store this vaccine first before you produce it again","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {            
            String id = idText.getText();
            //Check if the id exist
            if (idSet.contains(id)) {
                JOptionPane.showMessageDialog(null,"This id already exists please use another one!","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            idSet.add(id);
            int dose = Integer.parseInt(doseText.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy");
            Date manuDate = formatter.parse(manuDateText.getText());
            Date expDate = formatter.parse(expDateText.getText()); 
            selectedVaccine.setId(id);
            selectedVaccine.setDoseProdeced(dose);
            selectedVaccine.setProDate(manuDate);
            selectedVaccine.setExpDate(expDate);
            System.out.println(idSet.size());
//            vaccineText.setText("");
            idText.setText("");
            doseText.setText("");
            manuDateText.setText("");
            expDateText.setText("");
            populateVaccineProduceTable();
            
            JOptionPane.showMessageDialog(null,"Produce vaccine successfully!");
        } catch (ParseException ex) {
//            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Date format is incorrect! Please follow 'MM dd, yyyy'","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_produceBtnActionPerformed

    private void vaccineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccineTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaccineTextActionPerformed

    private void doseTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doseTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doseTextActionPerformed

    private void storeBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        storeBtn.setBorder(btnBorder);
    }//GEN-LAST:event_storeBtnMouseExited

    private void storeBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        storeBtn.setBorder(btnBorder);
    }//GEN-LAST:event_storeBtnMouseEntered

    private void storeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeBtnActionPerformed
         int selectedRow = vaccineProduceTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        WarehouseOrganization warehouseOrganization = (WarehouseOrganization) warehouseComboBox.getSelectedItem();
        if (warehouseOrganization == null) {
            return;
        }
        Vaccine selectedVaccine = (Vaccine) vaccineProduceTable.getValueAt(selectedRow, 0);
        if (!selectedVaccine.getVaccineType().equalsIgnoreCase(vaccineText.getText()) || selectedVaccine.getId() == null|| selectedVaccine.getId().equals("")) {
            JOptionPane.showMessageDialog(null,"Please produce the vaccine first or select the vaccine as text area suggests.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedVaccine.getId() != null|| !selectedVaccine.getId().equals("")) {
            String type = selectedVaccine.getVaccineType();
            String id = selectedVaccine.getId();
            int doseP = selectedVaccine.getDoseProdeced();
            int doesS = selectedVaccine.getDoseProdeced();
            Date proDate = selectedVaccine.getProDate();
            Date expDate = selectedVaccine.getExpDate();

            Vaccine producedVaccine = new Vaccine(type, id, doseP, doesS, proDate, expDate);
            Vaccine vaccineKey = null;
            for (Vaccine v : warehouseOrganization.getVaccineDirectory().getVaccineMap().keySet()) {
                if (v.getVaccineType().equals(selectedVaccine.getVaccineType())) {
                    vaccineKey = v;
                }
            }
            warehouseOrganization.getVaccineDirectory().getVaccineMap().get(vaccineKey).add(producedVaccine);        
            selectedVaccine.setId("");
            selectedVaccine.setDoseProdeced(0);
            selectedVaccine.setProDate(null);
            selectedVaccine.setExpDate(null);                         
            
            //Change the status to prodeced and stored in vaccine request table
            DefaultTableModel requestModel = (DefaultTableModel) vaccineRequestTable.getModel();
            DefaultTableModel produceModel = (DefaultTableModel) vaccineProduceTable.getModel();
            for (int count = 0; count < requestModel.getRowCount(); count++) {
                VaccineProduceRequest target = (VaccineProduceRequest) requestModel.getValueAt(count, 0);
//                int requestId = (int) produceModel.getValueAt(count, 1);
                if (selectedVaccine.getVaccineType().equals(target.getVaccine().getVaccineType())) {
                    target.setStatus("Produced and Stored");
                    target.setResolveDate(new Date());                    
                    vaccineText.setText("");
                } 
            }
            JOptionPane.showMessageDialog(null,"Stored vaccine successfully!");            
            populateVaccineProduceTable();
            populateVaccineRequestTable();
        } else {
            JOptionPane.showMessageDialog(null,"Please produce the vaccine first!","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_storeBtnActionPerformed

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

    private void uploadLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadLabelMouseExited
        uploadLabel.setBorder(null);
    }//GEN-LAST:event_uploadLabelMouseExited

    private void uploadLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadLabelMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        uploadLabel.setBorder(btnBorder);
    }//GEN-LAST:event_uploadLabelMouseEntered

    private void idTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextActionPerformed

    private void manuDateTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuDateTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manuDateTextActionPerformed

    private void expDateTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expDateTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expDateTextActionPerformed

    private void selectBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_selectBtnMouseExited

    private void selectBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_selectBtnMouseEntered

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        int selectedRow = vaccineProduceTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Vaccine selectedVaccine = (Vaccine) vaccineProduceTable.getValueAt(selectedRow, 0);        
        vaccineText.setText(selectedVaccine.getVaccineType());
        if (doseText.getText().equals("")) {
            doseText.setText("" + 0);
        } 
        else {
            int dose = Integer.parseInt(doseText.getText());
            doseText.setText("" + dose);
        }
        vaccineText.setEnabled(false);
    }//GEN-LAST:event_selectBtnActionPerformed

    private void tableDesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseClicked

    private void tableDesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseExited

    private void tableDesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabelMouseEntered

    private void tableDesLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseClicked

    private void tableDesLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseExited

    private void tableDesLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel1MouseEntered

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
    private javax.swing.JButton declineBtn;
    private javax.swing.JTextField doseText;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField expDateText;
    private javax.swing.JLabel headLabel;
    private javax.swing.JTextField idText;
    private javax.swing.JPanel infoJPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel leftJPanel;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JTextField manuDateText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField passwordText;
    private javax.swing.JTextField phoneNumText;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JLabel photoLabel1;
    private javax.swing.JButton produceAreaBtn;
    private javax.swing.JButton produceBtn;
    private javax.swing.JPanel producePanelArea;
    private javax.swing.JButton requestAreaBtn;
    private javax.swing.JPanel requestJPanel;
    private javax.swing.JPanel requestPanelArea;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton storeBtn;
    private javax.swing.JLabel tableDesLabel;
    private javax.swing.JLabel tableDesLabel1;
    private javax.swing.JLabel tableDesLabel2;
    private javax.swing.JPanel topJPanel;
    private javax.swing.JLabel uploadLabel;
    private javax.swing.JTextField usernameText;
    private javax.swing.JPanel vaccineProduceJPanel;
    private javax.swing.JTable vaccineProduceTable;
    private javax.swing.JTable vaccineRequestTable;
    private javax.swing.JTextField vaccineText;
    private javax.swing.JComboBox<Object> warehouseComboBox;
    private javax.swing.JPanel workAreaJPanel;
    // End of variables declaration//GEN-END:variables
}
