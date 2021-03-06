package com.tic.tac.toe.board;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JOptionPane;

import com.tic.tac.toe.exceptions.OccupiedException;

public class Board {
	
	char[][] Board;
	boolean Winner = false;
	boolean Draw = false;
 	
	public boolean isWinner() {
		return Winner;
	}

	public void setWinner(boolean winner) {
		Winner = winner;
	}

	public boolean isDraw() {
		return Draw;
	}

	public void setDraw(boolean draw) {
		Draw = draw;
	}


	
	public Board() {
		super();
		Board = new char[3][3];
	}

	public char[][] CreateBoard()
	{
		for(int row =0;row<3;row++ )
		{	for(int col=0;col <3;col++)
			{
				Board[row][col] = '_';
			}
		}
		return Board;
	}
	
	public String Display()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<Board.length;i++)
		{	for(int j=0;j<Board.length;j++)
			{
				sb.append(Board[i][j]).append("     ");
			}
			sb.append("\n").append("\n");
		}
		return sb.toString();
	}
	
	
	public void AssignPosition(char marker,int row, int column) throws OccupiedException
	{
	
		if(slotIsEmpty(row,column))
		{	
			Board[row][column] = marker; return;
		}
		throw new OccupiedException("Slot is already filled.");
		
	}
	
	public void AssignComputerPosition(char marker,int row, int column) 
	{
		
		Board[row][column] = marker; return;
		
	}
	
	
	public void SlotSelect(char marker ,String slot) throws OccupiedException
	{
		switch(slot)
		{
			case "1":
				AssignPosition(marker, 0,0);
				break;
			case "2":
				AssignPosition(marker, 0,1);
				break;
			case "3":
				AssignPosition(marker, 0,2);
				break;
			case "4":
				AssignPosition(marker, 1,0);
				break;
			case "5":
				AssignPosition(marker, 1,1);
				break;
			case "6":
				AssignPosition(marker, 1,2);
				break;
			case "7":
				AssignPosition(marker, 2,0);
				break;
			case "8":
				AssignPosition(marker, 2,1);
				break;
			case "9":
				AssignPosition(marker, 2,2);
				break;
			case "Spot is occupied.":
				throw new OccupiedException("Slot is already filled.");
	
		}
	}
	
	public void ComputerMove(char marker, String[] boardSlots) 
	{
		Random rand = new Random();
		int selection = rand.nextInt(1, 10);
		boolean isEmpty = ComputerSelectionOccupied(selection);
		while(!isEmpty)
		{
			selection = rand.nextInt(1, 10);
			isEmpty =  ComputerSelectionOccupied(selection);
		}
		String slot = String.valueOf(selection);
		switch(slot)
		{
			case "1":
					AssignComputerPosition(marker, 0,0);
					DisableSpot(boardSlots, selection);
					break;
			case "2":
					AssignComputerPosition(marker, 0,1);
					DisableSpot(boardSlots, selection);
					break;
			case "3":
					AssignComputerPosition(marker, 0,2);
					DisableSpot(boardSlots, selection);
					break;
			case "4":
				AssignComputerPosition(marker, 1,0);
				DisableSpot(boardSlots, selection);
				break;
			case "5":
					AssignComputerPosition(marker, 1,1);
					DisableSpot(boardSlots, selection);
					break;
			case "6":
					AssignComputerPosition(marker, 1,2);					
					DisableSpot(boardSlots, selection);
					break;
			case "7":
					AssignComputerPosition(marker, 2,0);
					DisableSpot(boardSlots, selection);
					break;
			case "8":
			
					AssignComputerPosition(marker, 2,1);
					DisableSpot(boardSlots, selection);
					break;
			case "9":
					AssignComputerPosition(marker, 2,2);
					DisableSpot(boardSlots, selection);
					break;

		}
	}
	
	public boolean ComputerSelectionOccupied(int selection)
	{
		boolean isEmpty = false;
		String slot = String.valueOf(selection);
		switch(slot)
		{
			case "1":
					if(slotIsEmpty(0,0))
						isEmpty = true;
					break;
			case "2":
					if(slotIsEmpty(0,1))
						isEmpty = true;
					break;
			case "3":
					if(slotIsEmpty(0,2))
						isEmpty = true;
					break;
			case "4":
				if(slotIsEmpty(1,0))
					isEmpty = true;
				break;
			case "5":
				if(slotIsEmpty(1,1))
					isEmpty = true;
					break;
			case "6":
				if(slotIsEmpty(1,2))
					isEmpty = true;
					break;
			case "7":
				if(slotIsEmpty(2,0))
					isEmpty = true;
					break;
			case "8":
				if(slotIsEmpty(2,1))
					isEmpty = true;
					break;
			case "9":
				if(slotIsEmpty(2,1))
					isEmpty = true;
				break;
		}
		return isEmpty;
	}
	
	public void DisableSpot(String[] boardSlots, int selection)
	{
		boardSlots[selection-1] = "Spot is occupied.";

	}
	
	
	public void CheckWinOrDraw(IPlayer player)
	{
		boolean horizontalWin = HorizontalWinner(player.get_marker());
		boolean verticalWin = VerticalWinner(player.get_marker());
		boolean diagonalWin = DiagonalWinner(player.get_marker());
		
		
		if(horizontalWin)
		{
			Winner = true;
			JOptionPane.showMessageDialog(null, player.getName() + " has won."); 
			System.exit(0);

		}
		
		if(diagonalWin)
		{	
			Winner = true;
			JOptionPane.showMessageDialog(null,player.getName() + " has won."); 
			System.exit(0);

		}
		
		if(verticalWin)
		{	
			Winner = true;
			JOptionPane.showMessageDialog(null,player.getName() + " has won.");
			System.exit(0);

		}
		
		if(IsDraw())
		{				
			JOptionPane.showMessageDialog(null, "Draw!.");
			System.exit(0);
		}

	}
	
	
	
	
	public boolean slotIsEmpty(int row,int column)
	{
		return (Board[row][column] == '_');
	}
	
	public boolean HorizontalWinner(char marker)
	{
		if((Board[0][0] == marker) && Board[0][1] == marker && Board[0][2] == marker)
			return true;
		
		if((Board[1][0] == marker) && Board[1][1] == marker && Board[1][2] == marker)
			return true;
		
		if((Board[2][0] == marker) && Board[2][1] == marker && Board[2][2] == marker)
			return true;
		
		return false;
	}
	
	public boolean VerticalWinner(char marker)
	{
		if((Board[0][0] == marker) && Board[1][0] == marker && Board[2][0] == marker)
			return true;
		
		if((Board[0][1] == marker) && Board[1][1] == marker && Board[2][1] == marker)
			return true;
		
		if((Board[0][2] == marker) && Board[1][2] == marker && Board[2][2] == marker)
			return true;
		
		return false;
	}
	
	public boolean DiagonalWinner(char marker)
	{
		if((Board[0][0] == marker) && Board[1][1] == marker && Board[2][2] == marker)
			return true;
		
		if((Board[2][0] == marker) && Board[1][1] == marker && Board[0][2] == marker)
			return true;
		
		return false;
	}
	
	public boolean IsDraw()
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				if(Board[i][j] == '_') 
					return Draw;
			}
		setDraw(true);
		return Draw;
	}

}
