package com.game.maze;

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
public class MazeError {
	
	public static final int NO_ERROR = 0;
	public static final int ERROR = 1;
	
	protected int isValidInput(String inData) {
		if (inData != null) {
			if (inData.length() == 0) {
				System.out.println("Enter a valid Character !!");
			} else if (inData.length() > 1) {
				System.out.println("Enter only 1 Character !!");
			} else {
				if(inData.equals(MazeMain.SYMBOL_UP) || inData.equals(MazeMain.SYMBOL_DOWN) || inData.equals(MazeMain.SYMBOL_LEFT) || inData.equals(MazeMain.SYMBOL_RIGHT)) {
						System.out.println("Valid input " + inData);
						return MazeError.NO_ERROR;
				} else {
					System.out.println("InValid input " + inData);
				}
			}
		}
		return MazeError.ERROR;
	}
}
