import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgService {
    JLabel label;
    ImageIcon image;

    public static JLabel loadImage (String path) {
        ImageIcon image = new ImageIcon(path);
        JLabel label = new JLabel(image);
        
        return label;
    }
    public static String getIconPath() {
       return "src\\res\\icon.png";
    }

    public static void updateImage (JLabel label, String path) {
        ImageIcon image = new ImageIcon(path);
        label.setIcon(image);
    }
}