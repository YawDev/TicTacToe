package com.tic.tac.toe.game;

import java.time.LocalDate;
import java.util.Random;

import javax.swing.JOptionPane;

import com.tic.tac.toe.board.Board;
import com.tic.tac.toe.board.ComputerOpponent;
import com.tic.tac.toe.board.IPlayer;
import com.tic.tac.toe.board.PlayerOne;
import com.tic.tac.toe.board.PlayerTwo;
import com.tic.tac.toe.exceptions.OccupiedException;

public class Game {
	
	protected Board _board = new Board();
	protected boolean againstComputer = false;
	protected boolean isRunning = false;
	PlayerOne playerOne; PlayerTwo playerTwo; ComputerOpponent computer;
	String[] boardSlots =  {"1","2","3","4","5","6","7","8","9"};
	
	public void Start() throws OccupiedException
	{
		isRunning = true;
		_board.CreateBoard();
		
		do
		{	
			Run();
		}
		while(isRunning);
	}
	
	public void Run()
	{
		Object[] options = {"Two Player", "One Player"};
		Object selectedValue =JOptionPane.showInputDialog(null, "Select Game Mode"+"\n" , "Tic Tac Toe", 
				JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
		
		if(selectedValue == null)
		{
			isRunning = false;
			System.exit(0);
		}
		switch(selectedValue.toString())
		{
			case "Two Player":
				SetupTwoPlayers();
				GameInAction();
				break;
			case "One Player":
				SetupAgainstComputerOpponent();
				GameInAction();
				break;
		}
	}
	
	
	public void GameInAction()
	{
		boolean gameInSession = true;
		while(gameInSession)
		{
			PlayerOneTurn();
			ShowBoard();
			_board.CheckWinOrDraw(playerOne);

			
			if(againstComputer)
			{
				ComputerTurn();
				ShowBoard();
				_board.CheckWinOrDraw(computer);
			}
			else
			{
				PlayerTwoTurn();
				ShowBoard();
				_board.CheckWinOrDraw(playerTwo);
			}
		}
	}
	
	public void ShowBoard()
	{
		JOptionPane.showMessageDialog(null, _board.Display());
	}
	
	
	
	public void SetupTwoPlayers()
	{
		SetPlayerOne();
		SetPlayerTwo();
	}
	
	public void SetupAgainstComputerOpponent()
	{
		SetPlayerOne();
		SetComputerMarker();
		againstComputer = true;
		JOptionPane.showMessageDialog(null, "Computer Opponent: "+computer.get_marker());

	}
	
	
	
	public void SetPlayerOne()
	{
		String[] options = {"x", "o"};
		String marker =(String) JOptionPane.showInputDialog(null, "Select Player One", "Tic Tac Toe", 
				JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
		playerOne = new PlayerOne();		
		playerOne.set_marker(marker.charAt(0));
	}
	
	public void SetPlayerTwo()
	{
		String[] options = {"x"};
		if(playerOne.get_marker() == 'x')
			options[0] = "o";

		String marker =(String) JOptionPane.showInputDialog(null, "Select Player Two", "Tic Tac Toe", 
				JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
		playerTwo = new PlayerTwo();
		playerTwo.set_marker(marker.charAt(0));
	}
	
	
	public char SetComputerMarker()
	{
		computer = new ComputerOpponent();
		if(playerOne.get_marker() == 'x')
		{	computer.set_marker('o'); return computer.get_marker();}
		
		computer.set_marker('x');
		return computer.get_marker();
	}
	
	
	public void PlayerOneTurn()
	{
		playerOne.setTurn(true);
		while(playerOne.isTurn())
		{
			String slot =(String) JOptionPane.showInputDialog(null,_board.Display(), "Player Ones's Turn: "+playerOne.get_marker(), 
				JOptionPane.INFORMATION_MESSAGE, null,
				boardSlots, boardSlots[0]);
			if(slot == null)
				System.exit(0);
			
			try {
				
				_board.SlotSelect(playerOne.get_marker(),slot);
				int selection = Integer.parseInt(slot);
				_board.DisableSpot(boardSlots, selection);
				playerOne.setTurn(false);
			} catch (OccupiedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		}
		
	}
	
	public void PlayerTwoTurn()
	{
		playerTwo.setTurn(true);
		while(playerTwo.isTurn())
		{
			String slot =(String) JOptionPane.showInputDialog(null, _board.Display(),"Player Two's Turn: "+playerTwo.get_marker(),
				JOptionPane.INFORMATION_MESSAGE, null,
				boardSlots, boardSlots[0]);
			if(slot == null)
				System.exit(0);
			
			try {
				_board.SlotSelect(playerTwo.get_marker(),slot);
				int selection = Integer.parseInt(slot);
				_board.DisableSpot(boardSlots, selection);
				playerTwo.setTurn(false);
			} catch (OccupiedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		}
	}
		
		public void ComputerTurn()
		{
			computer.setTurn(true);
			while(computer.isTurn())
			{
				_board.ComputerMove(computer.get_marker(),boardSlots);
				computer.setTurn(false);
			}
		}
}
