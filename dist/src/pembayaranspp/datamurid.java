/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import com.sun.glass.events.KeyEvent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author TOSHIBA DC
 */
public class datamurid extends javax.swing.JInternalFrame {
    byte[] person_image;
   
Statement st;
             ResultSet rs;
    
    /**
     * Creates new form datamurid
     */
    public datamurid() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
         this.person_image=null;
        initComponents();
       
         jTable2.setShowGrid(true);
        jTable2.setGridColor(Color.blue);
        jTable2.setSelectionBackground(Color.gray);
        JTableHeader th =jTable2.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.PLAIN, 14));
        tampil();
         getConnection();
          otomatis();
           siapIsi(false);
        tombolNormal();
        kelas();
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

  public void kelas(){
        try{
       Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            
            String sql="SELECT *FROM tambahdatakelas";
             rs=st.executeQuery(sql);
             
             while(rs.next()){
                 kelasCB.addItem(rs.getString("kelas"));
                 
             }
             rs.last();
             int jumlahdata=rs.getRow();
             rs.first();
             
        }catch(Exception ex){
           
        
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
            || kelasCB.getSelectedItem()== null
              || agamaCB.getSelectedItem()== null
              || genderCB.getSelectedItem()== null
              || statusCB.getSelectedItem()== null
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
        nisTF.setText("");
        namaTF.setText("");
        tempatTF.setText("");
          tglTF.setDate(null);
          kelasCB.setSelectedItem("");
           agamaCB.setSelectedItem("");
            kelasCB.setSelectedItem("");
             genderCB.setSelectedItem("");
             statusCB.setSelectedItem("");
             alamatTF.setText("");
          imageTF.setIcon(null);
          Imgpath =null;
        
    }
      private void siapIsi(boolean a){
        nisTF.setEnabled(a);
        namaTF.setEnabled(a);
        tempatTF.setEnabled(a);
        tglTF.setEnabled(a);
        kelasCB.setEnabled(a);
        agamaCB.setEnabled(a);
         genderCB.setEnabled(a);
        statusCB.setEnabled(a);
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
    
    
    public ArrayList<spp>getsppList(){
        
   ArrayList<spp> list = new ArrayList<spp>();
   Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   try {
   st = con.createStatement();
   rs = st.executeQuery("SELECT `nis`, `nama`, `tempatlahir`, `tanggallahir`,`kelas`,`agama`,`gender`,`status`,`alamat`,`image` FROM `datasiswa`");
   
  spp p;
   while(rs.next()){
   p = new spp(
   rs.getString("nis"),
   rs.getString("nama"),
   rs.getString("tempatlahir"),
   rs.getString("tanggallahir"),
   rs.getString("kelas"),
   rs.getString("agama"),
   rs.getString("gender"),
   rs.getString("status"), 
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
           DateFormat vblnth = new SimpleDateFormat("YYYY");
            String blnth = vblnth.format(Calendar.getInstance().getTime());
            Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            
            String sql="select right (nis,2)+1 from datasiswa";
             rs=st.executeQuery(sql);
            
           
           
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no=blnth+"43501"+no;
                    nisTF.setText(no);}
                }
            else
            {
                nisTF.setText(blnth+"4350180"); 
            }
            } catch (Exception e) 
            {
        }
    
    }
public void tampil(){
        
        ArrayList<spp> list = getsppList();
        
        String[] columnName = {"nis","nama","tempatlahir","tanggallahir","kelas","agama","gender","status","alamat","image"};
       
        Object[][] rows = new Object[list.size()][10];
        for(int i = 0; i < list.size(); i++){
            rows[i][0] = list.get(i).getNis();
            rows[i][1] = list.get(i).getName();
            rows[i][2] = list.get(i).getTempat();
            rows[i][3] = list.get(i).getAddDate();
               rows[i][4] = list.get(i).getKelas();
                  rows[i][5] = list.get(i).getAgama();
                     rows[i][6] = list.get(i).getGender();
                        rows[i][7] = list.get(i).getStatus();
                           rows[i][8] = list.get(i).getAlamat();
                    
             ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage()
             .getScaledInstance(80, 60, Image.SCALE_SMOOTH) );   
                
            rows[i][9] = image;
            
        
        }

        query model = new query(rows, columnName);
        jTable2.setModel(model);
        jTable2.setRowHeight(120);
        jTable2.getColumnModel().getColumn(9).setPreferredWidth(150);
    }


private void simpan()
{  
    if(checkInputs() && Imgpath != null)
        {
            try{
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("INSERT INTO datasiswa(nis,nama,tempatlahir,tanggallahir,kelas,agama,gender,status,alamat,image)"
                    + "values(?,?,?,?,?,?,?,?,?,?)");
                
                ps.setString(1,nisTF.getText());
                ps.setString(2, namaTF.getText());
                 ps.setString(3, tempatTF.getText());
                SimpleDateFormat dateFormat =new SimpleDateFormat("YYYY-MM-dd");
                String addDate=dateFormat.format(tglTF.getDate());
                ps.setString(4, addDate);
                
                ps.setString(5, (String) kelasCB.getSelectedItem());
                ps.setString(6, (String) agamaCB.getSelectedItem());
                ps.setString(7, (String) genderCB.getSelectedItem());
                ps.setString(8, (String) statusCB.getSelectedItem());
                ps.setString(9, alamatTF.getText());
                InputStream img = new FileInputStream(new File(Imgpath));
                ps.setBlob(10, img);
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
     if(checkInputs() && nisTF.getText() !=null)
     {
            String UpdateQuery= null;
            PreparedStatement ps =null;
            Connection con=getConnection();
            if(Imgpath == null)
            {
                try {
                  
                    UpdateQuery="UPDATE datasiswa SET nama=?,tempatlahir=?,tanggallahir=?,kelas=?,agama=?,gender=?,status=?"
                            +",alamat =? WHERE nis=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1,namaTF.getText());
                    ps.setString(2,tempatTF.getText());
                    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                    String addDate =dateFormat.format(tglTF.getDate());
                    ps.setString(3,addDate);
                    ps.setString(4, (String) kelasCB.getSelectedItem());
                      ps.setString(5, (String) agamaCB.getSelectedItem());
                        ps.setString(6, (String) genderCB.getSelectedItem());
                          ps.setString(7, (String) statusCB.getSelectedItem());
                            ps.setString(8, (String) alamatTF.getText());
                            ps.setString(9, (String) nisTF.getText());
                            
                    
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
             UpdateQuery="UPDATE datasiswa SET nama=?,tempatlahir=?,tanggallahir=?,kelas=?,agama=?,gender=?,status=?,alamat=?"
                            +",image =? WHERE nis=?";
            ps=con.prepareStatement(UpdateQuery);
            ps.setString(1,namaTF.getText());
                    ps.setString(2,tempatTF.getText());
                    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                    String addDate =dateFormat.format(tglTF.getDate());
                    ps.setString(3,addDate);
                    ps.setString(4, (String) kelasCB.getSelectedItem());
                      ps.setString(5, (String) agamaCB.getSelectedItem());
                        ps.setString(6, (String) genderCB.getSelectedItem());
                          ps.setString(7, (String) statusCB.getSelectedItem());
                            ps.setString(8, (String) alamatTF.getText());
                              ps.setBlob(9, img);
                            ps.setString(10, (String) nisTF.getText());
                  
                   
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

            nisTF.setText(jTable2.getValueAt(index,0).toString());
        namaTF.setText(jTable2.getValueAt(index,1).toString());
         tempatTF.setText(jTable2.getValueAt(index,2).toString());
         String date=jTable2.getValueAt(index, 3).toString();
           try {
            
            SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=P.parse(date);
            tglTF.setDate(d);
           }catch(Exception e){
               e.printStackTrace();
        }
           
           kelasCB.setSelectedItem(jTable2.getValueAt(index,4).toString());
            agamaCB.setSelectedItem(jTable2.getValueAt(index,5).toString());
             genderCB.setSelectedItem(jTable2.getValueAt(index,6).toString());
              statusCB.setSelectedItem(jTable2.getValueAt(index,7).toString());
               alamatTF.setText(jTable2.getValueAt(index,8).toString());
           
           Image pic = ((ImageIcon)jTable2.getValueAt(index, 9)).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon img = new ImageIcon(pic);
           imageTF.setIcon(img);
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);


    }

private void hapus()
{
      
            try {
                Connection con =getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM datasiswa WHERE nis =?");
                  ps.setString(1, (String) nisTF.getText());
                ps.executeUpdate();
                tampil();
                bersih();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
                
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
        jLabel4 = new javax.swing.JLabel();
        tempatTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nisTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        tglTF = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatTF = new javax.swing.JTextArea();
        jSeparator10 = new javax.swing.JSeparator();
        genderCB = new javax.swing.JComboBox<>();
        statusCB = new javax.swing.JComboBox<>();
        kelasCB = new javax.swing.JComboBox<>();
        agamaCB = new javax.swing.JComboBox<>();
        imageTF = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        fileBT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        xBT = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        editBT = new javax.swing.JButton();

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
        jLabel2.setText("DATA MURID");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nama             :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 100, 30));

        tempatTF.setBorder(null);
        tempatTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tempatTFKeyReleased(evt);
            }
        });
        jPanel3.add(tempatTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 110, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nis                 :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 90, 30));

        nisTF.setBorder(null);
        jPanel3.add(nisTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 380, 40));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 260, 10));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 470, 10));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tanggal Lahir :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, 30));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 470, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Alamat          :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 90, 40));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 200, 10));

        tglTF.setBackground(new java.awt.Color(255, 255, 255));
        tglTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tglTFKeyReleased(evt);
            }
        });
        jPanel3.add(tglTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 160, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Tempat Lahir :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 125, -1, 30));

        namaTF.setBorder(null);
        namaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTFActionPerformed(evt);
            }
        });
        namaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaTFKeyReleased(evt);
            }
        });
        jPanel3.add(namaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 380, 40));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 470, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Kelas             :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, 90, 30));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 200, 10));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Agama           :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 90, 30));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 250, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Gender          :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 225, 90, 30));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 200, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Status           :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 90, 30));

        alamatTF.setColumns(20);
        alamatTF.setRows(5);
        alamatTF.setBorder(null);
        alamatTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                alamatTFKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(alamatTF);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 380, 60));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 250, 10));

        genderCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Gender-", "Laki-Laki", "Perempuan" }));
        genderCB.setBorder(null);
        genderCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderCBActionPerformed(evt);
            }
        });
        genderCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                genderCBKeyReleased(evt);
            }
        });
        jPanel3.add(genderCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 110, 40));

        statusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Status------", "Aktif", "Tidak Aktif" }));
        statusCB.setBorder(null);
        statusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusCBActionPerformed(evt);
            }
        });
        statusCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                statusCBKeyReleased(evt);
            }
        });
        jPanel3.add(statusCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 160, 40));

        kelasCB.setBorder(null);
        kelasCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kelasCBActionPerformed(evt);
            }
        });
        kelasCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kelasCBKeyReleased(evt);
            }
        });
        jPanel3.add(kelasCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 110, 40));

        agamaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Pilih Agama------", "Islam", "Kristen", "Hindu", "Budha", " " }));
        agamaCB.setBorder(null);
        agamaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agamaCBActionPerformed(evt);
            }
        });
        agamaCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                agamaCBKeyReleased(evt);
            }
        });
        jPanel3.add(agamaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 160, 40));

        imageTF.setBackground(new java.awt.Color(255, 255, 255));
        imageTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                imageTFKeyReleased(evt);
            }
        });
        jPanel3.add(imageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 210, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Foto Siswa   :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 365, -1, 40));
        jPanel3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 470, 10));

        fileBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fileBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bahan/p_open.png"))); // NOI18N
        fileBT.setText("Ambil Foto");
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
        jPanel3.add(fileBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 160, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 490, 470));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Table Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 560, 300));

        cariTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTFActionPerformed(evt);
            }
        });
        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTFKeyReleased(evt);
            }
        });
        jPanel4.add(cariTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 240, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("<");
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
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, 80, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("->>");
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
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 70, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText(">");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 80, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("<<--");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 80, 30));
        jPanel4.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, -10, -1, -1));

        xBT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        xBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        xBT.setContentAreaFilled(false);
        xBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xBTMouseEntered(evt);
            }
        });
        xBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xBTActionPerformed(evt);
            }
        });
        jPanel4.add(xBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 30, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/show.png"))); // NOI18N
        jButton1.setText("DETAIL");
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
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 580, 410));

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
        jPanel1.add(hapusBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 500, 110, 40));

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
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 120, 40));

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
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 110, 40));

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
        jPanel1.add(editBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genderCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderCBActionPerformed

    private void statusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCBActionPerformed

    private void kelasCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelasCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kelasCBActionPerformed

    private void agamaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agamaCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agamaCBActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          pos--;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void namaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTFActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        pos++;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         pos=0;
        ShowItem (pos);
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         int index=jTable2.getSelectedRow();
       nisTF.setText(jTable2.getValueAt(index,0).toString());
        namaTF.setText(jTable2.getValueAt(index,1).toString());
         tempatTF.setText(jTable2.getValueAt(index,2).toString());
         String date=jTable2.getValueAt(index, 3).toString();
           try {
            
            SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=P.parse(date);
            tglTF.setDate(d);
           }catch(Exception e){
               e.printStackTrace();
        }
          
                  
                
           
           kelasCB.setSelectedItem(jTable2.getValueAt(index,4).toString());
            agamaCB.setSelectedItem(jTable2.getValueAt(index,5).toString());
             genderCB.setSelectedItem(jTable2.getValueAt(index,6).toString());
              statusCB.setSelectedItem(jTable2.getValueAt(index,7).toString());
               alamatTF.setText(jTable2.getValueAt(index,8).toString());
           
           Image pic = ((ImageIcon)jTable2.getValueAt(index, 9)).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon img = new ImageIcon(pic);
           imageTF.setIcon(img);
       hapusBT.setEnabled(true);
        editBT.setEnabled(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        // TODO add your handling code here:
       if(editBT.getText().equalsIgnoreCase("edit")){
            editBT.setText("Batal");
            siapIsi(true);
            nisTF.setEnabled(false);
          
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
          pos=jTable2.getRowCount() -1;
        ShowItem(pos);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fileBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileBTMouseEntered
        // TODO add your handling code here:
           fileBT.setBackground(new Color(0,153,153));
        fileBT.setForeground(Color.red);
    }//GEN-LAST:event_fileBTMouseEntered

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        // TODO add your handling code here:
        tambahBT.setBackground(new Color(0,153,153));
       tambahBT.setForeground(Color.red);
    }//GEN-LAST:event_tambahBTMouseEntered

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

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        // TODO add your handling code here:
        hapusBT.setBackground(new Color(0,153,153));
       hapusBT.setForeground(Color.red);
    }//GEN-LAST:event_hapusBTMouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
        jButton7.setBackground(new Color(0,153,153));
       jButton7.setForeground(Color.red);
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        jButton6.setBackground(new Color(0,153,153));
       jButton6.setForeground(Color.red);
                                        
        
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
        jButton4.setBackground(new Color(0,153,153));
       jButton4.setForeground(Color.red);
                                        
    }//GEN-LAST:event_jButton4MouseEntered

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            //bersih();
            siapIsi(true);
         otomatis();
            namaTF.requestFocus();
            nisTF.setEnabled(false);
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

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
           jButton2.setBackground(new Color(0,153,153));
       jButton2.setForeground(Color.red);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
      jTable2.setBackground(new Color(0,153,153));
      jTable2.setForeground(Color.red);
    }//GEN-LAST:event_jTable2MouseEntered

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
                String setnis=rs.getString("nis");
                nisTF.setText(setnis);
               String setnama=rs.getString("nama");
                namaTF.setText(setnama);
                String settempat=rs.getString("tempatlahir");
                tempatTF.setText(settempat);
                 tglTF.setDate(rs.getDate("tanggallahir"));
                 
                 kelasCB.setSelectedItem(rs.getString("kelas"));
                 agamaCB.setSelectedItem(rs.getString("agama"));
                genderCB.setSelectedItem(rs.getString("gender"));
                statusCB.setSelectedItem(rs.getString("status"));
              
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

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_UP || evt.getKeyCode() ==KeyEvent.VK_DOWN){
         int index=jTable2.getSelectedRow();
       nisTF.setText(jTable2.getValueAt(index,0).toString());
        namaTF.setText(jTable2.getValueAt(index,1).toString());
         tempatTF.setText(jTable2.getValueAt(index,2).toString());
         String date=jTable2.getValueAt(index, 3).toString();
           try {
            
            SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=P.parse(date);
            tglTF.setDate(d);
           }catch(Exception e){
               e.printStackTrace();
        }
           
           kelasCB.setSelectedItem(jTable2.getValueAt(index,4).toString());
            agamaCB.setSelectedItem(jTable2.getValueAt(index,5).toString());
             genderCB.setSelectedItem(jTable2.getValueAt(index,6).toString());
              statusCB.setSelectedItem(jTable2.getValueAt(index,7).toString());
               alamatTF.setText(jTable2.getValueAt(index,8).toString());
           
           Image pic = ((ImageIcon)jTable2.getValueAt(index, 9)).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon img = new ImageIcon(pic);
           imageTF.setIcon(img);
           editBT.setEnabled(true);
           
       hapusBT.setEnabled(true);
       }
    }//GEN-LAST:event_jTable2KeyReleased

    private void cariTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTFActionPerformed

    private void xBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xBTActionPerformed
        // TODO add your handling code here:
        cariTF.setText("");
        xBT.hide();
    }//GEN-LAST:event_xBTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         boolean closable = true;
         detailmurid fDB = new  detailmurid(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
         jButton1.setBackground(new Color(0,153,153));
        jButton1.setForeground(Color.red);
    }//GEN-LAST:event_jButton1MouseEntered

    private void xBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xBTMouseEntered
        // TODO add your handling code here:
      xBT.setBackground(new Color(0,153,153));
        xBT.setForeground(Color.red);
    }//GEN-LAST:event_xBTMouseEntered

    private void namaTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTFKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tempatTF.requestFocus();
        }
    }//GEN-LAST:event_namaTFKeyReleased

    private void tempatTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tempatTFKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          tglTF.requestFocus();
        }
    }//GEN-LAST:event_tempatTFKeyReleased

    private void tglTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglTFKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            kelasCB.requestFocus();
        }
    }//GEN-LAST:event_tglTFKeyReleased

    private void kelasCBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kelasCBKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           kelasCB.requestFocus();
        }
    }//GEN-LAST:event_kelasCBKeyReleased

    private void agamaCBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agamaCBKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            agamaCB.requestFocus();
        }
    }//GEN-LAST:event_agamaCBKeyReleased

    private void genderCBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genderCBKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           genderCB.requestFocus();
        }
    }//GEN-LAST:event_genderCBKeyReleased

    private void statusCBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusCBKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            statusCB.requestFocus();
        }
    }//GEN-LAST:event_statusCBKeyReleased

    private void alamatTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatTFKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            alamatTF.requestFocus();
        }
    }//GEN-LAST:event_alamatTFKeyReleased

    private void imageTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imageTFKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           imageTF.requestFocus();
        }
    }//GEN-LAST:event_imageTFKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> agamaCB;
    private javax.swing.JTextArea alamatTF;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton editBT;
    private javax.swing.JButton fileBT;
    private javax.swing.JComboBox<String> genderCB;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel imageTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> kelasCB;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField nisTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JComboBox<String> statusCB;
    private javax.swing.JButton tambahBT;
    private javax.swing.JTextField tempatTF;
    private com.toedter.calendar.JDateChooser tglTF;
    private javax.swing.JButton xBT;
    // End of variables declaration//GEN-END:variables
}
