/**Class putting together the game Simon
 * 
 * @author Madeline Cook
 * @version CS2410.gamecomponent.ScoreBoard.1
 *  
 */
package cs2410.gamecomponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ScoreBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Keeps the highest score received so far
	 */
	private static int highScore;
	/**
	 * Keeps how many rounds the player has succeeded at
	 */
	private int roundNum;
	/**
	 * The label that shows the current players score
	 */
	private JLabel scoreLabel;
	/**
	 * The label that shows the high Score
	 */
	private static JLabel highScoreLabel;
	/**
	 * The button that lets the player chose start or reset the game
	 */
	private static JButton startOrResetBtn;
	/**
	 * Boolean so you can select which label is on your startOrResetBtn
	 */
	private boolean isItStartBtn;
	
	
	/** Constructor sets the place of the Score Board and initialize roundNum to 0;
	 * 
	 * @param width how many btns the board is dealing with wide
	 * @param btnSize the size of the btns on the board
	 * @param height the height you want the ScoreBoard to be
	 * @param border how much you want to shift down the ScoreBoard
	 */
	public ScoreBoard(int width, int btnSize, int height, int border) {
		UIManager.put("Button.background", Color.WHITE);
		
		isItStartBtn = true;
		roundNum =0;
		highScoreLabel = new JLabel("High Score: " + highScore , SwingConstants.LEFT);
		scoreLabel = new JLabel("Current Score: " + Integer.toString(roundNum), SwingConstants.RIGHT);
		startOrResetBtn = new JButton("Restart");
		setBounds(2*border, border, width*btnSize+(2*border), height);
		this.setLayout(new GridLayout(1,3));
		highScoreLabel.setForeground(new Color(255,255,255));
		scoreLabel.setForeground(new Color(255,255,255));
		add(highScoreLabel, BorderLayout.CENTER);
		add(startOrResetBtn);
		add(scoreLabel);
	}
	
	
	/** Increases the roundNum by 1
	 * 
	 */
	public void increaseRound(){
		roundNum++;
		scoreLabel.setText("Current Score: " + Integer.toString(roundNum));	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**Sets the highScore if rNum is larger than highScore
	 * 
	 * @param rNum the end games round Number
	 */
	public static void setHighScore(int rNum){
		if(rNum>highScore){
			highScore = rNum;
			highScoreLabel.setText("High Score: " + Integer.toString(highScore));
			highScoreLabel.update(highScoreLabel.getGraphics());	
		}
	}
	
	/**Getter method for roundNum
	 * 
	 * @return roundNum 
	 */
	public int getRoundNum(){
		return roundNum;
	}
	
	/**Getter method for highScore
	 * 
	 * @return highScore
	 */
	public int getHighScore(){
		return highScore;
	}
	
	/**Setter method for if the btn should be Start or Reset
	 * 
	 * @param onOrOff Boolean for if it is start or reset
	 */
	public void setStartResetBtn(boolean onOrOff){
		isItStartBtn = onOrOff;
		if(isItStartBtn){
			startOrResetBtn.setText("Start");
			this.update(this.getGraphics());
		}
		else{
			startOrResetBtn.setText("Restart");
			this.update(this.getGraphics());
		}
	}
	
	/**Getter method for isItStart
	 * 
	 * @return isItStartBtn
	 */
	public boolean startOrReset(){
		return isItStartBtn;
	}
	
	/**Getter method for the JButton of startOrResetBtn
	 * 
	 * @return startOrResetBtn
	 */
	public JButton getStartOrResetBtn(){
		return startOrResetBtn;
	}
	


}
