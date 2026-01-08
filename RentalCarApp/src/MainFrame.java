import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {
    private RentalManager manager = new RentalManager();

    public MainFrame() {
        setTitle("Rent Go - Araç Filomuz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 1100, 700); // Pencereyi biraz daha yüksek yaptık
        
        // 1. Ana Panel (Scroll'un içine girecek olan panel)
        // Kartların yan yana ve aşağı doğru dizilmesi için FlowLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 242, 245));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
        
        // Araç Verilerini Tanımlama (Yeni araçları ekledik)
        manager.addCar(new Car("RENAULT", "CLIO", 1299, "Benzin", "Otomatik"));
        manager.addCar(new Car("OPEL", "CORSA", 1399, "Benzin", "Otomatik"));
        manager.addCar(new Car("FIAT", "DOBLO", 1400, "Dizel", "Manuel"));
        manager.addCar(new Car("BMW", "3.20i", 4500, "Benzin", "Otomatik"));
        manager.addCar(new Car("VW", "GOLF", 2200, "Benzin", "Yarı-Oto"));
        manager.addCar(new Car("VW", "PASSAT", 3200, "Dizel", "Otomatik"));

        // Görsel isimlerini sırasıyla diziye ekliyoruz
        String[] carImages = {"clio.png", "corsa.png", "doblo.png", "bmw.png", "golf.png", "volkswagen.png"};
        
        // Araç kartlarını döngü ile panele ekleme
        int i = 0;
        for (Car c : manager.carList) {
            contentPanel.add(createCarCard(c, carImages[i]));
            i++;
        }

        // 2. SCROLL PANE: Aşağı kaydırma özelliğini veren bileşen
        // contentPanel'in boyutu kart sayısına göre otomatik artacak
        // Kart sayın arttığında scrollbar otomatik belirecek
        contentPanel.setPreferredSize(new Dimension(1000, 1000)); // İç panelin yüksekliğini manuel artırdık
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Yumuşak kaydırma
        scrollPane.setBorder(null);
        
        getContentPane().add(scrollPane);
    }

    private JPanel createCarCard(Car car, String imgName) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 420));
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        // Marka/Model
        JLabel lblName = new JLabel(car.brand + " " + car.model);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblName.setBounds(20, 20, 250, 25);
        card.add(lblName);

        // Detaylar
        JLabel lblSpecs = new JLabel(car.fuelType + " | " + car.transmission);
        lblSpecs.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSpecs.setForeground(Color.GRAY);
        lblSpecs.setBounds(20, 45, 200, 20);
        card.add(lblSpecs);

        // Görsel
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/" + imgName));
            Image img = icon.getImage().getScaledInstance(240, 140, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(img));
            lblImg.setBounds(30, 80, 240, 140);
            card.add(lblImg);
        } catch (Exception e) {
            System.err.println(imgName + " yüklenemedi!");
        }

        // Fiyat
        JLabel lblPrice = new JLabel("₺" + car.dailyPrice + " / Gün");
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblPrice.setForeground(new Color(230, 80, 25)); // Turuncu
        lblPrice.setBounds(20, 250, 250, 30);
        card.add(lblPrice);

        // Mavi Buton
        JButton btnRent = new JButton("RENT A CAR (KİRALA)");
        btnRent.setBackground(new Color(41, 54, 110)); // Lacivert
        btnRent.setForeground(Color.WHITE);
        btnRent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRent.setBounds(20, 330, 260, 50);
        btnRent.setFocusPainted(false);
        btnRent.setBorder(null);
        
        btnRent.addActionListener(e -> JOptionPane.showMessageDialog(null, car.brand + " kiralandı!"));
        card.add(btnRent);

        return card;
    }
}