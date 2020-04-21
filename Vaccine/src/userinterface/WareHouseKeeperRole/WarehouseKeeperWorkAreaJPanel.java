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
import Business.Organization.Organization;
import Business.Organization.WarehouseOrganization;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.WorkQueue.VaccineProduceRequest;
import Business.WorkQueue.VaccinePurchaseRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Date;
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
public class WarehouseKeeperWorkAreaJPanel extends javax.swing.JPanel {
    private JPanel container;
    private WarehouseOrganization organization;
    private Enterprise enterprise;
    private Network network;            
    private UserAccount userAccount;
    private EcoSystem system;
    private SignIn frame;
    /**
     * Creates new form WarehouseKeeperWorkAreaJPanel
     */
    public WarehouseKeeperWorkAreaJPanel(JPanel container, SignIn frame, UserAccount userAccount, WarehouseOrganization organization, Enterprise enterprise, Network network, EcoSystem system) {
        initComponents();
        this.container = container;
        this.frame = frame;
        this.organization = organization;
        this.enterprise = enterprise;
        this.network = network;
        this.userAccount = userAccount;
        this.system = system;
        setTableProperty();
        populateTextField();
        populateVaccineInfoTable();
        populateTotalTable();
        populateRequestTable();
        populateVaccinePurchaseTable();
        populateFactoryComboBox();
        vaccineIDComboBox.removeAllItems();
    }
    
