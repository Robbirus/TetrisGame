package mino;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

import java.awt.Color;
import java.awt.Graphics2D;

public class Mino {

    private Block[] block = new Block[4];
    private Block[] tempBlock = new Block[4];
    private int autoDropCounter = 0;
    private int direction = 1;
    private boolean leftCollision;
    private boolean rightCollision;
    private boolean bottomCollision;
    private boolean active = true;
    private boolean deactivating;
    private int deactivateCounter = 0;


    public void create(Color c){

        for(int i = 0; i < 4; i++) {
            block[i] = new Block(c);
        }

        for(int i = 0; i < 4; i++) {
            tempBlock[i] = new Block(c);
        }
    }

    public void setXY(int x, int y){}

    public void updateXY(int direction){

        checkRotationCollision();

        if(!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction;
            block[0].setX(tempBlock[0].getXPostion());
            block[0].setY(tempBlock[0].getYPostion());
            block[1].setX(tempBlock[1].getXPostion());
            block[1].setY(tempBlock[1].getYPostion());
            block[2].setX(tempBlock[2].getXPostion());
            block[2].setY(tempBlock[2].getYPostion());
            block[3].setX(tempBlock[3].getXPostion());
            block[3].setY(tempBlock[3].getYPostion());
        }
    }

    public void checkMovementCollision(){

        this.leftCollision = false;
        this.rightCollision = false;
        this.bottomCollision = false;

        // check static block
        checkStaticBlockCollision();

        // Check frame collision
        // left wall
        for(int i = 0; i < block.length; i++){
            if (block[i].getXPostion() == PlayManager.left_x) {
                leftCollision = true;
                break;
            }
        }

        // Right wall
        for(int i = 0; i < block.length; i++){
            if (block[i].getXPostion() + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
                break;
            }
        }

        // Bottom Floor
        for(int i = 0; i < block.length; i++){
            if (block[i].getYPostion() + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
                break;
            }
        }
    }

    public void checkRotationCollision(){
        this.leftCollision = false;
        this.rightCollision = false;
        this.bottomCollision = false;

        // check static block
        checkStaticBlockCollision();

        // Check frame collision
        // left wall
        for(int i = 0; i < block.length; i++){
            if (tempBlock[i].getXPostion() < PlayManager.left_x) {
                leftCollision = true;
                break;
            }
        }

        // Right wall
        for(int i = 0; i < block.length; i++){
            if (tempBlock[i].getXPostion() + Block.SIZE > PlayManager.right_x) {
                rightCollision = true;
                break;
            }
        }

        // Bottom Floor
        for(int i = 0; i < block.length; i++){
            if (tempBlock[i].getYPostion() + Block.SIZE > PlayManager.bottom_y) {
                bottomCollision = true;
                break;
            }
        }
    }

    private void checkStaticBlockCollision(){

        for(int i = 0; i < PlayManager.staticBlocks.size(); i++){

            int targetX = PlayManager.staticBlocks.get(i).getXPostion();
            int targetY = PlayManager.staticBlocks.get(i).getYPostion();

            // Check Down
            for(int j = 0; j < block.length; j++){
                if(block[j].getYPostion() + Block.SIZE == targetY && block[j].getXPostion() == targetX){
                    bottomCollision = true;

                }
            }
            // Check left
            for(int j = 0; j < block.length; j++){
                if(block[j].getXPostion() - Block.SIZE == targetX && block[j].getYPostion() == targetY){
                    leftCollision = true;

                }
            }

            // Check right
            for(int j = 0; j < block.length; j++){
                if(block[j].getXPostion() + Block.SIZE == targetX && block[j].getYPostion() == targetY){
                    rightCollision = true;

                }
            }
        }
    }

    public void update(){

        if(deactivating){
            deactivating();
        }

        // Move the mino
        if(KeyHandler.isUpPressed()){
            switch (direction){
                case 1: getDirection2(); break;
                case 2: getDirection3(); break;
                case 3: getDirection4(); break;
                case 4: getDirection1(); break;
            }

            KeyHandler.setUpPressed(false);
            GamePanel.se.play(3, false);
        }

        checkMovementCollision();

        if(KeyHandler.isDownPressed()){
            // If the mino's bottom is not hitting, it can go down
            if(!bottomCollision) {
                block[0].setY(block[0].getYPostion() + Block.SIZE);
                block[1].setY(block[1].getYPostion() + Block.SIZE);
                block[2].setY(block[2].getYPostion() + Block.SIZE);
                block[3].setY(block[3].getYPostion() + Block.SIZE);

                // When moved down, reset the autoDropCounter
                autoDropCounter = 0;
            }

            KeyHandler.setDownPressed(false);
        }

        if(KeyHandler.isLeftPressed()){

            if(!leftCollision) {
                block[0].setX(block[0].getXPostion() - Block.SIZE);
                block[1].setX(block[1].getXPostion() - Block.SIZE);
                block[2].setX(block[2].getXPostion() - Block.SIZE);
                block[3].setX(block[3].getXPostion() - Block.SIZE);

            }
            KeyHandler.setLeftPressed(false);
        }

        if(KeyHandler.isRightPressed()){

            if(!rightCollision) {
                block[0].setX(block[0].getXPostion() + Block.SIZE);
                block[1].setX(block[1].getXPostion() + Block.SIZE);
                block[2].setX(block[2].getXPostion() + Block.SIZE);
                block[3].setX(block[3].getXPostion() + Block.SIZE);
            }
            KeyHandler.setRightPressed(false);
        }

        if(bottomCollision){

            if(!deactivating) {
                GamePanel.se.play(4, false);
            }
            deactivating = true;

        } else {
            autoDropCounter++; //the counter increases in every frame
            if (autoDropCounter == PlayManager.dropInterval) {
                // The mino goes down
                block[0].setY(block[0].getYPostion() + Block.SIZE);
                block[1].setY(block[1].getYPostion() + Block.SIZE);
                block[2].setY(block[2].getYPostion() + Block.SIZE);
                block[3].setY(block[3].getYPostion() + Block.SIZE);
                autoDropCounter = 0;
            }
        }
    }

    private void deactivating(){

        deactivateCounter++;

        // Wait 45 Frames until deactivate
        if(deactivateCounter == 45){

            deactivateCounter = 0;
            checkMovementCollision(); // Check if the bottom is still hitting

            // if the bottom is still hitting after 45 frames, deactivate the mino
            if(bottomCollision){
                active = false;
            }
        }
    }

    public void draw(Graphics2D g2){

        int margin = 2;
        g2.setColor(block[0].getColor());
        g2.fillRect(block[0].getXPostion()+margin, block[0].getYPostion()+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(block[1].getXPostion()+margin, block[1].getYPostion()+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(block[2].getXPostion()+margin, block[2].getYPostion()+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(block[3].getXPostion()+margin, block[3].getYPostion()+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
    }

    public void getDirection1(){}

    public void getDirection2(){}

    public void getDirection3(){}

    public void getDirection4(){}

    public Block[] getBlock() {
        return block;
    }

    public void setBlock(Block[] block) {
        this.block = block;
    }

    public Block[] getTempBlock() {
        return tempBlock;
    }

    public void setTempBlock(Block[] tempBlock) {
        this.tempBlock = tempBlock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeactivating() {
        return deactivating;
    }

    public void setDeactivating(boolean deactivating) {
        this.deactivating = deactivating;
    }
}
