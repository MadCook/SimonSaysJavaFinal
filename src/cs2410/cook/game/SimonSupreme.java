package cs2410.cook.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Vector;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import gamecomponents.AboutMsg;
import gamecomponents.ColorPieces;
import gamecomponents.GameOverMsg;
import gamecomponents.HighScoreMsg;
import gamecomponents.HistoryDataMsg;
import gamecomponents.RulesMsg;
import gamecomponents.ScoreBoard;


public class SimonSupreme extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Pieces wide on the panel board
	 */
	private int numPiecesWide =2;
	/**
	 * Pieces high on the panel board
	 */
	private int numPiecesHigh =2;
	/**
	 * the container
	 */
	private Container thePane;
	/**
	 * the board holding all the pieces
	 */
	private JPanel theBoardPanel;
	/**
	 * the border between the panels
	 */
	private JPanel borderPanel1;
	/**
	 * the border between panels
	 */
	private JPanel borderPanel2;
	/**
	 * the score board
	 */
	private ScoreBoard theScoreBoard;
	/**
	 * array full of panels
	 */
	private ColorPieces[] panelArr;
	
	private Color color01  = new Color(255,0,0);
	private Color color02  = new Color(255,255,0);
	private Color color03  = new Color(0,234,255);
	private Color color04  = new Color(170,0,255);
	private Color color05  = new Color(255,127,0);
	private Color color06  = new Color(191,255,0);
	private Color color07  = new Color(0,149,255);
	private Color color08  = new Color(255,0,170);
	private Color color09  = new Color(255,212,0);
	private Color color10 = new Color(106,255,0);
	private Color color11 = new Color(0,64,255);
	private Color color12 = new Color(237,185,185);
	private Color color13 = new Color(185,215,237);
	private Color color14 = new Color(231,233,185);
	private Color color15 = new Color(220,185,237);
	private Color color16 = new Color(185,237,224);
	private Color color17 = new Color(143,35,35);
	private Color color18 = new Color(35,98,143);
	private Color color19 = new Color(143,106,35);
	private Color color20 = new Color(107,35,143);
	private Color color21 = new Color(79,143,35);
	private Color color22 = new Color(90,90,90);
	private Color color23 = new Color(115,115,115);
	private Color color24 = new Color(204,204,204);
	private Color color25 = new Color(255,255,255);
	
	/**
	 * Array holding the color of panels
	 */
	private Color[] colorArr = {color01, color02, color03, color04, color05, 
								color06, color15, color08, color09, color10, 
								color11, color22, color13, color24, color07,
								color21, color17, color23, color19, color25,
								color16, color12, color18, color14, color20};
	
	/**
	 * the menu
	 */
	private JMenuBar theMenu;
	/**
	 * the menu fields
	 */
	private JMenu settings, stats, help, modeSubMenu;
	/**
	 * the menu options
	 */
	private JMenuItem menuOptions;
	/**
	 * button group holding if computerIncrease is on or off
	 */
	private ButtonGroup group;
	/**
	 * boolean handling if the game is over or not
	 */
	private boolean gameOver;
	
	/**
	 * game over message
	 */
	private GameOverMsg gameOverMessage;
	/**
	 * about message
	 */
	private AboutMsg    	 aboutMessage;
	/**
	 * rule message
	 */
	private RulesMsg 		 rulesMessage;
	/**
	 * high score message
	 */
	private HighScoreMsg highScoreMessage;
	/**
	 * history data message
	 */
	private HistoryDataMsg historyDataMessage;
	
	/**
	 * path file for score text file
	 */
	private Path gameFile = Paths.get("Resources/Score.txt");
	
	/**
	 * color that will hold the new panel color
	 */
	private Color panelColor = Color.RED;
	/**
	 * colorChoser boolean
	 */
	private boolean colorChoser = false;
	
	/**
	 * boolean holding if it's the players turn
	 */
	private boolean playersTurn = false;
	/**
	 * thread for the computer
	 */
	private Thread Comp;
	
	/**
	 * midi channel
	 */
    private MidiChannel midi;
	
    /**
     * random number generator
     */
	Random rnd = new Random();
	/**
	 * Vector of color pieces
	 */
	private Vector<ColorPieces> simonOrder = new Vector<ColorPieces>(); 
	/**
	 * iterator
	 */
	int iterate;
	
	
	/**
	 * Constructor for game
	 */
	private SimonSupreme(){

		setUpGame();
		setUpMenu();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		gameOver = false;
	}
	
	/**
	 * Set up screen
	 */
	private void setUpGame(){
		this.setTitle("Simonish");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thePane = this.getContentPane();
		
		theBoardPanel = new JPanel();
		theScoreBoard = new ScoreBoard();
		borderPanel1 = new JPanel();
		borderPanel2 = new JPanel();
		gameOverMessage = new GameOverMsg(theScoreBoard);
		aboutMessage = new AboutMsg();
		rulesMessage = new RulesMsg();
		highScoreMessage = new HighScoreMsg();
		historyDataMessage = new HistoryDataMsg();
		
		
		theBoardPanel.setPreferredSize(new Dimension(400,400));
		borderPanel1.setMaximumSize(new Dimension(10,10));
		borderPanel2.setMaximumSize(new Dimension(10,10));
		thePane.setLayout(new BoxLayout(thePane, BoxLayout.PAGE_AXIS));
		
		theBoardPanel.setOpaque(false);
		borderPanel1.setOpaque(false);
		borderPanel2.setOpaque(false);
		thePane.setBackground(java.awt.Color.GRAY.darker());
		
		setUpColor();
		OpenScores();
		
		theScoreBoard.getStartOrResetBtn().addActionListener(this);
		
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
	        midi = synth.getChannels()[0];
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.addMouseListener(this);
		
		thePane.add(theScoreBoard);
		thePane.add(borderPanel1);
		thePane.add(theBoardPanel);
		thePane.add(borderPanel2);
		
	}
	
	
	/**
	 * Start the game 
	 */
	private void startGame(){
		if(theScoreBoard.checkIfItsStart())
			theScoreBoard.changeToReset();
		simonOrder.removeAllElements();
		computersTurn();
	}
	
	/**
	 * Restart the game
	 */
	private void restartGame(){
		gameOver = false;
		
		theScoreBoard.resetRound();
		startGame();
	}
	
	/**
	 * Set up the color field
	 */
	private void setUpColor(){
		theBoardPanel.removeAll();
		theBoardPanel.invalidate();
		theBoardPanel.setLayout(new GridLayout(numPiecesHigh,numPiecesWide,10,10));
		

		panelArr = null;
		panelArr = new ColorPieces[numPiecesWide*numPiecesHigh];
		
		for (int i = 0; i < panelArr.length; i++) {
			int j= i%25;
			panelArr[i] = new ColorPieces(colorArr[j]);
			panelArr[i].addMouseListener(this);
			theBoardPanel.add(panelArr[i]);
		}
		
		this.update(this.getGraphics());	
		this.pack();
	}
	
	/**
	 * Set up the menu
	 */
	private void setUpMenu(){
		theMenu = new JMenuBar();
		
		settings = new JMenu("Settings");
		theMenu.add(settings);
		
		menuOptions= new JMenuItem("Chose color",new ImageIcon("Resources/color2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		settings.add(menuOptions);
		
		modeSubMenu = new JMenu("Chose mode");
		settings.add(modeSubMenu);
		
		menuOptions= new JMenuItem("Easy",new ImageIcon("Resources/green2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		modeSubMenu.add(menuOptions);
		
		menuOptions= new JMenuItem("Medium",new ImageIcon("Resources/orange2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		modeSubMenu.add(menuOptions);
		
		menuOptions= new JMenuItem("Hard",new ImageIcon("Resources/red2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		modeSubMenu.add(menuOptions);
		
		menuOptions= new JMenuItem("Change Board Size",new ImageIcon("Resources/grid2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		settings.add(menuOptions);
		
		modeSubMenu = new JMenu("Change Image Group");
		settings.add(modeSubMenu);
		
		group = new ButtonGroup();
		menuOptions = new JRadioButtonMenuItem("Shapes",new ImageIcon("Resources/shape2.PNG"), true);
	
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Black Shapes",new ImageIcon("Resources/black2.PNG"),false);
		
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Colored Shapes",new ImageIcon("Resources/clr2.PNG"), false);
		
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Cartoons",new ImageIcon("Resources/cartoon2.PNG"), false);
		
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Characters",new ImageIcon("Resources/char2.PNG"), false);
		
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Squares",new ImageIcon("Resources/block2.PNG"), false);
		
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		
		
		modeSubMenu = new JMenu("Computer Speed Increase");
		settings.add(modeSubMenu);
		
		group = new ButtonGroup();
		menuOptions = new JRadioButtonMenuItem("On",new ImageIcon("Resources/on2.PNG"), false);
	
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		menuOptions = new JRadioButtonMenuItem("Off",new ImageIcon("Resources/off2.PNG"),true);
		menuOptions.addActionListener(this);
		group.add(menuOptions);
		modeSubMenu.add(menuOptions);
		
		stats = new JMenu("Stats");
		theMenu.add(stats);
		
		menuOptions= new JMenuItem("High score",new ImageIcon("Resources/celebrate2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		stats.add(menuOptions);
		
		menuOptions= new JMenuItem("Game history",new ImageIcon("Resources/stats2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		stats.add(menuOptions);
		
		help = new JMenu("Help");
		theMenu.add(help);
		
		menuOptions= new JMenuItem("About",new ImageIcon("Resources/question2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		help.add(menuOptions);
		
		menuOptions= new JMenuItem("Rules",new ImageIcon("Resources/rule2.PNG"));
		menuOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.ALT_MASK));
		menuOptions.addActionListener(this);
		help.add(menuOptions);
		
		this.setJMenuBar(theMenu);
		
		panelColor = Color.RED;
		iterate =0;
	}

	/**
	 * Change how many panels are on the playing field
	 */
	private void changeBoardSize(){
		numPiecesWide = Integer.parseInt(JOptionPane.showInputDialog("How many pieces wide should your board be?", 3));
		numPiecesHigh = Integer.parseInt(JOptionPane.showInputDialog("How many pieces tall should your board be?", 3));

		setUpColor();
		restartGame();
	}	
			
	/**
	 * Displays all of the panels that are selected
	 */
	private void computersTurn(){

		Comp = new Thread(new Runnable() {

			@Override
			public void run() {
			
				playersTurn = false;

				simonOrder.addElement(panelArr[rnd.nextInt(numPiecesWide*numPiecesHigh)]);

				for(int i =0; i<simonOrder.size(); i++){
					midi.programChange(simonOrder.elementAt(i).getInstrumentTmp());
					midi.noteOn(simonOrder.elementAt(i).getNoteTmp(), 150);
					simonOrder.elementAt(i).computersLightUp(theScoreBoard.getRoundNum()+1);	
					midi.allNotesOff();
				}
				iterate =0;
				playersTurn = true;
			}
		});
		Comp.start();
	}
	
	/**
	 * Opens and reads in the file for the scores
	 */
	private void OpenScores(){
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(gameFile, charset)) {
		    String line = null;
		    int j = 0;
		    while ((line = reader.readLine()) != null) {
		    	if(j<20)
		       		if(j%2==0){
		       			theScoreBoard.setNameScores(j/2, line);
		       			j++;
		       		}
		       		else{
		       			theScoreBoard.setArrScore((j-1)/2, Integer.parseInt(line));
		       			j++;
		       		}
		    	else if(j == 20){
		    		int l = Integer.parseInt(line);
		       		theScoreBoard.setGamesPlayed(l);
		       		j++;
		       	}
		    	else if(j == 21){
		    		int l = Integer.parseInt(line);
		       		theScoreBoard.setSumOfGames(l);
		       		j++;
		    	}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	
	/**
	 * Checks to see if the piece selected was the correct piece
	 */
	private void playersTurn(ColorPieces chosenBtn){
		
		if(!gameOver){
		if(chosenBtn == simonOrder.elementAt(iterate)){
			iterate++;
		}
		else{
			gameOver();
		}
		 if(iterate ==simonOrder.size()&&!gameOver){
				computersTurn();
				theScoreBoard.increaseRound();
		}
		    
		}	
	}
	
	/**
	 * Stuff done when you've reached game over
	 */
	private void gameOver(){
		gameOver=true;
		midi.programChange(127);
		midi.noteOn(73, 150);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		midi.allNotesOff();
		gameOverMessage.updateGameOverMsg(theScoreBoard);
		gameOverMessage.gameoverPopUp(this);
		theScoreBoard.setHighScore();
		try {
			theScoreBoard.updateFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Handles choosing the new color of the panel 
	 */
	private void changeColor(){
		colorChoser = true;
		panelColor = JColorChooser.showDialog(this, "Choose a Color then select the panel you want it applied to", panelColor);
		
	}
	
	/**
	 * Sets the new panels color 
	 */
	private void setColor(ColorPieces pieceSelected){
		for(int i=0; i<(numPiecesHigh*numPiecesWide); i++){
			if(pieceSelected==panelArr[i]){
				colorArr[i] = panelColor;
			}
		}
		
	}
	
	private void changePieceIcons(int selection){
		ColorPieces.changeArray(selection);
		
		for(int i=0; i<panelArr.length; i++){
			panelArr[i].changeIcon();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SimonSupreme();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(colorChoser){
			ColorPieces tmp = ((ColorPieces)e.getSource());
			tmp.changeBackround(panelColor);
			setColor(tmp);
		}
		else if(playersTurn && e.getSource()instanceof ColorPieces){
			ColorPieces tmp = ((ColorPieces)e.getSource());
			midi.programChange(tmp.getInstrumentTmp());
			midi.noteOn(tmp.getNoteTmp(), 150);
			tmp.playerLightUp();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(colorChoser){
			colorChoser = false;
		}
		else if(playersTurn && e.getSource()instanceof ColorPieces){
		ColorPieces tmp = ((ColorPieces)e.getSource());
		tmp.playerDarken();
		midi.allNotesOff();
		playersTurn(tmp);
		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == theScoreBoard.getStartOrResetBtn()) {
			if(theScoreBoard.checkIfItsStart() == true)
				startGame();
			else
				restartGame();
		}
		
		else{
		JMenuItem tmp = ((JMenuItem)e.getSource());
		if(tmp != null)
		if(tmp.getText() == "Chose color")
			changeColor();
		
		else if(tmp.getText() == "Easy")
			ColorPieces.setbtnTime("easy");
		else if(tmp.getText() == "Medium")
			ColorPieces.setbtnTime("medium");
		else if(tmp.getText() == "Hard")
			ColorPieces.setbtnTime("hard");
		
		else if(tmp.getText() == "Change Board Size")
			changeBoardSize();
		
		else if(tmp.getText() == "Shapes")
			changePieceIcons(0);
		else if(tmp.getText() == "Black Shapes")
			changePieceIcons(1);
		else if(tmp.getText() == "Colored Shapes")
			changePieceIcons(2);
		else if(tmp.getText() == "Cartoons")
			changePieceIcons(3);
		else if(tmp.getText() == "Characters")
			changePieceIcons(4);
		else if(tmp.getText() == "Squares")
			changePieceIcons(5);
		
		else if(tmp.getText() == "On")
			ColorPieces.turnOnComputerSpeedUp(true);
		else if(tmp.getText() == "Off")
			ColorPieces.turnOnComputerSpeedUp(false);
		
		else if(tmp.getText() == "High score")
			highScoreMessage.rulesMsgPopUp(this,theScoreBoard.namesArr(),theScoreBoard.scoresArr());
		else if(tmp.getText() == "Game history")
			historyDataMessage.historyMsgPopUp(this, theScoreBoard.getAverage(),theScoreBoard.getGamesPlayed());
		else if(tmp.getText() == "About")
			aboutMessage.aboutMsgPopUp(this);
		else if(tmp.getText() == "Rules")
			rulesMessage.rulesMsgPopUp(this);

	}
	}
	
}
