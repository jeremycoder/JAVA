/* 
Names: TA, AB, Jeremy Mwangelwa
Emails: 
Group Number: 3
Course: CET 350 - 001 Technical Computing with Java
Spring 2016

Class Program Number: 5, Application
Program Name: Bounce2.java
*/
import java.util.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;


public class Bounce2 extends Frame
{
		
	
	public Bounce2 () 
	{
		super();	
		
		//Remove window when closed
		
		      addWindowListener(new WindowAdapter()
		      {
		         public void windowClosing(WindowEvent e)
		         {
		           dispose();
		           System.exit(0); //calling the method is a must
		         }
		      });
		   }

		   public static void main(String[] args)
		   {
			   Bounce2 app=new Bounce2();
		      app.setBounds(500,500,400,400);
		      app.setVisible(true);
		   }
		
	
		
	public static void applet()
	{
		//Temp variables
		int i = 0;
		int j = 0;
		
		//Used for removing multiple rectangles with one click
		int BlocksSize1 = 0;
		int BlocksSize2 = 0;
		boolean repeat = true;
		
		//Create various rectangles
		Rectangle screen, rect1, rect2, circleRect;
				
		//Create canvas
		//BallCanvas canvas;
		
		//Create a vector of rectangles
		Vector<Rectangle> Blocks = new Vector();
		
		//Temporary variables
		int tempx = 0;
		int tempy = 0;
		
		//Determine direction of ball
		String yDir = "null";
		String xDir = "null";
		
		//Vector variables
		int vectSize = 0;
		
		//Mouse variables
		int mouseClickX = 0;
		int mouseClickY = 0;
		int mouseX = 0;
		int mouseY = 0;
		int mouseDraggedX = 0;
		int mouseDraggedY = 0;
		int mouseXOrig = 0;
		int mouseYOrig = 0;
		int mousePressedX = 0;
		int mousePressedY = 0;
		int mouseReleasedX = 0;
		int mouseReleasedY = 0;
		
		//Rectangle variables
		int rectX = 0;
		int rectY = 0;
		int rectWidth = 0;
		int rectHeight = 0;
		
		//Rectangle 2 Variables
		int rect2X = 0;
		int rect2Y = 0;
		int rect2Width = 0;
		int rect2Height = 0;
		
		//Screen dimensions
		int screenWidth = 500;
		int screenHeight = 300;
		
		//Create button, labels, and scrollbars
		Button runButton, pauseButton, quitButton;
		Label label;
		Scrollbar speedSB, sizeSB;
		
		//Applet window minimums and maximums
		int xWinMin = 0;
		int yWinMin = 0;
		int xWinMax = 500;
		int yWinMax = 550;
		
		//Play area (where objects can move around) minimums and maximums
		int xPlayMin = 0;
		int yPlayMin = 0;
		int xPlayMax = 495;
		int yPlayMax = 295;
		
		//First X and Y positions
		int firstXPos = 5;
		int firstYPos = 5;
		
		//First width and height
		int firstWidth = 50;
		int firstHeight = 50;
		
		//First circle step
		int firstXStep = 6;
		int firstYStep = 6;
		
		//Variables used to store xPos and yPos for later use
		int origxPos = 0;
		int origyPos = 0;
		
		//Positions of circle/rectangle
		int xPos = firstXPos;
		int yPos = firstYPos;
		
		//Width and Height of Circle/Rectangle
		int width = firstWidth;
		int height = firstHeight;
						
		//Distance moved by circle/rectangle in X and Y directions per loopTime
		int xStep = firstXStep;
		int yStep = firstYStep;
		
		int origWidth = 0;	//Temporary value to store circle/rectangle width
		int origHeight = 0;	//Temporary value to store circle/rectangle height		
		
		boolean startFlag = false; //Flag to signal start/stop of motion
		boolean stopFlag = false;
		boolean quitFlag = false;
		boolean sizeSBMoveFlag = false; //Flag to signal movement in size scroll bar
		
		//Rectangle flags
		boolean rectangleAddedFlag = false;
		
		//Mouse Flags
		boolean mouseDraggedFlag = false;
		boolean mouseReleasedFlag = false;
		boolean mousePressedFlag = false;
		boolean mouseClickedFlag = false;
		
		//Initial loopTime based on original position of speed Scroll bar
		int loopTime = 150;
		
		int speedSBInverter = 16;
		int speedSBMultiplier = 5;
		
		Thread t; //This thread will execute the moving of circle/rectangle
		
		//Create panels
		Panel topPanel;
		Panel bottomPanel;
		
		//Create new frame
		Frame frame = new Frame();
			
		//Set GridLayout on Applet. 2 rows, 0 columns
		frame.setLayout(new GridLayout(2,0));
		
		topPanel = new Panel();
		bottomPanel = new Panel();
		
		//Set colors to panels
		topPanel.setBackground(Color.cyan);
		bottomPanel.setBackground(Color.white);
		
		//Add panels to applet
		frame.add("Center", topPanel);
		frame.add("South", bottomPanel);
		
		//Add BorderLayout to top panel and size
		topPanel.setLayout(new BorderLayout(0,0));		
		topPanel.setSize(screenWidth, screenHeight);
		
		//Add the canvas to the topPanel
		//topPanel.add(canvas);
				
		//Create initial scroll bar values
		int speedSBStartValue = 8;
		int sizeSBStartValue = 5;
		
		//Create GridBagLayout, fill the bottom panel
		GridBagLayout display = new GridBagLayout();
		bottomPanel.setLayout(display);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.EAST;
		
		double colWeights[] = {1,1,1,1,1,1,1,1}; //8
		double rowWeights[] = {1,1,1,1,1,1,1,1,1,1,1,1}; //28
		
		display.rowWeights = rowWeights;
		display.columnWeights = colWeights;		
				
		//Set constraints, add run button to bottomPanel
		runButton = new Button("RUN");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 0;
		bottomPanel.add(runButton, gbc);
		
		//Set constraints, add pause button to bottomPanel
		pauseButton = new Button("PAUSE");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		bottomPanel.add(pauseButton, gbc);
		
		//Set constraints, add quit button to bottomPanel
		quitButton = new Button("QUIT");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 2;
		gbc.gridy = 0;
		bottomPanel.add(quitButton, gbc);
		
		//Set constraints, add speed label to bottomPanel
		label = new Label("SPEED");
		gbc.gridx = 0;
		gbc.gridy = 1;
		bottomPanel.add(label, gbc);
		
		//Set constraints, add speed scrollbar to bottomPanel
		speedSB = new Scrollbar(Scrollbar.HORIZONTAL, speedSBStartValue, 1, 1, 15);
		speedSB.setBlockIncrement(2);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		bottomPanel.add(speedSB, gbc);
		
		//Set constraints, add speed label to bottomPanel
		label = new Label("SIZE");
		gbc.gridx = 0;
		gbc.gridy = 3;
		bottomPanel.add(label, gbc);
		
		//Set constraints, add speed scrollbar to bottomPanel
		sizeSB = new Scrollbar(Scrollbar.HORIZONTAL, sizeSBStartValue, 1, 1, 9);
		sizeSB.setBlockIncrement(2);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 4;
		bottomPanel.add(sizeSB, gbc);
		
		//Screen rectangle dimensions
		screen = new Rectangle(0,0,500,300);
		
		//Register button to receive events
		//runButton.addActionListener(this);
		//pauseButton.addActionListener(this);
		//quitButton.addActionListener(this);
		
		//Register scrollbars to receive events
		//speedSB.addAdjustmentListener(this);
		//sizeSB.addAdjustmentListener(this);
		
		//Register canvas to receive mouse events
		//canvas.addMouseListener(this);
		//canvas.addMouseMotionListener(this);
		
		//Create rectangles
		//rect1 = new Rectangle(100, 50, 40, 40);
		//rect2 = new Rectangle(100, 100, 40, 40);
		//rect3 = new Rectangle(100, 150, 40, 40);
		//Blocks.addElement(rect1);
		//Blocks.addElement(rect2);
		//Blocks.addElement(rect3);
		
		//Set initial loopTime value
		loopTime = (speedSBInverter - speedSB.getValue()) * speedSBMultiplier;
		frame.setVisible(true);		
		
	}
	
	
	
	

			
	
}