package Sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuTest {
	//Test
	public static void main(String[] args) throws FileNotFoundException {
		Sudoku s1 = new Sudoku(readSudoku("src/Sudoku/SudokuA.txt"));
		System.out.println(s1.toString());
		
		System.out.println(arraytoString(s1.getBlock(0, 0)));
	}
	
	
	
	
	
	
	public static int[][] readSudoku(String filePath) throws FileNotFoundException {
		
		//Liest eine Matrix aus einem file mit Pfad filePath ein
		ArrayList<String> rowOfMatrix = new ArrayList<String>();
		Scanner fileReader = new Scanner(new File(filePath)); //�ffne neuen Scanner
		while (fileReader.hasNext()) {
			String row = fileReader.nextLine();
			rowOfMatrix.add(row);
		}
		
		//Bereit Z�hlung der Spalten vor in dem der Scanner instanziert wird. 
		Scanner parseLine = new Scanner(rowOfMatrix.get(0));
		parseLine.useDelimiter(" ");
		
		//Closing the readers
		parseLine.close();
		fileReader.close();
		int[][] out = new int[9][9]; //Instanziere leere Matrix
		
		for (int i = 0; i < rowOfMatrix.size();i++) {
			//�bernehme alle Werte aus der Arraylist in die Matrix mit Parser vom Scanner. 
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
}
