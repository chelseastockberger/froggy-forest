package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
STARTMENU
What appears when you load up the game.
 */

public class StartMenu{

    JFrame frame = new JFrame();
    JButton button;
    BufferedImage img;
    Image btnimg;

    public StartMenu(){
        try {
            File file = new File("resources/icons/start_art.png");
            img = ImageIO.read(file);
            file = new File("resources/icons/button.png");
            BufferedImage btnimgB = ImageIO.read(file);

            btnimg = btnimgB.getScaledInstance(btnimgB.getWidth()*5, btnimgB.getHeight()*5, Image.SCALE_DEFAULT);
            button = new JButton(new ImageIcon(btnimg));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw()
    {

        // Making the frame
        Image img_ = img.getScaledInstance(img.getWidth()*5, img.getHeight()*5, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(img_));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(label);
        frame.pack();

        // Add button
        button.setBounds(55*5, 116*5, 450, 215);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        frame.add(button);
        frame.getContentPane().add(button);
        frame.setLayout(null);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
