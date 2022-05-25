package com.sxt;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends Bullet {
    public EnemyBullet(String img, int x, int y, Direction direction,GamePanel gamePanel){
        super(img, x, y, direction, gamePanel);
    }

    public void paintSelf(Graphics g){
        g.drawImage(img, x, y, null);
        go();
    }
}
