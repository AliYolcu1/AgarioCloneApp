package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * yem, may�n, rakip ve oyuncu s�n�flar�n�n ortak �zelliklerini bar�nd�ran �st s�n�f
 * @author Ali
 *
 */

public abstract class GameObject {

	private int x;
	private int y;
	private int radius;
	private Color color;

	public GameObject(int x, int y, int radius, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
/**
 * karakterin �izdirilmesi
 * @param g2d �izimde kullan�lan de�i�ken
 */
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		
		
		g2d.fillOval(getX(), getY(), getRadius(), getRadius());

	}
	
	public Rectangle getRectangle() {
		Rectangle rect = new Rectangle(getX(), getY(), getRadius(), getRadius());
		return rect;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
