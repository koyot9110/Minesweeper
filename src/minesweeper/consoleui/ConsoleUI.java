package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field
	 * )
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();
			throw new UnsupportedOperationException(
					"Resolve the game state - winning or loosing condition.");
		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		int column = 0;
		char row = 'A';
		for (int i = -1; i < field.getRowCount(); i++) {
			for (int j = -1; j < field.getColumnCount(); j++) {
				if ((i == -1) && (j == -1)) {
					System.out.printf("  ");
				} else if (j == -1 && i > -1) {
					System.out.printf(row + " ");
					row++;
				} else if (i == -1 && j > -1) {
					System.out.printf(column + " ");
					column++;
				} else {
					Tile tempTile = field.getTile(i, j);
					if (tempTile == null) {
						System.out.println("asdad");
					}
					switch (tempTile.getState()) {
					case OPEN:
						if (tempTile instanceof Mine) {
							System.out.printf("X ");
						} else if (tempTile instanceof Clue) {
							System.out.print(((Clue) tempTile).getValue());
						}
						break;
					case MARKED:
						System.out.printf("M ");
						break;
					case CLOSED:
						System.out.printf("- ");
						break;
					}
				}
			}
			System.out.println();
		}
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		throw new UnsupportedOperationException(
				"Method processInput not yet implemented");
	}
}
