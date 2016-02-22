package gamecomponents;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HistoryDataMsg {
	/**
	 * image for the icon
	 */
	private ImageIcon icon; 
	/**
	 * String for historyMSg
	 */
	private String historyMsg;
	
	/**
	 * Sets up the history data message
	 */
	public HistoryDataMsg(){
		icon = new ImageIcon("Resources/simon.png");
	}
	
	/**
	 * Handles the history message pop up
	 * @param mainScreen
	 * @param average
	 * @param numGamePlayed
	 */
	public void historyMsgPopUp(JFrame mainScreen, double average, int numGamePlayed){
		historyMsg =" Games played: "+numGamePlayed +"\n"+
					" The average score is: "+average;
		
		JOptionPane.showMessageDialog(mainScreen, historyMsg, "High Scores", JOptionPane.WARNING_MESSAGE, icon);
		
	}
}