/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

/**
 *
 * @author TOSHIBA DC
 */
public class dataguru extends javax.swing.JInternalFrame {
     byte[] person_image;
 Statement st  ;
              ResultSet rs;
    /**
     * Creates new form datamurid
     */
    public dataguru() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        this.person_image=null;
        initComponents();
        
         jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.blue);
        jTable1.setSelectionBackground(Color.gray);
        JTableHeader th =jTable1.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.PLAIN, 14));
        tampil();
         getConnection();
         otomatis();
         
           siapIsi(false);
        tombolNormal();
        xBT.hide();
    }
    String Imgpath = null;
      int pos=0;
     
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

  
      
      public ImageIcon ResizeImage(String imagePath, byte[] pic){
    
    
    ImageIcon myImage=null;
    if(imagePath !=null)
    {
        
        myImage=new ImageIcon(imagePath);
    }else{
        myImage=new ImageIcon(pic);
    }
    
    Image img=myImage.getImage();
    Image img2=img.getScaledInstance(imageTF.getWidth(),imageTF.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon image=new ImageIcon(img2);
    return image;
}

       public boolean checkInputs()
{
    if(
            
      
         namaTF.getText()== null
        || tempatTF.getText()== null
           
              || genderCB.getSelectedItem()== null
              || guruCB.getSelectedItem()== null
             || alamatTF.getText()== null
            
        || tglTF.getDate() == null
            ){
       return false;
    }
else{
    try{
     
     return true;
    }catch(Exception ex)
    {
        return false;
    }
    }
}
        
       
    
    
    private void bersih(){
        nigTF.setText("");
        namaTF.setText("");
        tempatTF.setText("");
          tglTF.setDate(null);
         
             genderCB.setSelectedItem("");
             guruCB.setSelectedItem("");
             alamatTF.setText("");
          imageTF.setIcon(null);
          Imgpath =null;
        
    }
      private void siapIsi(boolean a){
        nigTF.setEnabled(a);
        namaTF.setEnabled(a);
        tempatTF.setEnabled(a);
        tglTF.setEnabled(a);
       
         genderCB.setEnabled(a);
        guruCB.setEnabled(a);
        alamatTF.setEnabled(a);
        imageTF.setEnabled(a);
       
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
        hapusBT.setEnabled(false);
        editBT.setEnabled(false);
       
        fileBT.setEnabled(false);
    }
    
    
    public ArrayList<guru>getguruList(){
        
   ArrayList<guru> list = new ArrayList<guru>();
   Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   try {
   st = con.createStatement();
   rs = st.executeQuery("SELECT `nig`, `nama`, `tempatlahir`, `tanggallahir`,`gender`,`guru`,`alamat`,`image` FROM `dataguru`");
   
  guru p;
   while(rs.next()){
   p = new guru(
   rs.getString("nig"),
   rs.getString("nama"),
   rs.getString("tempatlahir"),
   rs.getString("tanggallahir"),
   rs.getString("gender"),
   rs.getString("guru"), 
   rs.getString("alamat"),
   rs.getBytes("image")
  
   );
   list.add(p);
   }
   
   } catch (SQLException ex) {
   Logger.getLogger(datamurid.class.getName()).log(Level.SEVERE, null, ex);
   }
   return list;
   }

 private void otomatis() 
          
       
    {
       
        try {
        Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   st = con.createStatement();
           
            String sql="select right (nig,2)+1 from dataguru";
             rs=st.executeQuery(sql);
            
           
           
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    nigTF.setText("G"+no);}
                }
            else
            {
                nigTF.setText("G001"); 
            }
            } catch (Exception e) 
            {
        }
    
    }
 
public void tampil(){
        
        ArrayList<guru> list = getguruList();
        
        String[] columnName = {"nig","nama","tempatlahir","tanggallahir","gender","guru","alamat","image"};
       
        Object[][] rows = new Object[list.size()][8];
        for(int i = 0; i < list.size(); i++){
            rows[i][0] = list.get(i).getNig();
            rows[i][1] = list.get(i).getName();
            rows[i][2] = list.get(i).getTempat();
            rows[i][3] = list.get(i).getAddDate();
              
                     rows[i][4] = list.get(i).getGender();
                        rows[i][5] = list.get(i).getStatus();
                           rows[i][6] = list.get(i).getAlamat();
                    
             ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage()
             .getScaledInstance(80, 60, Image.SCALE_SMOOTH) );   
                
            rows[i][7] = image;
            
        
        }

        queryguru model = new queryguru(rows, columnName);
        jTable1.setModel(model);
        jTable1.setRowHeight(120);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(150);
    }


