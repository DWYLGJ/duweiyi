package com.sxt;

import java.awt.*;

public class Wall extends GameObject {

    public int width = 30;
    public int height = 30;
    public Wall(String img, int x, int y, GamePanel gamePanel){
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
