package UTIL;

import DAO.ItemDAO;
import DAO.SupplierDAO;
import DTO.Item;
import DTO.Supplier;
import javax.swing.table.AbstractTableModel;

public class ItemFullModel extends AbstractTableModel {

    ItemDAO items = null;

    public ItemFullModel(ItemDAO items) {
        this.items = items;
    }

    public ItemDAO getItems() {
        return items;
    }

    public int getRowCount() {
        return items.size();
    }

    public int getColumnCount() {
        return 6;
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
                columnName = "Supplier";
                break;
            case 3:
                columnName = "Unit";
                break;
            case 4:
                columnName = "Price";
                break;
            case 5:
                columnName = "Supply";
                break;
        }
        return columnName;
    }
    
    @Override
    public Object getValueAt(int row, int column){
        Item item = items.get(row);
        Object obj = null;
        switch(column){
            case 0:
                obj = item.getItemCode();
                break;
            case 1:
                obj = item.getItemName();
                break;
            case 2:
                obj = item.getSupplier().getSupCode() + " - " + item.getSupplier().getSupName();
                break;
            case 3:
                obj = item.getUnit();
                break;
            case 4: 
                obj = item.getPrice();
                break;
            case 5:
                obj = item.isSupplying();
                break;
        }
        return obj;
    }
}
