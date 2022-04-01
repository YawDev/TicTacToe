package com.tic.tac.toe.board;

public interface IPlayer {
	
	public void setTurn(boolean isTurn);

	public char get_marker();

	public void set_marker(char _marker);
	
	public String getName();
	
	public void setName(String number);
}
