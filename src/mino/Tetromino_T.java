package mino;

import java.awt.*;

public class Tetromino_T extends Mino {

    private final Block[] b = getBlock();
    private final Block[] tempB = getTempBlock();

    public Tetromino_T(){
        create(Color.MAGENTA);
    }

    @Override
    public void setXY(int x, int y){
        //   o
        // o o o
        //
        b[0].setX(x);
        b[0].setY(y);
        b[1].setX(b[0].getXPostion());
        b[1].setY(b[0].getYPostion() - Block.SIZE);
        b[2].setX(b[0].getXPostion() - Block.SIZE);
        b[2].setY(b[0].getYPostion());
        b[3].setX(b[0].getXPostion() + Block.SIZE);
        b[3].setY(b[0].getYPostion());

    }

    @Override
    public void getDirection1(){
        //   o
        // o o o
        //
        tempB[0].setX(b[0].getXPostion());
        tempB[0].setY(b[0].getYPostion());
        tempB[1].setX(b[0].getXPostion());
        tempB[1].setY(b[0].getYPostion() - Block.SIZE);
        tempB[2].setX(b[0].getXPostion() - Block.SIZE);
        tempB[2].setY(b[0].getYPostion());
        tempB[3].setX(b[0].getXPostion() + Block.SIZE);
        tempB[3].setY(b[0].getYPostion());

        updateXY(1);
    }

    @Override
    public void getDirection2(){
        //   o
        //   o o
        //   o
        tempB[0].setX(b[0].getXPostion());
        tempB[0].setY(b[0].getYPostion());
        tempB[1].setX(b[0].getXPostion() + Block.SIZE);
        tempB[1].setY(b[0].getYPostion());
        tempB[2].setX(b[0].getXPostion());
        tempB[2].setY(b[0].getYPostion() - Block.SIZE);
        tempB[3].setX(b[0].getXPostion());
        tempB[3].setY(b[0].getYPostion() + Block.SIZE);

        updateXY(2);
    }

    @Override
    public void getDirection3(){
        //
        // o o o
        //   o
        tempB[0].setX(b[0].getXPostion());
        tempB[0].setY(b[0].getYPostion());
        tempB[1].setX(b[0].getXPostion());
        tempB[1].setY(b[0].getYPostion() + Block.SIZE);
        tempB[2].setX(b[0].getXPostion() + Block.SIZE);
        tempB[2].setY(b[0].getYPostion());
        tempB[3].setX(b[0].getXPostion() - Block.SIZE);
        tempB[3].setY(b[0].getYPostion());

        updateXY(3);
    }

    @Override
    public void getDirection4(){
        //   o
        // o o o
        //
        tempB[0].setX(b[0].getXPostion());
        tempB[0].setY(b[0].getYPostion());
        tempB[1].setX(b[0].getXPostion() - Block.SIZE);
        tempB[1].setY(b[0].getYPostion());
        tempB[2].setX(b[0].getXPostion());
        tempB[2].setY(b[0].getYPostion() + Block.SIZE);
        tempB[3].setX(b[0].getXPostion());
        tempB[3].setY(b[0].getYPostion() - Block.SIZE);

        updateXY(4);
    }
}
