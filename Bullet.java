package com.sxt;

import java.awt.*;
import java.util.List;

public class Bullet extends GameObject{
    //����
    private int width = 10;
    private int height = 20;
    //�ٶ�
    private int speed = 20;
    //����
    Direction direction;
    //���캯��
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

  //�ӵ���̹����ײ���
    public void hitBot(){
        Rectangle next= this.getRec();
        List<Bot> bots = this.gamePanel.botList;
        //�ӵ���bot
        for(Bot bot: bots){
            if(bot.getRec().intersects(next)){
                System.out.println("hit bot");
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }
    //�ӵ���Χǽ��ײ���
    public void hitWall(){
       //Χǽ�б�
    	Rectangle next = this.getRec();
        List<Wall> walls = this.gamePanel.wallList;
        for(Wall w: walls) {
           //��ÿһ��Χǽ������ײ���
        	if (w.getRec().intersects(next)) {
                //ɾȥΧǽ���ӵ�
        		this.gamePanel.wallList.remove(w);
                this.gamePanel.removeList.add(this);
                //ֹͣѭ��
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