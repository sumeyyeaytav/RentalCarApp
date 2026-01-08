import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {
    public SearchFrame() {
        setTitle("Rent Go - Rezervasyon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 550); // Görseldeki genişliği yakalamak için
        
        // Arka Plan Resmi Paneli
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Yüklediğin dosya adıyla birebir aynı olmalı
                    ImageIcon img = new ImageIcon(getClass().getResource("/Backgroundroad.jpg"));
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
                } catch(Exception e) {
                    System.out.println("Arka plan resmi yüklenemedi!");
                }
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // 1. Üst Slogan (Görseldeki yazı stili)
        JLabel lblHero = new JLabel("<html>Her yolda, her koşulda<br>yanında.</html>");
        lblHero.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblHero.setForeground(Color.WHITE);
        lblHero.setBounds(60, 60, 600, 130);
        bgPanel.add(lblHero);

        JLabel lblSub = new JLabel("Geniş araç filosu ile Rent Go, Türkiye'nin dört bir yanında.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSub.setForeground(Color.WHITE);
        lblSub.setBounds(60, 190, 600, 30);
        bgPanel.add(lblSub);

        // 2. Beyaz Rezervasyon Paneli (Görseldeki alt beyaz kutu)
        JPanel searchBox = new JPanel();
        searchBox.setBackground(new Color(255, 255, 255, 240)); // Hafif şeffaf beyaz
        searchBox.setBounds(60, 300, 880, 140);
        searchBox.setLayout(null);
        bgPanel.add(searchBox);

        // Alış Noktası
        JLabel lblLoc = new JLabel("📍 Alış Noktası");
        lblLoc.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLoc.setBounds(20, 20, 150, 20);
        searchBox.add(lblLoc);

        String[] locations = {"Alış Noktası Seç", "İstanbul Havalimanı", "Ankara Esenboğa", "İzmir Adnan Menderes"};
        JComboBox<String> comboLoc = new JComboBox<>(locations);
        comboLoc.setBounds(20, 45, 250, 40);
        searchBox.add(comboLoc);

        // Alış Tarihi
        JLabel lblPickupDate = new JLabel("📅 Alış Tarihi");
        lblPickupDate.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPickupDate.setBounds(290, 20, 150, 20);
        searchBox.add(lblPickupDate);

        JTextField txtPickup = new JTextField(" 06/01/2026");
        txtPickup.setBounds(290, 45, 130, 40);
        searchBox.add(txtPickup);

        // İade Tarihi
        JLabel lblReturnDate = new JLabel("📅 İade Tarihi");
        lblReturnDate.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblReturnDate.setBounds(440, 20, 150, 20);
        searchBox.add(lblReturnDate);

        JTextField txtReturn = new JTextField(" 07/01/2026");
        txtReturn.setBounds(440, 45, 130, 40);
        searchBox.add(txtReturn);

        // Turuncu "Araçları Keşfet" Butonu (Görseldeki renk: #E65019)
        JButton btnSearch = new JButton("Araçları Keşfet");
        btnSearch.setBackground(new Color(230, 80, 25)); 
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSearch.setBounds(650, 40, 200, 50);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        searchBox.add(btnSearch);

        // Alt taraftaki küçük check-box yazısı
        JCheckBox chkLoc = new JCheckBox("Aracı farklı lokasyona bırakmak istiyorum.");
        chkLoc.setOpaque(false);
        chkLoc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chkLoc.setBounds(20, 95, 300, 25);
        searchBox.add(chkLoc);

        // Buton Olayı: MainFrame'e Geçiş
        btnSearch.addActionListener(e -> {
            if(comboLoc.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Lütfen bir alış noktası seçiniz!");
            } else {
                MainFrame main = new MainFrame();
                main.setVisible(true);
                this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        // Profesyonel görünüm için sistem temasını aktif et
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        new SearchFrame().setVisible(true);
    }
}