
package pembayaranspp;


public class guru {
    
    
    private String nig;
    private String name;
    private String tempat;
    private String addDate;
     private String gender;
      private String status;
        private String alamat;
       
    private byte[] picture;
    public guru(String pnig, String pname, String ptempat, String paddDate, String pgender, String pstatus, String pAlamat, byte[] pimg)
    {
    this.nig=pnig;
    this.name=pname;
    this.tempat=ptempat;
    this.addDate=paddDate;
       this.gender=pgender;
        this.status=pstatus;
         this.alamat=pAlamat;
          this.picture=pimg;
    
}
     public String getNig()
    {
        
        return nig;
        
        
    }
    public String getName()
    {
        return name;
        
        
    }
    
    public String getTempat()
            
    {
        
        return tempat;
    }
    
    public String getAddDate()
            
    {
        
        
        return addDate;
    }
    
      public String getGender()
            
    {
        
        
        return gender;
    }
      
      
      
       public String getStatus()
            
    {
        
        
        return status;
    }
        public String getAlamat()
            
    {
        
        
        return alamat;
    }
    
    public byte[] getImage()
            
    {
        
        
        
        return picture;
    }
}
