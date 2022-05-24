package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JFrame {

    //窗口长宽
    int width = 800;
    int height = 610;
    //指针图片
    Image select = Toolkit.getDefaultToolkit().getImage("C:/Users/像风走过八千里/eclipse-workspace/tankwar/src/images/selecttank.gif");
    //指针初始纵坐标
   int  y=250;
   //游戏模式	0 游戏未开始，1单人模式，2多人模式
   int state = 0;
   int a=1;
   private boolean start = false;
   //临时变量
    //窗口的启动方法
    public void launch(){
        //标题
        setTitle("坦克大战");
        //窗口初始大小
        setSize(width, height);
        //使屏幕居中
        setLocationRelativeTo(null);
        //添加关闭事件
        setDefaultCloseOperation(3);
        //用户不能调整大小
        setResizable(false);
        //使窗口可见
        setVisible(true);
        //添加键盘监听器
        this.addKeyListener(new GamePanel.KeyMonitor());
        //重绘
        while (true){
            repaint();
            try {
                //线程休眠  1秒 = 1000毫秒
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
@Override
public void paint(Graphics g) {
	g.setColor(Color.DARK_GRAY);//设置背景颜色
	g.fillRect(0, 0, width, height);//填充整个画布
	g.setColor(Color.CYAN);//改变画笔颜色
	g.setFont(new Font("仿宋",Font.BOLD,50));//改变文字大小和样式
	  if(state == 0){   
		//添加文字
	g.drawString("游戏模式",220,100);
	g.drawString("单人模式", 220, 200);
	g.drawString("多人模式", 220, 300);
	g.drawString("按1，2选择模式，按回车开始游戏",0,400);
	//绘制指针
    g.drawImage(select,160,y,null);
}
    else if(state == 1||state == 2){
        //添加文字
        g.drawString("游戏开始",220,300);
        if(state == 1){
            g.drawString("单人模式",220,200);
        }
        else{
            g.drawString("多人模式",220,200);
        }
    }
}

//键盘监听器
class KeyMonitor extends KeyAdapter {
	//按下键盘
	@Override
    public void keyPressed(KeyEvent e) {
        //返回键值
		int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_1:
                    y = 150;
                    a = 1;
                    break;           
            case KeyEvent.VK_2:            
                    y = 250;
                    a = 2;
                    break;
            case KeyEvent.VK_ENTER:
                state = a;
                break;
                }		
                System.out.println(e.getKeyChar());
        
    }
}
	
//main
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
