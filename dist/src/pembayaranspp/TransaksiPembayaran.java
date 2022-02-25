/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaranspp;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.I;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA DC
 */
public class TransaksiPembayaran extends javax.swing.JInternalFrame {
    konversi konversiAngka=new konversi();
    byte[] person_image;
    private DefaultTableModel TabModel;
    Connection conn;
    Statement st;
    ResultSet rs;
    /**
     * Creates new form datamurid
     */
    public TransaksiPembayaran() {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
         try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        initComponents();
       combo();
       otomatis();
        siapIsi(false);
    
        tombolNormal();
        tgl();
        btlBT.hide();
        this.person_image=null;
     
        getConnection();
          Object header[]={"nis","nama","jenisbayar","totalbayar","MetodePembayaran"};
        TabModel=new DefaultTableModel(null, header);
      
        
    }
    
    
  
      private void tgl(){
    Date date = new Date();
        tglTF.setDate(date);
}
      
     String Imgpath = null;
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
public void combo(){
    
    
    querybayar qb= new querybayar();
    HashMap<String,Integer>map=qb.combo();
    for(String s : map.keySet()){
        jenisCB.addItem(s);
    }
}
   
    
   
     public boolean checkInputs()
{
    if(
             
      
         noTF.getText()== null
             ||tglTF.getDate()== null
             ||namaTF.getText()== null
            ||tgl1TF.getText()== null
            ||kelasTF.getText()== null
            
      ||jenisCB.getSelectedItem()== null
           
            ||nisTF.getText()== null
            
            || bayarTF.getText()== null
             
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
        
       
     public void ambildata() {
      
         String metode=null;
        if(kasCB.isSelected()){
           metode="Kas";
        }else if(tfCB.isSelected()){
            metode="Transfer";
            
        }
        try {
            jTable1.setModel(TabModel);
                String kolom1 = nisTF.getText();
                String kolom2 = namaTF.getText();
                 String kolom3 = (String) jenisCB.getSelectedItem();
                  String kolom4 = bayarTF.getText();
                  String kolom5 =metode;
             
                String[] kolom = {kolom1, kolom2, kolom3, kolom4,kolom5};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    
    private void bersih(){
        noTF.setText("");
        nisTF.setText("");
        
          namaTF.setText("");
           jenisCB.setSelectedItem("");
             tgl1TF.setText("");
              kelasTF.setText("");
             imageTF.setIcon(null);
          Imgpath =null;
           bayarTF.setText("");
             totalTF.setText("0");
             terbilangTF.setText("");
          
          
         
            
             
        
    }
      private void siapIsi(boolean a){
        noTF.setEnabled(a);
        nisTF.setEnabled(a);
          namaTF.setEnabled(a);
        tglTF.setEnabled(a);
          tgl1TF.setEnabled(a);
            kelasTF.setEnabled(a);
       
         jenisCB.setEnabled(a);
          bayarTF.setEnabled(a);
              totalTF.setEnabled(a);
               totalTF.setEnabled(a);
               terbilangTF.setEnabled(a);
        
       
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
       
      
    }
   

    
    private void otomatis() 
       
    {
       
        try {
            DateFormat vblnth = new SimpleDateFormat("ddMMYYYY");
            String blnth = vblnth.format(Calendar.getInstance().getTime());
            Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            
            String sql="select right (nobayar,2)+1 from transaksipembayaran";
             rs=st.executeQuery(sql);
            
           
           
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    
                    no=blnth+no;
                    noTF.setText("TB"+no);}
                }
            else
            {
                noTF.setText("TB"+blnth); 
            }
            } catch (Exception e) 
            {
        }
    
    }
    private void tulis()
            
    {
          int rubah=Integer.parseInt(totalTF.getText());
      terbilangTF.setText(konversiAngka.angka(rubah)+"Rupiah");
    }
private void simpan()
{  
     setKoneksi();
        try {
              tulis();
            Date skrg=new Date();
            SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
            String tgl=frm.format(skrg); 
            
            int t = jTable1.getRowCount();
            for(int i=0;i<t;i++)    
            {
            String nis=jTable1.getValueAt(i, 0).toString();
            String nama=jTable1.getValueAt(i, 1).toString();
             String jenis=jTable1.getValueAt(i, 2).toString();
               int bayar= Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            String metode=jTable1.getValueAt(i, 4).toString();
         
            String sql ="insert into transaksipembayaran values('"+noTF.getText()+
                    "','"+tgl+"','"+nis+"','"+nama+"','"+tgl1TF.getText()+"','"+kelasTF.getText()+"','"+jenis+"','"+bayar+"','"+totalTF.getText()+"','"+terbilangTF.getText()+"','"+metode+"')";
            
            st.executeUpdate(sql);
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Simpan transaksi gagal");
        }
            
}
private void hapusdatadaritabel() {
        int a = jTable1.getSelectedRow();
        if(a == -1){
        }TabModel.removeRow(a);
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
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nisTF = new javax.swing.JTextField();
        noTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        namaTF = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        imageTF = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        subtotTF = new javax.swing.JTextField();
        tgl1TF = new javax.swing.JTextField();
        kelasTF = new javax.swing.JTextField();
        nTF = new javax.swing.JTextField();
        tglTF = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        bayarTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jenisCB = new javax.swing.JComboBox<>();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btlBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        totalTF = new javax.swing.JTextField();
        simpanBT = new javax.swing.JButton();
        terbilangTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        kasCB = new javax.swing.JRadioButton();
        tfCB = new javax.swing.JRadioButton();

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nama  Siswa       :");

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
        jLabel2.setText("TRANSAKSI PEMBAYARAN");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Kelas  :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("No Pembayaran  :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tanggal  Pembayaran  :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 150, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Nis                      :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 110, 40));

        nisTF.setBorder(null);
        nisTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nisTFActionPerformed(evt);
            }
        });
        nisTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nisTFKeyReleased(evt);
            }
        });
        jPanel3.add(nisTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 410, 40));

        noTF.setBorder(null);
        noTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTFActionPerformed(evt);
            }
        });
        jPanel3.add(noTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 120, 40));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 260, 10));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 280, -1));

        namaTF.setBorder(null);
        namaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTFActionPerformed(evt);
            }
        });
        jPanel3.add(namaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 400, 40));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 520, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 530, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, 240, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Foto Siswa :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 70, 40));
        jPanel3.add(imageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 190, 140));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 230, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("tgl Lahir             :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 120, 40));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 270, 10));
        jPanel3.add(subtotTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, 0));

        tgl1TF.setBorder(null);
        tgl1TF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl1TFActionPerformed(evt);
            }
        });
        jPanel3.add(tgl1TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 160, 40));

        kelasTF.setBorder(null);
        jPanel3.add(kelasTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 180, 40));
        jPanel3.add(nTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 0, -1));
        jPanel3.add(tglTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 130, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 550, 380));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Masukan Inputan Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Jenis Bayar         :");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 40));

        bayarTF.setBorder(null);
        bayarTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarTFActionPerformed(evt);
            }
        });
        bayarTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayarTFKeyTyped(evt);
            }
        });
        jPanel4.add(bayarTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 360, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Bayaran               :");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 40));

        jenisCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--pilih Bayaran--" }));
        jenisCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisCBActionPerformed(evt);
            }
        });
        jPanel4.add(jenisCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 370, 40));
        jPanel4.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 460, 10));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 500, 140));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Tabel Tampung Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 480, 150));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 500, 200));

        btlBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btlBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        btlBT.setText("BATAL BAYARAN");
        btlBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btlBTMouseEntered(evt);
            }
        });
        btlBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlBTActionPerformed(evt);
            }
        });
        jPanel1.add(btlBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 170, 40));

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
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 120, 40));

        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });
        totalTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalTFKeyTyped(evt);
            }
        });
        jPanel1.add(totalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, 350, 50));

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
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 120, 40));
        jPanel1.add(terbilangTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(914, 520, -1, 0));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("TOTAL PEMBAYARAN :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 130, 50));

        kasCB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(kasCB);
        kasCB.setText("KAS");
        kasCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kasCBMouseClicked(evt);
            }
        });
        kasCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasCBActionPerformed(evt);
            }
        });
        jPanel1.add(kasCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 70, -1));

        tfCB.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(tfCB);
        tfCB.setText("TRANSFER");
        tfCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfCBMouseClicked(evt);
            }
        });
        jPanel1.add(tfCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 240, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nisTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nisTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nisTFActionPerformed

    private void noTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTFActionPerformed

    private void nisTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nisTFKeyReleased
        // TODO add your handling code here:
          Connection con = getConnection();
   Statement st;
   ResultSet rs;
           String sql="select * from  datasiswa where nis='"+ nisTF.getText() +"'";
        try {
             st = con.createStatement();
             rs=st.executeQuery(sql);
            if (rs.next())
            {
                String setnis=rs.getString("nis");
                nisTF.setText(setnis);
               String setnama=rs.getString("nama");
                namaTF.setText(setnama); 
                  String settgl=rs.getString("tanggallahir");
                tgl1TF.setText(settgl); 
                   String setkelas=rs.getString("kelas");
                kelasTF.setText(setkelas); 
                      byte[] img=rs.getBytes("image");
               ImageIcon ImageIcon =new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(imageTF.getWidth(),imageTF.getHeight(),Image.SCALE_SMOOTH));
                
               imageTF.setIcon(ImageIcon);
               person_image=img;
               
               
                
              
                
            }
            else {
               
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_nisTFKeyReleased

    private void btlBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlBTActionPerformed
       
         int baris = jTable1.getSelectedRow();
        int bayar=Integer.parseInt(jTable1.getModel().getValueAt(baris, 3).toString());
        int total=Integer.parseInt(totalTF.getText());
        
        int totbay=total-bayar;
        totalTF.setText(Integer.toString(totbay));
        hapusdatadaritabel();
        btlBT.hide();
        
    }//GEN-LAST:event_btlBTActionPerformed

    private void totalTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalTFKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
      int rubah=Integer.parseInt(totalTF.getText());
      terbilangTF.setText(konversiAngka.angka(rubah)+"Rupiah");
        }
    }//GEN-LAST:event_totalTFKeyPressed

    private void namaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTFActionPerformed

    private void jenisCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisCBActionPerformed
        // TODO add your handling code here:
        querybayar qb=new querybayar();
        HashMap<String,Integer>map=qb.combo();
        bayarTF.setText(map.get(jenisCB.getSelectedItem().toString()).toString());
    }//GEN-LAST:event_jenisCBActionPerformed

    private void tgl1TFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl1TFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1TFActionPerformed

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
         if(tambahBT.getText().equalsIgnoreCase("TAMBAH")){
            tambahBT.setText("BERSIH");
            siapIsi(true);
            bersih();
            otomatis();
          nisTF.requestFocus();
            noTF.setEnabled(false);
            simpanBT.setEnabled(true);
           
        }else{
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
            tombolNormal();
        }

    }//GEN-LAST:event_tambahBTActionPerformed

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
       if(nisTF.getText().equals("") || namaTF.getText().equals("") || bayarTF.getText().equals("0")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan");
        } else{
            simpan();
           
            int pesan=JOptionPane.showConfirmDialog(null, "Cetak Bukti Bayaran","Cetak",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                cetak_bukti();
            }else {
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }
            bersih();
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void totalTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalTFKeyReleased
        // TODO add your handling code here:
         if("".equals(totalTF.getText())){
      int rubah=Integer.parseInt(totalTF.getText());
      terbilangTF.setText(konversiAngka.angka(rubah)+"Rupiah");
        }
       
    }//GEN-LAST:event_totalTFKeyReleased

    private void totalTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalTFKeyTyped
       if("".equals(totalTF.getText())){
    tulis();
        }
    }//GEN-LAST:event_totalTFKeyTyped

    private void bayarTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarTFKeyTyped
        // TODO add your handling code here:
        if("".equals(bayarTF.getText())){
             int harga=Integer.parseInt(bayarTF.getText());
        
       
        int total=Integer.parseInt(totalTF.getText());
        
       
            
            int totbay=total+harga;
            totalTF.setText(Integer.toString(totbay));
            
            ambildata();
            
      jenisCB.setSelectedItem("");
        bayarTF.setText("");
      
     
      
        }
        
    }//GEN-LAST:event_bayarTFKeyTyped

    private void bayarTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarTFKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
             int harga=Integer.parseInt(bayarTF.getText());
        int total=Integer.parseInt(totalTF.getText());
        
       
            
            int totbay=total+harga;
            totalTF.setText(Integer.toString(totbay));
            
            ambildata();
            
      jenisCB.setSelectedItem("");
        bayarTF.setText("");
       
     
      
         
        }
    }//GEN-LAST:event_bayarTFKeyReleased

    private void bayarTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarTFActionPerformed

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTFActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jTable1.setRowSelectionAllowed(true);
        int a = jTable1.getSelectedRow();
        String kolom1 = jTable1.getValueAt(a,0).toString();
        String kolom2 = jTable1.getValueAt(a,1).toString();
        String kolom3 = jTable1.getValueAt(a,2).toString();
        String kolom4 = jTable1.getValueAt(a,3).toString();
        btlBT.show();
        
    }//GEN-LAST:event_jTable1MouseClicked

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

    private void btlBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btlBTMouseEntered
        // TODO add your handling code here:
       btlBT.setBackground(new Color(0,153,153));
      btlBT.setForeground(Color.red);
    }//GEN-LAST:event_btlBTMouseEntered

    private void kasCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kasCBMouseClicked
        // TODO add your handling code here:
        int harga=Integer.parseInt(bayarTF.getText());
        int total=Integer.parseInt(totalTF.getText());
        
       
            
            int totbay=total+harga;
            totalTF.setText(Integer.toString(totbay));
            
            ambildata();
            
      jenisCB.setSelectedItem("");
        bayarTF.setText("");
    }//GEN-LAST:event_kasCBMouseClicked

    private void tfCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCBMouseClicked
        // TODO add your handling code here:
         int harga=Integer.parseInt(bayarTF.getText());
        int total=Integer.parseInt(totalTF.getText());
        
       
            
            int totbay=total+harga;
            totalTF.setText(Integer.toString(totbay));
            
            ambildata();
            
      jenisCB.setSelectedItem("");
        bayarTF.setText("");
    }//GEN-LAST:event_tfCBMouseClicked

    private void kasCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasCBActionPerformed
        // TODO add your handling code here:
         String metode=null;
        if(kasCB.isSelected()){
           metode="";
        }else if(tfCB.isSelected()){
            metode="";
            
        }
    }//GEN-LAST:event_kasCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayarTF;
    private javax.swing.JButton btlBT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel imageTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jenisCB;
    private javax.swing.JRadioButton kasCB;
    private javax.swing.JTextField kelasTF;
    private javax.swing.JTextField nTF;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField nisTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JTextField subtotTF;
    private javax.swing.JButton tambahBT;
    private javax.swing.JTextField terbilangTF;
    private javax.swing.JRadioButton tfCB;
    private javax.swing.JTextField tgl1TF;
    private com.toedter.calendar.JDateChooser tglTF;
    private javax.swing.JTextField totalTF;
    // End of variables declaration//GEN-END:variables

    public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            st=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }
    
    void cetak_bukti(){
        try {
            String NamaFile = "src/Laporan/bukti.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            HashMap param = new HashMap();
            param.put("bukti",noTF.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
