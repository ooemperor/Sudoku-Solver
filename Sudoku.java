package Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
	
	private int [][] field = new int[9][9];
	//[row][column]
	
	private ArrayList<Integer> [][] possVals = new ArrayList [9][9];
	
	public Sudoku() {
		for (int i = 0;i<9;i++) {
			for (int j = 0;j<9;j++) {
				this.field[0][0] = 0;
			}
		}
	}
	
	public Sudoku(int[][] field) {
		//Konstruktor with given int[][] from Input
		try {
			this.field = field;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setField(int row, int col, int val) {
		this.field[row][col] = val;
	}
	
	public int getField(int row, int col) {
		return this.field[row][col];
	}
	
	public int [] getRow(int row) {
		return this.field[row];
	}
	
	public int [] getCol(int col) {
		int [] out = new int[9];
		for (int i = 0;i<9;i++) {
			out[i] = this.field[i][col];
		}
		return out;
	}
	
	public int[][] getBlock(int row, int col){
		//Get the Block for a field. 
		int bRow = row/3;
		int bCol = col/3;
		int [][] out = new int[3][3];
		for (int r = (3*(bRow));r<(3*(bRow) + 3);r++) {
			for (int c = (3*(bCol));c<(3*(bCol) + 3);c++) {
				out[(r%3)][(c%3)] = this.field[r][c];
			}
		}
		return out;
	}
	
	public void setPossVals(int row, int col) {
		//setting the possible value for a field
		for (int i = 0; i<9;i++) {
			if (checkRowColBlock(row, col, i) == true) {
				this.possVals[row][col].add(i);
			}
			else {
				continue;
			}
		}
	}
	
	
	private boolean checkRowColBlock(int row, int col, int val) {
		//returns true if the value is contained in the Block, Row or Col. 
		boolean trueRow = ((Arrays.asList(getRow(row)).contains(val)));
		boolean trueCol = ((Arrays.asList(getCol(col)).contains(val)));
		int[][] block = getBlock(row, col);
		boolean trueBlock = ((Arrays.asList(block[0])).contains(val)) ||
				((Arrays.asList(block[0])).contains(val)) ||
				((Arrays.asList(block[0])).contains(val));
		
		return (trueRow || trueCol || trueBlock);
	}
	
	public String toString() {
		//toString Method for the Board. 
		String out = "\n   ";
		for (int[] row : this.field) {
			for (int val : row) {
				out += (val + " ");
			}
			out += "\n   ";
		}
		return out;
	}
	
	
}
