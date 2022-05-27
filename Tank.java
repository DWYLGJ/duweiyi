package com.sxt;

import java.awt.*;

public class Tank extends GameObject{

    private boolean attackCoolDown =true;//攻击冷却状态
    private int attackCoolDownTime =1000;//攻击冷却时间毫秒间隔1000ms发射子弹
    private String upImage; //向上移动时的图片
    private String downImage;//向下移动时的图片
    private String rightImage;//向右移动时的图片
    private String leftImage;//向左移动时的图片
    boolean alive = true;
    //坦克size
    int width = 40;
    int height = 50;
    //坦克初始方向
    Direction direction = Direction.UP;
    //坦克速度
    private int speed = 5;
    //坦克坐标，方向，图片，方向，面板
    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.upImage = upImage;
        this.leftImage = leftImage;
        this.downImage = downImage;
        this.rightImage = rightImage;
    }

    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImage);
        this.x -= speed;
    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        this.x += speed;
    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        this.y -= speed;
    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
        this.y += speed;
    }

    public void attack(){
        Point p = getHeadPoint();
        if(attackCoolDown && alive){
            Bullet bullet = new Bullet("image/bullet/bulletGreen.gif",p.x,p.y,direction, this.gamePanel);
            this.gamePanel.bulletList.add(bullet);
            attackCoolDown = false;
            //线程开始
            new AttackCD().start();
        }
    }
//新线程
    public class AttackCD extends Thread{
        public void run(){
            attackCoolDown=false;//将攻击功能设置为冷却状态
            try{
                Thread.sleep(attackCoolDownTime);//休眠1秒
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            attackCoolDown=true;//将攻击功能解除冷却状态
            //线程终止
            this.stop();
        }
    }

    //根据方向确定头部位置，x和y是左下角的点
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x + width/2, y );
            case LEFT:
                return new Point(x, y + height/2);
            case DOWN:
                return new Point(x + width/2, y + height);
            case RIGHT:
                return new Point(x + width, y + height/2);
            default:
                return null;
        }
    }
//    //坦克与围墙碰撞检测
//    public boolean hitWall(int x,int y) {
// //围墙列表
//    ArrayList<Wall>walls = this.gamePanel.wallList;
//   //下一步距矩形
//    Rectangle next = new Rectangle(x,y,width,height);
//    	//遍历列表
//    for(Wall wall:walls) {
//    //与每一个围墙进行碰撞检测
//    	if(next.intersects(wall.getRec())) {
//    //发生碰撞，返回
  //    return true;
//       }
//    }
//    		return true;
//    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
