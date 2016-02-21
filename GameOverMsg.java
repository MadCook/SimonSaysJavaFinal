/**Class putting together the game Simon
 * 
 * @author Madeline Cook
 * @version CS2410.gamecomponent.GameOverMsg.1
 *  
 */
package cs2410.gamecomponent;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GameOverMsg{
	/**
	 * String that holds the Game Over Message
	 */
	private String endGameMsg;
	/**
	 * The icon on the Game over Message 
	 */
	private ImageIcon icon; 
	/**
	 *  The icon for the ok button 
	 */
	private ImageIcon okIcon;

	/** Constructor to set up the options for the JOptionPane customized preferences
	 * 
	 * @param theScore
	 */
	public GameOverMsg(ScoreBoard theScore){
		
		 icon = new ImageIcon(getClass().getResource("Resources/simon.png"));
		 okIcon = new ImageIcon(getClass().getResource("Resources/ok.png"));
		 
		 UIManager.put("OptionPane.background", Color.WHITE);
		 UIManager.put("Panel.background", Color.WHITE);
		 UIManager.put("OptionPane.messageForeground", Color.GRAY.darker());
		 UIManager.put("OptionPane.okIcon", okIcon);
		 UIManager.put("OptionPane.buttonOrientation", 4);
		 UIManager.put("OptionPane.okButtonText", "");
		 UIManager.put("OptionPane.setButtonMargin", true);
		 UIManager.put("Button.opaque", false);
		 UIManager.put("OptionPane.buttonPadding", 0);
		 UIManager.put("Button.background", Color.WHITE);
		 
		
		 
	}
	
	/** Updates the game over message with round details
	 * 
	 * @param theScore Hands the ScoreBoard so you can grab appropriate values
	 */
	public void updateGameOverMsg(ScoreBoard theScore){
		endGameMsg = 
				 highScoreMsg(theScore.getRoundNum(),theScore.getHighScore())   +
				 "You are a"+ rankScoreMsg(theScore.getRoundNum()) + "! \n" +
				 "Your score : "  + theScore.getRoundNum()      + "\n"  +
				 "High Score : "   + beatHighScore(theScore.getRoundNum(),theScore.getHighScore())     + "\n";
		
	}
	
	/**Pops up end game message
	 * 
	 * @param mainScreen hands the JFrame you want the message to pop up on
	 */
	public void gameoverPopUp(JFrame mainScreen){
		JOptionPane.showMessageDialog(mainScreen, endGameMsg, "Game Over", JOptionPane.WARNING_MESSAGE, icon);

	}
	
	/**Determines what rank the player is for end game message
	 * 
	 * @param roundNum the round the player last succeeded on
	 * @return a string of what their rank is
	 */
	private String rankScoreMsg(int roundNum){
		if(roundNum < 2){
			return " Peasant";
		}
		else if(roundNum < 4){
			return " Peddler";
		}
		else if(roundNum < 8){
			return " Minstrel";
		}
		else if(roundNum < 16){
			return "n Apprentice";
		}
		else if(roundNum < 20){
			return " Baroness";
		}
		else if(roundNum < 30){
			return " Countess";
		}
		else if(roundNum < 40){
			return " Princess";
		}
		else if(roundNum < 50){
			return " Queen";
		}
		return "fail";
	}
	
	/** If the score the player recieved tied or beat the high Score it returns a
	 *  congradulations message
	 * 
	 * @param roundNum the score the player last succeeded on
	 * @param highScore the sessions current high Score
	 * @return high Score message, tied message or empty string
	 */
	private String 	highScoreMsg(int roundNum, int highScore){	
	if(roundNum > highScore){
		return "You achieved a High Score! \n";
	}
	else if( roundNum == highScore){
		return "You tied for the High Score! \n"; 
	}
	return "";
	}
	
	/** Determines if player score beats highScore then displays the high Score
	 * 
	 * @param roundNum players last succeeded round
	 * @param highScore highScore previous to this game
	 * @return the new highScore
	 */
	private int beatHighScore(int roundNum, int highScore){
	int momentaryHighScore = highScore;
	if(roundNum>highScore)
		 momentaryHighScore = roundNum;
	return momentaryHighScore;
	}
	
	
	
	
}
