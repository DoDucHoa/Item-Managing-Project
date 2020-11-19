package UTIL;

import DTO.Supplier;
import DAO.SupplierDAO;
import javax.swing.table.AbstractTableModel;

public class SupplierFullModel extends AbstractTableModel {
    SupplierDAO suppliers = null;
    
    public SupplierFullModel(SupplierDAO suppliers) {
        this.suppliers = suppliers;
    }

    public SupplierDAO getSuppliers() {
        return suppliers;
    }

    public int getRowCount() {
        return suppliers.size();
    }

    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = "";
        switch (column) {
            case 0:
                columnName = "Code";
                break;
            case 1:
                columnName = "Name";
                break;
            case 2:
                columnName = "Address";
                break;
//            case 3:
//                columnName = "Colloborating";
//                break;
        }
        return columnName;
    }
    
    @Override
    public Object getValueAt(int row, int column){
        Supplier supplier = suppliers.get(row);
        Object obj = null;
        switch(column){
            case 0:
                obj = supplier.getSupCode();
                break;
            case 1:
                obj = supplier.getSupName();
                break;
            case 2:
                obj = supplier.getAddress();
                break;
            case 3:
                obj = supplier.isCollaborating();
                break;         
        }
        return obj;
    }
}
