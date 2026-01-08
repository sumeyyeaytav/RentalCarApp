import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    // Giriş alanlarını sınıf düzeyinde tanımlıyoruz ki her yerden erişelim
    private JTextField txtUser;
    private JPasswordField txtPass;

    public LoginFrame() {
        // Pencere Ayarları
        setTitle("Rent Go - Giriş Paneli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        setLocationRelativeTo(null); // Ekranın tam ortasında açar
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Şık Üst Panel (Header)
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 54, 110)); // Profesyonel Lacivert
        header.setBounds(0, 0, 450, 70);
        header.setLayout(null);
        add(header);

        JLabel lblTitle = new JLabel("MÜŞTERİ & ADMİN GİRİŞİ");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBounds(110, 20, 250, 30);
        header.add(lblTitle);

        // Kullanıcı Adı Alanı
        JLabel lblUser = new JLabel("Kullanıcı Adı:");
        lblUser.setBounds(75, 100, 100, 20);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(75, 125, 300, 40);
        add(txtUser);

        // Şifre Alanı
        JLabel lblPass = new JLabel("Şifre:");
        lblPass.setBounds(75, 180, 100, 20);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(75, 205, 300, 40);
        add(txtPass);

        // Giriş Butonu
        JButton btnLogin = new JButton("SİSTEME GİRİŞ YAP");
        btnLogin.setBackground(new Color(230, 80, 25)); // Turuncu
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBounds(75, 280, 300, 50);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        // --- BUTON TIKLAMA OLAYI ---
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            // Veritabanı sorgusu başlatılıyor
            String role = DatabaseManager.loginCheck(user, pass);

            if (role != null) {
                if (role.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Admin Paneline Gidiliyor...");
                    new AdminFrame().setVisible(true); // Admin ekranını açar
                } else {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı!");
                    new SearchFrame().setVisible(true); // Müşteri ekranını açar
                }
                this.dispose(); // Giriş ekranını kapatır
            } else {
                JOptionPane.showMessageDialog(null, "Hatalı Kullanıcı Adı veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
