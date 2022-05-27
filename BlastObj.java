package com.sxt;

import java.awt.*;
import java.awt.Graphics ;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class BlastObj extends GameObject {

    static Image[] imgs = new Image[8];

    int explodeCount = 0;

    static {
        for (int i = 0; i < 8; i++) {
            imgs[i] = Toolkit.getDefaultToolkit().getImage("src/Image/" +(i + 1)+".gif");
        }
    }

    public BlastObj() {
        super();
    }

    public BlastObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintSelf(Graphics g) {
        //绘制点击爆炸效果
        if (explodeCount < 8){
            g.drawImage(imgs[explodeCount],x,y,null);
            explodeCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
