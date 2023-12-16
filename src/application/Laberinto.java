package application;

import java.util.Random;
import java.util.ArrayList;



public class Laberinto {
	private boolean[][] mazeBoard;
	private int boardSize = 30;
	private int entryCol = 15;
	private int entryRow = boardSize-1;
	private int exitCol = 15;
	private int exitRow = 0;
	private int cursorCol;
	private int cursorRow;
	private ArrayList<Coordinate> coordList = new ArrayList<Coordinate>();
	
	public void initLaberinto() {
		startMaze(boardSize);
		generateMaze();
	}
	
	public void startMaze(int size) {
		mazeBoard = new boolean [size] [size];
	}
	
	public void generateMaze() {
		for (int i = 0; i< mazeBoard.length; i++) {
			for (int j = 0; j< mazeBoard[i].length; j++) {
				mazeBoard[i][j] = true;
			}
		}
		mazeBoard[entryRow][entryCol] = false;
		mazeBoard[exitRow][exitCol] = false;
		createMaze();
	}
	
	public void createMaze() {
		cursorRow = entryRow;
		cursorCol = entryCol;
		createSolution();
		ramificate();
	}
	
	public void createSolution() {
		for (int i = 0; i < 50; i++) {
			if (!randomMove())
				break;
			else {
				coordList.add(new Coordinate(cursorRow,cursorCol));
			}
		}
		stepBack();
		while(!towardsExit()) {
			stepBack();
		}
		pathToExit();
		
	}
	
	public boolean towardsExit() {
		int tempRow = cursorRow;
		int tempCol = cursorCol;
		double distance = calculateDistanceToExit(tempRow,tempCol);
		int direccion = -1;
		boolean first = true;
		while (distance != 0) {
			if (calculateDistanceToExit(tempRow-1,tempCol) < distance) { //Up
				distance = calculateDistanceToExit(tempRow-1,tempCol);
				direccion = 0;
			}
			if (calculateDistanceToExit(tempRow+1,tempCol) < distance) { // Down
				distance = calculateDistanceToExit(tempRow+1,tempCol);
				direccion = 1;
			}
			if (calculateDistanceToExit(tempRow,tempCol-1) < distance)  {//Left
				distance = calculateDistanceToExit(tempRow,tempCol-1);
				direccion = 2;
			}
			if (calculateDistanceToExit(tempRow,tempCol+1) < distance) { //Left
				distance = calculateDistanceToExit(tempRow,tempCol+1);
				direccion = 3;
			}
			switch (direccion) {
			case 0: tempRow--;
			break;
			
			case 1: tempRow++;
			break;
			
			case 2: tempCol--;
			break;
			
			case 3: tempCol++;
			break;
			
			default: break;
			}
			if(first) {
				if (!validate(tempRow,tempCol) || !checkAround(tempRow,tempCol,1)) {
					return false;
				}
				first = false;
			}
			else {
				if (!validate(tempRow,tempCol) || !checkAround(tempRow,tempCol,0)) {
					return false;
				}
			}
			
		}
		return true;
	}
	
	public void pathToExit() {
		double distance = calculateDistanceToExit(cursorRow,cursorCol);
		int direccion = -1;
		while (distance != 0) {
			if (calculateDistanceToExit(cursorRow-1,cursorCol) < distance) { //Up
				distance = calculateDistanceToExit(cursorRow-1,cursorCol);
				direccion = 0;
			}
			if (calculateDistanceToExit(cursorRow+1,cursorCol) < distance) { // Down
				distance = calculateDistanceToExit(cursorRow+1,cursorCol);
				direccion = 1;
			}
			if (calculateDistanceToExit(cursorRow,cursorCol-1) < distance)  {//Left
				distance = calculateDistanceToExit(cursorRow,cursorCol-1);
				direccion = 2;
			}
			if (calculateDistanceToExit(cursorRow,cursorCol+1) < distance) { //Left
				distance = calculateDistanceToExit(cursorRow,cursorCol+1);
				direccion = 3;
			}
			switch (direccion) {
			case 0: cursorRow--;
			break;
			
			case 1: cursorRow++;
			break;
			
			case 2: cursorCol--;
			break;
			
			case 3: cursorCol++;
			break;
			
			default: break;
			}
			mazeBoard[cursorRow][cursorCol] = false;
			coordList.add(new Coordinate(cursorRow,cursorCol));
		}
	}
	
	public void stepBack() {
		cursorRow = coordList.get(coordList.size()-1).getX();
		cursorCol = coordList.get(coordList.size()-1).getY();
		mazeBoard[cursorRow][cursorCol] = true;
		coordList.remove(coordList.size()-1);
		cursorRow = coordList.get(coordList.size()-1).getX();
		cursorCol = coordList.get(coordList.size()-1).getY();
	}
	
