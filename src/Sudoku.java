

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
	
	private int [][] field = new int[9][9];
	private ArrayList<Integer>[][] possVals = new ArrayList [9][9];
	//[row][column]
	
	
	public Sudoku() {
		for (int i = 0;i<9;i++) {
			for (int j = 0;j<9;j++) {
				this.field[0][0] = 0;
			}
		}
		setPossVals();
	}
	
	public Sudoku(int[][] field) {
		//Konstruktor with given int[][] from Input
		try {
			this.field = field;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//Making the ArrayLists in the Array
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				this.possVals[i][j] = new ArrayList<Integer>();
			}
		}
		setPossVals();
	}
	
	public void setField(int row, int col, int val) {
		this.field[row][col] = val;
		setPossVals();
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
	
	public boolean checkRowColBlock(int row, int col, int val) {
		//returns true if the value is contained in the Block, Row or Col. 
		boolean trueRow = (SudokuTest.arraySearch(getRow(row), val));
		boolean trueCol = (SudokuTest.arraySearch(getCol(col), val));
		int[][] block = getBlock(row, col);
		boolean trueBlock = ((SudokuTest.arraySearch(block[0], val)) ||
				(SudokuTest.arraySearch(block[1], val)) ||
				(SudokuTest.arraySearch(block[2], val)));
		
		return (trueRow || trueCol || trueBlock);
	}
	
	public ArrayList<Integer> [][] getPossVals() {
		return this.possVals;
	}
	
	public void setPossVals() {
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				possValField(i, j);
			}
		}
	}
	
	private void possValField(int row, int col) {
		//setting the possible value for a field
		//based on feedback of checkRowColBlock
		this.possVals[row][col].clear();
		if (this.field[row][col] != 0) {
			this.possVals[row][col].add(-1);
		}
		else {
			for (int i = 1; i<10;i++) {
				if (checkRowColBlock(row, col, i) == false) {
					this.possVals[row][col].add(i);
				}
				else {
					continue;
				}
			}
		}
	}
	
	public boolean isSolved() {
		boolean solved = true;
		
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				if (((getPossVals()[i][j]).get(0) != -1)){
					solved = false;
				}
			}
		}
		return solved;
	}
	
	
	public void solve() {
		
		while (!isSolved()) {
			for (int i2 = 0; i2<9;i2++) {
				for (int j2 = 0; j2<9;j2++) {
					if (((getPossVals()[i2][j2]).size() == 1) && (getPossVals()[i2][j2]).get(0) != -1) {
						this.field[i2][j2] = getPossVals()[i2][j2].get(0);
					}
				}
			}
			setPossVals();
		}
	}
	
	public int[][] getCopy() {
		int[][] copy = this.field;
		return copy;
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
