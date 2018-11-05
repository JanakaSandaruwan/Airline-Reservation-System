
package airlinereservationsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
    
    
}
