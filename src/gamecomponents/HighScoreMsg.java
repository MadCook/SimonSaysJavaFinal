package gamecomponents;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HighScoreMsg {
	/**
	 * image for icon
	 */
	private ImageIcon icon; 
	
	/**
	 * string for message
	 */
	private String highScoreMsg;
	
	/**
	 * Constructor for highScoreMsg
	 */
	public HighScoreMsg(){
		icon = new ImageIcon("Resources/simon.png");
	}
	
	/**
	 * Handles the msg for the rules
	 */
	public void rulesMsgPopUp(JFrame mainScreen, String[] names, int[] scores){
		highScoreMsg ="     Initials               Score \n";
		
		for(int i=0; i<10; i++){
			highScoreMsg +="       " + names[i] + "\n ______________"+ scores[i] + "__\n";
			
		}
		
		JOptionPane.showMessageDialog(mainScreen, highScoreMsg, "High Scores", JOptionPane.WARNING_MESSAGE, icon);
		
	}
	
}
