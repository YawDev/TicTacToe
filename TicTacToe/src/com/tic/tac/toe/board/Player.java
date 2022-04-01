package com.tic.tac.toe.board;

public class Player implements IPlayer{
	
	protected char _marker;
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	protected String Name;
	
	protected boolean isTurn;

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public char get_marker() {
		return _marker;
	}

	public void set_marker(char _marker) {
		this._marker = _marker;
	}
}
