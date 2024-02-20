import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class RollHotaru extends JFrame{
    public RollHotaru() {
        super("Roll Hotaru");

        ImageIcon icon = new ImageIcon(ImgService.getIconPath());
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        addGuiComponents();
    }

    private void addGuiComponents() {
        // Add components here
        JPanel panel = new JPanel();
        panel.setLayout(null);
        // panel.setBackground(new Color(0x000000));
        
        // 1. banner
        JLabel banner = ImgService.loadImage("src\\res\\hotaru_25.png");
        banner.setBounds(45,25, 400, 100);
        panel.add(banner);

        //2. dice
        JLabel dice = ImgService.loadImage("src\\res\\1.png");
        dice.setBounds(150, 125, 175, 175);
        panel.add(dice);

        //3. roll button
        Random rand = new Random();
        JButton rollButton = new JButton("Roll...");
        rollButton.setBounds(190, 300, 100, 40);
        rollButton.setFocusable(false);
        rollButton.setBackground(new Color(0xffffff));
        rollButton.setBorder(BorderFactory.createLineBorder(new Color(0x000000)));
        rollButton.addActionListener(e -> {
            rollButton.setEnabled(false);
            long startTime = System.currentTimeMillis();
            Thread rollThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long endTime = System.currentTimeMillis();
                    try{
                        while((endTime - startTime)/1000F < 3) {
                            //roll dice
                            int diceRoll = rand.nextInt(6)+1;
                            
                            //update dice image
                            ImgService.updateImage(dice, "src\\res\\"+diceRoll+".png");

                            repaint();
                            revalidate();

                            // sleep thread
                            Thread.sleep(60);

                            endTime = System.currentTimeMillis();
                        }

                        rollButton.setEnabled(true);
                    } catch (InterruptedException e) {
                        System.out.println("Threadding error" + e);
                    }


                }
            });
            rollThread.start();

        });
        panel.add(rollButton);


        //add content to frame
        this.getContentPane().add(panel);

    }
}
