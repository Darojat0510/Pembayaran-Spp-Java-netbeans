/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TOSHIBA DC
 */
public class table  extends AbstractTableModel {
    private String[] columns;
    private Object[][] rows;
    
    public  table(){}
    
    public  table(Object[][] data, String[] columnName){
    
        this.rows = data;
        this.columns = columnName;
    }

    table(Object[] rows, String[] columnName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
    
    
    public int getRowCount() {
     return this.rows.length;
    }

    public int getColumnCount() {
     return this.columns.length;
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.rows[rowIndex][columnIndex];
    }
    public String getColumnName(int col){
        return this.columns[col];
    }


}
