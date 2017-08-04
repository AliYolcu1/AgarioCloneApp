package logic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import model.Chip;
import model.Enemy;
import model.GameObject;
import model.Mine;
import model.Player;
import view.GameFrame;
import view.GamePanel;

public class GameLogic{

	
private boolean isGameRunning;
private int xTarget;
private int yTarget;

private Player player;
private ArrayList<GameObject> gameObjects;
// Chips that will be removed from the screen
private ArrayList<GameObject> chipsToRemove;
// Mines that will be removed from the screen
private ArrayList<GameObject> minesToRemove;
// Enemies that will be removed from the screen
private ArrayList<GameObject> enemiesToRemove;

private GameFrame gameFrame;
private GamePanel gamePanel;

private Random random;

/**
 * @author Ali
 * @param playerNick oyuncunun karakteri i�in se�ti�i isim
 * @param selectedColor oyuncunun, karakteri i�in se�ti�i renk
 */

public GameLogic(String playerNick, Color selectedColor) {
	player = new Player(10, 10, 20, 3, selectedColor, playerNick);

	gameObjects = new ArrayList<GameObject>();
	chipsToRemove = new ArrayList<GameObject>();
	minesToRemove = new ArrayList<GameObject>();
	enemiesToRemove = new ArrayList<GameObject>();

	gameObjects.add(player);

	gameFrame = new GameFrame();
	gamePanel = new GamePanel(gameObjects);
	gamePanel.setSize(640, 480);

	random = new Random();

	fillChips(25);
	fillMines(10);
	fillEnemies(4);

	addMouseEvents();
}

/**
 *
 * �ak��malar�n kontrol edildi�i method
 * b�y�k olan k���k olan� yer
 * �ak��malar� rectangle methodu ile kontrol eder
 */

private synchronized void checkCollisions() {

	for (GameObject gameObject : gameObjects) {
		// Instead of just a collision check,
		// we want to check if the object completely
		// contain the other object
		// if (player.getRectangle().intersects(gameObject.getRectangle())) {
		if (player.getRectangle().intersects(gameObject.getRectangle())) {
			if (gameObject instanceof Chip) {
				player.setRadius(player.getRadius() + gameObject.getRadius());
				// gameObjects.remove(gameObject);
				chipsToRemove.add(gameObject);
			}
			if (gameObject instanceof Mine) {
				player.setRadius((int) player.getRadius() / 2);
				minesToRemove.add(gameObject);
			}
			if (gameObject instanceof Enemy) {
				if (player.getRadius() > gameObject.getRadius()) {
					player.setRadius(player.getRadius() + gameObject.getRadius());
					enemiesToRemove.add(gameObject);
				} else if (player.getRadius() < gameObject.getRadius()) {
					gameObject.setRadius(gameObject.getRadius() + player.getRadius());
					// Game Over
					isGameRunning = false;
				}
			}
		}

		if (gameObject instanceof Enemy) {
			Enemy enemy = (Enemy) gameObject;

			for (GameObject gameObject2 : gameObjects) {
				if (enemy.getRectangle().intersects(gameObject2.getRectangle())) {
					if (gameObject2 instanceof Chip) {
						enemy.setRadius(enemy.getRadius() + gameObject2.getRadius());
						chipsToRemove.add(gameObject2);
					}
					if (gameObject2 instanceof Mine) {
						enemy.setRadius((int) enemy.getRadius() / 2);
						minesToRemove.add(gameObject2);
					}
				}
			}
		}
	}

	// Loop is complete, remove objects from the list
	gameObjects.removeAll(chipsToRemove);
	gameObjects.removeAll(minesToRemove);
	gameObjects.removeAll(enemiesToRemove);
}

/**
 *Silinen nesnelerin yerine yenilerinin eklendi�i method
 *Silinen nesne kadar ayn�s�ndan ekler.
 * 
 */

private synchronized void addNewObjects() {
	fillChips(chipsToRemove.size());
	fillMines(minesToRemove.size());
	fillEnemies(enemiesToRemove.size());

	chipsToRemove.clear();
	minesToRemove.clear();
	enemiesToRemove.clear();
}

/**
 * karakterimizi h�z�na g�re hareket ettirdi�imiz, yani koordinat�n� de�i�tirdi�imiz method
 */

private synchronized void movePlayer() {
	if (xTarget > player.getX()) {
		player.setX(player.getX() + player.getSpeed());
	} else if (xTarget < player.getX()) {
		player.setX(player.getX() - player.getSpeed());
	}

	if (yTarget > player.getY()) {
		player.setY(player.getY() + player.getSpeed());
	} else if (yTarget < player.getY()) {
		player.setY(player.getY() - player.getSpeed());
	}
}

/**
 * rakip bizden b�y�k ise �zerimize do�ru gelecek, k���k ise aram�zdaki mesafeyi s�rekli olarak a�maya �al��acak
 */

private synchronized void moveEnemies() {
	for (GameObject gameObject : gameObjects) {
		if (gameObject instanceof Enemy) {
			Enemy enemy = (Enemy) gameObject;
			if (enemy.getRadius() < player.getRadius()) {
				// Kacacak
				int distance = (int) Point.distance(player.getX(), player.getY(), enemy.getX(), enemy.getY());

				int newX = enemy.getX() + enemy.getSpeed();
				int newY = enemy.getY() + enemy.getSpeed();
				if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
					continue;
				}

				newX = enemy.getX() + enemy.getSpeed();
				newY = enemy.getY() - enemy.getSpeed();
				if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
					continue;
				}

				newX = enemy.getX() - enemy.getSpeed();
				newY = enemy.getY() + enemy.getSpeed();
				if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
					continue;
				}

				newX = enemy.getX() - enemy.getSpeed();
				newY = enemy.getY() - enemy.getSpeed();
				if (calculateNewDistanceToEnemy(enemy, distance, newX, newY)) {
					continue;
				}

			} else {
				// Yiyecek
				if (player.getX() > enemy.getX()) {
					enemy.setX(enemy.getX() + enemy.getSpeed());
				} else if (player.getX() < enemy.getX()) {
					enemy.setX(enemy.getX() - enemy.getSpeed());
				}

				if (player.getY() > enemy.getY()) {
					enemy.setY(enemy.getY() + enemy.getSpeed());
				} else if (player.getY() < enemy.getY()) {
					enemy.setY(enemy.getY() - enemy.getSpeed());
				}
			}
		}
	}
}

