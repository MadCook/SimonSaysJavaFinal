/**Class putting together the game Simon
 * 
 * @author Madeline Cook
 * @version CS2410.gamecomponent.ColorPieces.1
 *  
 */
package cs2410.gamecomponent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ColorPieces extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * Static int handling how many buttons we have wide in our current board
	 */
	private static int maxWidth;
	
	/** Constructor for each colorPiece to call set color, and set size
	 * 
	 * @param x Where it is on x axis
	 * @param y Where it is on y axis
	 * @param size size of btns
	 * @param border size of outside border that it has been shifted
	 */
	public ColorPieces(int x, int y, int size, int border) {
		int pieceNum = x + (y*maxWidth);
		this.setPreferredSize(new Dimension(size, size));
		this.setBounds(x*size+border, y*size+border, size, size);
		setColor(pieceNum);
	}

	/**Sets the color of the panel
	 * 
	 * @param pieceNum where it is located in an array
	 */
	private void setColor(int pieceNum){
	  switch (pieceNum) {
	    case 0:
	    	this.setBackground(new Color(170,6,25));
		  break;
	    case 1:
	    	this.setBackground(new Color(35,149,5));
		  break;
	    case 2:
	    	this.setBackground(new Color(13,60,116));
	      break;
	    case 3:
	    	this.setBackground(new Color(177,111,6));
	      break;	
	    case 4:
	    	this.setBackground(new Color(28,85,255).darker());
	      break;
	    case 5:
	    	this.setBackground(new Color(161,255,0).darker());
	      break;
	    case 6:
	    	this.setBackground(new Color(255,0,130).darker());
	      break;
	    case 7:
	    	this.setBackground(new Color(255,174,0).darker());
	      break;
	    case 8:
	    	this.setBackground(new Color(195,255,0).darker());
	      break; 
	    case 9:
	    	this.setBackground(new Color(0,255,132).darker());
	      break; 
	    case 10:
	    	this.setBackground(new Color(255,65,0).darker());
	      break;  
	    case 11:
	    	this.setBackground(new Color(62,106,50).darker());
	      break;  
	      
	  } 
  }	
	
  /** Setter method for the static MaxWidth
   * 	
   * @param mWidth what the maxWidth of current array is
   */
  public static void setMaxWidth(int mWidth){
	 maxWidth = mWidth;  
  }
  
  /** Handles the computer lighting up the panel 
   * 
   * @param mode controls how long the button will be lit up and darkened
   */
  public void compLightUp(String mode){
	  int btnTime =0;
	  if(mode =="Comp") btnTime = 1000;
	  else btnTime = 500;
	  
	  
	  this.setBackground(this.getBackground().brighter());  
	  this.update(this.getGraphics());	
		try {
			Thread.sleep(btnTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	  this.setBackground(this.getBackground().darker());
	  this.update(this.getGraphics());
		try {
			Thread.sleep(btnTime/6);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
  }
  
  /**Lights up the panel while the player is holding a panel
   * 
   */
  public void playerLightUp(){
	  this.setBackground(this.getBackground().brighter());  
	  this.update(this.getGraphics());	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
  }
  
  /** Keep the panel dark after the player lets go of the panel
   * 
   */
  public void playerDarken(){
	  this.setBackground(this.getBackground().darker());
	  this.update(this.getGraphics());
		try {
			Thread.sleep(500/5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
  }
  
  
}
