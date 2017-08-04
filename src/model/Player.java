package model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
/**
 * oyuncu karakteri sýnýfý
 * oyuncunun ismini karakterinin üzerine yazdýrýyoruz
 * 
 * @author Ali
 *
 */
public class Player extends GameObject {

	private String playerNick;

	public Player(int x, int y, int radius, int speed, Color color, String playerNick) {
		super(x, y, radius, color);
		this.speed = speed;
		this.playerNick = playerNick;
	}

	private int speed;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void draw(Graphics2D g2d) {

		FontMetrics fontMetrics = g2d.getFontMetrics(g2d.getFont());
		super.draw(g2d);
		int width = fontMetrics.stringWidth(playerNick);
		int nameX = getX() + (getRadius() - width) / 2;
		int nameY = getY() - fontMetrics.getHeight();
		g2d.drawString(playerNick, nameX, nameY);
	}

}
