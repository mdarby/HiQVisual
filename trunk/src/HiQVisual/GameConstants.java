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
 ** File Name: /export/home/mdarby/COMP620/Project1D/src/GameConstants.java
 **
 ** Description: Interface implementation for HiQ game constants
 **
 ** Input: None
 **
 ** Output: None
 **
 **************************************************************************/

package HiQVisual;

import java.awt.Color;

public interface GameConstants {
  public static final char HOLE = 'O';
  public static final char PEG = '|';
  public static final char INVALID = ' ';
  public static final int PAUSE = 300;
  
  public static final Color PEGCOLOR = Color.RED;
  public static final Color HOLECOLOR = Color.BLACK;
  public static final Color BORDERCOLOR = Color.WHITE;  
  
  public static final int PANELSIZE = (int)Window.gamePanel.getSize().getHeight();
  public static final int BOARDMARGIN = (PANELSIZE / (PANELSIZE / 10)) / 2;
  
  public static final int MATRIXSIZE = 7;
  public static final int HOLESIZE = (int)(PANELSIZE * .12);
  public static final int HOLEOFFSET = ((PANELSIZE - BOARDMARGIN) - (HOLESIZE * MATRIXSIZE)) / (MATRIXSIZE - 1);
  public static final int PEGMARGIN = ((PANELSIZE - BOARDMARGIN) - ((HOLESIZE * MATRIXSIZE) + (HOLEOFFSET * (MATRIXSIZE - 1)))) / 2;
}
