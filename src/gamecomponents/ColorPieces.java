package gamecomponents;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPieces extends JPanel{
	
	
	private static final int[] ColoredShape ={	1,	4,	0,	11,	13,
												15,	16,	17,	18,	19,
												28,	29,	30,	31,	32,
												33,	34};
	
	private static final int[] CartoonChar ={	2,		12,		14,		21,		22,
												86,		85,		87,		88,		89,
												90,		130,	131,	132,	133,
												156,	171,	172,	173,	174,
												175,	176,	177,	178,	179,
												180,	181,	182};
	
	private static final int[] BlackShape ={	3,		5,		6,		7,		8,
												9,		10,		20,		23,		24,
												25,		26,		27,		134,	135,
												136,	137,	138,	139,	140,
												141,	142,	143,	144,	145,
												146,	147,	148,	149,	150,
												152,	153,	154,	155,	156,
												157,	158,	159,	160,	161,
												162,	163,	164,	165,	166,
												167,	168,	169,	170};
	
	private static final int[] GlitchSquare={ 	37,		92,		93,		94,		95,
												96,		97,		98,		99,		100,
												101,	102,	103,	104,	105,
												106,	107,	108,	109,	110,
												111,	112,	113,	114,	115,
												116,	117,	118,	119,	120,
												121,	122,	123,	124,	125,
												127,	128};
	
	private static final int[] GlitchChar={		35,		36,		38,		64,		66,
												65,		67,		68,		69,		70,
												71,		72,		73,		74,		75,
												76,		77,		78,		79,		80,
												81,		82,		83,		84,		91};
	
	private static final int[] GlitchShape={	39,		40,		41,		42,		43,
												44,		45,		46,		47,		48,
												49,		50,		51,		52,		53,
												54,		55,		56,		57,		58,
												59,		60,		61,		62,		63};
	
	private static final int[][] pieceArray ={	GlitchShape,	BlackShape,		ColoredShape,	
												CartoonChar,	GlitchChar,		GlitchSquare};
	
	private static int chosenArrayPieces =0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * boolean handling a game mode
	 */
	private static boolean ComputerSpeedUp = false;
	/*
	 * Beginning time for panels
	 */
	static int begBtnTime =1000;
	/*
	 * actual time for buttons
	 */
	int btnTime =1000;
	/*
	 * color of panel
	 */
	private Color theColor;
	/*
	 * image on panel
	 */
	private ImageIcon theImage;
	/*
	 * label for image on panel
	 */
	private JLabel imageLabel;
	/*
	 * random number
	 */
	private Random rnd = new Random();
	
	/*
	 * array of instruments
	 */
    private int[] instrumentArr={ 1,	10,  	14,	   	28,    	32,
    							 41,	45,  	53,		55,		75,
    							 83,   	86,		96,		97,		98,
    							 107,   114,	118,	120    		
    				};
    
    /*
     * array of notes
     */
    private int[] notes={40,  	54,   	65,     73};
    
    /*
     * note selected
     */
    int noteTmp;
    /*
     * instrument selected
     */
    int instrumentTmp;

	/*
	 * Constructor for the color piece
	 * @param desiredBackground
	 */
	public ColorPieces(Color desiredBackground){
		this.setLayout(new GridBagLayout());
		theColor = desiredBackground.darker();
		theColor = theColor.darker();
		setBackground(theColor);
		this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,desiredBackground));
		String pather ="Resources/PieceImages/"+pieceArray[chosenArrayPieces][rnd.nextInt(pieceArray[chosenArrayPieces].length)]+".png" ;
		theImage = new ImageIcon(pather);
		imageLabel = new JLabel(theImage);
		this.add(imageLabel);

	
		noteTmp = notes[rnd.nextInt(4)];
		instrumentTmp = instrumentArr[rnd.nextInt(19)];	
	}
	
	/*
	 * lights up selected panel
	 */
	public void playerLightUp(){
			
		  this.setBackground(this.getBackground().brighter());  
		  this.setBackground(this.getBackground().brighter());  
		  this.update(this.getGraphics());	

	  }
	
	/*
	 * Darken the selected panel
	 */
	public void playerDarken(){
		  this.setBackground(theColor);
		  this.update(this.getGraphics());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	  }
	
	/*Computer lights up then darkens the piece
	 * @param roundNum
	 */
	public void computersLightUp(int roundNum){
		
		if (ComputerSpeedUp == true){
			btnTime = (int) (begBtnTime*Math.pow(1.1, -roundNum));
		}
		else{
			btnTime = begBtnTime;
		}

		this.setBackground(this.getBackground().brighter()); 
		this.setBackground(this.getBackground().brighter()); 
		  this.update(this.getGraphics());	
			try {
				Thread.sleep(btnTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		  this.setBackground(theColor);
		  this.update(this.getGraphics());
			try {
				Thread.sleep(btnTime/2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		
	}
	
	/*
	 * Changes the base starting value of the btn time
	 */
	static public void setbtnTime(String difficulty){
		if(difficulty == "easy")
			begBtnTime = 1000;
		else if(difficulty == "medium")
			begBtnTime = 750;
		else if(difficulty == "hard")
			begBtnTime = 500;
		
	}
	
	/* turning on or off the computer speeding up
	 * @param onOrOff
	 */
	public static void turnOnComputerSpeedUp(boolean onOrOff){
		ComputerSpeedUp = onOrOff;
	}
	
	/* changes background color
	 * @param newBackground
	 */
	public void changeBackround(Color newBackground){
		setBackground(newBackground);
		this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,newBackground));
		theColor = newBackground;
	}
	
	/* gets note
	 * @return noteTmp
	 */
	public int getNoteTmp(){
		return noteTmp;
	}
	
	/* gets instrument
	 * @return instrumentTmp
	 */
	public int getInstrumentTmp(){
		return instrumentTmp;
	}
	
	public static void changeArray(int desiredArray){
		chosenArrayPieces = desiredArray;
	}
	
	public void changeIcon(){
		String pather ="Resources/PieceImages/"+pieceArray[chosenArrayPieces][rnd.nextInt(pieceArray[chosenArrayPieces].length)]+".png" ;
		theImage = new ImageIcon(pather);
		imageLabel.removeAll();
		imageLabel.setIcon(theImage);
	}
}
