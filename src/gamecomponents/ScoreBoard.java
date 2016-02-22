package gamecomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ScoreBoard extends JPanel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Keeps the highest score received so far
	 */
	private static int highScore;
	/**
	 * Array of highScoreNames
	 */
	private String[] highScoreNames=	{	"XXX",	"XXX",	"XXX",	"XXX",	"XXX",	"XXX",	"XXX",	"XXX",	"XXX",	"XXX"};
	/**
	 * Array of highScoreScores
	 */
	private int[] highScoreList =		{		0,		0,		0,		0,		0,		0,		0,		0,		0,		0};
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
	
	/**
	 * Int handling number of gamesPlayed
	 */
	private static int 	gamesPlayed;
	/**
	 * Int handling sum of all games played
	 */
	private static int	sumOfGames;
	

	/**
	 * Constructor of the score board
	 */
	public ScoreBoard() {
		UIManager.put("Button.background", Color.WHITE);
		
		roundNum =0;
		highScoreLabel = new JLabel("High Score: " + highScore , SwingConstants.LEFT);
		scoreLabel = new JLabel("Current Score: " + Integer.toString(roundNum), SwingConstants.RIGHT);
		startOrResetBtn = new JButton("Start");
		isItStartBtn = true;
		
		
		this.setLayout(new GridLayout(1,3));
		this.setBackground(java.awt.Color.GRAY.darker());
		this.setMaximumSize(new Dimension(100000,150));
		highScoreLabel.setForeground(new Color(255,255,255));
		scoreLabel.setForeground(new Color(255,255,255));
		add(highScoreLabel, BorderLayout.CENTER);
		add(startOrResetBtn);
		add(scoreLabel);
	}
	
	/**
	 * Increments the round
	 */
	public void increaseRound(){
		roundNum++;
		scoreLabel.setText("Current Score: " + Integer.toString(roundNum));	
		scoreLabel.update(scoreLabel.getGraphics());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * returns roundNum
	 */
	public int getRoundNum(){
		return roundNum;
	}
	
	/**
	 * sets the new score if it beat the old score
	 */
	public void setHighScore(){
		
		for(int i=0; i<10; i++){
			if(roundNum>=highScoreList[i]){
				for(int j=8; j>=i; j--){
					highScoreList[j+1] 	= highScoreList[j];
					highScoreNames[j+1] = highScoreNames[j];
				}
				highScoreNames[i] =JOptionPane.showInputDialog(this, "Please enter your name to catalogue your high score", "Name");
				highScoreList[i] = roundNum;
				
				break;
			}
			
		}
		highScore = highScoreList[0];
		highScoreLabel.setText("Current Score: " + Integer.toString(highScoreList[0]));	
		incrementGamesPlayed();
		addSumOfGames();
	}
	
	/**
	 * @return highScore
	 */
	public int getHighScore(){
		return highScore;
	}
	
	/**
	 * @return isItStartBtn
	 */
	public boolean checkIfItsStart(){
		return isItStartBtn;
	}
	
	/**
	 * changes the button's text
	 */
	public void changeToReset(){
		isItStartBtn = false;
		
		startOrResetBtn.setText("Reset");
		this.update(this.getGraphics());
	}
	
	/**
	 * @return startOrResetBtn
	 */
	public JButton getStartOrResetBtn(){
		return startOrResetBtn;
	}
	
	/**
	 * reset the round number
	 */
	public void resetRound(){
		roundNum =0;
	}
	
	/**
	 * @param gamePlayed
	 * Sets the number of games played
	 */
	public void setGamesPlayed(Integer gamePlayed){
		gamesPlayed = gamePlayed;
	}
	
	/**
	 * return gamesPlayed;
	 */
	public int getGamesPlayed(){
		return gamesPlayed;
	}
	
	/**
	 * increments gamesPlayed
	 */
	private static void incrementGamesPlayed(){
		gamesPlayed++;
	}
	
	/**
	 * @param summedGames
	 * Sets sumOfGames
	 */
	public void setSumOfGames(int summedGames){
		sumOfGames = summedGames;
	}
	
	/**
	 * Add to the summed games
	 */
	private void addSumOfGames(){
		sumOfGames += roundNum;
	}
	
	/**
	 * returns average of your games
	 * @return average
	 */
	public double getAverage(){
		if(gamesPlayed==0)
			return 0;
		return (sumOfGames/gamesPlayed);
	}
	
	/**
	 * returns sum of total games
	 * @return sumOfGames
	 */
	public static int getSumOfGames(){
		return sumOfGames;
	}
	
	/**
	 * Sets on of the names on highScore board
	 * @param place
	 * @param name
	 * 
	 */
	public void setNameScores(int place, String name){
		highScoreNames[place] = name;
	}
	
	/**
	 * Set one of the highScores
	 * @param place
	 * @param Score
	 */
	public void setArrScore(int place, int Score){
		if(place ==0){
			highScore = Score;
			highScoreLabel.setText("High Score: " + highScore);
			highScoreLabel.update(highScoreLabel.getGraphics());
		}
		highScoreList[place] = Score;
	}
	
	/**
	 * returns the names on highScore list
	 * @return highScoreNames
	 */
	public String[] namesArr(){
		return highScoreNames;
	}
	
	/**
	 * returns the scores on highScore list
	 * @return highScoreList
	 */
	public int[] scoresArr(){
		return highScoreList;
	}
	
	/**
	 * Updates the highscore file
	 */
	public void updateFile() throws IOException {
		File fout = new File("Resources/Score.txt");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (int i = 0; i < 10; i++) {
			bw.write(highScoreNames[i]);
			bw.newLine();
			bw.write(new Integer(highScoreList[i]).toString());
			bw.newLine();
		}
		bw.write(new Integer(gamesPlayed).toString());
		bw.newLine();
		bw.write(new Integer(sumOfGames).toString());
		
		bw.close();
	}
}