private void simpan()
{  
    if(checkInputs() && Imgpath != null)
        {
            try{
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("INSERT INTO dataguru(nig,nama,tempatlahir,tanggallahir,gender,guru,alamat,image)"
                    + "values(?,?,?,?,?,?,?,?)");
                
                ps.setString(1,nigTF.getText());
                ps.setString(2, namaTF.getText());
                 ps.setString(3, tempatTF.getText());
                SimpleDateFormat dateFormat =new SimpleDateFormat("YYYY-MM-dd");
                String addDate=dateFormat.format(tglTF.getDate());
                ps.setString(4, addDate);
                
               
                ps.setString(5, (String) genderCB.getSelectedItem());
                ps.setString(6, (String) guruCB.getSelectedItem());
                ps.setString(7, alamatTF.getText());
                InputStream img = new FileInputStream(new File(Imgpath));
                ps.setBlob(8, img);
                ps.executeUpdate();
                tampil();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

         
        }
          } else{
JOptionPane.showMessageDialog(null, "Lengkapi inputan data","SEKOLAH CITA-CITA",JOptionPane.INFORMATION_MESSAGE);
            
        }
            
}
private void perbarui()
        
{
     if(checkInputs() && nigTF.getText() !=null)
     {
            String UpdateQuery= null;
            PreparedStatement ps =null;
            Connection con=getConnection();
            if(Imgpath == null)
            {
                try {
                  
                    UpdateQuery="UPDATE dataguru SET nama=?,tempatlahir=?,tanggallahir=?,gender=?,guru=?"
                            +",alamat =? WHERE nig=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1,namaTF.getText());
                    ps.setString(2,tempatTF.getText());
                    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                    String addDate =dateFormat.format(tglTF.getDate());
                    ps.setString(3,addDate);
                   
                        ps.setString(4, (String) genderCB.getSelectedItem());
                          ps.setString(5, (String) guruCB.getSelectedItem());
                            ps.setString(6, (String) alamatTF.getText());
                            ps.setString(7, (String) nigTF.getText());
                            
                    
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diedit","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
                    tampil();
                    bersih();
                    
                } catch (Exception ex) {
                    Logger.getLogger(datamurid.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                try{
                InputStream img = new FileInputStream(new File(Imgpath));
             UpdateQuery="UPDATE dataguru SET nama=?,tempatlahir=?,tanggallahir=?,gender=?,guru=?,alamat=?"
                            +",image =? WHERE nig=?";
            ps=con.prepareStatement(UpdateQuery);
            ps.setString(1,namaTF.getText());
                    ps.setString(2,tempatTF.getText());
                    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                    String addDate =dateFormat.format(tglTF.getDate());
                    ps.setString(3,addDate);
                    
                        ps.setString(4, (String) genderCB.getSelectedItem());
                          ps.setString(5, (String) guruCB.getSelectedItem());
                            ps.setString(6, (String) alamatTF.getText());
                              ps.setBlob(7, img);
                            ps.setString(8, (String) nigTF.getText());
                  
                   
                    ps.executeUpdate();
                    tampil();
                    bersih();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diedit","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
           
                
         
        }
             }else{
                  JOptionPane.showMessageDialog(null,"Id Tidak Tersedia","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);   
                    }
       
}


private void ShowItem(int index){

            nigTF.setText(jTable1.getValueAt(index,0).toString());
        namaTF.setText(jTable1.getValueAt(index,1).toString());
         tempatTF.setText(jTable1.getValueAt(index,2).toString());
         String date=jTable1.getValueAt(index, 3).toString();
           try {
            
            SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=P.parse(date);
            tglTF.setDate(d);
           }catch(Exception e){
               e.printStackTrace();
        }
           
          
             genderCB.setSelectedItem(jTable1.getValueAt(index,4).toString());
              guruCB.setSelectedItem(jTable1.getValueAt(index,5).toString());
               alamatTF.setText(jTable1.getValueAt(index,6).toString());
           
           Image pic = ((ImageIcon)jTable1.getValueAt(index, 7)).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon img = new ImageIcon(pic);
           imageTF.setIcon(img);
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);


    }

private void hapus()
{
      
            try {
                Connection con =getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM dataguru WHERE nig =?");
                  ps.setString(1, (String) nigTF.getText());
                ps.executeUpdate();
                tampil();
                bersih();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus","SEOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
           
            
        }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tempatTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        nigTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        namaTF = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        tglTF = new com.toedter.calendar.JDateChooser();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        xBT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        guruCB = new javax.swing.JComboBox<>();
        genderCB = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatTF = new javax.swing.JTextArea();
        imageTF = new javax.swing.JLabel();
        fileBT = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        hapusBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        editBT = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA MURID");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA GURU");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukkan Input Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tanggal Lahir :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 105, 90, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("NIG               :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nama Guru    :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 90, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tempat Lahir :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, 30));

        tempatTF.setBorder(null);
        jPanel3.add(tempatTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 140, 30));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 220, 10));

        nigTF.setBorder(null);
        jPanel3.add(nigTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 360, 40));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, 10));

        namaTF.setBorder(null);
        jPanel3.add(namaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 360, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 460, 10));
        jPanel3.add(tglTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 130, 30));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 480, 160));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray)), "Table Data Guru", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1040, 190));

        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTFKeyReleased(evt);
            }
        });
        jPanel4.add(cariTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 230, 40));

        xBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        xBT.setContentAreaFilled(false);
        jPanel4.add(xBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1080, 260));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Guru");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 60, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Foto Guru   :");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Gender       :");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Alamat       :");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        guruCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matematika", "Bahasa Indonsesia", "Ppkn", "Agama Islam", "Bahasa Inggirs", "Penjakes", "Kewirausahaan", "Komputer", " " }));
        jPanel5.add(guruCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 200, 30));

        genderCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        jPanel5.add(genderCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 160, 30));
        jPanel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 260, 10));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 520, 10));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 240, 10));

        alamatTF.setColumns(20);
        alamatTF.setRows(5);
        alamatTF.setBorder(null);
        jScrollPane1.setViewportView(alamatTF);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 440, 40));
        jPanel5.add(imageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 150, 70));

        fileBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fileBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bahan/p_open.png"))); // NOI18N
        fileBT.setText("AMBIL FOTO");
        fileBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileBTMouseEntered(evt);
            }
        });
        fileBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBTActionPerformed(evt);
            }
        });
        jPanel5.add(fileBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 170, 40));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 520, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 550, 190));

        hapusBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapusBT.setText("HAPUS");
        hapusBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusBTMouseEntered(evt);
            }
        });
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });
        jPanel1.add(hapusBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 120, 40));

        tambahBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tambahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Tambah.png"))); // NOI18N
        tambahBT.setText("TAMBAH");
        tambahBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahBTMouseEntered(evt);
            }
        });
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, 40));

        simpanBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Simpan.png"))); // NOI18N
        simpanBT.setText("SIMPAN");
        simpanBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanBTMouseEntered(evt);
            }
        });
        simpanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBTActionPerformed(evt);
            }
        });
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 120, 40));

        editBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        editBT.setText("EDIT");
        editBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBTMouseEntered(evt);
            }
        });
        editBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTActionPerformed(evt);
            }
        });
        jPanel1.add(editBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 120, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("-->");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, 60, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("<--");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 270, 60, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("->");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 270, 60, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("<-");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 270, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        // TODO add your handling code here:
        tambahBT.setBackground(new Color(0,153,153));
       tambahBT.setForeground(Color.red);
    }//GEN-LAST:event_tambahBTMouseEntered

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        // TODO add your handling code here:
       hapusBT.setBackground(new Color(0,153,153));
       hapusBT.setForeground(Color.red);
    }//GEN-LAST:event_hapusBTMouseEntered

    private void fileBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileBTMouseEntered
        // TODO add your handling code here:
        fileBT.setBackground(new Color(0,153,153));
       fileBT.setForeground(Color.red);
    }//GEN-LAST:event_fileBTMouseEntered

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        // TODO add your handling code here:
          simpanBT.setBackground(new Color(0,153,153));
      simpanBT.setForeground(Color.red);
    }//GEN-LAST:event_simpanBTMouseEntered

    private void editBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBTMouseEntered
        // TODO add your handling code here:
        editBT.setBackground(new Color(0,153,153));
       editBT.setForeground(Color.red);
    }//GEN-LAST:event_editBTMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          pos=jTable1.getRowCount() -1;
        ShowItem(pos);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
       jButton2.setBackground(new Color(0,153,153));
      jButton2.setForeground(Color.red);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
        jButton3.setBackground(new Color(0,153,153));
      jButton3.setForeground(Color.red);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
        jButton4.setBackground(new Color(0,153,153));
      jButton4.setForeground(Color.red);
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setBackground(new Color(0,153,153));
      jButton1.setForeground(Color.red);
    }//GEN-LAST:event_jButton1MouseEntered

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            //bersih();
            siapIsi(true);
            otomatis();
            namaTF.requestFocus();
            nigTF.setEnabled(true);
            tambahBT.setEnabled(true);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
            editBT.setEnabled(false);
           
            fileBT.setEnabled(true);
        } else{
            tambahBT.setText("Tambah");
            bersih();
            siapIsi(false);
            tombolNormal();
        
    }    
    }//GEN-LAST:event_tambahBTActionPerformed

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        // TODO add your handling code here:
         if(tambahBT.getText().equalsIgnoreCase("batal")){
                if(tambahBT.getText().equalsIgnoreCase("batal")){
                    simpan();
                } else{
                    JOptionPane.showMessageDialog(null, "Simpan data gagal, periksa kembali","SEKOLAH CITA-CITA",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(editBT.getText().equalsIgnoreCase("batal")){
                if(editBT.getText().equalsIgnoreCase("batal")){
                    perbarui();
                } else{
                    JOptionPane.showMessageDialog(null, "Edit data gagal, periksa kembali","SEKOLAH CITA-CITA",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
            editBT.setText("Edit");
            tombolNormal();
    }//GEN-LAST:event_simpanBTActionPerformed

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        // TODO add your handling code here:
        if(editBT.getText().equalsIgnoreCase("edit")){
            editBT.setText("Batal");
            siapIsi(true);
            nigTF.setEnabled(false);
          
            namaTF.requestFocus();
            tambahBT.setEnabled(false);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
            editBT.setEnabled(true);
           
            fileBT.setEnabled(true);
        } else{
            editBT.setText("Edit");
            bersih();
            siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_editBTActionPerformed

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        // TODO add your handling code here:
        int pesan=JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            if(pesan==JOptionPane.YES_OPTION){
                hapus();
                bersih();
                siapIsi(false);
                tombolNormal();
            } else{
                JOptionPane.showMessageDialog(null, "Hapus data gagal");
            }
            tampil();
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         pos=0;
        ShowItem (pos);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          pos++;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          pos--;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void fileBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBTActionPerformed
        // TODO add your handling code here:
         JFileChooser file= new JFileChooser();
       file.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","png");
       file.addChoosableFileFilter(filter);
       int result=file.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION){
           {
               File selectedFile =file.getSelectedFile();
               String path=selectedFile.getAbsolutePath();
               imageTF.setIcon(ResizeImage(path,null));
               Imgpath=path;
           }
           }else{
                   System.out.println("No File");
           
       }
    }//GEN-LAST:event_fileBTActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int index=jTable1.getSelectedRow();
       nigTF.setText(jTable1.getValueAt(index,0).toString());
        namaTF.setText(jTable1.getValueAt(index,1).toString());
         tempatTF.setText(jTable1.getValueAt(index,2).toString());
         String date=jTable1.getValueAt(index, 3).toString();
           try {
            
            SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=P.parse(date);
            tglTF.setDate(d);
           }catch(Exception e){
               e.printStackTrace();
        }
           
           
             genderCB.setSelectedItem(jTable1.getValueAt(index,4).toString());
              guruCB.setSelectedItem(jTable1.getValueAt(index,5).toString());
               alamatTF.setText(jTable1.getValueAt(index,6).toString());
           
           Image pic = ((ImageIcon)jTable1.getValueAt(index, 7)).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon img = new ImageIcon(pic);
           imageTF.setIcon(img);
       hapusBT.setEnabled(true);
        editBT.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void cariTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyReleased
        // TODO add your handling code here:
         Connection con = getConnection();
   Statement st;
   ResultSet rs;
           String sql="select * from  datasiswa where nis='"+ cariTF.getText() +"'";
        try {
             st = con.createStatement();
             rs=st.executeQuery(sql);
            if (rs.next())
            {
                String setnis=rs.getString("nig");
                nigTF.setText(setnis);
               String setnama=rs.getString("nama");
                namaTF.setText(setnama);
                String settempat=rs.getString("tempatlahir");
                tempatTF.setText(settempat);
                 tglTF.setDate(rs.getDate("tanggallahir"));
                 
                
                genderCB.setSelectedItem(rs.getString("gender"));
                guruCB.setSelectedItem(rs.getString("guru"));
              
                  String setalamat=rs.getString("alamat");
                alamatTF.setText(setalamat);
                      byte[] img=rs.getBytes("image");
               ImageIcon ImageIcon =new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH));
                
               imageTF.setIcon(ImageIcon);
               person_image=img;
                hapusBT.setEnabled(true);
                editBT.setEnabled(true);
               
                
               xBT.show();
                
            }
            else {
               
            }
        } catch (Exception e) {
        }
             
    }//GEN-LAST:event_cariTFKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatTF;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton editBT;
    private javax.swing.JButton fileBT;
    private javax.swing.JComboBox<String> genderCB;
    private javax.swing.JComboBox<String> guruCB;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel imageTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField nigTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JButton tambahBT;
    private javax.swing.JTextField tempatTF;
    private com.toedter.calendar.JDateChooser tglTF;
    private javax.swing.JButton xBT;
    // End of variables declaration//GEN-END:variables
}
