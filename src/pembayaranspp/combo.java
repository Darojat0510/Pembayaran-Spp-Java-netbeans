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
public class combo {
    private String Jenis;
    private int Bayar;
   
   public combo(String pjenis,int pbayar){
       this.Jenis=pjenis;
       this.Bayar=pbayar;
       
   } 
   
   public String getJenis(){
       return Jenis;
       
       
   }
    public void setJenis(String jenis){
        this.Jenis=jenis;
        
        
    }
    
    
     public int getbayar(){
       return Bayar;
       
       
   }
    public void setJenis(int bayar){
        this.Bayar=bayar;
        
        
    }
}
