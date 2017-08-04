package logic;

import java.awt.Color;

public class AgarioCloneApp {

	/**
	 * 
	 * @author Ali
	 * Uygulamanýn main methodunu barýndýran, baþlangýç sýnýfý
	 * 
	 */
	
	public static void main(String[] args) {

		GameLogic gameLogic = new GameLogic("Lö", Color.BLACK);
		gameLogic.startApplication();

	}

}
