/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservationsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Udhan
 */
public class PastFlightTable extends AbstractTableModel{
    private static final String [] COLUMN_NAMES = {"Schedule_id", "Date ", "Flight_id","Passenger_Count"};
    private static ArrayList<PastFlight> pf_list;
    
    public PastFlightTable(ArrayList <PastFlight> list){
        pf_list=list;
    }
    
    
    public int getRowCount() {
        return pf_list.size(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getColumnCount() {
        return COLUMN_NAMES.length; //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public String getColumnName(int colindex){
        return COLUMN_NAMES[colindex];
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return pf_list.get(rowIndex).getSchedule_id();
            case 1:
                return pf_list.get(rowIndex).getDate();
            case 2:
                return pf_list.get(rowIndex).getFlight_id();
            case 3:
                return pf_list.get(rowIndex).getPassCount();
//            case 4:
//                return stlist.get(rowIndex).getTo();
//            case 5:
//                return stlist.get(rowIndex).getSheduled_time();
//            case 6:
//                 return stlist.get(rowIndex).getDelay_time();
            default:
                return "Error";
            
        }
    }
}
