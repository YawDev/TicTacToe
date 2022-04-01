package com.tic.tac.toe.board;

import com.tic.tac.toe.exceptions.OccupiedException;
import com.tic.tac.toe.game.Game;

public class program {

	public static void main(String[] args) {
	
		try {
			Game game = new Game();
			game.Start();
		}
		catch (Exception e) {
		// TODO Auto-generated catch block
			System.out.println("Something went wrong.");
		}
		
	}

}
