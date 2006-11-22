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
** File Name: /export/home/mdarby/COMP620/Project1D/src/Main.java
**
** Description: Class file for Position (which describes a coordinate on an HiQ game board)
**
** Input: None
**
** Output: None
**
**************************************************************************/

package HiQVisual;
  
public class Position {
  private int column;
  private int row;  
  
/**************************************************************************
**
** Name: Position
**
** Description: Position constructor
**
** Parameters
**
** INPUT:
**    col * column number of the board
**    Row * row number of the board
**
** OUTPUT:
**    A new instance of the Position class
**
**************************************************************************/
  public Position(int col, int Row) {
    column = col;
    row = Row;
  }

/**************************************************************************
**
** Name: getColumn()
**
** Description: Returns the column number of a particular Position
**
** Parameters
**
** INPUT: 
**    None
**
** OUTPUT: 
**    Integer representation of a column
**
**************************************************************************/  
  public int getColumn(){
    return column;
  }

/**************************************************************************
**
** Name: getRow()
**
** Description: 
**
** Parameters
**
** INPUT:
**    col * column number of the board
**    Row * row number of the board
**
** OUTPUT:
**    Integer representation of a row
**
**************************************************************************/  
  public int getRow(){
    return row;
  }

/**************************************************************************
**
** Name: update()
**
** Description: Updates the column and row members of a Position
**
** Parameters
**
** INPUT:
**    col * column number of the board
**    Row * row number of the board
**
** OUTPUT:
**    None.
**
**************************************************************************/  
  public void update(int col, int Row){
    column = col;
    row = Row;
  }

/**************************************************************************
**
** Name: display()
**
** Description: Prepares a Position for printing
**
** Parameters
**
** INPUT:
**    None
**
** OUTPUT:
**    Returns a Position in the format of "(col,row)"
**
**************************************************************************/  
  public String display(){
    return "(" + column + "," + row + ")";
  }
}