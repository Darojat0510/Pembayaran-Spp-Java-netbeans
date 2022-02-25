/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author TOSHIBA DC
 */
public class tambahdatabayaransekolah extends javax.swing.JInternalFrame {

    /**
     * Creates new form datamurid
     */
    public tambahdatabayaransekolah() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        initComponents();
         jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.blue);
        jTable1.setSelectionBackground(Color.gray);
        JTableHeader th =jTable1.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.PLAIN, 14));
        tampil();
         getConnection();
       xBT.hide();
           siapIsi(false);
        tombolNormal();
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
       public boolean checkInputs()
{
    if(
            
      
         jenisTF.getText()== null
              
             || totalTF.getText()== null
            
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
        jenisTF.setText("");
        totalTF.setText("");
          
        
    }
      private void siapIsi(boolean a){
        noTF.setEnabled(a);
        jenisTF.setEnabled(a);
        totalTF.setEnabled(a);
        
       
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
        hapusBT.setEnabled(false);
        editBT.setEnabled(false);
    }
    
    
   
 
public void tampil(){
        
            
            try {
                
                
                Object header[]={"no","jenisbayaran","totalbayaran"};
                DefaultTableModel data=new DefaultTableModel(null,header);
                jTable1.setModel(data);
                
                Connection con = getConnection();
                Statement st;
                ResultSet rs;
                
                st = con.createStatement();
                String sql="select*from tambahdatabayaran";
           
                    
                    rs=st.executeQuery(sql);
                    while (rs.next())
                    {
                        String kolom1=rs.getString(1);
                        String kolom2=rs.getString(2);
                        String kolom3=rs.getString(3);
                    
                        String kolom[]={kolom1,kolom2,kolom3};
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
                PreparedStatement ps=con.prepareStatement("INSERT INTO tambahdatabayaran(jenisbayaran,totalbayaran)"
                    + "values(?,?)");
                
               
                ps.setString(1, jenisTF.getText());
                 ps.setInt(2, Integer.parseInt(totalTF.getText()));
               
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
     if(checkInputs() && noTF.getText() !=null)
     {
             if(checkInputs() && noTF.getText() !=null)
     {
            String UpdateQuery= null;
            PreparedStatement ps =null;
            Connection con=getConnection();
           
                try {
                  
                    UpdateQuery="UPDATE tambahdatabayaran SET jenisbayaran=?"
                            +",totalbayaran =? WHERE no=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1,jenisTF.getText());
                    ps.setString(2,totalTF.getText());
                          ps.setInt(3, Integer.parseInt(noTF.getText()));
                            
                            
                    
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
       
}

private void ShowItem(int index){

            noTF.setText(jTable1.getValueAt(index,0).toString());
        jenisTF.setText(jTable1.getValueAt(index,1).toString());
         totalTF.setText(jTable1.getValueAt(index,2).toString());
        
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);


    }

private void hapus()
{
      
            try {
                Connection con =getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM tambahdatabayaran WHERE no =?");
                  ps.setString(1, (String) noTF.getText());
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
        jSeparator1 = new javax.swing.JSeparator();
        totalTF = new javax.swing.JTextField();
        noTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jenisTF = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cariTF = new javax.swing.JTextField();
        xBT = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
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
        jLabel2.setText("TAMBAH DATA PEMBAYARAN");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Total Bayar            :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("No                         :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Jenis Pembayaran  :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 30));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 450, 10));

        totalTF.setBorder(null);
        jPanel3.add(totalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 330, 30));

        noTF.setBorder(null);
        jPanel3.add(noTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 330, 30));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 450, 10));

        jenisTF.setBorder(null);
        jenisTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jenisTFKeyReleased(evt);
            }
        });
        jPanel3.add(jenisTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 310, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 450, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 480, 160));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Table Data Pembayaran", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 540, 300));

        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTFKeyReleased(evt);
            }
        });
        jPanel4.add(cariTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 220, 40));

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
        jPanel4.add(xBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 20, 40));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("<");
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
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, -1, 30));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("->");
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
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, -1, 30));

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setText(">");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, 30));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setText("<-");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 580, 420));

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
        jPanel1.add(hapusBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 110, 40));

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
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 110, 40));

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
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 110, 40));

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
        jPanel1.add(editBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            //bersih();
            siapIsi(true);
        
           jenisTF.requestFocus();
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
          
            jenisTF.requestFocus();
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int index=jTable1.getSelectedRow();
         noTF.setText(jTable1.getValueAt(index,0).toString());
        jenisTF.setText(jTable1.getValueAt(index,1).toString());
         totalTF.setText(jTable1.getValueAt(index,2).toString());
        
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode()== KeyEvent.VK_UP || evt.getKeyCode() ==KeyEvent.VK_DOWN){
        int index=jTable1.getSelectedRow();
         noTF.setText(jTable1.getValueAt(index,0).toString());
        jenisTF.setText(jTable1.getValueAt(index,1).toString());
         totalTF.setText(jTable1.getValueAt(index,2).toString());
        
           hapusBT.setEnabled(true);
            editBT.setEnabled(true);
          }
        
        
    }//GEN-LAST:event_jTable1KeyReleased

    private void xBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xBTActionPerformed
        // TODO add your handling code here:
       xBT.hide();
        cariTF.setText("");
        tampil();
        
    }//GEN-LAST:event_xBTActionPerformed

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

    private void xBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xBTMouseEntered
        // TODO add your handling code here:
       xBT.setBackground(new Color(0,153,153));
       xBT.setForeground(Color.red);
    }//GEN-LAST:event_xBTMouseEntered

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
          jButton10.setBackground(new Color(0,153,153));
      jButton10.setForeground(Color.red);
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
        jButton9.setBackground(new Color(0,153,153));
      jButton9.setForeground(Color.red);
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
          jButton6.setBackground(new Color(0,153,153));
      jButton6.setForeground(Color.red);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
        jButton8.setBackground(new Color(0,153,153));
     jButton8.setForeground(Color.red);
    }//GEN-LAST:event_jButton8MouseEntered

    private void cariTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyReleased
        // TODO add your handling code here:
        Object header[]={"no","jenisbayaran","totalbayaran"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
         Connection con = getConnection();
                Statement st;
                ResultSet rs;
                try {
                st = con.createStatement();
        String sql="Select * from tambahdatabayaran where no like '%" + cariTF.getText() + "%'" + "or jenisbayaran like '%" + cariTF.getText()+ "%'";
        
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
               
                 
                
                String kolom[]={kolom1,kolom2,kolom3};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
                
                xBT.show();
    }//GEN-LAST:event_cariTFKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
          pos=0;
        ShowItem (pos);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
          pos++;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         pos--;
        if(pos<0)
        {
            pos=0;
            
        }
        ShowItem(pos);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         pos=jTable1.getRowCount() -1;
        ShowItem(pos);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jenisTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jenisTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         totalTF.requestFocus();
           }
    }//GEN-LAST:event_jenisTFKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton editBT;
    private javax.swing.JButton hapusBT;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jenisTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JButton tambahBT;
    private javax.swing.JTextField totalTF;
    private javax.swing.JButton xBT;
    // End of variables declaration//GEN-END:variables
}
