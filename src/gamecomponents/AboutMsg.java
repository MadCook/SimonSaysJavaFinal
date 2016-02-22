package gamecomponents;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutMsg {
	
	/**
	 * string for the about message
	 */
	private String aboutString;
	/**
	 * handles the icon
	 */
	private ImageIcon icon; 
	
	/**
	 * Constructor for about Message
	 */
	public AboutMsg(){
		icon = new ImageIcon("Resources/simon.png");
		aboutString = "This game was developped by Madeline Cook \n\n"+
		
						"There are numerous extra features added to the game for you to explore! \n\n"+
						
						"You can chose to change the color of one of your panels (Alt-1) which will\n"+
						" pop up a selection of colors for you to select. Once you have selected a color\n"+
						"select the panel you wish to change colors. [On larger board games the colors repeat.\n"+
						"Changing a panel will change the single panels color for the current game but changing\n"+
						"the board will alter ever 25th panel of the panel changed in later games] \n\n"+

						"Changing the mode of the game alters how quickly the computer shows the selection of\n"+
						"panels. (Alt-2) or (Alt-3) or (Alt-4)\n\n"+
						
						"To change the board size (Alt-5) will ask you how many panels wide your board is and how  \n"+
						"many high. This can be any number but you can enter so many btns where you no  \n"+
						"long see each panel. I would suggest having the screen fullscreen to play with larger boards.\n\n"+
						
						"To turn the game up one more notch go turn on Computer Increase Mode. Following an  \n"+
						"exponential decay formula the computer will start speeding up the sequence of panels.  \n"+
						"Better watch closely or you may miss which button it adds! \n\n"+
						
						"Because every time you change the board the sounds to all the pieces are randomly selected \n"+
						"We advise you to change the board size if you find one of the sounds particularly annoying"
						;
		
	}
	
	/**
	 * Show the about Message
	 */
	public void aboutMsgPopUp(JFrame mainScreen){
		JOptionPane.showMessageDialog(mainScreen, aboutString, "About the Game", JOptionPane.WARNING_MESSAGE, icon);

	}
	
}
