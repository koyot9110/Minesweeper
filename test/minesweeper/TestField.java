package minesweeper;

import static org.junit.Assert.*;

import java.util.Random;

import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;

import org.junit.Test;

public class TestField {
	
	@Test
	public void testGetRowCount() {
		Field field = new Field(9, 9, 10);
		int row = field.getRowCount();
		assertTrue("Pocet min nesedi", row == 9);
	}
	
	@Test
	public void testGetColumnCount() {
		Field field = new Field(9, 10, 10);
		int column = field.getColumnCount();
		assertTrue("Pocet min nesedi", column == 10);
	}
	
	@Test
	public void testGetMineCount() {
		Field field = new Field(9, 9, 11);
		int mine = field.getMineCount();
		assertTrue("Pocet min nesedi", mine == 11);
	}
	
	@Test
	public void testMineCount(){
		Random rnd = new Random();
		int rowCount = rnd.nextInt(10) + 5;
		int columnCount = rnd.nextInt(10) + 5;
		int mineCount = rnd.nextInt(10) + 5;
		
		if (rowCount * columnCount < mineCount) {
			mineCount = rnd.nextInt(10) + 5;
		}
		
		Field field = new Field(rowCount, columnCount, mineCount);
		int tempMine = 0;
		
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Mine) {
					tempMine++;
				}
			}
		}
		assertTrue("Pocet vygenerovanych min nie je zhodny s poctom min v poli", mineCount == tempMine);
	}
}
