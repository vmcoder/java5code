package com.game.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Combine everything you've learned so far to write a very simple maze game.
 *  (The maze is simple, not the program! Compare to Rogue and NetHack.)
 *  Overview
 *  Write a turn-based maze game.
 *  The maze is a simple 12x8 rectangle with a single exit.(So it's really just a big room, not a maze.) 
 *  The player is represented by a single character on the screen.
 *  The player starts at a random place somewhere in the maze-room, and the 
 *  exit is at some random location on the right/east side of the maze-room.
 *  Each turn, print the current map of the maze-room and ask the user which direction they want to move.
 *  The game ends when the player reaches the exit. Print some congratulatory message and end the program.
 *  
 *  Requirements
 *  Drawing the map. The map must be the requested size, but which characters you use for the player, 
 *  exit, and floor are up to you (as long as each is different). Here is what the maze might look 
 *  like on the screen using '*' for the player, '=' for the exit, and '.' for the room floor.
 *  
 *  @author vilpesh
 */
public class MazeMain {
	
	public static final String SYMBOL_PLAYER = "*";
	public static final String SYMBOL_EXIT = "X";
	public static final String SYMBOL_FLOOR_CHAR = "_";
	public static final String SYMBOL_UP = "n";
	public static final String SYMBOL_DOWN = "s";
	public static final String SYMBOL_LEFT = "e";
	public static final String SYMBOL_RIGHT = "w";
	public static final String SYMBOL_QUIT = "q";
	
	public static final String CHAR_FSLASH = "/";
	public static final String CHAR_COLON = ":";

	public static final int GAME_END = 0;
	public static final int GAME_PROGRESS = 1;
	
	public static final int MAZE_HEIGHT = 8;
	public static final int MAZE_WIDTH = 12;
	public static final int MAZE_INCREMENT_DECREMENT = 1;
	
	private String[][] maze2dArray = null;
	
	void initializeMaze(String[][] maze2dArray) {
		for (int i = 0; i < MazeMain.MAZE_HEIGHT; i++) {
			for (int j = 0; j < MazeMain.MAZE_WIDTH; j++) {
				maze2dArray[i][j] = MazeMain.SYMBOL_FLOOR_CHAR;
				if(i == 3 & j == 5)
					maze2dArray[i][j] = MazeMain.SYMBOL_PLAYER;
				if(i == 4 & j == 11)
					maze2dArray[i][j] = MazeMain.SYMBOL_EXIT;
				/*else
					maze2dArray[i][j] = MazeMain.SYMBOL_FLOOR_CHAR;*/
			}
		}
	}
	
	void displayMaze(String[][] maze2dArray) {
		for (int i = 0; i < MazeMain.MAZE_HEIGHT; i++) {
			System.out.print("\n");
			for (int j = 0; j < MazeMain.MAZE_WIDTH; j++) {
				System.out.print(maze2dArray[i][j]);
			}
		}
	}
	
	int processInput(String[][] maze2dArray,String inValue) {
		int oldX = 0 ,oldY = 0;
		for (int i = 0; i < MazeMain.MAZE_HEIGHT; i++) {
			for (int j = 0; j < MazeMain.MAZE_WIDTH; j++) {
				if(MazeMain.SYMBOL_PLAYER.equals(maze2dArray[i][j])) {
					System.out.println("Old player position x--> " + i + " y--> " + j);
					oldX = i;
					oldY = j;
					break;
				}
			}
		}
		
		if(inValue.equals(MazeMain.SYMBOL_UP)) {
			if((oldX - MazeMain.MAZE_INCREMENT_DECREMENT) >= 0) {
				if(MazeMain.SYMBOL_EXIT.equals(maze2dArray[oldX - MazeMain.MAZE_INCREMENT_DECREMENT][oldY])) {
					System.out.println("Congrats end of Game!!");
					return MazeMain.GAME_END;
				} else {
					maze2dArray[oldX][oldY] = MazeMain.SYMBOL_FLOOR_CHAR;
					maze2dArray[oldX - MazeMain.MAZE_INCREMENT_DECREMENT][oldY] = MazeMain.SYMBOL_PLAYER;
				}
			}
		} else if(inValue.equals(MazeMain.SYMBOL_DOWN)) {
			if((oldX + MazeMain.MAZE_INCREMENT_DECREMENT) < MazeMain.MAZE_HEIGHT) {
				if(MazeMain.SYMBOL_EXIT.equals(maze2dArray[oldX + MazeMain.MAZE_INCREMENT_DECREMENT][oldY])) {
					System.out.println("Congrats end of Game!!");
					return MazeMain.GAME_END;
				} else {
					maze2dArray[oldX][oldY] = MazeMain.SYMBOL_FLOOR_CHAR;
					maze2dArray[oldX + MazeMain.MAZE_INCREMENT_DECREMENT][oldY] = MazeMain.SYMBOL_PLAYER;
				}
			}
		} else if(inValue.equals(MazeMain.SYMBOL_LEFT)) {
			if((oldY - MazeMain.MAZE_INCREMENT_DECREMENT) >= 0) {
				if(MazeMain.SYMBOL_EXIT.equals(maze2dArray[oldX][oldY - MazeMain.MAZE_INCREMENT_DECREMENT])) {
					System.out.println("Congrats end of Game!!");
					return MazeMain.GAME_END;
				} else {
					maze2dArray[oldX][oldY] = MazeMain.SYMBOL_FLOOR_CHAR;
					maze2dArray[oldX][oldY - MazeMain.MAZE_INCREMENT_DECREMENT] = MazeMain.SYMBOL_PLAYER;
				}
			}
		} else if(inValue.equals(MazeMain.SYMBOL_RIGHT)) {
			if((oldY + MazeMain.MAZE_INCREMENT_DECREMENT) < MazeMain.MAZE_WIDTH) {
				if(MazeMain.SYMBOL_EXIT.equals(maze2dArray[oldX][oldY + MazeMain.MAZE_INCREMENT_DECREMENT])) {
					System.out.println("Congrats end of Game!!");
					return MazeMain.GAME_END;
				} else {
					maze2dArray[oldX][oldY] = MazeMain.SYMBOL_FLOOR_CHAR;
					maze2dArray[oldX][oldY + MazeMain.MAZE_INCREMENT_DECREMENT] = MazeMain.SYMBOL_PLAYER;
				}
			}
		}
		
		return MazeMain.GAME_PROGRESS;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] maze2dArray = new String [MazeMain.MAZE_HEIGHT][MazeMain.MAZE_WIDTH];
		MazeMain mzMain = new MazeMain();
		mzMain.initializeMaze(maze2dArray);
		
		
		String inVal = "n";
		MazeError mzError = new MazeError();
		InputStreamReader inStrRea = new InputStreamReader(System.in);
		BufferedReader bufInpRea =  new BufferedReader(inStrRea);
		StringBuffer strBufMsg = new StringBuffer("\nPlease input from ");
		strBufMsg.append(MazeMain.SYMBOL_UP).append(MazeMain.CHAR_FSLASH);
		strBufMsg.append(MazeMain.SYMBOL_DOWN).append(MazeMain.CHAR_FSLASH);
		strBufMsg.append(MazeMain.SYMBOL_LEFT).append(MazeMain.CHAR_FSLASH);
		strBufMsg.append(MazeMain.SYMBOL_RIGHT);
		strBufMsg.append(" ").append(MazeMain.CHAR_COLON).append(" ");
		try {
			while(!inVal.equals(MazeMain.SYMBOL_QUIT)) {
				mzMain.displayMaze(maze2dArray);
				System.out.print(strBufMsg.toString());
				inVal = bufInpRea.readLine();
				if (inVal != null) {
					if(MazeError.NO_ERROR == mzError.isValidInput(inVal)) {
						if(MazeMain.GAME_END == mzMain.processInput(maze2dArray,inVal))
							inVal = MazeMain.SYMBOL_QUIT;
					} else {
						System.out.println("No Change.");
					}
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufInpRea = null;
			inStrRea = null;
			strBufMsg = null;
			mzError = null;
			maze2dArray = null;
		}
	}
	
	/**
	 * @return the maze2dArray
	 */
	public String[][] getMaze2dArray() {
		return maze2dArray;
	}
	
	/**
	 * @param maze2dArray the maze2dArray to set
	 */
	public void setMaze2dArray(String[][] maze2dArray) {
		this.maze2dArray = maze2dArray;
	}

}
