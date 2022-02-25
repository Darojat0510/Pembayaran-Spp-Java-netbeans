/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA DC
 */
public class laporanmurid extends javax.swing.JInternalFrame {

    /**
     * Creates new form datamurid
     */
    public laporanmurid() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        initComponents();
        kelas();
        getConnection();
        
    }
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
   Logger.getLogger(laporanmurid.class.getName()).log(Level.SEVERE, null, ex);
   }
   return list;
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
        jTable1.setModel(model);
        jTable1.setRowHeight(120);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(150);
    }

    public void berdasarkankelas(){
         Object header[]={"nis","nama","tempatlahir","tanggallahir","kelas","agama","gender","status","alamat"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
         Connection con = getConnection();
                Statement st;
                ResultSet rs;
                try {
                st = con.createStatement();
        String sql="Select * from datasiswa where kelas like '%" + kelasCB.getSelectedItem() + "%'" + "or kelas like '%" + kelasCB.getSelectedItem()+ "%'";
        
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
               
                 
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
                
               
    }
    
     void cetak_semua(){
        try {
            String NamaFile = "src/Laporan/murid.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            HashMap param = new HashMap();
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void cetak_satuan(){
         try {
            String NamaFile = "src/laporan/murid1.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("kelas",kelasCB.getSelectedItem());
                   
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        kelasCB = new javax.swing.JComboBox<>();
        satu = new javax.swing.JRadioButton();
        semua = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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
        jLabel2.setText("LAPORAN MURID");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Filter Laporan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kelasCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kelasCBActionPerformed(evt);
            }
        });
        jPanel3.add(kelasCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 180, 40));

        satu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(satu);
        satu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        satu.setText("CETAK BERDASARKAN KELAS");
        satu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                satuMouseClicked(evt);
            }
        });
        jPanel3.add(satu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        semua.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(semua);
        semua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        semua.setText("CETAK SEMUA");
        semua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                semuaMouseClicked(evt);
            }
        });
        jPanel3.add(semua, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 190, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print.png"))); // NOI18N
        jButton1.setText("CETAK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 170, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 450, 170));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Table Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 910, 210));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 930, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if(satu.isSelected()){
            cetak_satuan();
        } else if(semua.isSelected()){
            cetak_semua();
        } else{
            JOptionPane.showMessageDialog(null, "Pilih kriteria cetak kelas");
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kelasCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelasCBActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_kelasCBActionPerformed

    private void semuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_semuaMouseClicked
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_semuaMouseClicked

    private void satuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_satuMouseClicked
        // TODO add your handling code here:
        berdasarkankelas();
    }//GEN-LAST:event_satuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> kelasCB;
    private javax.swing.JRadioButton satu;
    private javax.swing.JRadioButton semua;
    // End of variables declaration//GEN-END:variables

}
