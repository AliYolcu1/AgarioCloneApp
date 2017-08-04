package view;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

/**
 * 640/480 boyutlarýnda oluþturduðumuz frame
 * 
 * @author Ali 
 */
	public GameFrame() {
		setTitle("Agario Clone");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
