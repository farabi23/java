import javax.swing.*;
import java.awt.*;


public class App {
    public static void main(String[] args)throws Exception{
        int broadWidth = 360;
        int broadHeight = 640;
        
        int centerx = 500;
        int centery = 100;

        JFrame frame = new JFrame("Flappy Bird");

        frame.setLocation(centerx,centery);
        frame.setSize(broadWidth, broadHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        FlappyBird flappyBird = new FlappyBird();

        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);


    }
}
