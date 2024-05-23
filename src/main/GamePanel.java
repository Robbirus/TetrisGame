package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private final int FPS = 60;
    private Thread gameThread;
    private final PlayManager playManager;

    public static Sound music = new Sound();
    public static Sound se = new Sound();

    public GamePanel(){

        // Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        // Implement KeyListener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        playManager = new PlayManager();

    }

    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();

        music.play(0, true);
        music.loop();
    }

    @Override
    public void run() {

        // Game Loop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update(){

        if(!KeyHandler.isPausePressed() && !playManager.isGameOver()){
            playManager.update();

        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        playManager.draw(g2);
    }


}
