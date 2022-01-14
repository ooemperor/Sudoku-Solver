

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuTest {
	//Test
	public static void main(String[] args) throws FileNotFoundException {
		Sudoku s1 = new Sudoku(readSudoku("src/SudokuC.txt"));
		System.out.println(s1.toString());
		
		/*
		System.out.println(arraytoString(s1.getBlock(0, 0)));
		System.out.println(s1.getPossVals()[4][4]);
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				if (((s1.getPossVals()[i][j]).size() == 1) && (s1.getPossVals()[i][j]).get(0) != -1) {
					System.out.println(i+ " "+ j);
				}
			}
		}
		System.out.println(s1.checkRowColBlock(0, 0, 9));
		*/
		while (true) {
			try {
				s1 = new Sudoku(readSudoku("src/SudokuC.txt"));
				s1.trySolve();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(s1.toString());
		
		
	}
	
	
	public static int[][] readSudoku(String filePath) throws FileNotFoundException {
		
		//Liest eine Matrix aus einem file mit Pfad filePath ein
		ArrayList<String> rowOfMatrix = new ArrayList<String>();
		Scanner fileReader = new Scanner(new File(filePath)); //Öffne neuen Scanner
		while (fileReader.hasNext()) {
			String row = fileReader.nextLine();
			rowOfMatrix.add(row);
		}
		
		//Bereit Zählung der Spalten vor in dem der Scanner instanziert wird. 
		Scanner parseLine = new Scanner(rowOfMatrix.get(0));
		parseLine.useDelimiter(" ");
		
		//Closing the readers
		parseLine.close();
		fileReader.close();
		int[][] out = new int[9][9]; //Instanziere leere Matrix
		
		for (int i = 0; i < rowOfMatrix.size();i++) {
			//Übernehme alle Werte aus der Arraylist in die Matrix mit Parser vom Scanner. 
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
		//converts a given Array to a String
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
}
