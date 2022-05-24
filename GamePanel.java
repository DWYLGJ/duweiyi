package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JFrame {

    //���ڳ���
    int width = 800;
    int height = 610;
    //ָ��ͼƬ
    Image select = Toolkit.getDefaultToolkit().getImage("C:/Users/����߹���ǧ��/eclipse-workspace/tankwar/src/images/selecttank.gif");
    //ָ���ʼ������
   int  y=250;
   //��Ϸģʽ	0 ��Ϸδ��ʼ��1����ģʽ��2����ģʽ
   int state = 0;
   int a=1;
   private boolean start = false;
   //��ʱ����
    //���ڵ���������
    public void launch(){
        //����
        setTitle("̹�˴�ս");
        //���ڳ�ʼ��С
        setSize(width, height);
        //ʹ��Ļ����
        setLocationRelativeTo(null);
        //��ӹر��¼�
        setDefaultCloseOperation(3);
        //�û����ܵ�����С
        setResizable(false);
        //ʹ���ڿɼ�
        setVisible(true);
        //��Ӽ��̼�����
        this.addKeyListener(new GamePanel.KeyMonitor());
        //�ػ�
        while (true){
            repaint();
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
@Override
public void paint(Graphics g) {
	g.setColor(Color.DARK_GRAY);//���ñ�����ɫ
	g.fillRect(0, 0, width, height);//�����������
	g.setColor(Color.CYAN);//�ı仭����ɫ
	g.setFont(new Font("����",Font.BOLD,50));//�ı����ִ�С����ʽ
	  if(state == 0){   
		//�������
	g.drawString("��Ϸģʽ",220,100);
	g.drawString("����ģʽ", 220, 200);
	g.drawString("����ģʽ", 220, 300);
	g.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",0,400);
	//����ָ��
    g.drawImage(select,160,y,null);
}
    else if(state == 1||state == 2){
        //�������
        g.drawString("��Ϸ��ʼ",220,300);
        if(state == 1){
            g.drawString("����ģʽ",220,200);
        }
        else{
            g.drawString("����ģʽ",220,200);
        }
    }
}

//���̼�����
class KeyMonitor extends KeyAdapter {
	//���¼���
	@Override
    public void keyPressed(KeyEvent e) {
        //���ؼ�ֵ
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
