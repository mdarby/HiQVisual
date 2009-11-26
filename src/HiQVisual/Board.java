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
** File Name: /export/home/mdarby/COMP620/Project1D/src/Board.java
**
** Description: Class file for Board (which describes a HiQ game board)
**
** Input: None
**
** Output: None
**
**************************************************************************/

package HiQVisual;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class Board implements GameConstants {
  private char board[][] = new char[MATRIXSIZE][MATRIXSIZE];
  private Vector jumpList = new Vector();
  private static int time = 0;
 
/**************************************************************************
**
** Name: Board()
**
** Description: Board Constructor
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    New Board instance with no pegs
**
**************************************************************************/    
  public Board(){
    //Initialize entire board at 1
    for(int i=0; i<MATRIXSIZE; i++){
      for(int k=0; k<MATRIXSIZE; k++){
	board[i][k] = HOLE;
      }
    }
    
    //Mark top left and right quads as invalid
    for(int x=0; x<2; x++){
      for(int y=0; y<2; y++){
	board[x][y] = INVALID;
      }
      for(int y=MATRIXSIZE-2; y<MATRIXSIZE; y++){
	board[x][y] = INVALID;
      }
    }
    
    //Mark bottom left and right quads as invalid
    for(int x=MATRIXSIZE-2; x<MATRIXSIZE; x++){
      for(int y=MATRIXSIZE-2; y<MATRIXSIZE; y++){
	board[x][y] = INVALID;
      }
      for(int y=0; y<2; y++){
	board[x][y] = INVALID;
      }
    }   
  }  
  
/**************************************************************************
**
** Name: Board()
**
** Description: Board Constructor
**
** Parameters
**
** INPUT:
**    String string * String version of a Board
**    Vector list * List of previous moves that are associated with the Board
**
** OUTPUT:
**    New Board instance with no pegs
**
**************************************************************************/  
  public Board(String string, Vector list){
    int i,j,k;
    
    for(i=0,j=0,k=0; i<string.length(); i++, k++){
      if(i%(MATRIXSIZE) == 0 && i > 0){
	j++;
	k=0;
      }
      
      board[j][k] = string.charAt(i);
    }

    jumpList.addAll(list);
    
    ++time;
  }
  
/**************************************************************************
**
** Name: Board(BufferedReader)
**
** Description: Board Constructor
**
** Parameters
**
** INPUT:
**    inFile * A FileReader object representing a file containing a stored board.
**
** OUTPUT:
**    New Board instance with stored board configuration
**
**************************************************************************/   
  public Board(BufferedReader inFile){
    try {
      for(int i=0; i<MATRIXSIZE; i++){
        char[] currline = inFile.readLine().toCharArray();
        
        for(int k=0; k<MATRIXSIZE; k++){
          board[i][k] = currline[k];
        }
      }
      
      ++time;
      
    } catch (IOException ex) {
      System.out.println("An error occurred whilst reading file...");
    }
  }
  
/**************************************************************************
**
** Name: clear()
**
** Description: Removes all pegs from a board
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
  public void clear(){
    for(int i=0; i<MATRIXSIZE; i++){
      for(int k=0; k<MATRIXSIZE; k++){
	if(board[i][k] != INVALID){
	  board[i][k] = HOLE;
	}
      }
    }    
  }  
  
/**************************************************************************
**
** Name: getList()
**
** Description: Returns a Board instance's list of previous moves
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    Vector of previous moves
**
**************************************************************************/  
  public Vector getList(){
    return jumpList;
  }
  
/**************************************************************************
**
** Name: getJumpPoint()
**
** Description: Finds the next possible directional movement on a HiQ board
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    A new Point instance
**
**************************************************************************/  
  public Point getJumpPoint(Point pos, String dir){
    int column = (int)pos.getX();
    int row = (int)pos.getY();    
    Point p = new Point(0,0);
    
    if(dir == "left"){
      p.setLocation(column,row-1);
    }
    else if(dir == "right"){
      p.setLocation(column,row+1);
    }
    else if(dir == "up"){
      p.setLocation(column-1,row);
    }
    else if(dir == "down"){
      p.setLocation(column+1,row);
    }
    
    return p;
  }
  
/**************************************************************************
**
** Name: goBack()
**
** Description: Returns a Board instance to a previous state
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    None.
**
**************************************************************************/  
  public void goBack(Point pos, String dir){
    Point over = getJumpPoint(pos,dir);
    Point land = getJumpPoint(over,dir);
    String rev_dir;
    
    if(dir == "left" || dir == "right"){
      rev_dir = dir == "left" ? "right" : "left";
    } else{
      rev_dir = dir == "up" ? "down" : "up";
    }
    
    //Reverse last jump
    jump(land, rev_dir);
    
    //Remove backwards jump from list
    jumpList.removeElementAt(jumpList.size()-1);
    jumpList.removeElementAt(jumpList.size()-1);
  }
  
/**************************************************************************
**
** Name: inBounds()
**
** Description: Checks to see if a coordinate is legal
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  private boolean inBounds(Point pos){
    int column = (int)pos.getX();
    int row = (int)pos.getY();
    
    if((column >= 0 && column < MATRIXSIZE) && (row >= 0 && row < MATRIXSIZE)){
      return true;
    } else{
      return false;
    }
  }
 
/**************************************************************************
**
** Name: initBoard()
**
** Description: Initializes board to default values
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    New Board instance with default values
**
**************************************************************************/  
  public void initBoard(){
    //Initialize entire board at 1
    for(int i=0; i<MATRIXSIZE; i++){
      for(int k=0; k<MATRIXSIZE; k++){
	board[i][k] = PEG;
      }
    }
    
    //Mark top left and right quads as invalid
    for(int x=0; x<2; x++){
      for(int y=0; y<2; y++){
	board[x][y] = INVALID;
      }
      for(int y=MATRIXSIZE-2; y<MATRIXSIZE; y++){
	board[x][y] = INVALID;
      }
    }
    
    //Mark bottom left and right quads as invalid
    for(int x=MATRIXSIZE-2; x<MATRIXSIZE; x++){
      for(int y=MATRIXSIZE-2; y<MATRIXSIZE; y++){
	board[x][y] = INVALID;
      }
      for(int y=0; y<2; y++){
	board[x][y] = INVALID;
      }
    }
    
    //Mark center hole as open
    board[3][3] = HOLE;
    
    ++time;
  }  
  
/**************************************************************************
**
** Name: isHole()
**
** Description: Checks to see if a coordinate is a 'Hole'
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isHole(Point pos){
    int column = (int)pos.getX();
    int row = (int)pos.getY();
    
    return board[column][row] == HOLE ? true : false;
  } 
  
/**************************************************************************
**
** Name: isJumpValid()
**
** Description: Checks to see if a directional jump is legal and valid
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    boolean value
**
**************************************************************************/ 
  public boolean isJumpValid(Point pos, String dir){
    boolean flag = false;
    Point over = getJumpPoint(pos,dir);
    Point land = getJumpPoint(over,dir);
    
    if(isValid(pos) && inBounds(over) && inBounds(land)){
      if(isPeg(pos) && isPeg(over) && isHole(land)){
	flag = true;
      }
    }
    
    return flag;
  }
  
/**************************************************************************
**
** Name: isPeg()
**
** Description: Checks to see if a coordinate is a 'Peg'
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isPeg(Point pos){
    int column = (int)pos.getX();
    int row = (int)pos.getY();
    
    return board[column][row] == PEG ? true : false;
  }
  
/**************************************************************************
**
** Name: isvalid()
**
** Description: Checks to see if a coordinate is not 'Invalid'
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isValid(Point pos){
    if(inBounds(pos)){
      int column = (int)pos.getX();
      int row = (int)pos.getY();
      
      return board[column][row] != INVALID ? true : false;
    } else{
      return false;
    }
  }
   
/**************************************************************************
**
** Name: jump()
**
** Description: Performs a series of peg movements and appends a list of moves
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    None.
**
**************************************************************************/ 
  public void jump(Point pos, String dir){
    Point over = getJumpPoint(pos,dir);
    Point land = getJumpPoint(over,dir);
    
    swapState(pos);  //Original Point
    swapState(over); //Over Point
    swapState(land); //Land Point

    String string = "Peg " + pos.toString() + " jumped " + dir + " over " + over.toString() + " to hole " + land.toString() + "\n";
    jumpList.add(string);
  }
  
/**************************************************************************
**
** Name: pegCount()
**
** Description: Counts pegs on a board
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    int containing peg count
**
**************************************************************************/    
  public int pegCount(){
    int pegCount = 0;
    
    for(int j=0; j<MATRIXSIZE; j++){
      for(int k=0; k<MATRIXSIZE; k++){
	if(board[j][k] == PEG){
	  pegCount++;
	}
      }
    }    
    
    return pegCount;
  }
  
/**************************************************************************
**
** Name: printMoves()
**
** Description: Prints the list of moves associated with a Board instance
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
  private void printMoves(){
    for(int i=0; i<jumpList.size(); i++){
      System.out.print("Jump #" + (i+1) + " = " + jumpList.elementAt(i));
    }
  }

/**************************************************************************
**
** Name: show()
**
** Description: Prints a graphical representation of a Board instance
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
  public void show(){    
    Graphics2D g = (Graphics2D) Window.gamePanel.getGraphics();
    
    for(int i=0; i<MATRIXSIZE; i++){
      for(int k=0; k<MATRIXSIZE; k++){
	if(board[i][k] != INVALID){
	  
	  int x = (BOARDMARGIN / 2) + (i*HOLESIZE) + (i*HOLEOFFSET) + PEGMARGIN;
	  int y = (BOARDMARGIN / 2) + (k*HOLESIZE) + (k*HOLEOFFSET) + PEGMARGIN;
	  
	  //Draw Point border
	  g.setColor(BORDERCOLOR);
	  g.drawRect(x,y,HOLESIZE,HOLESIZE);
	  
	  //Select Point color
	  g.setColor(board[i][k] == PEG ? PEGCOLOR : HOLECOLOR);
	  
	  //Paint Point
	  g.fillRect(x+1,y+1,HOLESIZE-1,HOLESIZE-1);
	}
      }
    }  
  }
 
/**************************************************************************
**
** Name: swapState()
**
** Description: Swaps the state of a coordinate on a HiQ board
**
** Parameters
**
** INPUT:
**    pos * A Point instance containing a board coordinate
**
** OUTPUT:
**    None
**
**************************************************************************/  
  public void swapState(Point pos){
    int column = (int)pos.getX();
    int row = (int)pos.getY();
    
    board[column][row] = (board[column][row] == HOLE) ? PEG : HOLE;
  }
  
/**************************************************************************
**
** Name: toString()
**
** Description: Serializes a Board instance into a string
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    String representation of a Board object
**
**************************************************************************/    
  public String toString(){
    String string = "";
    
    for(int j=0; j<MATRIXSIZE; j++){
      for(int k=0; k<MATRIXSIZE; k++){
	string += board[j][k];
      }
    }
    
    return string;
  }
}
