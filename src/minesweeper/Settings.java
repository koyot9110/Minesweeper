package minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
	private final int rowCount;
	private final int columnCount;
	private final int mineCount;

	public static final Settings BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);

	private static final String SETTING_FILE = System.getProperty("user.home")
			+ System.getProperty("file.separator") + "minesweeper.settings";

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public boolean equals(Object o) {
		if (o.equals(this)) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return getRowCount() * getColumnCount() * getMineCount();
	}

	public void save() {
		try {
			FileOutputStream out = new FileOutputStream(SETTING_FILE);
			ObjectOutputStream s = new ObjectOutputStream(out);
			s.writeObject(this);
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Settings load() {
		try {
			FileInputStream in = new FileInputStream(SETTING_FILE);
			ObjectInputStream s = new ObjectInputStream(in);
			s.readObject();
			s.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return Settings.BEGINNER;
	}
}
