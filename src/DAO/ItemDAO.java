package DAO;

/**
 *
 * @author HoaDD
 */
import DTO.Supplier;
import DTO.Item;
import UTIL.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ItemDAO extends Vector<Item> {

    final int SUPPLYING = 1;
    final int NOTSUPPLYING = 2;

    public ItemDAO() {
    }

    public boolean findID(String itemCode) {
        boolean check = true;
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "SELECT itemCode FROM tblItems WHERE itemCode =  ?";
                stm = cn.prepareStatement(sql);
                stm.setString(1, itemCode);
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

    public int findSup(String supCode) {
        for (int i = 0; i < this.size(); i++) {
            if (supCode.equals(this.get(i).getSupplier().getSupCode())) {
                return i;
            }
        }
        return -1;
    }

    public void loadFromDB(MyConnection dBObj, SupplierDAO suppliers, int supply) throws SQLException {
        //setting connection
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        //variable
        String itemCode, itemName, supCode, unit;
        float price;
        boolean supplying;

        String sql = "select * from tblItems";

        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                stm = cn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    itemCode = rs.getString(1);
                    itemName = rs.getString(2);
                    unit = rs.getString(3);
                    price = rs.getFloat(4);
                    supplying = rs.getBoolean(5);
                    supCode = rs.getString(6);
                    Supplier supplier = suppliers.findSupplier(supCode);
                    Item item = new Item(itemCode, itemName, unit, price, supplying, supplier);
                    this.add(item);
                }
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } //close connection
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

    public static int addItem(String itemCode, String itemName, String unit, float price, boolean supplying, String supplier) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "insert tblItems values(?,?,?,?,?,?)";
                st = cn.prepareStatement(sql);

                st.setString(1, itemCode);
                st.setString(2, itemName);
                st.setString(3, unit);
                st.setFloat(4, price);
                st.setBoolean(5, supplying);
                st.setString(6, supplier);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The item " + itemCode + " has been added.");
            }
        } catch (Exception e) {
        } //close connection
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

    public static int updateItem(String itemCode, String itemName, String unit, float price, boolean supplying, String supplier) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "update tblItems set itemName = ?, unit = ?, price = ?, supplying = ?, supCode = ? where itemCode = ?";
                st = cn.prepareStatement(sql);

                st.setString(1, itemName);
                st.setString(2, unit);
                st.setFloat(3, price);
                st.setBoolean(4, supplying);
                st.setString(5, supplier);
                st.setString(6, itemCode);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The item " + itemCode + " has been updated.");
            }
        } catch (Exception e) {
        } //close connection
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

    public static int deleteItem(String itemCode) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "delete from tblItems where itemCode = ?";
                st = cn.prepareStatement(sql);

                st.setString(1, itemCode);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The item " + itemCode + " has been deleted.");
            }
        } catch (Exception e) {
        } //close connection
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
