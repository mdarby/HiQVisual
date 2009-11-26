/***************************************************************************
**
** Programmer: Matt Darby
**
** Date: 2006-10-26
**
** Course: Comp620, Wednesday 06:00 * 09:40 p.m.
**
** Assignment: Project 1D
**
** File Name: /export/home/mdarby/COMP620/Project1D/src/HiQ.java
**
** Description: Class file for HiQ (which describes a HiQ game)
**
** Input: None
**
** Output: None
**
**************************************************************************/

package HiQVisual;

import java.awt.Point;
import java.util.Vector;

public class HiQ implements GameConstants {
  public static Vector <Board>boardStack = new Vector();
  private Vector visitStack = new Vector();
  private Vector direction = new Vector();
  public static boolean gameOver = false;

/**************************************************************************
**
** Name: HiQ()
**
** Description: HiQ constructor
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    A new instance of the HiQ class
**
**************************************************************************/
  public HiQ() {
    direction.add("left");
    direction.add("right");
    direction.add("up");
    direction.add("down");
  }

/**************************************************************************
**
** Name: gameFinished()
**
** Description: Checks the game status
**
** Parameters
**
** INPUT:
**    board * A Board instance to check
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  private boolean gameFinished(Board board){
    Point centerPos = new Point(3,3);
    
    if(board.isPeg(centerPos) && board.pegCount() == 1){
      gameOver = true;
      return true;
    }
    else{
      return false;
    }
  }
 
/**************************************************************************
**
** Name: run()
**
** Description: The main recursive algorithm of the game.
**
** Parameters
**
** INPUT:
**    board * A non-visited Board instance to check for further movements
**
** OUTPUT:
**    None
**
**************************************************************************/  
  private void run(){   
    
    while(!gameOver && !boardStack.isEmpty()){
      Board board = boardStack.lastElement();
      boardStack.removeElementAt(boardStack.size()-1);

      visitStack.add(board.toString()); //mark source board as visited

      board.show();
      
      try {
	Thread.currentThread().sleep(PAUSE);
      } catch (InterruptedException ex) {
	ex.printStackTrace();
      }
      
      for(int j=0; j<MATRIXSIZE; j++){
	for(int k=0; k<MATRIXSIZE; k++){
	  for(int x=0; x<direction.size(); x++){

	    Point currPos = new Point(j,k);
	    String currDir = (String)direction.elementAt(x);

	    if(board.isJumpValid(currPos,currDir)){
	      board.jump(currPos,currDir); //update board with this possible move

	      if(gameFinished(board)){
		board.show();
	      }
	      else{
		String possBoard = board.toString();

		if(notVisited(possBoard)){
		  boardStack.add(new Board(possBoard, board.getList()));
		  board.goBack(currPos,currDir);
		}
	      }
	    }
	  }
	}
      }
    }
  }

/**************************************************************************
**
** Name: notVisited
**
** Description: Checks to see if a board instance has already been visited
**
** Parameters
**
** INPUT:
**    string * This is a string representation of a Board instance
**
** OUTPUT:
**    None
**
**************************************************************************/  
  private boolean notVisited(String string){
    return !visitStack.contains(string) ? true : false;
  }

/**************************************************************************
**
** Name: startGame()
**
** Description: Starts the main algorithm containing the default game logic
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    None
**
**************************************************************************/  
  public void startGame(){
    run();
  }
}