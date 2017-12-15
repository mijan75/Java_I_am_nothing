/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queen;

/**
 *
 * @author mijan
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueenBoard {
	
	public int numberOfQueen;
	public int board[][];
	public int queenPosition[];
	
	public QueenBoard(int numberOfQueen) {
		queenPosition = new int [numberOfQueen];
		board = new int [numberOfQueen][numberOfQueen];
		this.numberOfQueen = numberOfQueen;
	}
	
	public void placeOfQueen() {
		queenPosition = generateQueen();
		for(int i=0; i<board.length; i++)
			board[queenPosition[i]][i] = 1;
	}
	
	public int[] generateQueen() {
		int positionOfQueen[] = new int[numberOfQueen];
		Random rn = new Random();
		List<Integer> position = new ArrayList<>();
		
		for(int i=0; i<numberOfQueen; i++)
			position.add(rn.nextInt(numberOfQueen));

		for(int i=0; i<position.size(); i++)			
			positionOfQueen[i] = position.get(i);
		return positionOfQueen;
	}
	
	public int h() {
		int totalAttack = 0;
		for(int i=0; i<board.length; i++) {
			int count = 0;
			
			for(int j=0; j<board[0].length; j++)
				if(board[i][j] == 1) {
					count++;
					totalAttack = totalAttack + (count - 1);
				}
		}
		int diagonalAttack = calculateDiagonal();
		return totalAttack + diagonalAttack ;
	}
	
	public int calculateDiagonal() {
		int attack = 0;
		for(int i=0; i<numberOfQueen; i++) {
			for(int j=0; j<numberOfQueen; j++) {
				if(board[i][j] == 1) {
					int nRow = i;
                    int nCol = j;
                    //System.out.println(nRow + " " + nCol);
                    for (int k = 1; k <= numberOfQueen; k++) {  
                        if(nRow + k < numberOfQueen && nCol + k < numberOfQueen){
                            if (board[nRow + k][nCol + k] == 1) {
                                attack++;
                            }
                        }
                        if(nRow + k <numberOfQueen && nCol - k >=0){
                            if (board[nRow + k][nCol - k] == 1) {
                                attack++;
                            }
                        }
                       
                    }
				}
			}
		}
		return attack;
	}
	
	public void moveQueen(int row, int col) {
		board[queenPosition[col]][col] = 2;
		board[row][col] = 1;
	}
	
	public void changeQueen(int row, int col) {
		if(board[row][col] == 1)
			board[row][col] = 0;
	}
	
	public void originalPosition(int col) {
		for(int i=0; i<numberOfQueen; i++) {
			if(board[i][col] == 2)
				board[i][col] = 1;
		}
	}
	
	public void positionOfBestQueen(int col, int bestPosition) {
		for(int i=0; i<numberOfQueen; i++)
			board[i][col] = 0;
		board[bestPosition][col] = 1;
	}
	
	public void printBoard() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				System.out.printf("%d ", board[i][j]);
			}
			System.out.println();
		}
		
	}
}
