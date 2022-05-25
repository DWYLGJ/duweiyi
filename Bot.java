package com.sxt;

import java.awt.*;
import java.util.Random;

public class Bot extends Tank {

	public Bot(String img, int x, int y, String upImage, String downImage,
			String leftImage, String rightImage,
			GamePanel gamePanel) {
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