    void populateTextField() {
        headLabel.setEnabled(false);            
        headLabel.setText("Warehouse Keeper: " + userAccount.getEmployee().getName());
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
    
    private void setTableProperty() {
        vaccinePurchaseTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccinePurchaseTable.getTableHeader().setOpaque(false);
        vaccinePurchaseTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccinePurchaseTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccineInfoTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineInfoTable.getTableHeader().setOpaque(false);
        vaccineInfoTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineInfoTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccineRequestTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineRequestTable.getTableHeader().setOpaque(false);
        vaccineRequestTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineRequestTable.getTableHeader().setForeground(new Color(0, 0, 0));
        
        vaccineTotalTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        vaccineTotalTable.getTableHeader().setOpaque(false);
        vaccineTotalTable.getTableHeader().setBackground(new Color(120, 168, 252));
        vaccineTotalTable.getTableHeader().setForeground(new Color(0, 0, 0));                        
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
        Object[] row = new Object[6];        
        for (WorkRequest r : organization.getWorkQueue().getWorkRequestList()) {            
            if (r instanceof VaccinePurchaseRequest) {
                row[0] = r;
                row[1] = r.getId();
                row[2] = ((VaccinePurchaseRequest) r).getDosesPurchase();
                row[3] = ((VaccinePurchaseRequest) r).getDoseForUpdate();
                row[4] = r.getSender();
                row[5] = r.getStatus();
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
     
     void populateFactoryComboBox() {
        factoryComboBox.removeAllItems();
        for (Organization f : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (f.getOrganizationType().equals(Organization.OrganizationType.Factory)) {
                factoryComboBox.addItem(f);
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

        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        workAreaJPanel = new javax.swing.JPanel();
        requestJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        vaccinePurchaseTable = new javax.swing.JTable();
        selectBtn = new javax.swing.JButton();
        declineBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        vaccineRequestTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        doseText = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        expText = new javax.swing.JTextField();
        manuText = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        vaccineIDComboBox = new javax.swing.JComboBox<>();
        purchaseVaccineText = new javax.swing.JTextField();
        confirmBtn = new javax.swing.JButton();
        tableDesLabel1 = new javax.swing.JLabel();
        tableDesLabel2 = new javax.swing.JLabel();
        vaccineStockJPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        vaccineTotalTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        vaccineText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        doseRequestText = new javax.swing.JTextField();
        factoryComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        requestBtn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        vaccineInfoTable = new javax.swing.JTable();
        requestSelectBtn = new javax.swing.JButton();
        tableDesLabel3 = new javax.swing.JLabel();
        tableDesLabel4 = new javax.swing.JLabel();
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
        stockPanelArea = new javax.swing.JPanel();
        stockAreaBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        topJPanel = new javax.swing.JPanel();
        headLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        photoLabel = new javax.swing.JLabel();
        uploadLabel = new javax.swing.JLabel();

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gotvaccineblue.gif"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vaccine005.gif"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(8, 41, 87));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
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
            .addGap(0, 200, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1300, 697));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workAreaJPanel.setLayout(new java.awt.CardLayout());

        requestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        vaccinePurchaseTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccinePurchaseTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "VACCINE", "REQUEST ID", "DOSE REQUEST", "REQUEST REMAIN", "SENDER", "STATUS"
            }
        ));
        vaccinePurchaseTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccinePurchaseTable.setShowVerticalLines(false);
        jScrollPane4.setViewportView(vaccinePurchaseTable);

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
                "VACCINE", "REQUEST ID", "DOSE PURCHASE", "RECEIVER", "STATUS"
            }
        ));
        vaccineRequestTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccineRequestTable.setShowVerticalLines(false);
        jScrollPane6.setViewportView(vaccineRequestTable);
        if (vaccineRequestTable.getColumnModel().getColumnCount() > 0) {
            vaccineRequestTable.getColumnModel().getColumn(4).setResizable(false);
            vaccineRequestTable.getColumnModel().getColumn(4).setHeaderValue("STATUS");
        }

        jLabel17.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel17.setText("Doses");

        doseText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        doseText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doseTextActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel18.setText("Vaccine");

        jLabel21.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel21.setText("EXP Date");

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel22.setText("ID");

        expText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        expText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expTextActionPerformed(evt);
            }
        });

        manuText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        manuText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        manuText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuTextActionPerformed(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel23.setText("MANU Date");

        vaccineIDComboBox.setBackground(new java.awt.Color(120, 168, 252));
        vaccineIDComboBox.setFont(new java.awt.Font("Menlo", 2, 18)); // NOI18N
        vaccineIDComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        purchaseVaccineText.setEditable(false);
        purchaseVaccineText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        purchaseVaccineText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        purchaseVaccineText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseVaccineTextActionPerformed(evt);
            }
        });

        confirmBtn.setBackground(new java.awt.Color(102, 102, 102));
        confirmBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        confirmBtn.setText("Confirm");
        confirmBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        confirmBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmBtnMouseEntered(evt);
            }
        });
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        tableDesLabel1.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel1.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel1.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel1.setText("Vaccine Produce Request");
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

        javax.swing.GroupLayout requestJPanelLayout = new javax.swing.GroupLayout(requestJPanel);
        requestJPanel.setLayout(requestJPanelLayout);
        requestJPanelLayout.setHorizontalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(declineBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(544, 544, 544)
                .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(purchaseVaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vaccineIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(manuText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(expText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(tableDesLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        requestJPanelLayout.setVerticalGroup(
            requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestJPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tableDesLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(declineBtn)
                    .addComponent(selectBtn))
                .addGap(37, 37, 37)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vaccineIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(requestJPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(purchaseVaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(doseText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addGap(18, 18, 18)
                .addGroup(requestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(manuText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(expText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(tableDesLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        workAreaJPanel.add(requestJPanel, "card2");

        vaccineStockJPanel.setBackground(new java.awt.Color(255, 255, 255));
        vaccineStockJPanel.setPreferredSize(new java.awt.Dimension(1300, 697));

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

        factoryComboBox.setBackground(new java.awt.Color(120, 168, 252));
        factoryComboBox.setFont(new java.awt.Font("Menlo", 2, 18)); // NOI18N
        factoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Al Bayan", 1, 18)); // NOI18N
        jLabel12.setText("Facotory");

        requestBtn.setBackground(new java.awt.Color(102, 102, 102));
        requestBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        requestBtn.setText("Request");
        requestBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        requestBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requestBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                requestBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                requestBtnMouseEntered(evt);
            }
        });
        requestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestBtnActionPerformed(evt);
            }
        });

        vaccineInfoTable.setFont(new java.awt.Font("American Typewriter", 0, 14)); // NOI18N
        vaccineInfoTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "VACCINE", "VACCINE ID", "DOSE", "MANU DATE", "EXP DATE"
            }
        ));
        vaccineInfoTable.setSelectionBackground(new java.awt.Color(80, 148, 240));
        vaccineInfoTable.setShowVerticalLines(false);
        jScrollPane7.setViewportView(vaccineInfoTable);

        requestSelectBtn.setBackground(new java.awt.Color(102, 102, 102));
        requestSelectBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        requestSelectBtn.setText("Select");
        requestSelectBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        requestSelectBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requestSelectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestSelectBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                requestSelectBtnMouseEntered(evt);
            }
        });
        requestSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestSelectBtnActionPerformed(evt);
            }
        });

        tableDesLabel3.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel3.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel3.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel3.setText("Vaccine Information");
        tableDesLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableDesLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesLabel3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableDesLabel3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableDesLabel3MouseEntered(evt);
            }
        });

        tableDesLabel4.setBackground(new java.awt.Color(204, 255, 204));
        tableDesLabel4.setFont(new java.awt.Font("American Typewriter", 1, 24)); // NOI18N
        tableDesLabel4.setForeground(new java.awt.Color(120, 168, 252));
        tableDesLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableDesLabel4.setText("Vaccine Stock");
        tableDesLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableDesLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesLabel4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableDesLabel4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableDesLabel4MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout vaccineStockJPanelLayout = new javax.swing.GroupLayout(vaccineStockJPanel);
        vaccineStockJPanel.setLayout(vaccineStockJPanelLayout);
        vaccineStockJPanelLayout.setHorizontalGroup(
            vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane5)
            .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                        .addGroup(vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vaccineStockJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(factoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(doseRequestText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(requestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vaccineStockJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(requestSelectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                .addGroup(vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(tableDesLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(tableDesLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 586, Short.MAX_VALUE))
        );
        vaccineStockJPanelLayout.setVerticalGroup(
            vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vaccineStockJPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(tableDesLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(requestSelectBtn)
                .addGap(18, 18, 18)
                .addGroup(vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(vaccineText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doseRequestText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vaccineStockJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(factoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requestBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(tableDesLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        workAreaJPanel.add(vaccineStockJPanel, "card3");

        add(workAreaJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 870, 650));

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

        stockPanelArea.setBackground(new java.awt.Color(23, 35, 51));

        stockAreaBtn.setBackground(new java.awt.Color(41, 57, 80));
        stockAreaBtn.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        stockAreaBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockAreaBtn.setText("Stock");
        stockAreaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        stockAreaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stockAreaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stockAreaBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stockAreaBtnMouseEntered(evt);
            }
        });
        stockAreaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockAreaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockPanelAreaLayout = new javax.swing.GroupLayout(stockPanelArea);
        stockPanelArea.setLayout(stockPanelAreaLayout);
        stockPanelAreaLayout.setHorizontalGroup(
            stockPanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelAreaLayout.createSequentialGroup()
                .addComponent(stockAreaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        stockPanelAreaLayout.setVerticalGroup(
            stockPanelAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stockAreaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
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
                    .addComponent(stockPanelArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(stockPanelArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(leftJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 697));

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

        add(topJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 1170, 50));

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

    private void selectBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        selectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_selectBtnMouseExited

    private void selectBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        selectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_selectBtnMouseEntered

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        int selectedRow = vaccinePurchaseTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccinePurchaseRequest vaccinePurchaseRequest = (VaccinePurchaseRequest) vaccinePurchaseTable.getValueAt(selectedRow, 0);
        
        Vaccine selectedVaccine = vaccinePurchaseRequest.getVaccine();
        purchaseVaccineText.setText(selectedVaccine.getVaccineType());
        purchaseVaccineText.setEnabled(false);
        doseText.setText("" + vaccinePurchaseRequest.getDosesPurchase());
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
    
    private void declineBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseExited

    private void declineBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declineBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        declineBtn.setBorder(btnBorder);
    }//GEN-LAST:event_declineBtnMouseEntered

    private void declineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineBtnActionPerformed

        int selectedRow = vaccinePurchaseTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccinePurchaseRequest vaccinePurchaseRequest = (VaccinePurchaseRequest) vaccinePurchaseTable.getValueAt(selectedRow, 0);
        vaccinePurchaseRequest.setStatus("Declined");
    }//GEN-LAST:event_declineBtnActionPerformed

    private void vaccineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaccineTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaccineTextActionPerformed

    private void doseRequestTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doseRequestTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doseRequestTextActionPerformed

    private void requestBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        requestBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestBtnMouseExited

    private void requestBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        requestBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestBtnMouseEntered

    private void requestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestBtnActionPerformed

        int selectedRow = vaccineTotalTable.getSelectedRow();
        if (selectedRow < 0 || vaccineText.getText().equals("") || vaccineText.getText() == null) {
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (doseRequestText.getText() == null || doseRequestText.getText().equals("") || vaccineText.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please select a vaccine first or the quantity if invalid!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Vaccine selectedVaccine = (Vaccine) vaccineTotalTable.getValueAt(selectedRow, 0);
        FactoryOrganization factoryOrganization = (FactoryOrganization) factoryComboBox.getSelectedItem();
        DefaultTableModel requestModel = (DefaultTableModel) vaccineRequestTable.getModel();
        for (int i = 0; i < requestModel.getRowCount(); i++) {
             VaccineProduceRequest targetRequest = (VaccineProduceRequest) requestModel.getValueAt(i, 0);
             if (targetRequest.getVaccine().getVaccineType().equalsIgnoreCase(selectedVaccine.getVaccineType())) {
                 if (targetRequest.getReceiver().getName().equalsIgnoreCase(factoryOrganization.getName())) {
                    if (!targetRequest.getStatus().equalsIgnoreCase("produced and stored")) {
                        JOptionPane.showMessageDialog(null,"One request of this kind vaccine is still processing, please try later.","Warning",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                 }
             }             
        }
        
        VaccineProduceRequest request = new VaccineProduceRequest();
        int dose = Integer.parseInt(doseRequestText.getText());
        
        request.setVaccine(selectedVaccine);
        request.setDosesRequest(dose);
        request.setReceiver(factoryOrganization);
        request.setSender(organization);
        request.setStatus("Pending");
        organization.getWorkQueue().getWorkRequestList().add(request);
        system.getWorkQueue().getWorkRequestList().add(request);
        factoryOrganization.getWorkQueue().getWorkRequestList().add(request);
        populateRequestTable();
        JOptionPane.showMessageDialog(null,"Vaccine Purchase Request Created Successfully!");
        vaccineText.setText("");
        doseRequestText.setText("");
    }//GEN-LAST:event_requestBtnActionPerformed

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
        inactiveColor(stockPanelArea);
        vaccineStockJPanel.setVisible(false);
        requestJPanel.setVisible(true);
        
        
    }//GEN-LAST:event_requestAreaBtnActionPerformed

    private void stockAreaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockAreaBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        stockAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_stockAreaBtnMouseExited

    private void stockAreaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockAreaBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
        stockAreaBtn.setBorder(btnBorder);
    }//GEN-LAST:event_stockAreaBtnMouseEntered

    private void stockAreaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockAreaBtnActionPerformed
         
        activeColor(stockPanelArea);
        inactiveColor(requestPanelArea);
        vaccineStockJPanel.setVisible(true);
        requestJPanel.setVisible(false);        
    }//GEN-LAST:event_stockAreaBtnActionPerformed

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

    private void headLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseClicked

    }//GEN-LAST:event_headLabelMouseClicked

    private void headLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseExited
        headLabel.setBorder(null);
    }//GEN-LAST:event_headLabelMouseExited

    private void headLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headLabelMouseEntered

    }//GEN-LAST:event_headLabelMouseEntered

    private void doseTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doseTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doseTextActionPerformed

    private void expTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expTextActionPerformed

    private void manuTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manuTextActionPerformed

    private void purchaseVaccineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseVaccineTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseVaccineTextActionPerformed

    private void confirmBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmBtnMouseExited
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        confirmBtn.setBorder(btnBorder);
    }//GEN-LAST:event_confirmBtnMouseExited

    private void confirmBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        confirmBtn.setBorder(btnBorder);
    }//GEN-LAST:event_confirmBtnMouseEntered

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        int selectedRow = vaccinePurchaseTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaccinePurchaseRequest vaccinePurchaseRequest = (VaccinePurchaseRequest) vaccinePurchaseTable.getValueAt(selectedRow, 0);        
        if (vaccinePurchaseRequest.getStatus().equalsIgnoreCase("Approved")) {
            JOptionPane.showMessageDialog(null,"This work request has completed yet!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
                
        Vaccine purchaseVaccine = vaccinePurchaseRequest.getVaccine();
        String id = "" + vaccineIDComboBox.getSelectedItem();
        int doseTyped = Integer.parseInt(doseText.getText());
        int dosePurchase = vaccinePurchaseRequest.getDosesPurchase();
        if (doseTyped != dosePurchase) {
            JOptionPane.showMessageDialog(null,"The quantity of the vaccine you tend to sold does not match it is requested!","Warning",JOptionPane.WARNING_MESSAGE); 
            return;
        }
//            SimpleDateFormat formatter = new SimpleDateFormat();
//            Date manuDate = formatter.parse(manuText.getText());
//            Date expDate = formatter.parse(expText.getText());
        purchaseVaccine.setId(id);
        purchaseVaccine.setDoseInStock(doseTyped);

        

        DefaultTableModel infoModel = (DefaultTableModel) vaccineInfoTable.getModel();           
        for (int i = 0; i < infoModel.getRowCount(); i++) {
            Vaccine sameIDVaccine = (Vaccine)infoModel.getValueAt(i, 0);
            if (sameIDVaccine.getId().equals(id)) {
                int updatePurchaseDoseReqeust = doseTyped - sameIDVaccine.getDoseInStock() < 0 ? 0 : doseTyped - sameIDVaccine.getDoseInStock();
                if (updatePurchaseDoseReqeust > 0) {
                    sameIDVaccine.setDoseInStock(0);
                    vaccinePurchaseRequest.setDoseForUpdate(updatePurchaseDoseReqeust);
                    purchaseVaccine.setExpDate(sameIDVaccine.getExpDate());
                    purchaseVaccine.setProDate(sameIDVaccine.getProDate());
                    JOptionPane.showMessageDialog(null,"The vaccine under this id is deficient, please split with another one!","Warning",JOptionPane.WARNING_MESSAGE);                    
                    populateRequestTable();                    
                }
                else {
                    vaccinePurchaseRequest.setDoseForUpdate(updatePurchaseDoseReqeust);
                    sameIDVaccine.setDoseInStock(sameIDVaccine.getDoseInStock() - doseTyped);
                    purchaseVaccine.setExpDate(sameIDVaccine.getExpDate());
                    purchaseVaccine.setProDate(sameIDVaccine.getProDate());
                    vaccinePurchaseRequest.setStatus("Approved");
                    vaccinePurchaseRequest.setResolveDate(new Date());
                    JOptionPane.showMessageDialog(null,"Approve vaccine purchase successfully.");
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

    private void requestSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestSelectBtnActionPerformed
        int selectedRow = vaccineTotalTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null,"Please select a row from table first.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Vaccine selectedVaccine = (Vaccine) vaccineTotalTable.getValueAt(selectedRow, 0);
        vaccineText.setText(selectedVaccine.getVaccineType());
        vaccineText.setEnabled(false);
    }//GEN-LAST:event_requestSelectBtnActionPerformed

    private void requestSelectBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestSelectBtnMouseEntered
        Border btnBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        requestSelectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestSelectBtnMouseEntered

    private void requestSelectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestSelectBtnMouseClicked
        Border btnBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        requestSelectBtn.setBorder(btnBorder);
    }//GEN-LAST:event_requestSelectBtnMouseClicked

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

    private void tableDesLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel3MouseClicked

    private void tableDesLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel3MouseExited

    private void tableDesLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel3MouseEntered

    private void tableDesLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel4MouseClicked

    private void tableDesLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel4MouseExited

    private void tableDesLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesLabel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDesLabel4MouseEntered

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
    private javax.swing.JButton confirmBtn;
    private javax.swing.JButton declineBtn;
    private javax.swing.JTextField doseRequestText;
    private javax.swing.JTextField doseText;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField expText;
    private javax.swing.JComboBox<Object> factoryComboBox;
    private javax.swing.JLabel headLabel;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel leftJPanel;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JTextField manuText;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField passwordText;
    private javax.swing.JTextField phoneNumText;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JLabel photoLabel1;
    private javax.swing.JTextField purchaseVaccineText;
    private javax.swing.JButton requestAreaBtn;
    private javax.swing.JButton requestBtn;
    private javax.swing.JPanel requestJPanel;
    private javax.swing.JPanel requestPanelArea;
    private javax.swing.JButton requestSelectBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton stockAreaBtn;
    private javax.swing.JPanel stockPanelArea;
    private javax.swing.JLabel tableDesLabel1;
    private javax.swing.JLabel tableDesLabel2;
    private javax.swing.JLabel tableDesLabel3;
    private javax.swing.JLabel tableDesLabel4;
    private javax.swing.JPanel topJPanel;
    private javax.swing.JLabel uploadLabel;
    private javax.swing.JTextField usernameText;
    private javax.swing.JComboBox<Object> vaccineIDComboBox;
    private javax.swing.JTable vaccineInfoTable;
    private javax.swing.JTable vaccinePurchaseTable;
    private javax.swing.JTable vaccineRequestTable;
    private javax.swing.JPanel vaccineStockJPanel;
    private javax.swing.JTextField vaccineText;
    private javax.swing.JTable vaccineTotalTable;
    private javax.swing.JPanel workAreaJPanel;
    // End of variables declaration//GEN-END:variables
}
