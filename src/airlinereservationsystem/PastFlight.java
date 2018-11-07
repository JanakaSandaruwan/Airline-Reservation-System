/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservationsystem;

/**
 *
 * @author Udhan
 */
public class PastFlight {
    
    private String schedule_id;
    private String date;
    private String flight_id;
    private String pass_count;
//    private String from;
//    private String to;
//    private String sheduled_time;
//    private String delay_time;
    
    public void setDate(String date) {
        this.date = date;
    }
    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }
    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }
    
    public void setPassCount(String pass_count) {
        this.pass_count = pass_count;
    }
    
    
    public String getDate() {
        return this.date;
    }
    public String getSchedule_id() {
        return this.schedule_id;
    }
    public String getFlight_id() {
        return this.flight_id ;
    }
    
    public String getPassCount(){
       return this.pass_count;
    }
}
