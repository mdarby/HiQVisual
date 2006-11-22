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

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class Board implements GameConstants {
  private char board[][] = new char[MAX_COLS][MAX_ROWS];
  private Vector jumpList = new Vector();
  private static int time = 0;
 
/**************************************************************************
**
** Name: Board()
**
** Description: Board constuctor
**
** Parameters
**
** INPUT:
**    string * A string representation of a Board instance
**    list * A vector of previous board movements
**
** OUTPUT:
**    New Board instance
**
**************************************************************************/ 
  public Board(String string, Vector list){
    int i,j,k;
    
    for(i=0,j=0,k=0; i<string.length(); i++, k++){
      if(i%(MAX_COLS) == 0 && i > 0){
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
      for(int i=0; i<MAX_COLS; i++){
        char[] currline = inFile.readLine().toCharArray();
        
        for(int k=0; k<MAX_ROWS; k++){
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
**    New Board instance with default values
**
**************************************************************************/  
  public Board(){
    //Initialize entire board at 1
    for(int i=0; i<MAX_COLS; i++){
      for(int k=0; k<MAX_ROWS; k++){
	board[i][k] = PEG;
      }
    }
    
    //Mark top left and right quads as invalid
    for(int x=0; x<2; x++){
      for(int y=0; y<2; y++){
	board[x][y] = INVALID;
      }
      for(int y=MAX_ROWS-2; y<MAX_ROWS; y++){
	board[x][y] = INVALID;
      }
    }
    
    //Mark bottom left and right quads as invalid
    for(int x=MAX_COLS-2; x<MAX_COLS; x++){
      for(int y=MAX_ROWS-2; y<MAX_ROWS; y++){
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
** Name: getJumpPosition()
**
** Description: Finds the next possible directional movement on a HiQ board
**
** Parameters
**
** INPUT:
**    pos * A Position instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    A new Position instance
**
**************************************************************************/  
  public Position getJumpPosition(Position pos, String dir){
    int column = pos.getColumn();
    int row = pos.getRow();    
    Position p = new Position(0,0);
    
    if(dir == "left"){
      p.update(column,row-1);
    }
    else if(dir == "right"){
      p.update(column,row+1);
    }
    else if(dir == "up"){
      p.update(column-1,row);
    }
    else if(dir == "down"){
      p.update(column+1,row);
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
**    pos * A Position instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    None.
**
**************************************************************************/  
  public void goBack(Position pos, String dir){
    Position over = getJumpPosition(pos,dir);
    Position land = getJumpPosition(over,dir);
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
**    pos * A Position instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  private boolean inBounds(Position pos){
    int column = pos.getColumn();
    int row = pos.getRow();
    
    if((column >= 0 && column < MAX_COLS) && (row >= 0 && row < MAX_ROWS)){
      return true;
    } else{
      return false;
    }
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
**    pos * A Position instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isHole(Position pos){
    int column = pos.getColumn();
    int row = pos.getRow();
    
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
**    pos * A Position instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    boolean value
**
**************************************************************************/ 
  public boolean isJumpValid(Position pos, String dir){
    boolean flag = false;
    Position over = getJumpPosition(pos,dir);
    Position land = getJumpPosition(over,dir);
    
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
**    pos * A Position instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isPeg(Position pos){
    int column = pos.getColumn();
    int row = pos.getRow();
    
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
**    pos * A Position instance containing a board coordinate
**
** OUTPUT:
**    boolean value
**
**************************************************************************/  
  public boolean isValid(Position pos){
    if(inBounds(pos)){
      int column = pos.getColumn();
      int row = pos.getRow();
      
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
**    pos * A Position instance containing a board coordinate
**    dir * A String containing the direction to go
**
** OUTPUT:
**    None.
**
**************************************************************************/ 
  public void jump(Position pos, String dir){
    Position over = getJumpPosition(pos,dir);
    Position land = getJumpPosition(over,dir);
    
    swapState(pos);  //Original Position
    swapState(over); //Over Position
    swapState(land); //Land Position

    String string = "Peg " + pos.display() + " jumped " + dir + " over " + over.display() + " to hole " + land.display() + "\n";
    jumpList.add(string);
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
    System.out.print("\n\n");
    for(int i=0; i<MAX_COLS; i++){
      for(int k=0; k<MAX_ROWS; k++){
	System.out.print(board[i][k] + " ");
      }
      System.out.print("\n");
    }
    System.out.print("\n\n");
    
    printMoves();
    System.out.println("\n\nTime: " + time + "\n\n");
    
    Graphics2D g = (Graphics2D) Window.gamePanel.getGraphics();
    g.setColor(Color.WHITE);
    g.drawString("will this work",100,100);
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
**    pos * A Position instance containing a board coordinate
**
** OUTPUT:
**    None
**
**************************************************************************/  
  public void swapState(Position pos){
    int column = pos.getColumn();
    int row = pos.getRow();
    
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
    
    for(int j=0; j<MAX_COLS; j++){
      for(int k=0; k<MAX_ROWS; k++){
	string += board[j][k];
      }
    }
    
    return string;
  }
}
