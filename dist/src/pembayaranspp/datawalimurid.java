/*s
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author TOSHIBA DC
 */
public class datawalimurid extends javax.swing.JInternalFrame {

   

    /**
     * Creates new form datamurid
     */
    public datawalimurid() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        initComponents();
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.blue);
        jTable1.setSelectionBackground(Color.gray);
        JTableHeader th =jTable1.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.PLAIN, 14));
        tampil();
        xBT.hide();
         getConnection();
       
         
           siapIsi(false);
        tombolNormal();
    
    }
    
    
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
public String Idsiswa,Namasiswa;

    public String IDsiswa() {
        return Idsiswa;
    }

    ;
    
    public String NAmasiswa() {
        return Namasiswa;
    }

       public boolean checkInputs()
{
    if(
             
         namaTF.getText()== null
            || ibuTF.getText()== null
            || anakTF.getText()== null
            || kerjaayahTF.getText()== null
            || kerjaibuTF.getText()== null
             
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
        noTF.setText("");
        namaTF.setText("");
          ibuTF.setText("");
            anakTF.setText("");
          kerjaayahTF.setText("");
          kerjaibuTF.setText("");

    }
      private void siapIsi(boolean a){
        noTF.setEnabled(a);
        namaTF.setEnabled(a);
        ibuTF.setEnabled(a);
       
         anakTF.setEnabled(a);
          kerjaayahTF.setEnabled(a);
            kerjaibuTF.setEnabled(a);
        
       
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
        hapusBT.setEnabled(false);
        editBT.setEnabled(false);
      
    }
    
    
   public void tampil(){
            
            try {

                Object header[]={"no","namaayah","namaibu","namaanak","pekerjaanayah","pekerjaanibu"};
                DefaultTableModel data=new DefaultTableModel(null,header);
                jTable1.setModel(data);
                
                Connection con = getConnection();
                Statement st;
                ResultSet rs;
                
                st = con.createStatement();
                String sql="select*from datawalimurid";
           
                    
                    rs=st.executeQuery(sql);
                    while (rs.next())
                    {
                        String kolom1=rs.getString(1);
                        String kolom2=rs.getString(2);
                        String kolom3=rs.getString(3);
                        String kolom4=rs.getString(4);
                        String kolom5=rs.getString(5);
                         String kolom6=rs.getString(6);
                    
                        String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                        data.addRow(kolom);
                    }
               

            } catch (SQLException ex) {
            Logger.getLogger(tambahdatabayaransekolah.class.getName()).log(Level.SEVERE, null, ex);
            }
       
}


private void simpan()
{  
    if(checkInputs())
        {
            try{
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("INSERT INTO datawalimurid(namaayah,namaibu,namaanak,pekerjaanayah,pekerjaanibu)"
                    + "values(?,?,?,?,?)");
                ps.setString(1, namaTF.getText());
                 ps.setString(2, ibuTF.getText());
                  ps.setString(3, anakTF.getText());
                   ps.setString(4, kerjaayahTF.getText());
                    ps.setString(5, kerjaibuTF.getText());
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
     if(checkInputs())
     {
            String UpdateQuery= null;
            PreparedStatement ps =null;
            Connection con=getConnection();
           
                try {
                  
                    UpdateQuery="UPDATE datawalimurid SET namaayah=?,namaibu=?,namaanak=?,pekerjaanayah=?"
                            +",pekerjaanibu =? WHERE no=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1,namaTF.getText());
                    ps.setString(2,ibuTF.getText());
                        ps.setString(3,anakTF.getText());
                            ps.setString(4,kerjaayahTF.getText());
                                ps.setString(5,kerjaibuTF.getText());
                  
                   
                       
                            ps.setInt(6, Integer.parseInt(noTF.getText()));
                            
                    
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diedit","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);
                    tampil();
                    bersih();
                    
                } catch (Exception ex) {
                    Logger.getLogger(datawalimurid.class.getName()).log(Level.SEVERE, null, ex);
                }
                
           
                    
         
             }else{
                  JOptionPane.showMessageDialog(null,"Id Tidak Tersedia","SEKOLAH CITA-CITA", JOptionPane.INFORMATION_MESSAGE);   
                    }
       
}


private void ShowItem(int index){

            noTF.setText(jTable1.getValueAt(index,0).toString());
        namaTF.setText(jTable1.getValueAt(index,1).toString());
         ibuTF.setText(jTable1.getValueAt(index,2).toString());
          anakTF.setText(jTable1.getValueAt(index,3).toString());
          kerjaayahTF.setText(jTable1.getValueAt(index,4).toString());  
               kerjaibuTF.setText(jTable1.getValueAt(index,5).toString());
          
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);


    }

private void hapus()
{
      
            try {
                Connection con =getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM datawalimurid WHERE no =?");
                 ps.setInt(1, Integer.parseInt(noTF.getText()));
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kerjaibuTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        noTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ibuTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        namaTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        anakTF = new javax.swing.JTextField();
        kerjaayahTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        xBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        editBT = new javax.swing.JButton();
        idsiswaTF = new javax.swing.JTextField();

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
        jLabel2.setText("DATA WALI MURID");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 240, 120));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Pekerjaan Ibu    :");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 40));

        kerjaibuTF.setBorder(null);
        jPanel4.add(kerjaibuTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 260, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("No                     :");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 40));

        noTF.setBorder(null);
        noTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTFActionPerformed(evt);
            }
        });
        jPanel4.add(noTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 260, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nama Ayah        :");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 110, 40));

        ibuTF.setBorder(null);
        ibuTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ibuTFKeyReleased(evt);
            }
        });
        jPanel4.add(ibuTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 260, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Nama Ibu           :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 110, 40));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 370, -1));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, -1));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 370, -1));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 370, -1));
        jPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 370, -1));
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 370, -1));

        namaTF.setBorder(null);
        namaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaTFKeyReleased(evt);
            }
        });
        jPanel4.add(namaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 260, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nama Anak        :");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Pekerjaan Ayah  :");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 40));

        anakTF.setBorder(null);
        anakTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                anakTFKeyReleased(evt);
            }
        });
        jPanel4.add(anakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 210, 40));

        kerjaayahTF.setBorder(null);
        kerjaayahTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kerjaayahTFKeyReleased(evt);
            }
        });
        jPanel4.add(kerjaayahTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 260, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 165, 50, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 390, 340));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Table Wali Murid", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 660, 230));

        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTFKeyReleased(evt);
            }
        });
        jPanel5.add(cariTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 190, 40));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("<<-");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("->");
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
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, -1, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("<-");
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
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, 30));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("->>");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, 30));

        xBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        xBT.setContentAreaFilled(false);
        xBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xBTActionPerformed(evt);
            }
        });
        jPanel5.add(xBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 30, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 680, 340));

        hapusBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapusBT.setText("HAPUS");
        hapusBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hapusBTMouseExited(evt);
            }
        });
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });
        jPanel1.add(hapusBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 110, 40));

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
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, 40));

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
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 110, 40));

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
        jPanel1.add(editBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 110, 40));
        jPanel1.add(idsiswaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 460, 10, 0));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void hapusBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusBTMouseExited

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        // TODO add your handling code here:
          hapusBT.setBackground(new Color(0,153,153));
       hapusBT.setForeground(Color.red);
    }//GEN-LAST:event_hapusBTMouseEntered

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
          jButton5.setBackground(new Color(0,153,153));
       jButton5.setForeground(Color.red);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
         jButton6.setBackground(new Color(0,153,153));
       jButton6.setForeground(Color.red);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
         jButton7.setBackground(new Color(0,153,153));
       jButton7.setForeground(Color.red);
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
         jButton8.setBackground(new Color(0,153,153));
       jButton8.setForeground(Color.red);
    }//GEN-LAST:event_jButton8MouseEntered

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1MouseEntered

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            //bersih();
            siapIsi(true);
           
            namaTF.requestFocus();
            noTF.setEnabled(false);
            tambahBT.setEnabled(true);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
            editBT.setEnabled(false);
           
           
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
            noTF.setEnabled(false);
          
            namaTF.requestFocus();
            tambahBT.setEnabled(false);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
            editBT.setEnabled(true);
           
            
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         pos=0;
        ShowItem (pos);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         pos=jTable1.getRowCount() -1;
        ShowItem(pos);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
          pos--;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
          pos++;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index=jTable1.getSelectedRow();
           noTF.setText(jTable1.getValueAt(index,0).toString());
        namaTF.setText(jTable1.getValueAt(index,1).toString());
         ibuTF.setText(jTable1.getValueAt(index,2).toString());
          anakTF.setText(jTable1.getValueAt(index,3).toString());
          kerjaayahTF.setText(jTable1.getValueAt(index,4).toString());  
               kerjaibuTF.setText(jTable1.getValueAt(index,5).toString());
          
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);    
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
          int index=jTable1.getSelectedRow();
           noTF.setText(jTable1.getValueAt(index,0).toString());
        namaTF.setText(jTable1.getValueAt(index,1).toString());
         ibuTF.setText(jTable1.getValueAt(index,2).toString());
          anakTF.setText(jTable1.getValueAt(index,3).toString());
          kerjaayahTF.setText(jTable1.getValueAt(index,4).toString());  
               kerjaibuTF.setText(jTable1.getValueAt(index,5).toString());
          
           hapusBT.setEnabled(true);
            editBT.setEnabled(true); 
    }//GEN-LAST:event_jTable1KeyReleased

    private void cariTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyReleased
        // TODO add your handling code here:
          Object header[]={"no","namaayah","namaibu","namaanak","pekerjaanayah","pekerjaanibu"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
         Connection con = getConnection();
                Statement st;
                ResultSet rs;
                try {
                st = con.createStatement();
        String sql="Select * from datawalimurid where no like '%" + cariTF.getText() + "%'" + "or namaayah like '%" + cariTF.getText()+ "%'";
        
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
               
                 
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
                
                xBT.show();
    }//GEN-LAST:event_cariTFKeyReleased

    private void xBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xBTActionPerformed
        // TODO add your handling code here:
        xBT.hide();
        cariTF.setText("");
        tampil();
    }//GEN-LAST:event_xBTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
         pglsiswa fDB = new  pglsiswa(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        idsiswaTF.setText(Idsiswa);
         anakTF.setText(Namasiswa);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void noTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTFActionPerformed

    private void namaTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         ibuTF.requestFocus();
           }
    }//GEN-LAST:event_namaTFKeyReleased

    private void ibuTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ibuTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         anakTF.requestFocus();
           }
    }//GEN-LAST:event_ibuTFKeyReleased

    private void anakTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anakTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        kerjaayahTF.requestFocus();
           }
    }//GEN-LAST:event_anakTFKeyReleased

    private void kerjaayahTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kerjaayahTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         kerjaibuTF.requestFocus();
           }
    }//GEN-LAST:event_kerjaayahTFKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anakTF;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton editBT;
    private javax.swing.JButton hapusBT;
    private javax.swing.JTextField ibuTF;
    private javax.swing.JTextField idsiswaTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kerjaayahTF;
    private javax.swing.JTextField kerjaibuTF;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JButton tambahBT;
    private javax.swing.JButton xBT;
    // End of variables declaration//GEN-END:variables
}
