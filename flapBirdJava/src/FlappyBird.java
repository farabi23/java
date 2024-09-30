import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.lang.Math;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int broadWidth = 360;
    int broadHeight = 640;

    //IMages
    Image backGroundImg;
    Image birdImg;
    Image topcolumnImg;
    Image bottomcolumnImg;

    double score = 0;

    //bird
    int birdX = broadWidth/8;
    int birdY = broadHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;

            if(gameOver){
                //restatr by reset condtition
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
                



            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;

        Image img;

        Bird(Image img){
            this.img = img;
        }

    }
    //Pipes
    int pipeX = broadWidth;
    int pipeY = 0;

    int pipeWidth = 64;
    int pipeHeight =512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int heigth = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img){
            this.img = img;
        }
    }


//game logic
    Bird bird;
    int velocityX = -4;// moves pipes to the left
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();


    Timer gameLoop;
    Timer placePipesTimer;

    boolean gameOver = false;


    FlappyBird(){
        setPreferredSize(new Dimension(broadWidth, broadHeight));
        //setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);
        //loadImages
        backGroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topcolumnImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomcolumnImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdImg);

        pipes = new ArrayList<Pipe>();
        // pipes place
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
        placePipesTimer.start();

    }
    public void placePipes(){
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openSpace = broadHeight/4;


        Pipe topPipe = new Pipe(topcolumnImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomcolumnImg);
        bottomPipe.y = topPipe.y + pipeHeight + openSpace;
        pipes.add(bottomPipe);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
     /*       System.out.println("draw");
            if(bird.y < 280){
                System.out.println("reached");

            }else {
                System.out.println("moving");
            }*/
        // background
        g.drawImage(backGroundImg, 0,0,broadWidth, broadHeight, null);
        //bird
        g.drawImage(bird.img, bird.x,bird.y, bird.width,bird.height, null);

        //drawing pipes
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x,pipe.y, pipe.width, pipe.heigth, null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver ){
            g.drawString("Game Over: " + String.valueOf((int) score ), 10, 35);
        }else{
            g.drawString(String.valueOf((int) score), 10, 35 );
        }
    }
    public void move(){
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

        if (!pipe.passed && bird.x > pipe.x + pipe.width){
            pipe.passed = true;
            score += 0.5;
        }

            if(collision(bird, pipe)){
                gameOver = true;
            }

        }

        if(bird.y > broadHeight){
            gameOver = true;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }


    }
    public boolean collision(Bird a, Pipe b){
        return a.x < b.x + b.width &&
         a.x + a.width > b.x &&
         a.y < b.y + b.heigth &&
         a.y + a.height > b.y;
    }
}
