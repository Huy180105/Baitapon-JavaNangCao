import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import view.MainMenu; // Import lớp Menu vừa tạo

public class Main {
    public static void main(String[] args) {
        // 1. Thiết lập giao diện giống Windows/MacOS cho đẹp
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Khởi chạy Menu chính
        SwingUtilities.invokeLater(() -> {
            try {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            } catch (Exception e) {
                System.out.println("Lỗi khởi chạy: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}