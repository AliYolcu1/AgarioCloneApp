package model;

import java.awt.Color;

public class Enemy extends GameObject {

/**
 * Rakip karakter s�n�f�
 * @param x koordinat
 * @param y koordinat
 * @param radius yar��ap
 * @param speed h�z
 * @param color renk
 */
	public Enemy(int x, int y, int radius, int speed, Color color) {
		super(x, y, radius, color);
		this.speed = speed;
		
		
	}

	private int speed;

	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
