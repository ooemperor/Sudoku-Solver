

import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

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
	
	private boolean checkRowColBlock(int row, int col, int val) {
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
	
	private void setPossVals() {
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
	
	public boolean isSolved() throws IndexOutOfBoundsException {
		boolean solved = true;
		
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				
				//Print statements used for debugging
				//System.out.println(i+ " "+ j);
				//System.out.println(getPossVals()[i][j]);
				if (((getPossVals()[i][j]).get(0) != -1)){
					solved = false;
				}
			}
		}
		return solved;
	}
	
	
	/*
	//commented out because there might be logical problem in this part
	//to be revisited if necessary later. 
	public boolean solve() throws IndexOutOfBoundsException {
		int counter = 0;
		while (!isSolved() && counter < 100) {
			for (int i2 = 0; i2<9;i2++) {
				for (int j2 = 0; j2<9;j2++) {
					if (((getPossVals()[i2][j2]).size() == 1) && (getPossVals()[i2][j2]).get(0) != -1) {
						this.field[i2][j2] = getPossVals()[i2][j2].get(0);
					}
				}
			}
			setPossVals();
			counter++;
		}
		if (!isSolved()) {
			Sudoku copy = this.getCopyOfSudoku();
			for (int i3 = 0; i3<9;i3++) {
				for (int j3 = 0; j3<9;j3++) {
					
					if (((copy.getPossVals()[i3][j3]).size() > 1)) {
						for (int pos = 0; pos< (copy.getPossVals()[i3][j3].size()); pos++) {
							copy = this.getCopyOfSudoku();
							copy.field[i3][j3] = copy.getPossVals()[i3][j3].get(pos);
							copy.setPossVals();
							System.out.println(copy.toString());
							System.out.println(i3 + " "+ j3);
							System.out.println(copy.getPossVals()[i3][j3]);
							try {
								copy.solve();
								if (copy.isSolved() == true) {
									this.field = copy.field;
									this.possVals = copy.possVals;
									return isSolved();
								}	
							}
							catch (IndexOutOfBoundsException e) {
								System.out.println("i skipped one");
								System.out.println(i3 + "" + j3);
								continue;
							}
						}
					}
				}
			}
		}
		return isSolved();
	}
	*/
	
	public boolean solve() {
		while (true) {
			try {
				System.out.println("i try");
				boolean solv = this.trySolve();
				if (solv) {
					break;
				}
			}
			catch (IndexOutOfBoundsException err) {
				System.out.println("Error");
			}
		}
		return true;
	}
	
	public boolean trySolve() throws IndexOutOfBoundsException {
		
		int counter = 0;
		while (!isSolved() && counter < 100) {
			for (int i2 = 0; i2<9;i2++) {
				for (int j2 = 0; j2<9;j2++) {
					if (((getPossVals()[i2][j2]).size() == 1) && (getPossVals()[i2][j2]).get(0) != -1) {
						this.field[i2][j2] = getPossVals()[i2][j2].get(0);
					}
				}
			}
			setPossVals();
			counter++;
		}


		while (!isSolved()) {
			Sudoku copy = this.getCopyOfSudoku();
			Random rand = new Random();
			int row = rand.nextInt(9);
			int col = rand.nextInt(9);
			ArrayList<Integer> possVal = (copy.getPossVals()[row][col]);
			if (( possVal.size() > 1)) {
				copy.field[row][col] = possVal.get(rand.nextInt(possVal.size()));
				copy.setPossVals();
				try {
					if (copy.trySolve() == true) {
						
						this.field = copy.field;
						this.possVals = copy.possVals;
					}
				} catch (Exception er) {
					break;
				}
				
			}
		}
		return isSolved();
	}
		
	private Sudoku getCopyOfSudoku() {
		return this;
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
