
package airlinereservationsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class SheduleTable extends AbstractTableModel {
    
    private static final String [] COLUMN_NAMES = {"Date", "Airline ", "Flight" ,"From", "To", "Sheduled Time","Delay Time"};
    private static ArrayList<Shedule> stlist;
    
    public SheduleTable(ArrayList <Shedule> list){
        stlist=list;
    }
    
    @Override
    public int getRowCount() {
        return stlist.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
     public String getColumnName(int colindex){
        return COLUMN_NAMES[colindex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return stlist.get(rowIndex).getDate();
            case 1:
                return stlist.get(rowIndex).getAirline();
            case 2:
                return stlist.get(rowIndex).getFlight();
            case 3:
                return stlist.get(rowIndex).getFrom();
            case 4:
                return stlist.get(rowIndex).getTo();
            case 5:
                return stlist.get(rowIndex).getSheduled_time();
            case 6:
                 return stlist.get(rowIndex).getDelay_time();
            default:
                return "Error";
            
        }
    }
    
}
