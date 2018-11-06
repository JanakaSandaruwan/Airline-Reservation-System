
package airlinereservationsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBoperation {
    String url = "jdbc:mysql://localhost:3307/airlinereservationsys";
    String password="";
    String username = "root";
    Connection con =null;
    PreparedStatement pst=null;
    ResultSet rs;
    
    
    boolean addUser(user em){
        try{
            //System.out.println("sdfsdf1");
            con = (Connection)DriverManager.getConnection(url, username, password);
            // System.out.println("sdfsdf");
            String query= "INSERT INTO user values (?,?,?,?,?,?,?,?,?)";
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
           
            String query= "select  date,flight_id,airplane_id,b.name,c.name,departure_time,new_departure_time from schedule left join delay using (delay_id)  left join flight using(flight_id) left join route using (route_id),airport as b, airport as c where b.airport_code= from_port_id and c.airport_code = to_port_id";
            
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
    
    
}
