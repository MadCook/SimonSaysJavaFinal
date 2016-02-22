package gamecomponents;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RulesMsg {
	/**
	 * string handling rules
	 */
	private String ruleString;
	/**
	 * image icon 
	 */
	private ImageIcon icon; 
	
	/**
	 * Constructor setting up image
	 */
	public RulesMsg(){
		icon = new ImageIcon("Resources/simon.png");
		ruleString = "To start the game press the 'Start' button.\n"+
					 "This should brighten up a single random panel.\n"+
					 "This should enter your turn which will be\n"+
					 "indicated by the background returning to the\n"+
					 "dark grey color. Once you have selected the \n"+
					 "correct panel it will return to the computers\n"+
					 "turn and will have another panel added to \n"+
					 "the sequence. This pattern will continue\n"+
					 "until you screw up. \n\n"+

					 "Once you've screwed up your results will\n"+
					 "appear. If you've beaten one of the\n"+
					 "previous top 10 high schores, you will be\n"+
					 "prompted to enter in your name. \n\n"+

					 "Press reset to start over! Again and Again!";
		
	}
	
	/**
	 * showing message to the screen
	 * @param mainScreen
	 */
	public void rulesMsgPopUp(JFrame mainScreen){
		JOptionPane.showMessageDialog(mainScreen, ruleString, "About the Game", JOptionPane.WARNING_MESSAGE, icon);

	}
}
