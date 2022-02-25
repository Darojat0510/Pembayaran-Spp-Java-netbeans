/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

/**
 *
 * @author TOSHIBA DC
 */
public class walimurid {
    private int no;
    private String namaayah;
    private String namaibu;
    private String namaanak;
     private String kerjaayah;
      private String kerjaibu;
     
    public walimurid(int pno, String pnamaayah, String pnamaibu, String pnamaanak, String pkerjaayah,String pkerjaibu)
    {
    this.no=pno;
    this.namaayah=pnamaayah;
    this.namaibu=pnamaibu;
    this.namaanak=pnamaanak;
    this.kerjaayah=pkerjaayah;
      this.kerjaibu=pkerjaibu;
     
      
    
    }

    public int getNo()
    {
        
        return no;
        
        
    }
    public String getNamaayah()
    {
        return namaayah;
        
        
    }
    
    public String getNamaibu()
            
    {
        
        return namaibu;
    }
    
    public String getNamaanak()
            
    {
        
        
        return namaanak;
    }
    public String getKerjaayah()
            
    {
        
        
        return kerjaayah;
    }
     public String getKerjaibu()
            
    {
        
        
        return kerjaibu;
    }
     
}
