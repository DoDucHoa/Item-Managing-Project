package GUI;

import DAO.ItemDAO;
import DAO.SupplierDAO;
import DAO.UserDAO;
import UTIL.MyConnection;
import UTIL.ItemFullModel;
import UTIL.SupplierFullModel;
import javax.swing.DefaultComboBoxModel;
import DTO.Item;
import DTO.Supplier;
import DTO.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author HoaDD
 */
public class MainFrame extends javax.swing.JDialog {

    /**
     * Creates new form MainFrame
     */
    private LoginForm p;
    MyConnection dbAccess = null;
    SupplierDAO suppliers;
    ItemDAO items;
    ItemFullModel itemModel;
    SupplierFullModel supplierModel;
    boolean addNewItem = false;
    UserDAO users;

    public MainFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Item Management System");
        p = (LoginForm) parent;

        //database connection
        dbAccess = new MyConnection();

        //load supplier
        suppliers = new SupplierDAO();
        try {
            suppliers.loadFromDB(dbAccess);
        } catch (SQLException ex) {
        }

        //load item
        items = new ItemDAO();
        int getAll = 3;
        try {
            items.loadFromDB(dbAccess, suppliers, getAll);
        } catch (SQLException ex) {
        }

        //table
        itemModel = new ItemFullModel(items);
        supplierModel = new SupplierFullModel(suppliers);
        setupModel();
    }

    private void setupModel() {
        txtItemCode.setEditable(false);
        txtSupCode.setEditable(false);
        
        //load data to table
        tblItem.setModel(itemModel);
        tblSup.setModel(supplierModel);

        //add text for combobox
        this.cbSupplier.setModel(new DefaultComboBoxModel(suppliers));
        cbSupplier.updateUI();
    }

    private boolean checkValid() {
        //check item code
        if (addNewItem == false && txtItemCode.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please press 'Add new' button and enter item code.");
            return false;
        } else if (txtItemCode.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Item code is empty. Please enter.");
            txtItemCode.requestFocus();
            return false;
        } else if (!items.findID(txtItemCode.getText().trim().toUpperCase()) && addNewItem == true) {
            JOptionPane.showMessageDialog(this, "Item " + txtItemCode.getText().trim().toUpperCase() + " is existed.");
            txtItemCode.setText("");
            txtItemCode.requestFocus();
            return false;
        }
        
        //check price
        try {
            float price = Float.parseFloat(txtPrice.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Wrong input ( price must be a NUMBER and don't have white space between).", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrice.requestFocus();
            return false;
        }

        if (!txtPrice.equals("")) {
            float price = Float.parseFloat(txtPrice.getText());
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price must be greater than 0 !");
                txtPrice.requestFocus();
                return false;
            }
        }
        return true;
    }

    private boolean checkValidAddItem() {
        if (suppliers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Supplier is empty !\nPlease add a new one and return.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean checkValidDeleteSup(String supCode) {
        if (items.findSup(supCode) >= 0) {
            JOptionPane.showMessageDialog(this, "Please delete all items of " + supCode + " before deleting.");
            return false;
        } 
        return true;
    }

    private boolean checkValidSup() {
        if (addNewItem == false && txtSupCode.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please press 'Add new' button and enter supplier code.");
            return false;
        } else if (txtSupCode.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter item code.");
            txtSupCode.requestFocus();
            return false;
        } else if (txtName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter name.");
            txtName.requestFocus();
            return false;
        } else if (!suppliers.findSup(txtSupCode.getText().trim().toUpperCase()) && addNewItem == true) {
            JOptionPane.showMessageDialog(this, "Supplier " + txtSupCode.getText().trim().toUpperCase() + " is existed.");
            txtSupCode.setText("");
            txtItemCode.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tblSupplier = new javax.swing.JScrollPane();
        tblSup = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSupCode = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        chkCollaborating = new javax.swing.JCheckBox();
        btnAddSup = new javax.swing.JButton();
        btnSaveSup = new javax.swing.JButton();
        btnDeleteSup = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();
        btnSaveItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkSupplying = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        cbSupplier = new javax.swing.JComboBox<>();
        btnLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        tblSup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSupMouseReleased(evt);
            }
        });
        tblSupplier.setViewportView(tblSup);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel2.setText("Supplier code:");

        jLabel3.setText("Name:");

        jLabel4.setText("Address:");

        jLabel5.setText("Collaborating:");

        btnAddSup.setText("Add new");
        btnAddSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupActionPerformed(evt);
            }
        });

        btnSaveSup.setText("Save");
        btnSaveSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSupActionPerformed(evt);
            }
        });

        btnDeleteSup.setText("Delete");
        btnDeleteSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSupActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel6.setText("Details of supplier");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAddSup, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnSaveSup, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteSup, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkCollaborating)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(42, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(chkCollaborating))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSup)
                    .addComponent(btnSaveSup)
                    .addComponent(btnDeleteSup))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Supplier", jPanel1);

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblItem);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel7.setText("Item code:");

        jLabel8.setText("Name:");

        jLabel9.setText("Unit:");

        jLabel10.setText("Price:");

        btnAddItem.setText("Add new");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        btnSaveItem.setText("Save");
        btnSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveItemActionPerformed(evt);
            }
        });

        btnDeleteItem.setText("Delete");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel11.setText("Details of item");

        jLabel1.setText("Supplying:");

        chkSupplying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSupplyingActionPerformed(evt);
            }
        });

        jLabel12.setText("Supplier:");

        cbSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel12))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtItemCode, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(chkSupplying)
                                    .addComponent(cbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnSaveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(chkSupplying))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddItem)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteItem)
                        .addComponent(btnSaveItem)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Item", jPanel2);

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogOut)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkSupplyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSupplyingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSupplyingActionPerformed

    private void tblSupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupMouseReleased
        // TODO add your handling code here:
        int row = tblSup.getSelectedRow();
        int col = tblSup.getSelectedColumn();
        tblSup.getCellEditor(row, col).cancelCellEditing();
    }//GEN-LAST:event_tblSupMouseReleased

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
        // TODO add your handling code here:
        addNewItem = false;
        int pos = tblItem.getSelectedRow();
        Item item = itemModel.getItems().get(pos);
        txtItemCode.setText(item.getItemCode());
        txtItemCode.setEditable(false);
        txtItemName.setText(item.getItemName());
        int index = suppliers.find(item.getSupplier().getSupCode());
        cbSupplier.setSelectedIndex(index);
        txtUnit.setText("" + item.getUnit());
        txtPrice.setText("" + item.getPrice());
        chkSupplying.setSelected(item.isSupplying());
    }//GEN-LAST:event_tblItemMouseClicked

    private void tblSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupMouseClicked
        // TODO add your handling code here:
        addNewItem = false;
        int pos = tblSup.getSelectedRow();
        Supplier supplier = supplierModel.getSuppliers().get(pos);
        txtSupCode.setText(supplier.getSupCode());
        txtSupCode.setEditable(false);
        txtName.setText(supplier.getSupName());
        txtAddress.setText(supplier.getAddress());
        chkCollaborating.setSelected(supplier.isCollaborating());
    }//GEN-LAST:event_tblSupMouseClicked

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:
        if (checkValidAddItem()) {
            addNewItem = true;
            txtItemCode.setText("");
            txtItemCode.setEditable(true);
            txtItemCode.requestFocus();
            txtItemName.setText("");
            cbSupplier.setSelectedIndex(0);
            txtUnit.setText("");
            txtPrice.setText("");
            chkSupplying.setSelected(true);
        }
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItemActionPerformed
        // TODO add your handling code here:
        if (checkValid()) {
            String itemCode = txtItemCode.getText().trim().toUpperCase();
            String itemName = txtItemName.getText();
            Supplier supplier = (Supplier) cbSupplier.getSelectedItem();
            String supCode = supplier.getSupCode();
            String unit = txtUnit.getText();
            float price = Float.parseFloat(txtPrice.getText().replaceAll("\\s", ""));
            boolean supplying = chkSupplying.isSelected();

            Item item = new Item(itemCode, itemName, unit, price, supplying, supplier);

            //add new item
            if (addNewItem == true) {
                try {
                    ItemDAO.addItem(itemCode, itemName, unit, price, supplying, supCode);
                } catch (SQLException ex) {
                }
                itemModel.getItems().add(item);
            } //update item
            else {
                try {
                    ItemDAO.updateItem(itemCode, itemName, unit, price, supplying, supCode);
                } catch (SQLException ex) {
                }
                int pos = tblItem.getSelectedRow();
                itemModel.getItems().set(pos, item);
            }
            //update table
            tblItem.updateUI();
            addNewItem = false;
        }
    }//GEN-LAST:event_btnSaveItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        // TODO add your handling code here:
        String itemCode = txtItemCode.getText();
        if (!itemCode.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Please confirm to delete " + itemCode + ".", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                int pos = tblItem.getSelectedRow();

                try {
                    ItemDAO.deleteItem(itemCode);
                } catch (SQLException ex) {

                }

                itemModel.getItems().removeElementAt(pos);
                tblItem.updateUI();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose 1 item to delete.");
        }
    }//GEN-LAST:event_btnDeleteItemActionPerformed

    private void btnAddSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupActionPerformed
        // TODO add your handling code here:
        addNewItem = true;
        txtSupCode.setText("");
        txtSupCode.setEditable(true);
        txtSupCode.requestFocus();
        txtName.setText("");
        txtAddress.setText("");
        chkCollaborating.setSelected(true);
    }//GEN-LAST:event_btnAddSupActionPerformed

    private void btnSaveSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSupActionPerformed
        // TODO add your handling code here:
        if (checkValidSup()) {
            String supCode = txtSupCode.getText().trim().toUpperCase();
            String supName = txtName.getText();
            String address = txtAddress.getText();
            boolean colloborating = chkCollaborating.isSelected();

            Supplier supplier = new Supplier(supCode, supName, address, colloborating);

            //adding new supplier
            if (addNewItem == true) {
                try {
                    SupplierDAO.addSupplier(supCode, supName, address, colloborating);
                } catch (SQLException ex) {
                }
                supplierModel.getSuppliers().add(supplier);
            } //update supplier
            else {
                try {
                    SupplierDAO.updateSupplier(supCode, supName, address, colloborating);
                } catch (SQLException ex) {

                }
                int pos = tblSup.getSelectedRow();
                supplierModel.getSuppliers().set(pos, supplier);
            }
            //update table
            tblSup.updateUI();
            addNewItem = false;
        }
    }//GEN-LAST:event_btnSaveSupActionPerformed

    private void btnDeleteSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSupActionPerformed
        // TODO add your handling code here:
        String supCode = txtSupCode.getText();
        if (checkValidDeleteSup(supCode)) {
            if (!supCode.isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Please confirm to delete " + supCode + ".", "Confirm", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    int pos = tblSup.getSelectedRow();
                    try {
                        //call delete
                        SupplierDAO.deleteSupplier(supCode);
                    } catch (SQLException ex) {

                    }
                    //update table
                    supplierModel.getSuppliers().removeElementAt(pos);
                    tblSup.updateUI();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please choose 1 supplier to delete.");
            }
        }
    }//GEN-LAST:event_btnDeleteSupActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you want to log out ?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            this.dispose();
            LoginForm lf = new LoginForm();
            lf.setVisible(true);
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame dialog = new MainFrame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAddSup;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnDeleteSup;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnSaveItem;
    private javax.swing.JButton btnSaveSup;
    private javax.swing.JComboBox<String> cbSupplier;
    private javax.swing.JCheckBox chkCollaborating;
    private javax.swing.JCheckBox chkSupplying;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblItem;
    private javax.swing.JTable tblSup;
    private javax.swing.JScrollPane tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSupCode;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