	public void ramificate() {
		int steps = 30;
		for (int i = 0; i < coordList.size(); i++) {
			cursorRow = coordList.get(i).getX();
			cursorCol = coordList.get(i).getY();
			for (int j = 0; j < steps; j++) {
				if (!randomMove())
					break;
			}	
		}
		for (int i = 1; i < boardSize-2;i++) {
			for (int j = 1;j < boardSize-2;j++ ) {
				if (!getBoardPos(i, j)) {
					cursorRow = i;
					cursorCol  = j;
					for (int k = 0; k < steps; k++) {
						if (!randomMove())
							break;
					}	
				}
			}
		}
	}
	
	public boolean randomMove() {
		Random rand = new Random();
		int dir1, dir2, dir3,dir4;
		
		//Generate random direction sequence
		dir1= rand.nextInt(4);
		do {
			dir2= rand.nextInt(4);
		}while (dir2 == dir1);
		do {
			dir3 = rand.nextInt(4);
		}while (dir3 == dir2 || dir3 == dir1);
		do {
			dir4 = rand.nextInt(4);
		}while (dir4 == dir3 || dir4 == dir2 || dir4 == dir1);
		
		if(!tryMove(dir1))
			if(!tryMove(dir2))
				if(!tryMove(dir3))
					if(!tryMove(dir4))
						return false;
		return true;
	}
	
	public boolean tryMove(int dir) {
		int tempRow = cursorRow;
		int tempCol = cursorCol;
		switch(dir) {
			case 0: //UP
				tempRow--;
				if (validate(tempRow,tempCol)) {
					if (checkAround(tempRow,tempCol,1)) {
						createPath(tempRow,tempCol);
						return true;
					}
				}
				return false;
			case 1: //DOWN
				tempRow++;
				if (validate(tempRow,tempCol)) {
					if (checkAround(tempRow,tempCol,1)) {
						createPath(tempRow,tempCol);
						return true;
					}
				}
				return false;
			case 2: //LEFT
				tempCol--;
				if (validate(tempRow,tempCol)) {
					if (checkAround(tempRow,tempCol,1)) {
						createPath(tempRow,tempCol);
						return true;
					}
				}
			default: //RIGHT
				tempCol++;
				if (validate(tempRow,tempCol)) {
					if (checkAround(tempRow,tempCol,1)) {
						createPath(tempRow,tempCol);
						return true;
					}
				}
				return false;
		}
	}
	
	public void createPath (int row, int col) {
		cursorRow = row;
		cursorCol = col;
		mazeBoard[cursorRow][cursorCol] = false;
	}
	
	public boolean validate (int row, int col) {
		if (row >= 1 && row < boardSize-1) {//Check if in bounds
			if (col >= 1 && col < boardSize-1) {
				if (getBoardPos(row,col)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkAround(int row, int col, int tolerance){
		int contPath = 0;
		int tempRow = row;
		int tempCol = col;
		//Check up
		tempRow--;
		if (tempRow >= 1 && tempRow < boardSize-1) {// Check if in bounds
			if (!getBoardPos(tempRow,tempCol)) {
				contPath++;
			}
		}
		tempRow = row;//Reset TempRow
		//Check down
		tempRow++;
		if (tempRow >= 1 && tempRow < boardSize-1) {// Check if in bounds
			if (!getBoardPos(tempRow,tempCol)) {
				contPath++;
			}
		}
		tempRow = row;//Reset TempRow
		//Check Left
		tempCol--;
		if (tempCol >= 1 && tempCol < boardSize-1) {// Check if in bounds
			if (!getBoardPos(tempRow,tempCol)) {
				contPath++;
			}
		}
		tempCol = col;//Reset TempCol
		//Check right
		tempCol++;
		if (tempCol >= 1 && tempCol < boardSize-1) {// Check if in bounds
			if (!getBoardPos(tempRow,tempCol)) {
				contPath++;
			}
		}
		tempCol = col;//Reset TempCol
		if (contPath <= tolerance)
			return true;
		else
			return false;
	}
	
	public double calculateDistanceToExit(int row, int col) {
		if (exitRow == 0)
			return Math.sqrt(Math.pow((exitRow+1-row), 2) + Math.pow((exitCol-col), 2));
		else if (exitRow == boardSize-1)
			return Math.sqrt(Math.pow((exitRow-1-row), 2) + Math.pow((exitCol-col), 2));
		else if (exitCol == 0)
			return Math.sqrt(Math.pow((exitRow-row), 2) + Math.pow((exitCol+1-col), 2));
		else if (exitCol == boardSize-1)
			return Math.sqrt(Math.pow((exitRow-row), 2) + Math.pow((exitCol-1-col), 2));
		else
			return Math.sqrt(Math.pow((exitRow-row), 2) + Math.pow((exitCol-col), 2));
	}
	
	public int getBoardSize() {
		return boardSize;
	}

	public int getEntryCol() {
		return entryCol;
	}

	public int getEntryRow() {
		return entryRow;
	}

	public int getExitCol() {
		return exitCol;
	}

	public int getExitRow() {
		return exitRow;
	}
	
	public boolean getBoardPos(int row, int col) {
        return mazeBoard[row][col];
    }
}
