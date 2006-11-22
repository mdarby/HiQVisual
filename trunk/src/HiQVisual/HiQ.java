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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class HiQ implements GameConstants {
  private Vector visitStack = new Vector();
  private Vector direction = new Vector();
  

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
    if(board.isPeg(new Position(3,3))){
      String str = board.toString();
      int pegCount =0;

      for(int i=0; i<str.length(); i++){
	if(str.charAt(i) == PEG){
	  pegCount++;
	}
      }
      
      return pegCount == 1 ? true : false;
    }
    return false;
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
  private void run(Board board){
    
    visitStack.add(board.toString()); //mark source board as visited
    
    for(int j=0; j<MAX_COLS; j++){
      for(int k=0; k<MAX_ROWS; k++){
	for(int x=0; x<direction.size(); x++){
	  Position curr_pos = new Position(j,k);
	  String curr_dir = (String)direction.elementAt(x);
	  
	  if(board.isJumpValid(curr_pos,curr_dir)){
	    board.jump(curr_pos,curr_dir); //update board with this possible move
	    
	    String possible_board = board.toString();
	      
	    if(notVisited(possible_board)){
	      if(gameFinished(board)){
		board.show();
		System.exit(0);
	      } else{
		run(new Board(possible_board, board.getList()));
	      }
	    }
	    
	    board.goBack(curr_pos,curr_dir);
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
** Description: Starts the main algorithm containing the game logic
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
    run(new Board());
  }
  
/**************************************************************************
**
** Name: startGame(Board)
**
** Description: Starts the main algorithm containing the game logic with an 
**              existing board configuration
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
  public void startGame(Board b){
    run(b);
  }  

/**************************************************************************
**
** Name: Main()
**
** Description: Starts the HiQ game
**
** Parameters
**
** INPUT:
**    args * Any command line parameters (not used)
**
** OUTPUT:
**    None
**
**************************************************************************/  
  public static void main(String[] args) {
    HiQ hiq = new HiQ();
    
    if(args.length > 0){
      BufferedReader in;
      try {
        in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF8"));
        hiq.startGame(new Board(in));
      } catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
      } catch (FileNotFoundException ex) {
        System.out.println(args[0] + " does not exist!");
        System.exit(1);
      }
    }
    else{
      hiq.startGame();
    }
    System.out.println("The board is not solveable from this starting point!");
  }
}