/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA DC
 */
public class querybayar {
    
     public Connection getConnection()
    {
    Connection con=null;
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(datamurid.class.getName()).log(Level.SEVERE, null, ex);
            
            return con;
        }
        

    
    
}
    public HashMap<String,Integer>combo(){
       HashMap<String,Integer>map =new HashMap<String,Integer>(); 
        Connection con =getConnection();
        Statement st;
        ResultSet rs;
        
       
           try {
               st=con.createStatement();
               rs=st.executeQuery("SELECT `jenisbayaran`,`totalbayaran` FROM `tambahdatabayaran`");
                      combo cb;
                      while(rs.next()){
                      cb= new combo(rs.getString(1),rs.getInt(2));
                      map.put(cb.getJenis(),cb.getbayar());
                      }
           } catch (SQLException ex) {
               Logger.getLogger(querybayar.class.getName()).log(Level.SEVERE, null, ex);
           }
           return map;  
    }
            
            
      
    
    
}
