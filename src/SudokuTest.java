
import java.io.*;
import java.lang.*;
import java.util.*;

public class SudokuTest {
	// Test
	public static void main(String[] args) throws FileNotFoundException {
		Sudoku s2 = new Sudoku(readSudoku("src/SudokuSolved.txt"));
		System.out.println(s2.toString());
		System.out.println(s2.isCorrect());
		Sudoku s3 = new Sudoku(readSudoku("src/SudokuA.txt"));
		System.out.println(s3.toString());
		solveSudoku("src/SudokuD.txt");

		
		

	}

	public static void solveSudoku(String path) throws FileNotFoundException {
		Sudoku s1 = new Sudoku(readSudoku(path));
		System.out.println(s1.toString());
		while (true) {
			try {
				s1 = new Sudoku(readSudoku(path));
				s1.trySolve();
				System.out.println("Checking if Solution is valid");
				if (s1.isCorrect()) {
					break;
				} else {
					continue;
				}
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		
		System.out.println(s1.toString());
	
	}
	
	/*
	public static void solveSudoku(Sudoku s) {
		Sudoku s1 = new Sudoku(s.getField());
		System.out.println(s1.toString());
		while (true) {
			try {
				s1 = new Sudoku(s.getField());
				s1.trySolve();
				System.out.println("Checking if Solution is valid");
				if (s1.isCorrect()) {
					break;
				} else {
					continue;
				}
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		
		System.out.println(s1.toString());
	
	}
	*/

	public static int[][] readSudoku(String filePath) throws FileNotFoundException {

		// Liest eine Matrix aus einem file mit Pfad filePath ein
		ArrayList<String> rowOfMatrix = new ArrayList<String>();
		Scanner fileReader = new Scanner(new File(filePath)); // Öffne neuen Scanner
		while (fileReader.hasNext()) {
			String row = fileReader.nextLine();
			rowOfMatrix.add(row);
		}

		// Bereit Zählung der Spalten vor in dem der Scanner instanziert wird.
		Scanner parseLine = new Scanner(rowOfMatrix.get(0));
		parseLine.useDelimiter(" ");

		// Closing the readers
		parseLine.close();
		fileReader.close();
		int[][] out = new int[9][9]; // Instanziere leere Matrix

		for (int i = 0; i < rowOfMatrix.size(); i++) {
			// Übernehme alle Werte aus der Arraylist in die Matrix mit Parser vom Scanner.
			Scanner parseLine2 = new Scanner(rowOfMatrix.get(i));
			parseLine.useDelimiter(" ");
			int j = 0;

			while (parseLine2.hasNext()) {
				out[i][j] = parseLine2.nextInt();
				j++;
			}
			parseLine2.close();
		}
		return out;

	}

	public static String arraytoString(int[][] a) {
		// converts a given Array to a String
		String out = "\n   ";
		for (int[] row : a) {
			for (int val : row) {
				out += (val + " ");
			}
			out += "\n   ";
		}
		return out;
	}

	public static boolean arraySearch(int[] a, int val) {
		for (int i : a) {
			if (i == val) {
				return true;
			}
		}
		return false;
	}

	public static int arrayCount(int[] a, int val) {
		int counter = 0;
		for (int i : a) {
			if (i == val) {
				counter++;
			}
		}
		return counter;
	}
}
