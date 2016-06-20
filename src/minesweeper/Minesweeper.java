package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;

	private long startMillis = System.currentTimeMillis();

	private BestTimes bestTimes;

	private static Minesweeper instance;

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();

		Field field = new Field(10, 10, 10);
		userInterface.newGameStarted(field);
	}

	public int getPlayingSecond() {
		long time = System.currentTimeMillis() - startMillis;
		return (int) time % 1000;
	}

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	public static Minesweeper getInstance() {
		return instance;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		new Minesweeper();
	}
}
