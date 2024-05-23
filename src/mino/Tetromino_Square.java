package mino;

import java.awt.*;

public class Tetromino_Square extends Mino {

    private final Block[] b = getBlock();
    private final Block[] tempB = getTempBlock();

    public Tetromino_Square(){
        create(Color.YELLOW);
    }

    @Override
    public void setXY(int x, int y){
        // o o
        // o o
        //
        b[0].setX(x);
        b[0].setY(y);
        b[1].setX(b[0].getXPostion());
        b[1].setY(b[0].getYPostion() + Block.SIZE);
        b[2].setX(b[0].getXPostion() + Block.SIZE);
        b[2].setY(b[0].getYPostion());
        b[3].setX(b[0].getXPostion() + Block.SIZE);
        b[3].setY(b[0].getYPostion() + Block.SIZE);
    }

    @Override
    public void getDirection1(){}

    @Override
    public void getDirection2(){}

    @Override
    public void getDirection3(){}

    @Override
    public void getDirection4(){}
}
