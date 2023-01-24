package Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EndMenu{

    JFrame frame = new JFrame();
    JButton quitbutton, restartbutton;
    BufferedImage img;
    Image restartimg;
    Image quitimg;

    public EndMenu(boolean didWin){
        try {
            File file;
            if(didWin) {
                file = new File("resources/icons/won_art.png");
            }else{
                file = new File("resources/icons/lose_art.png");
            }
            img = ImageIO.read(file);
            file = new File("resources/icons/restart.png");
            BufferedImage btnimgB = ImageIO.read(file);
            restartimg = btnimgB.getScaledInstance(btnimgB.getWidth()*5, btnimgB.getHeight()*5, Image.SCALE_DEFAULT);
            restartbutton = new JButton(new ImageIcon(restartimg));
            file = new File("resources/icons/quit.png");
            btnimgB = ImageIO.read(file);
            quitimg = btnimgB.getScaledInstance(btnimgB.getWidth()*5, btnimgB.getHeight()*5, Image.SCALE_DEFAULT);
            quitbutton = new JButton(new ImageIcon(restartimg));

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

        // Add quit button
        quitbutton.setBounds(53*5, 105*5, 450, 29*5);
        quitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
        quitbutton.setFocusPainted(false);
        quitbutton.setBorderPainted(false);
        quitbutton.setContentAreaFilled(false);

        // Add restart button
        restartbutton.setBounds(53*5, 65*5, 450, 29*5);
        restartbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                Game game = Game.getGame();

                game.restartGame();
            }
        });
        restartbutton.setFocusPainted(false);
        restartbutton.setBorderPainted(false);
        restartbutton.setContentAreaFilled(false);

        frame.add(restartbutton);
        frame.getContentPane().add(restartbutton);
        frame.add(quitbutton);
        frame.getContentPane().add(quitbutton);
        frame.setLayout(null);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
