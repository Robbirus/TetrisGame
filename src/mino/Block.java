package mino;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Rectangle {

    private int x;
    private int y;
    private Color color;
    public final static int SIZE = 30;


    public Block(Color color){
        this.color = color;
    }

    public void draw(Graphics2D g2){
        int margin = 2;
        g2.setColor(color);
        g2.fillRect(x+margin, y+margin, SIZE-(margin*2), SIZE-(margin*2));
    }

    public int getXPostion(){
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getYPostion() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
