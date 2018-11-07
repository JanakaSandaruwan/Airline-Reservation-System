
package airlinereservationsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBoperation {
    String url = "jdbc:mysql://localhost:3306/airlinereservationsys";
    String password="";
    String username = "root";
    Connection con =null;
    PreparedStatement pst=null;
    ResultSet rs;
     
    //ArrayList<PastFlight> pf_list=new ArrayList<PastFlight>();
    
    boolean addUser(user em){
        System.out.println(em.getFirstname()+" "+em.getFirstname().length());
        try{
            //System.out.println("sdfsdf1");
            con = (Connection)DriverManager.getConnection(url, username, password);
            // System.out.println("sdfsdf");
            String query= "INSERT INTO user values (?,?,?,?,?,?,?,?,?,?)";
            pst =(PreparedStatement)con.prepareStatement(query);
            
            pst.setInt(1,em.getUser_id());
            pst.setString(2,em.getUsername());
            pst.setString(3,em.getPassword());
            pst.setString(4,em.getFirstname());
            pst.setString(5,em.getLastname());
            pst.setString(6, em.getUser_category_id());
            pst.setString(7,em.getEmail());
            pst.setString(8,em.getPhonenumber());
            pst.setString(9,em.getAddress());
            pst.setInt(10,em.getAge());
            
            
            
            pst.executeUpdate();
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    }
    
    int checkforuser(user em){
        try{
            con = (Connection)DriverManager.getConnection(url, username, password);
          
            String query= "select username from user";
            pst =(PreparedStatement)con.prepareStatement(query);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(em.getUsername().equals(rs.getString(1))){
                     return 1;
                }
            }
            
            return 0;
            
        }catch(Exception e){
            System.out.println(e);
            
            return -1;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    }
    
    
    boolean checkforlogin(String uname, String pword){
        try{
            //System.out.println(uname);
            //System.out.println(pword);
            
            con = (Connection)DriverManager.getConnection(url, username, password);
            // System.out.println("sdfsdf");
            String query= "Select password from user where username=?";
            
            pst =(PreparedStatement)con.prepareStatement(query);
            pst.setString(1,uname);
            
           
            
            rs = pst.executeQuery();
            
            while(rs.next()){
               // System.out.println(rs.getString(1)+" "+pword);
                if( pword.equals(rs.getString(1))){
                    // System.out.print(rs.getString(1));
                     return true;
                }
            }
            //System.out.print("ooopp");
            return false;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    
    }
    
    boolean checkforadminlogin(String uname, String pword){
        try{
            //System.out.println(uname);
            //System.out.println(pword);
            
            con = (Connection)DriverManager.getConnection(url, username, password);
            // System.out.println("sdfsdf");
            String query= "Select password from admin where username=?";
            
            pst =(PreparedStatement)con.prepareStatement(query);
            pst.setString(1,uname);
            
           
            
            rs = pst.executeQuery();
            
            while(rs.next()){
               // System.out.println(rs.getString(1)+" "+pword);
                if( pword.equals(rs.getString(1))){
                     System.out.print(rs.getString(1));
                     return true;
                }
            }
            //System.out.print("ooopp");
            return false;
            
        }catch(Exception e){
            System.out.println(e);
            
            return false;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    
    }
    
    
    ArrayList<Shedule> getShedule(){
        
        ArrayList<Shedule> list=new ArrayList<Shedule>();
        
        try{
           
            con = (Connection)DriverManager.getConnection(url, username, password);
           
            String query= "select  date,flight_id,airplane_id,b.name,c.name,departure_time,new_departure_time,schedule_id from schedule left join delay using (delay_id)  left join flight using(flight_id) left join route using (route_id),airport as b, airport as c where b.airport_code= from_port_id and c.airport_code = to_port_id";
            
            pst =(PreparedStatement)con.prepareStatement(query);
            
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                Shedule em=new Shedule();
                
                em.setDate(rs.getString(1));
                em.setAirline(rs.getString(2));
                em.setFlight(rs.getString(3));
                em.setFrom(rs.getString(4));
                em.setTo(rs.getString(5));
                em.setSheduled_time(rs.getString(6));
                em.setDelay_time(rs.getString(7));
                em.setSheduleid(rs.getString(8));
                
                
                
                list.add(em);
                
            }
            
            return list;
            
        }catch(Exception e){
            System.out.println(e);
            
            return null;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
        
        
    
    }
    
//    ArrayList<PastFlight> getPastFlightTable(){
//        return this.pf_list;
//    }
    
    void checkFlightHistory(String from_port_id, String to_port_id){
        try{
         
            con = (Connection)DriverManager.getConnection(url, username, password);
          
            String query= "Select route_id from route where from_port_id=? and to_port_id=?";
            
            pst =(PreparedStatement)con.prepareStatement(query);
            pst.setString(1,from_port_id);
            pst.setString(2,to_port_id);
           
            rs = pst.executeQuery();
            
            while(rs.next()){
                String route_id=rs.getString(1);
              //  System.out.println(route_id);
                String query_2="Select flight_id from flight where route_id=?";
                PreparedStatement pst_2 =(PreparedStatement)con.prepareStatement(query_2);
                pst_2.setString(1,route_id);
                
                ResultSet rs_2 = pst_2.executeQuery();
                
                while(rs_2.next()){
                    String flight_id=rs_2.getString(1);
                  //  System.out.println(flight_id);
                  //  String query_date="SELECT CAST(GETDATE() AS DATE)";
                  //  PreparedStatement pst_date=(PreparedStatement)con.prepareStatement(query_date);
                 //   ResultSet rs_date=pst_date.executeQuery();
                 //   String today=rs_date.getString(1);
                    
                //    System.out.println(flight_id);
                    String query_3="Select schedule_id,date,flight_id,booked_seats_business+booked_seats_platinum+booked_seats_econ from schedule where flight_id=?";///hjh
                    PreparedStatement pst_3=(PreparedStatement)con.prepareStatement(query_3);
                    pst_3.setString(1,flight_id);
                  //  pst_3.setString(2,today);
                    
                    ResultSet rs_3=pst_3.executeQuery();
                    
                    while(rs_3.next()){
                       PastFlight pf=new PastFlight();
               //        System.out.println(rs_3.getString(1));
                       pf.setDate(rs_3.getString(2));
                       pf.setFlight_id(rs_3.getString(3));
                       pf.setSchedule_id(rs_3.getString(1));
                       pf.setPassCount(rs_3.getString(4));
                       Value.pf_list.add(pf);
                    }
                    
                }
               // System.out.println(rs.getString(1)+" "+pword);
//                if( pword.equals(rs.getString(1))){
//                     System.out.print(rs.getString(1));
//                    // return true;
//                }
            }
            //System.out.print("ooopp");
           // return false;
            
        }catch(Exception e){
            System.out.println(e);
            
           // return false;
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    
    }
    
    
    Price getPrices(Shedule ss){
        //System.out.println("fff"+ss.getSheduleid()+"  ff");
        try{
            Price em=new Price();
            con = (Connection)DriverManager.getConnection(url, username, password);
            System.out.println(ss.getSheduleid()+"  ff");
            String query= "select econ_price,business_price,platinum_price from schedule left join price using(price_id) where schedule_id="+ss.getSheduleid();
            pst =(PreparedStatement)con.prepareStatement(query);
           // pst.setString(1,ss.getSheduleid());
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                
                em.setEcon_price(rs.getFloat(1));
                em.setBusiness_price(rs.getFloat(2));
                em.setPlatinum_price(rs.getFloat(3));
                
                
            }
            
            return em;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
           
        }finally{
            try{
                if(pst != null){
                    pst.close();
                }
                if (con != null){
                    con.close();
                }
            }catch(Exception e){
            
            }
        }
    }
    
    
}
