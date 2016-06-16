package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
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

	/**
	 * New game
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();
			if (field.getState() == GameState.SOLVED) {
				update();
				System.out.println("You won!!! CONGRATULATION!!!");
				System.exit(0);
			} else if (field.getState() == GameState.FAILED) {
				update();
				System.out.println("You lose! GAME OVER!!!");
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Draw game field
	 */
	@Override
	public void update() {
		char row = 'A';
		int column = 1;
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
					switch (tempTile.getState()) {
					case OPEN:
						if (tempTile instanceof Mine) {
							System.out.printf("X ");
						} else if (tempTile instanceof Clue) {
							System.out.print(((Clue) tempTile).getValue() + " ");
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
		System.out.println("Enter X for exit");
		System.out.println("Enter M/m for mark tile");
		System.out.println("Enter O/o for open tile");
		
		Pattern pattern = Pattern.compile("([XMmOo]{1})([A-Z]{1}||[a-z]{1})?(\\d{1,2})?");
		Matcher matcher = pattern.matcher(readLine());
		
		if (matcher.matches()) {
			int row = matcher.group(2).toUpperCase().charAt(0)-'A';
			int column = Integer.parseInt(matcher.group(3)) - 1;
			
			switch (matcher.group(1).toUpperCase()) {
			case "X": System.exit(0);
				break;
			case "M": field.markTile(row, column);
				break;
			case "O": field.openTile(row, column);
				break;
			}
		} else {
			System.out.println("Wrong format input");
			processInput();
		}
	}
}
