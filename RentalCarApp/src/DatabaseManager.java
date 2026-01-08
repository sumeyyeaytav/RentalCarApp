import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    // Veritabanı dosyasının yolu - Proje ana dizinindeki rental.db dosyasına bakar
    private static final String URL = "jdbc:sqlite:rental.db";

    // Veritabanına fiziksel bağlantı kuran yardımcı metot
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Kullanıcı adı ve şifreyi veritabanında sorgular.
     * @return 'admin', 'user' veya hatalı girişte null döner
     */
    public static String loginCheck(String user, String pass) {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("role"); // Veritabanındaki rolü döndürür
            }
        } catch (SQLException e) {
            System.err.println("Giriş sorgusu sırasında hata: " + e.getMessage());
        }
        return null;
    }

    /**
     * Veritabanındaki tüm araçları çeker ve bir liste olarak döndürür.
     * Bu metot MainFrame ekranında araçları listelemek için kullanılır.
     */
    public static ArrayList<Car> getCarsFromDB() {
        ArrayList<Car> list = new ArrayList<>();
        String query = "SELECT * FROM cars";
        
        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                // Veritabanındaki sütun isimlerine göre Car nesnesi oluşturulur
                list.add(new Car(
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getDouble("price"),
                    rs.getString("fuel"),
                    rs.getString("transmission")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Araç listesi çekilirken hata: " + e.getMessage());
        }
        return list;
    }
}