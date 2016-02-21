/**Class putting together the game Simon
 * 
 * @author Madeline Cook
 * @version CS2410.game.Simonish.1
 *  
 */

package cs2410.game;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cs2410.gamecomponent.ColorPieces;
import cs2410.gamecomponent.GameOverMsg;
import cs2410.gamecomponent.ScoreBoard;

public class Simonish extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Boolean handling if the game is over or not
	 */
	private boolean gameOver=true;
	/**
	 * int handling the size of the btnPanels
	 */
	private int btnSize = 150;
	/**
	 * how many btns the game has wide
	 */
	private int width = 2;
	/**
	 * how many btns the game has tall
	 */
	private int height = 2;
	/**
	 * size of border around scoreBoard and btns and between them
	 */
	private int border =15;
	/**
	 * how tall you want the scoreBoard
	 */
	private int scoreBoardheight =50;
	/**
	 * the array that handles the btns
	 */
	private ColorPieces[][] pieces;
	/**
	 * Vector handles the order that btns will be illuminated/clicked
	 */
	private Vector<Integer> simonOrder = new Vector<Integer>(); 
	/**
	 * Handles the game over message
	 */
	private GameOverMsg gom;
	/**
	 * Iterator handling which item in the vector btn you should have selected
	 */
	private int iterate =0;
	/**
	 * container holding everything!
	 */
	private Container thePane;
	/**
	 * Panel holding the btns
	 */
	private JPanel theBoardPanel;
	/**
	 * Panel holding scoreBoard
	 */
	private ScoreBoard scoreBoard;

	
	/** Constructor setting the main frame up and setting the first set of buttons
	 * 
	 */
	private Simonish(){
		this.setTitle("Simonish");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		thePane = this.getContentPane();
		thePane.setLayout(null);
		resetGame();
		scoreBoard.setStartResetBtn(true);
	}
	
	/**Starts the game and calls the first turn of the computer
	 * 
	 */
	private void startGame(){
		gameOver = false;
		computersTurn();
	}
	
	/** Resets all of the values on your different panels
	 * 
	 */
	private void resetGame(){
		thePane.removeAll();
		simonOrder.removeAllElements();
		thePane.setPreferredSize(new Dimension((btnSize*width)+(7/2*border), ((btnSize*height)+scoreBoardheight)+(7/2*border)));
		
		theBoardPanel = new JPanel();
		scoreBoard = new ScoreBoard(width,btnSize, scoreBoardheight,border/2);
		gom = new GameOverMsg(scoreBoard);
		
		theBoardPanel.setLayout(null);
		theBoardPanel.setBounds(0+(3/2)*border, scoreBoardheight+(border), width*btnSize+border, height*btnSize+border);
		
		thePane.setBackground(java.awt.Color.GRAY.darker());
		scoreBoard.setOpaque(false);
		theBoardPanel.setOpaque(true);
		
		scoreBoard.getStartOrResetBtn().addActionListener(this);
		thePane.add(scoreBoard);
		thePane.add(theBoardPanel);
		setBtns();	
		
		thePane.update(thePane.getGraphics());	
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**Initializes the Btns
	 * 
	 */
	private void setBtns(){
		this.pieces = new ColorPieces[width][height];
		ColorPieces.setMaxWidth(width);
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				ColorPieces temp = new ColorPieces(w, h, btnSize, border/2);
				pieces[w][h] = temp;
				theBoardPanel.add(pieces[w][h]);
				pieces[w][h].addMouseListener(this);
			}
		}
		
	}
	
	/**Sets the values and displays game over message
	 * 
	 */
	private void gameOver(){
		gameOver=true;
		simonOrder.removeAllElements();
		gom.updateGameOverMsg(scoreBoard);
		gom.gameoverPopUp(this);
		ScoreBoard.setHighScore(scoreBoard.getRoundNum());
	}
	
	/**Iterates through the computers turns
	 * 
	 */
	private void computersTurn(){
		theBoardPanel.setBackground(java.awt.Color.BLACK);
		theBoardPanel.update(theBoardPanel.getGraphics());	
		Random rnd = new Random();
		simonOrder.add(rnd.nextInt((width*height)));
		int w =0;
		int h =0;
		for(int turn =0; turn<simonOrder.size(); turn++){
			w = simonOrder.elementAt(turn) % width;
			h = simonOrder.elementAt(turn) / width;
			pieces[w][h].compLightUp("Comp");	
		}
		iterate =0;
		theBoardPanel.setBackground(java.awt.Color.WHITE);
		theBoardPanel.update(theBoardPanel.getGraphics());	
	}
	
	/**Feeds in the players choice for btn checks to see if correct, if not calls gameOver
	 * 
	 * @param chosenBtn
	 */
	public void playersTurn(Integer chosenBtn){
		if(!gameOver){
		if((chosenBtn == simonOrder.elementAt(iterate)) ){
			iterate++;
		}
		else{
			gameOver();
		}
		 if(!(iterate<simonOrder.size())&&!gameOver){
				iterate =0;
				scoreBoard.increaseRound();
				this.update(this.getGraphics());
				computersTurn();
		}
		    
		}	
	}
	
	public static void main(String[] args) {
		new Simonish();

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		((ColorPieces)e.getSource()).playerLightUp();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				if(e.getSource() == pieces[w][h]){
					pieces[w][h].playerDarken();
					if(!gameOver){
					playersTurn(w+(h*width));
					}
					break;
				}
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == scoreBoard.getStartOrResetBtn()) {
			if(!scoreBoard.startOrReset()){
				resetGame();
				this.update(this.getGraphics());
				startGame();
			}
			else{
				scoreBoard.setStartResetBtn(false);
				startGame();
				
			}

		}
	}
}
