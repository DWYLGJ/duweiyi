package com.sxt;

import java.awt.*;
import java.util.List;

public class Bullet extends GameObject{
    //长宽
    private int width = 10;
    private int height = 20;
    //速度
    private int speed = 20;
    //方向
    Direction direction;
    //构造函数
    public Bullet(String img, int x, int y, Direction direction,GamePanel gamePanel) {
        super(img, x,  y, gamePanel);
        this.direction = direction;
    }

    public void go(){
        switch (direction){
            case UP:
                upward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
        }
        this.hitWall();
    }

  //子弹与坦克碰撞检测
    public void hitBot(){
        Rectangle next= this.getRec();
        List<Bot> bots = this.gamePanel.botList;
        //子弹和bot
        for(Bot bot: bots){
            if(bot.getRec().intersects(next)){
                System.out.println("hit bot");
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }
    //子弹与围墙碰撞检测
    public void hitWall(){
       //围墙列表
    	Rectangle next = this.getRec();
        List<Wall> walls = this.gamePanel.wallList;
        for(Wall w: walls) {
           //与每一个围墙进行碰撞检测
        	if (w.getRec().intersects(next)) {
                //删去围墙和子弹
        		this.gamePanel.wallList.remove(w);
                this.gamePanel.removeList.add(this);
                //停止循环
                break;  
            }
        }
    }
    public void leftward(){
        x -= speed;
    }
    public void rightward(){
        x += speed;
    }
    public void upward(){
        y -= speed;
    }
    public void downward(){
        y += speed;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
        hitBot();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
