package com.sxt;

import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JFrame {

	static URL url;
	static AudioClip ac;

    /** ����˫����ͼƬ */
    Image offScreenImage = null;
    //��Ϸ״̬ 0 ��Ϸδ��ʼ�� 1 ����ģʽ�� 2 ˫��ģʽ�� 3 ��Ϸ��ͣ�� 4 ��Ϸʧ�ܣ� 5 ��Ϸʤ��
    int state= 0;
    //��Ϸ�Ƿ�ʼ
    private boolean start = false;
    //��ʱ����
    int a = 1;
    //�ػ����
    public int count = 0;
    //���ڳ���
    int width = 1000;
    int height = 800;
    //���弯��
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    //��Ϸָ��
    Image select = Toolkit.getDefaultToolkit().getImage("image/selecttank.gif");
    //ָ���ʼ�߶�
    int y = 160;
    //���
    private PlayerOne playerOne = new PlayerOne("image/player1/p1tankU.gif", 150, 510,
            "image/player1/p1tankU.gif","image/player1/p1tankD.gif",
            "image/player1/p1tankL.gif","image/player1/p1tankR.gif",this);

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
        //��Ӽ����¼�
        this.addKeyListener(new GamePanel.KeyMonitor());
        //���Χǽ
        for(int i = 0; i< 14; i ++){
             wallList.add(new Wall("images/walls.gif", i*60 ,170, this ));
        }
        wallList.add(new Wall("image/walls.gif", 305 ,560,this ));
        wallList.add(new Wall("image/walls.gif", 305 ,500,this ));
        wallList.add(new Wall("image/walls.gif", 365 ,500,this ));
        wallList.add(new Wall("image/walls.gif", 425 ,500,this ));
        wallList.add(new Wall("image/walls.gif", 425 ,560,this ));
        wallList.add(new Wall("image/walls.gif", 200 ,700,this ));
        wallList.add(new Wall("image/walls.gif", 445 ,50,this ));
        wallList.add(new Wall("image/walls.gif", 700 ,330,this ));
        wallList.add(new Wall("image/walls.gif", 700 ,390,this ));
        wallList.add(new Wall("image/walls.gif", 700 ,450,this ));
        wallList.add(new Wall("image/walls.gif", 700 ,550,this ));
        while (true){
            if (count % 100 == 1) {
                Random r = new Random();
                int rnum =r.nextInt(800);
                botList.add(new Bot("image/enemy/enemy1U.gif", rnum, 110,
                        "image/enemy/enemy1U.gif","image/enemy/enemy1D.gif",
                        "image/enemy/enemy1L.gif","image/enemy/enemy1R.gif", this));
                //System.out.println("bot: " + botList.size());
            }
            repaint();
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        //����������һ����С��ImageͼƬ
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //��ø�ͼƬ�Ļ���
        Graphics gImage= offScreenImage.getGraphics();
        //���ñ�����ɫ
        gImage.setColor(Color.LIGHT_GRAY );
        //�����������
        gImage.fillRect(0, 0, width, height);
        //�ұ仭����ɫ
        gImage.setColor(Color.GRAY );
        //�ı����ִ�С����ʽ
        gImage.setFont(new Font("����",Font.BOLD,50));
        if(state == 0){
            //�������
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("����ģʽ",220,200);
            gImage.drawString("˫��ģʽ",220,300);
            gImage.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            //�������
            gImage.drawString("��Ϸ��ʼ",220,300);
            if(state == 1){
                gImage.drawString("����ģʽ",220,200);
            }
            else{
                gImage.drawString("˫��ģʽ",220,200);
            }
            //paint�ػ���ϷԪ��
            for(Tank player: tankList){
                player.paintSelf(gImage);
            }
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall: wallList){
                wall.paintSelf(gImage);
            }
            //�ػ����+1
            count++;
        }
        /** �����������ƺ�Ŷ��ͼ���������Ƶ������Ļ����� */
        g.drawImage(offScreenImage, 0, 0, null);
    }
    private class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    if(!start){
                        y = 150;
                        a = 1;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_2:
                    if(!start){
                        y = 250;
                        a = 2;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_ENTER:
                    tankList.add(playerOne);
                    state = a;
                    start = true;
                    //System.out.println("state:"+""+state);
                    break;
                default:
                    playerOne.keyPressed(e);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
  	  File f1 = new File("src/Music/war1.wav");
  	  url = f1.toURL();
  	  ac = Applet.newAudioClip(url);
  	  ac.loop();

        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}