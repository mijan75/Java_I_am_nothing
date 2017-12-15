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

public class Queen {
	
	public Queen() {
		int numberOfQueen = 8;
		boolean start = true;
		int iterationCount = 0;
		
		while(start) {
			QueenBoard queenBoard = new QueenBoard (numberOfQueen);
			queenBoard.placeOfQueen();
			
			int localMin = queenBoard.h();
			boolean best = false;
			int [] bestQueenPosition = new int [numberOfQueen];
			
			for(int i=0; i<numberOfQueen; i++) {
				best = false;
				for(int j=0; j<numberOfQueen; j++) {
					if(j != queenBoard.queenPosition[i]) {
						queenBoard.moveQueen(j, i);

						if(queenBoard.h() < localMin) {
							localMin = queenBoard.h();
							best = true;
							bestQueenPosition[i] = j;
						}
						queenBoard.changeQueen(j,i);
					}
				}
				queenBoard.originalPosition(i);
				if(best) {
					queenBoard.positionOfBestQueen( i,bestQueenPosition[i]);
				}
			}
			iterationCount++;
			if(queenBoard.h() == 0){
                            start = false;
                            queenBoard.printBoard();
                            System.out.println("Done in iteration " + iterationCount);
                            System.out.println();
                        }
				
			if(iterationCount == 2000)
				start = false;
		}
		
	}
	
	public static void main(String[] args) {
		Queen queen = new Queen();
	}

}

