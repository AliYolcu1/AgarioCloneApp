package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.GameObject;

public class GamePanel extends JPanel {

	private ArrayList<GameObject> gameObjects;

	public GamePanel(ArrayList<GameObject> gameObjects) {

		this.gameObjects = gameObjects;

	}
/**
 * oyun içindeki nesnelerin çizdirildiði method
 */
	@Override
	protected synchronized void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);

		Graphics2D g2d = (Graphics2D) arg0;

		for (GameObject gameObject : gameObjects) {

			gameObject.draw(g2d);
		}

	}

}
