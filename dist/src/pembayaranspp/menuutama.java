package pembayaranspp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA DC
 */
public class menuutama extends javax.swing.JFrame {
 String nama = login.getU_nama();
     int banyakBarang = 0;
     static String a,b,c;

     Connection conn;
    Statement st;
    ResultSet rs;
    /**
     * Creates new form menuutama
     */
    public menuutama() {
       
        initComponents();
        setVisible(false);
        setResizable(false);
         userJL.setText(nama);
        jamtanggal();
        banyakguru();
        banyakmurid();
         totalbayaran();
         jam();
      setIcon();
       ActionListener acl =new ActionListener(){
         @Override
     public void actionPerformed(ActionEvent e){
         
         a=jalanTF.getText();
         b=a.substring(0, 1);
         c=a.substring(1, a.length());
         jalanTF.setText(c+b);
     }
     };new javax.swing.Timer(200,acl).start();

        
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
    

     private void setIcon() {
          ImageIcon img = new ImageIcon("src/gambar/pembelian.png");
        setIconImage(img.getImage());
    }

     public void banyakmurid() {
       

        try {
            Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            String sql = "SELECT count(nama) AS nm FROM datasiswa;";
          rs=st.executeQuery(sql);
             rs = st.executeQuery(sql); 

            rs.next();

            muridTF.setText(String.valueOf(rs.getString(1)));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void banyakguru() {
       

        try {
            Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            String sql = "SELECT count(nama) AS nm FROM dataguru;";
          rs=st.executeQuery(sql);
             rs = st.executeQuery(sql); 

            rs.next();

           guruTF.setText(String.valueOf(rs.getString(1)));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void hakAksesTu(){
          homeBT.setEnabled(false);
          pBT.setEnabled(false);
        totalTF.setText("");
         datapembayaran.hide();
          transaksipembayaran.hide();
          
           pengguna.hide();
           lppembayaran.hide();
           dt.hide();
 
        
    }
     
    
    public void hakAksesKeuangan(){
        
           homeBT.setEnabled(false);
         lpmurid.hide();
         lpwalimurid.hide();
        lpguru.hide();
        walimurid.hide();
       guru.hide();
      tambahdatakelas.hide();
      pengguna.hide();
      lpmurid.hide();
      lpguru.hide();
     lpwalimurid.hide();
     dpBT.setEnabled(false);
    
   

    }
    public void hakAksesKepsek(){
        
         homeBT.setEnabled(false);
         dpBT.setEnabled(false);
         pBT.setEnabled(false);
         datapembayaran.hide();
         transaksipembayaran.hide();
         lpmurid.hide();
         lpguru.hide();
        lpwalimurid.hide();
        lppembayaran.hide();
        murid.hide();
        walimurid.hide();
      tambahdatakelas.hide();
     
      lp.hide();
      dt.hide();
      muridBT.setEnabled(false);
      guru.hide();

    }
    private void jamtanggal() {
           DateFormat tgl=new SimpleDateFormat("dd MMMM yyyy");
        DateFormat waktu=new SimpleDateFormat("HH:mm:ss");
        String htgl=tgl.format(Calendar.getInstance().getTime());
        String hwaktu=waktu.format(Calendar.getInstance().getTime());
        loginJL.setText(htgl); 
     }
  
   public void totalbayaran() {
        try {
              Connection con = getConnection();
   Statement st;
   ResultSet rs;
   st = con.createStatement();
            String sql = "SELECT SUM(total) AS totalPendapatan FROM transaksipembayaran;";
            rs=st.executeQuery(sql);
             rs = st.executeQuery(sql); 

            rs.next();

            totalTF.setText(String.valueOf(rs.getString(1)));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
  public void jam(){
        ActionListener taskPerformer = new ActionListener() {
            
    public void actionPerformed(ActionEvent evt) {
        String nol_jam = "", nol_menit = "",nol_detik = "";
        java.util.Date dateTime = new java.util.Date();
        int nilai_jam = dateTime.getHours();
        int nilai_menit = dateTime.getMinutes();
        int nilai_detik = dateTime.getSeconds();
        if(nilai_jam <= 9) nol_jam= "0";
        if(nilai_menit <= 9) nol_menit= "0";
        if(nilai_detik <= 9) nol_detik= "0";
        String waktu = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);
        jam.setText(waktu+":"+menit+":"+detik+"");
    }
        };
        new Timer(1000, taskPerformer).start();
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
        dp = new javax.swing.JDesktopPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        muridTF = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        guruTF = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totalTF = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        p = new javax.swing.JPanel();
        jalanTF = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        loginJL = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        userJL = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        homeBT = new javax.swing.JButton();
        dpBT = new javax.swing.JButton();
        lBT = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        pBT = new javax.swing.JButton();
        muridBT = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        murid = new javax.swing.JMenuItem();
        guru = new javax.swing.JMenuItem();
        walimurid = new javax.swing.JMenuItem();
        pengguna = new javax.swing.JMenuItem();
        tambahdatakelas = new javax.swing.JMenuItem();
        dt = new javax.swing.JMenu();
        datapembayaran = new javax.swing.JMenuItem();
        transaksipembayaran = new javax.swing.JMenuItem();
        lp = new javax.swing.JMenu();
        lpmurid = new javax.swing.JMenuItem();
        lpwalimurid = new javax.swing.JMenuItem();
        lpguru = new javax.swing.JMenuItem();
        lppembayaran = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("JUMLAH MURID");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 152, -1));

        muridTF.setBackground(new java.awt.Color(255, 102, 102));
        muridTF.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        muridTF.setForeground(new java.awt.Color(255, 255, 255));
        muridTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        muridTF.setText("0");
        muridTF.setBorder(null);
        muridTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muridTFActionPerformed(evt);
            }
        });
        jPanel5.add(muridTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 180, 50));
        jPanel5.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, 10));

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 250, 130));

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("JUMLAH GURU");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 152, -1));

        guruTF.setBackground(new java.awt.Color(0, 204, 204));
        guruTF.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        guruTF.setForeground(new java.awt.Color(255, 255, 255));
        guruTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        guruTF.setText("0");
        guruTF.setBorder(null);
        guruTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guruTFActionPerformed(evt);
            }
        });
        jPanel6.add(guruTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 180, 50));
        jPanel6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, 10));

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 250, 130));

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("JUMLAH GURU");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TOTAL PENDAPATAN");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        totalTF.setBackground(new java.awt.Color(102, 102, 255));
        totalTF.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        totalTF.setForeground(new java.awt.Color(255, 255, 255));
        totalTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        totalTF.setText("0");
        totalTF.setBorder(null);
        totalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTFActionPerformed(evt);
            }
        });
        jPanel4.add(totalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 170, 50));
        jPanel4.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, 10));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rp.");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 60));

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 250, 130));

        p.setBackground(new java.awt.Color(255, 255, 255));
        p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jalanTF.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jalanTF.setText("  WELCOME  TO APLIKASI PEMBAYARAN SEKOLAH CITA-CITA");
        p.add(jalanTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 750, 40));

        jPanel7.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 1110, 60));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("WELCOME   :");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 120, -1));

        dp.add(jPanel7);
        jPanel7.setBounds(0, 0, 1110, 570);

        jPanel1.add(dp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 1110, 570));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("APLIKASI PEMBAYARAN SEKOLAH");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SEKOLAH CITA-CITA");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 200, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TANGGAL    :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 120, 30));

        loginJL.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        loginJL.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(loginJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 160, 30));

        jam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jam.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, 100, 30));

        userJL.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        userJL.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(userJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 280, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("WELCOME   :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 120, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("JAM:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1110, 80));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user (2).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 130));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 10));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 10));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 190, 10));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 220, 10));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("JAKARTA SELATAN");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SEKOLAH CITA-CITA");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        homeBT.setBackground(new java.awt.Color(255, 255, 255));
        homeBT.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        homeBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home-icon.png"))); // NOI18N
        homeBT.setText("HOME");
        homeBT.setContentAreaFilled(false);
        homeBT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        homeBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeBTMouseEntered(evt);
            }
        });
        homeBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBTActionPerformed(evt);
            }
        });
        jPanel2.add(homeBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 220, 60));

        dpBT.setBackground(new java.awt.Color(255, 255, 255));
        dpBT.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        dpBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Pelanggan.png"))); // NOI18N
        dpBT.setText("DATA GURU");
        dpBT.setContentAreaFilled(false);
        dpBT.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        dpBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dpBTMouseEntered(evt);
            }
        });
        dpBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpBTActionPerformed(evt);
            }
        });
        jPanel2.add(dpBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 220, 60));

        lBT.setBackground(new java.awt.Color(255, 255, 255));
        lBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/1300031354_cancel.png"))); // NOI18N
        lBT.setText("LOGOUT");
        lBT.setContentAreaFilled(false);
        lBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBTMouseEntered(evt);
            }
        });
        lBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lBTActionPerformed(evt);
            }
        });
        jPanel2.add(lBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 220, 60));
        jPanel2.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 220, 10));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, 10));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 220, 10));
        jPanel2.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 220, 10));
        jPanel2.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 220, 10));

        pBT.setBackground(new java.awt.Color(255, 255, 255));
        pBT.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/pembelian.png"))); // NOI18N
        pBT.setText("PEMBAYARAN");
        pBT.setContentAreaFilled(false);
        pBT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pBTMouseEntered(evt);
            }
        });
        pBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pBTActionPerformed(evt);
            }
        });
        jPanel2.add(pBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 220, 60));

        muridBT.setBackground(new java.awt.Color(255, 255, 255));
        muridBT.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        muridBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Pelanggan.png"))); // NOI18N
        muridBT.setText("DATA MURID");
        muridBT.setBorderPainted(false);
        muridBT.setContentAreaFilled(false);
        muridBT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        muridBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                muridBTMouseEntered(evt);
            }
        });
        muridBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muridBTActionPerformed(evt);
            }
        });
        jPanel2.add(muridBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 220, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 650));

        jMenu1.setText("File");

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Data Master");

        murid.setText("Data Murid");
        murid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                muridMouseEntered(evt);
            }
        });
        murid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muridActionPerformed(evt);
            }
        });
        jMenu2.add(murid);

        guru.setText("Data Guru");
        guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guruMouseEntered(evt);
            }
        });
        guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guruActionPerformed(evt);
            }
        });
        jMenu2.add(guru);

        walimurid.setText("Data Wali Murid");
        walimurid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                walimuridMouseEntered(evt);
            }
        });
        walimurid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                walimuridActionPerformed(evt);
            }
        });
        jMenu2.add(walimurid);

        pengguna.setText("Pengguna");
        pengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                penggunaMouseEntered(evt);
            }
        });
        pengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunaActionPerformed(evt);
            }
        });
        jMenu2.add(pengguna);

        tambahdatakelas.setText("Tambah Data Kelas");
        tambahdatakelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahdatakelasMouseEntered(evt);
            }
        });
        tambahdatakelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahdatakelasActionPerformed(evt);
            }
        });
        jMenu2.add(tambahdatakelas);

        jMenuBar1.add(jMenu2);

        dt.setText("Data Transaksi");

        datapembayaran.setText("Data Pembayaran");
        datapembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                datapembayaranMouseEntered(evt);
            }
        });
        datapembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datapembayaranActionPerformed(evt);
            }
        });
        dt.add(datapembayaran);

        transaksipembayaran.setText("Transaksi Pembayaran");
        transaksipembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transaksipembayaranMouseEntered(evt);
            }
        });
        transaksipembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksipembayaranActionPerformed(evt);
            }
        });
        dt.add(transaksipembayaran);

        jMenuBar1.add(dt);

        lp.setText("Laporan");

        lpmurid.setText("Laporan Murid");
        lpmurid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lpmuridMouseEntered(evt);
            }
        });
        lpmurid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpmuridActionPerformed(evt);
            }
        });
        lp.add(lpmurid);

        lpwalimurid.setText("Laporan Wali Murid");
        lpwalimurid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lpwalimuridMouseEntered(evt);
            }
        });
        lpwalimurid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpwalimuridActionPerformed(evt);
            }
        });
        lp.add(lpwalimurid);

        lpguru.setText("Laporan Guru");
        lpguru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lpguruMouseEntered(evt);
            }
        });
        lpguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpguruActionPerformed(evt);
            }
        });
        lp.add(lpguru);

        lppembayaran.setText("Laporan Pembayaran");
        lppembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lppembayaranMouseEntered(evt);
            }
        });
        lppembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lppembayaranActionPerformed(evt);
            }
        });
        lp.add(lppembayaran);

        jMenuBar1.add(lp);

        jMenu5.setText("Peraturan");

        jMenuItem9.setText("Ganti Password");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseEntered(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setText("Help");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseEntered(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("About");

        jMenuItem11.setText("Tentang Aplikasi");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseEntered(evt);
            }
        });
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void muridBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muridBTActionPerformed
        // TODO add your handling code here:
         datamurid p= new datamurid();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_muridBTActionPerformed

    private void totalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTFActionPerformed

    private void guruTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guruTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guruTFActionPerformed

    private void muridTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muridTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_muridTFActionPerformed

    private void dpBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpBTActionPerformed
        // TODO add your handling code here:
         dataguru p= new dataguru();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_dpBTActionPerformed

    private void pBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pBTActionPerformed
        // TODO add your handling code here:
          TransaksiPembayaran p= new TransaksiPembayaran();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_pBTActionPerformed

    private void lBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lBTActionPerformed
        // TODO add your handling code here:
         login h=new login();
        h.setLocationRelativeTo(null);
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_lBTActionPerformed

    private void homeBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBTMouseEntered
        // TODO add your handling code here:
        homeBT.setBackground(new Color( 0,153,153));
        homeBT.setForeground(Color.red);
       
    }//GEN-LAST:event_homeBTMouseEntered

    private void muridBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muridBTMouseEntered
        // TODO add your handling code here:
         muridBT.setBackground(new Color( 0,153,153));
        muridBT.setForeground(Color.red);
    }//GEN-LAST:event_muridBTMouseEntered

    private void dpBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dpBTMouseEntered
        // TODO add your handling code here:
         dpBT.setBackground(new Color( 0,153,153));
        dpBT.setForeground(Color.red);
    }//GEN-LAST:event_dpBTMouseEntered

    private void pBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBTMouseEntered
        // TODO add your handling code here:
         pBT.setBackground(new Color( 0,153,153));
        pBT.setForeground(Color.red);
    }//GEN-LAST:event_pBTMouseEntered

    private void lBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lBTMouseEntered
        // TODO add your handling code here:
         lBT.setBackground(new Color( 0,153,153));
        lBT.setForeground(Color.red);
    }//GEN-LAST:event_lBTMouseEntered

    private void muridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muridActionPerformed
        // TODO add your handling code here:
        datamurid p= new datamurid();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_muridActionPerformed

    private void homeBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBTActionPerformed
        // TODO add your handling code here:
         menuutama a = new menuutama();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_homeBTActionPerformed

    private void walimuridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walimuridActionPerformed
        // TODO add your handling code here:
        datawalimurid p= new datawalimurid();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_walimuridActionPerformed

    private void datapembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datapembayaranActionPerformed
        // TODO add your handling code here:
       tambahdatabayaransekolah p= new tambahdatabayaransekolah();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_datapembayaranActionPerformed

    private void transaksipembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksipembayaranActionPerformed
        // TODO add your handling code here:
        TransaksiPembayaran p= new  TransaksiPembayaran();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_transaksipembayaranActionPerformed

    private void lpmuridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpmuridActionPerformed
        // TODO add your handling code here:
        laporanmurid p= new laporanmurid();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_lpmuridActionPerformed

    private void lpwalimuridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpwalimuridActionPerformed
        // TODO add your handling code here:
       try {
            String NamaFile = "src/laporan/walimurid.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            HashMap param = new HashMap();
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_lpwalimuridActionPerformed

    private void lppembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lppembayaranActionPerformed
        // TODO add your handling code here:
       laporanpembayaran p= new laporanpembayaran();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_lppembayaranActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        gantipassword p= new gantipassword();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        help p= new help();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        tentangaplikasi p= new tentangaplikasi();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
          login h=new login();
        h.setLocationRelativeTo(null);
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guruActionPerformed
        // TODO add your handling code here:
        dataguru dg= new dataguru();
       dp.add(dg);
       admin d = new admin();
       dp.remove(d);
        dg.setVisible(true);
    }//GEN-LAST:event_guruActionPerformed

    private void tambahdatakelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahdatakelasActionPerformed
        // TODO add your handling code here:
       kelas k= new kelas();
       dp.add(k);
       admin d = new admin();
       dp.remove(d);
        k.setVisible(true);
    }//GEN-LAST:event_tambahdatakelasActionPerformed

    private void penggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunaActionPerformed
        // TODO add your handling code here:
        pengguna p= new pengguna();
       dp.add(p);
       admin d = new admin();
       dp.remove(d);
        p.setVisible(true);
    }//GEN-LAST:event_penggunaActionPerformed

    private void lpguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpguruActionPerformed
        // TODO add your handling code here:
         try {
            String NamaFile = "src/laporan/guru.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pembayaranspp","root","");
            HashMap param = new HashMap();
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_lpguruActionPerformed

    private void muridMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muridMouseEntered
        // TODO add your handling code here:
        murid.setBackground(new Color( 0,153,153));
           murid.setForeground(Color.red);
    }//GEN-LAST:event_muridMouseEntered

    private void guruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guruMouseEntered
        // TODO add your handling code here:
          guru.setBackground(new Color( 0,153,153));
           guru.setForeground(Color.red);
    }//GEN-LAST:event_guruMouseEntered

    private void walimuridMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_walimuridMouseEntered
        // TODO add your handling code here:
          walimurid.setBackground(new Color( 0,153,153));
           walimurid.setForeground(Color.red);
    }//GEN-LAST:event_walimuridMouseEntered

    private void penggunaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaMouseEntered
        // TODO add your handling code here:
         pengguna.setBackground(new Color( 0,153,153));
           pengguna.setForeground(Color.red);
    }//GEN-LAST:event_penggunaMouseEntered

    private void tambahdatakelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahdatakelasMouseEntered
        // TODO add your handling code here:
         tambahdatakelas.setBackground(new Color( 0,153,153));
           tambahdatakelas.setForeground(Color.red);
    }//GEN-LAST:event_tambahdatakelasMouseEntered

    private void datapembayaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datapembayaranMouseEntered
        // TODO add your handling code here:
          datapembayaran.setBackground(new Color( 0,153,153));
           datapembayaran.setForeground(Color.red);
    }//GEN-LAST:event_datapembayaranMouseEntered

    private void transaksipembayaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksipembayaranMouseEntered
        // TODO add your handling code here:
         transaksipembayaran.setBackground(new Color( 0,153,153));
          transaksipembayaran.setForeground(Color.red);
    }//GEN-LAST:event_transaksipembayaranMouseEntered

    private void lpmuridMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpmuridMouseEntered
        // TODO add your handling code here:
      lpmurid.setBackground(new Color( 0,153,153));
          lpmurid.setForeground(Color.red);
    }//GEN-LAST:event_lpmuridMouseEntered

    private void lpwalimuridMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpwalimuridMouseEntered
        // TODO add your handling code here:
         lpwalimurid.setBackground(new Color( 0,153,153));
          lpwalimurid.setForeground(Color.red);
    }//GEN-LAST:event_lpwalimuridMouseEntered

    private void lpguruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpguruMouseEntered
        // TODO add your handling code here:
       lpguru.setBackground(new Color( 0,153,153));
          lpguru.setForeground(Color.red);
    }//GEN-LAST:event_lpguruMouseEntered

    private void lppembayaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lppembayaranMouseEntered
        // TODO add your handling code here:
          lppembayaran.setBackground(new Color( 0,153,153));
         lppembayaran.setForeground(Color.red);
    }//GEN-LAST:event_lppembayaranMouseEntered

    private void jMenuItem9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseEntered
        // TODO add your handling code here:
        jMenuItem9.setBackground(new Color( 0,153,153));
        jMenuItem9.setForeground(Color.red);
    }//GEN-LAST:event_jMenuItem9MouseEntered

    private void jMenuItem10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseEntered
        // TODO add your handling code here:
        jMenuItem10.setBackground(new Color( 0,153,153));
       jMenuItem10.setForeground(Color.red);
    }//GEN-LAST:event_jMenuItem10MouseEntered

    private void jMenuItem11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseEntered
        // TODO add your handling code here:
        jMenuItem11.setBackground(new Color( 0,153,153));
       jMenuItem11.setForeground(Color.red);
    }//GEN-LAST:event_jMenuItem11MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuutama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuutama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuutama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuutama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuutama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem datapembayaran;
    private javax.swing.JDesktopPane dp;
    private javax.swing.JButton dpBT;
    private javax.swing.JMenu dt;
    private javax.swing.JMenuItem guru;
    private javax.swing.JTextField guruTF;
    private javax.swing.JButton homeBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel jalanTF;
    private javax.swing.JLabel jam;
    private javax.swing.JButton lBT;
    private javax.swing.JLabel loginJL;
    private javax.swing.JMenu lp;
    private javax.swing.JMenuItem lpguru;
    private javax.swing.JMenuItem lpmurid;
    private javax.swing.JMenuItem lppembayaran;
    private javax.swing.JMenuItem lpwalimurid;
    private javax.swing.JMenuItem murid;
    private javax.swing.JButton muridBT;
    private javax.swing.JTextField muridTF;
    private javax.swing.JPanel p;
    private javax.swing.JButton pBT;
    private javax.swing.JMenuItem pengguna;
    private javax.swing.JMenuItem tambahdatakelas;
    private javax.swing.JTextField totalTF;
    private javax.swing.JMenuItem transaksipembayaran;
    private javax.swing.JLabel userJL;
    private javax.swing.JMenuItem walimurid;
    // End of variables declaration//GEN-END:variables

}
