
public class Main {
    public static void main(String[] args) {
        // 1. Veritabanı dosyasının varlığını ve tabloları kontrol et
        // (Opsiyonel: DatabaseManager içinde bir setup metodu varsa buraya eklenebilir)
        
        // 2. Uygulamayı en baştan, yani Giriş Ekranından başlatıyoruz
        // Bu komut LoginFrame sınıfındaki constructor'ı çalıştırır
        LoginFrame login = new LoginFrame();
        
        // 3. Pencereyi görünür yap
        login.setVisible(true);
    }
}