package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    private static boolean upPressed;
    private static boolean downPressed;
    private static boolean leftPressed;
    private static boolean rightPressed;
    private static boolean pausePressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            upPressed = true;
        }

        if(code == KeyEvent.VK_S){
            downPressed = true;
        }

        if(code == KeyEvent.VK_Q){
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SPACE){
            if(pausePressed){
                pausePressed = false;
                GamePanel.music.play(0, true);
                GamePanel.music.loop();

            } else {
                pausePressed = true;
                GamePanel.music.stop();

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static boolean isDownPressed() {
        return downPressed;
    }

    public static void setDownPressed(boolean downPressed) {
        KeyHandler.downPressed = downPressed;
    }

    public static boolean isUpPressed() {
        return upPressed;
    }

    public static void setUpPressed(boolean upPressed) {
        KeyHandler.upPressed = upPressed;
    }

    public static boolean isLeftPressed() {
        return leftPressed;
    }

    public static void setLeftPressed(boolean leftPressed) {
        KeyHandler.leftPressed = leftPressed;
    }

    public static boolean isRightPressed() {
        return rightPressed;
    }

    public static void setRightPressed(boolean rightPressed) {
        KeyHandler.rightPressed = rightPressed;
    }

    public static boolean isPausePressed() {
        return pausePressed;
    }

    public static void setPausePressed(boolean pausePressed) {
        KeyHandler.pausePressed = pausePressed;
    }
}
