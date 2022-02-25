
package pembayaranspp;


public class spp {    
 private String nis;
    private String name;
    private String tempat;
    private String addDate;
     private String kelas;
      private String agama;
        private String gender;
       private String status;
     private String alamat;
    private byte[] picture;
    public spp(String pnis, String pname, String ptempat, String paddDate, String pkelas, String pagama, String pgender, String pstatus, String pAlamat, byte[] pimg)
    {
    this.nis=pnis;
    this.name=pname;
    this.tempat=ptempat;
    this.addDate=paddDate;
    this.kelas=pkelas;
      this.agama=pagama;
       this.gender=pgender;
        this.status=pstatus;
         this.alamat=pAlamat;
          this.picture=pimg;
      
    
    }

  
    
    public String getNis()
    {
        
        return nis;
        
        
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
    public String getKelas()
            
    {
        
        
        return kelas;
    }
     public String getAgama()
            
    {
        
        
        return agama;
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