private boolean calculateNewDistanceToEnemy(Enemy enemy, int distance, int x, int y) {
	int newDistance = (int) Point.distance(player.getX(), player.getY(), x, y);
	if (newDistance > distance) {
		enemy.setX(x);
		enemy.setY(y);
		return true;
	}
	return false;
}

/**
 * yemleri olu�turdu�umuz yer
 * @param n ka� tane yem yarat�lmak istendi�inin say�s�
 */

private void fillChips(int n) {
	for (int i = 0; i < n; i++) {
		int x = random.nextInt(gamePanel.getWidth());
		int y = random.nextInt(gamePanel.getHeight());
		if (x >= gamePanel.getWidth()) {
			x -= 15;
		}
		if (y >= gamePanel.getHeight()) {
			y -= 15;
		}
		gameObjects.add(new Chip(x, y, 10, Color.ORANGE));
	}
}


/**
 * may�nlar�n yarat�ld��� method
 * @param n yarat�lmak istenen may�n say�s�
 */
private void fillMines(int n) {
	for (int i = 0; i < n; i++) {

		int x = random.nextInt(gamePanel.getWidth());
		int y = random.nextInt(gamePanel.getHeight());
		if (x >= gamePanel.getWidth()) {
			x -= 15;
		}
		if (y >= gamePanel.getHeight()) {
			y -= 15;
		}

		Mine mine = new Mine(x, y, 20, Color.GREEN);

		while (player.getRectangle().intersects(mine.getRectangle())) {
			x = random.nextInt(gamePanel.getWidth());
			y = random.nextInt(gamePanel.getHeight());
			if (x >= gamePanel.getWidth()) {
				x -= 15;
			}
			if (y >= gamePanel.getHeight()) {
				y -= 15;
			}
			mine.setX(x);
			mine.setY(y);
		}

		gameObjects.add(mine);
	}
}

/**
 * rakiplerin yarat�ld��� method
 * @param n ka� adet rakip yarat�laca��n�n say�s�
 */

private void fillEnemies(int n) {
	for (int i = 0; i < n; i++) {
		int x = random.nextInt(gamePanel.getWidth());
		int y = random.nextInt(gamePanel.getHeight());
		if (x >= gamePanel.getWidth()) {
			x -= 15;
		}
		if (y >= gamePanel.getHeight()) {
			y -= 15;
		}
		Enemy enemy = new Enemy(x, y, (random.nextInt(10) + 25), 1, Color.RED);
		while (player.getRectangle().intersects(enemy.getRectangle())) {
			x = random.nextInt(gamePanel.getWidth());
			y = random.nextInt(gamePanel.getHeight());
			if (x >= gamePanel.getWidth()) {
				x -= 15;
			}
			if (y >= gamePanel.getHeight()) {
				y -= 15;
			}
			enemy.setX(x);
			enemy.setY(y);
		}

		gameObjects.add(enemy);
	}
}

/**
 * oyun �al��t���nda ger�ekle�ecek olaylar�n ak���
 * thread kullan�larak s�rekli olarak ekrana yeniden �izdiriyoruz.
 * 
 */
private void startGame() {
	new Thread(new Runnable() {

		@Override
		public void run() {
			while (isGameRunning) {
				movePlayer();
				moveEnemies();
				checkCollisions();
				addNewObjects();
				gamePanel.repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}).start();
}

/**
 * uygulama �al��t���nda yarat�lacak ve yap�lacak i�lemlerin oldu�u method
 * mouse hareketleri dinlenerek karakterimizi hareket ettiriyoruz.
 * 
 */

public void startApplication() {
	gameFrame.setContentPane(gamePanel);
	gameFrame.setVisible(true);
	isGameRunning = true;
	startGame();
}

private void addMouseEvents() {
	gameFrame.addMouseListener(new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	});

	gamePanel.addMouseMotionListener(new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			xTarget = e.getX();
			yTarget = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {

		}
	});
}
}
