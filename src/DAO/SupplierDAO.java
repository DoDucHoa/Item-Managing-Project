package DAO;

import DTO.Supplier;
import UTIL.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SupplierDAO extends Vector<Supplier> {

    public SupplierDAO() {
        super();
    }
    
    public boolean findSup(String supCode) {
        boolean check = true;
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "SELECT supCode FROM tblSuppliers WHERE supCode =  ?";
                stm = cn.prepareStatement(sql);
                stm.setString(1, supCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }
    
    public int find(String supCode) {
        for (int i = 0; i < this.size(); i++) {
            if (supCode.equals(this.get(i).getSupCode())) {
                return i;
            }
        }
        return -1;
    }
    
    public Supplier findSupplier(String supCode) {
        int i = find(supCode);
        return i < 0 ? null : this.get(i);
    }

    public void loadFromDB(MyConnection dbObj) throws SQLException {
        //setting connection
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        //variable
        String supCode, supName, address;
        boolean collaborating;

        try {
            cn = MyConnection.connectDB();         
            if (cn != null) {
                String sql = "select * from tblSuppliers";
                stm = cn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    supCode = rs.getString(1);
                    supName = rs.getString(2);
                    address = rs.getString(3);
                    collaborating = rs.getBoolean(4);
                    Supplier supplier = new Supplier(supCode, supName, address, collaborating);
                    this.add(supplier);
                }
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        //close connection
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public static int addSupplier(String supCode, String supName, String address, boolean collaborating) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "insert tblSuppliers values(?,?,?,?)";
                st = cn.prepareStatement(sql);
                st.setString(1, supCode);
                st.setString(2, supName);
                st.setString(3, address);
                st.setBoolean(4, collaborating);
                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "A supplier has been added.");
            }
        } catch (Exception e) {
        } 
        
        //close connection
        finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public static int updateSupplier(String supCode, String supName, String address, boolean collaborating) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "update tblSuppliers set supName = ?, address = ?, collaborating = ? where supCode = ?";
                
                st = cn.prepareStatement(sql);
                st.setString(1, supName);
                st.setString(2, address);
                st.setBoolean(3, collaborating);
                st.setString(4, supCode);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The supplier " + supCode + " has been updated.");
            }
        } catch (Exception e) {
        } 
        
        //close connection
        finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public static int deleteSupplier(String supCode) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "delete from tblSuppliers where supCode = ?";
                st = cn.prepareStatement(sql);

                st.setString(1, supCode);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The item " + supCode + " has been deleted.");
            }
        } catch (Exception e) {
        } 
        
        //close connection
        finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }
}
