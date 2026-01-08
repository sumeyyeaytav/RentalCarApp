
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    // Giriş kutularını tanımlıyoruz
    private JTextField txtBrand, txtModel, txtPrice, txtFuel, txtTrans, txtImg;

    public AdminFrame() {
        setTitle("Rent Go - Yönetici Paneli");
        setBounds(100, 100, 450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(8, 2, 10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Form alanlarını ekliyoruz
        add(new JLabel(" Marka:"));
        txtBrand = new JTextField(); add(txtBrand);

        add(new JLabel(" Model:"));
        txtModel = new JTextField(); add(txtModel);

        add(new JLabel(" Günlük Fiyat (₺):"));
        txtPrice = new JTextField(); add(txtPrice);

        add(new JLabel(" Yakıt Türü:"));
        txtFuel = new JTextField(); add(txtFuel);

        add(new JLabel(" Vites Tipi:"));
        txtTrans = new JTextField(); add(txtTrans);

        add(new JLabel(" Görsel Dosya Adı (Örn: bmw.png):"));
        txtImg = new JTextField(); add(txtImg);

        JButton btnSave = new JButton("ARACI VERİTABANINA EKLE");
        btnSave.setBackground(new Color(41, 54, 110)); // Lacivert
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(btnSave);

        // Kaydetme butonu olayı
        btnSave.addActionListener(e -> saveCarToDB());
    }

    private void saveCarToDB() {
        // SQL Sorgusu: Yeni araç eklemek için
        String sql = "INSERT INTO cars(brand, model, price, fuel, transmission, imageName) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, txtBrand.getText());
            pstmt.setString(2, txtModel.getText());
            pstmt.setDouble(3, Double.parseDouble(txtPrice.getText()));
            pstmt.setString(4, txtFuel.getText());
            pstmt.setString(5, txtTrans.getText());
            pstmt.setString(6, txtImg.getText());

            pstmt.executeUpdate(); // Veritabanına gönder
            JOptionPane.showMessageDialog(this, "Araç başarıyla kaydedildi!");
            
            // Alanları temizle
            txtBrand.setText(""); txtModel.setText(""); txtPrice.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hata oluştu: " + ex.getMessage());
        }
    }
}
