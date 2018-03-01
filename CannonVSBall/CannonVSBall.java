package cannonvsball;

/* 

Names: TA, AB, Jeremy Mwangelwa
Emails: 
Group Number: 3
Course: CET 350 - 001 Technical Computing with Java
Spring 2016

Class Program Number: 6
Program Name: CannonVSBall.java
*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class CannonVSBall extends Frame implements WindowListener, Runnable,
ComponentListener, ActionListener, AdjustmentListener, MouseListener,
MouseMotionListener, ItemListener, KeyListener
{	
	//Serialization
	private static final long serialVersionUID = 1L;
	
	//Create Menu bar objects
	MenuBar menuBar;
	Menu controlsMenu, parametersMenu, environmentMenu;
	Menu sizeSubMenu, speedSubMenu;
	MenuItem pauseMI, runMI, restartMI, quitMI;
	MenuItem sizeMI, speedMI;
	MenuItem earthMI, moonMI, mercuryMI, venusMI, marsMI;
	MenuItem jupiterMI, saturnMI, uranusMI, neptuneMI, plutoMI;
	
	CheckboxMenuItem xsmallSize, smallSize, medSize, largeSize, xlargeSize;
	CheckboxMenuItem xslowSpeed, slowSpeed, medSpeed, fastSpeed, xfastSpeed;
	
	//Create panels
	Panel topPanel;
	Panel bottomPanel;
	
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
	
	//Window minimums and maximums
	int xWinMin = 0;
	int yWinMin = 0;
	int xWinMax = 1000;
	int yWinMax = 700;
	
	//Play area (where objects can move around) minimums and maximums
	int xPlayMin = 0;
	int yPlayMin = 0;
	int xPlayMax = 995;
	int yPlayMax = 320;
	
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
	int mouseButtonNumber = 0;
	
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
	
	//Ball motion flag
	boolean startFlag = false;
	boolean stopFlag = false;
	boolean quitFlag = false;
	
	//Size scrollbar flag
	boolean angleSBMoveFlag = false;
	
	//Rectangle flags
	boolean rectangleAddedFlag = false;
	
	//Scroll bars
	Scrollbar weaponVelocitySB, angleSB;
	
	//Mouse Flags
	boolean mouseDraggedFlag = false;
	boolean mouseReleasedFlag = false;
	boolean mousePressedFlag = false;
	boolean mouseClickedFlag = false;
	boolean mouseClickedFlagForWeapon = false;
	
	//Calculate velocities flag
	boolean calcVelFlag = false;
	boolean stopCalcFlag = false;
	
	//Mouse Click Count
	int mouseClickCount = 0;
	
	//Create initial scroll bar values
	int weaponVelocitySBStartValue = 8;
	int angleSBStartValue = 5;
	
	int weaponVelocitySBInverter = 16;
	int weaponVelocitySBMultiplier = 5;
	
	//Temp variables
	int i = 0;
	int j = 0;
	
	//Used for removing multiple rectangles with one click
	int BlocksSize1 = 0;
	int BlocksSize2 = 0;
	boolean repeat = true;
	
	//Create a vector of rectangles
	Vector<Rectangle> Blocks = new Vector<Rectangle>();
	
	//Create various rectangles
	Rectangle screen, rect1, rect2, rect3, circleRect;
	Rectangle cannonRect, cannonBallRect, weaponRect;
	
	//Cannon Points
	Point cannonPoint1;
	Point cannonPoint2;
	Point cannonPoint3;
	Point cannonPoint4;
	
	//Create Polygon and X and Y Points
	Polygon cannon;
	
	//Cannon Coordinates
	int cannonX1 = 0;
	int cannonY1 = 0;
	
	int cannonX2 = 0;
	int cannonY2 = 0;
	
	int cannonX3 = 0;
	int cannonY3 = 0;
	
	int cannonX4 = 0;
	int cannonY4 = 0;
	
	//Original Cannon Position
	int cannonOrigX = xWinMax - 55;
	int cannonOrigY = 285;
	int cannonFrontX = cannonOrigX - 100;
	
	//Cannon angle variables
	double deg = 0.0;
	double rad = 0.0;
	double degPlusOffset = 0.0;
	double angOffset = 0.0;
	int cannonWidth = 10;
	double cannonHyp = 0.0;
	double alpha = 0.0;
	
	//Cannon ball coordinates and dimensions
	int cannonBallX = 0;
	int cannonBallY = 0;
	int cannonBallWidth = 50;
	int cannonBallHeight = 50;
	
	//Reference line coordinates for cannon
	int refX1 = 0;
	int refY1 = 0;
	int refX2 = 0;
	int refY2 = 0;
	int refRadius = 0;
	int refXOffset = 0;
	int refYOffset = 0;
	int testRefX = 0;
	int testRefY = 0;
	
	double p3X = 0;
	double p3Y = 0;
	
	double p4X = 0;
	double p4Y = 0;
		
	//Temporary variables
	int tempx = 0;
	int tempy = 0;
	
	//Weapon coordinates
	int weaponX = 0;
	int weaponY = 0;
	
	//Weapon motion variables
	double weaponV0 = 21.0;
	double weaponV0X = 0.0;
	double weaponV0Y = 0.0;	
	double weaponV = 0.0;
	double weaponVX = 0.0;
	double weaponVY = 0.0;
	boolean weaponMotion = false;
	boolean lostProjectile = false;
	
	//Temporary variable
	double temp = 0.0;
	double temp2 = 0.0;
	int tempSBValue = 0;
	
	//Weapon systems
	String weaponSystem = "projectile";
	
	//Planet flags
	boolean earthFlag = true;
	boolean moonFlag = false;
	boolean mercuryFlag = false;
	boolean venusFlag = false;
	boolean marsFlag = false;
	boolean jupiterFlag = false;
	boolean saturnFlag = false;
	boolean uranusFlag = false;
	boolean neptuneFlag = false;
	boolean plutoFlag = false;
	
	//Gravities for this game
	double earthGravity = -1.81;
	double moonGravity = -0.47;
	double mercuryGravity = -0.85;
	double venusGravity = -1.52;
	double marsGravity = -0.9;
	double jupiterGravity = -5.5;
	double saturnGravity = -1.9;
	double uranusGravity = -1.25;
	double neptuneGravity = -2.49;
	double plutoGravity = -0.13;
	
	//Planetary object's gravity; should always be negative in this game
	double gravity = earthGravity;
			
	
	//Actual Gravities for Planets and Our Moon
	double actualEarthGravity = 9.8;
	double actualMoonGravity = 1.622;
	double actualMercuryGravity = 3.7;
	double actualVenusGravity = 8.87;
	double actualMarsGravity = 3.711;
	double actualJupiterGravity = 24.79;
	double actualSaturnGravity = 10.44;
	double actualUranusGravity = 8.69;
	double actualNeptuneGravity = 11.15;
	double actualPlutoGravity = 0.62;
	
	//Environment information
	String environment = "";
	double envGravity = 0.0;
	
	//Used to balance firing power for all environments
	double powerBalance = 5.5;
	
	//Used to display actual velocity
	double actualWeaponV0 = 0.0;
	double actualWeaponV0Feet = 0.0;
	
	//Time
	int time = 0;
	int stopTime = 0;
	int startTime = 0;
	double totalTime = 0;
	double timeBalance = 0.0;
	
	//Distance
	double xDistance = 0.0;
	double yDistance = 0.0;
	int wInitialxPos = 0;
	int wFinalxPos = 0;
	int wInitialyPos = 0;
	int wFinalyPos = 0;
	
	//Vector variables
	int vectSize = 0;
	
	//Create button, labels, and scrollbars
	Button runPauseButton, restartButton, quitButton;
	Label label;
	
	//Thread to control ball movement
	Thread t; 
	
	//Time for thread delay in milliseconds
	int loopTime = 50;
	
	//For synchronizing computer time with human time
	int timeAndLoopTime = 0;
	
	//Create canvas
	BallCanvas canvas;
	
	//Scores
	int ballScore = -1;
	int yourScore = 0;
	
	//Constructor
	public CannonVSBall() 
	{
		lostProjectile = false;
		//Start on Earth
		environment = "Earth";
		envGravity = actualEarthGravity;
		
		//Set Gravitational Balance
		//SystempowerBalance = earthGravity;
		
		//Initial scores
		ballScore = -1;
		yourScore = 0;
		
		//Initiate angle and weapon velocity
		deg = 45.0;		
		
		//Create new instance
		canvas = new BallCanvas();
		
		//Create new frame
		Frame frame = new Frame();
					
		//Set GridLayout layout manager
		frame.setLayout(new GridLayout(2,0));
		
		//Create menu bar
		menuBar = new MenuBar();
		
		//Build the Menus
		controlsMenu = new Menu("Game Controls");
		parametersMenu = new Menu("Parameters");
		environmentMenu = new Menu("Environment");
		
		//Build the ControlsMenu menu items
		pauseMI = new MenuItem("Pause");		
		runMI = new MenuItem("Run");
		restartMI = new MenuItem("Restart");
		quitMI = new MenuItem("Quit");		
		
		//Set controlsMenu shortcuts
		pauseMI.setShortcut(new MenuShortcut(KeyEvent.VK_P));
		runMI.setShortcut(new MenuShortcut(KeyEvent.VK_R));
		restartMI.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		quitMI.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
		
		//Build the parametersMenu submenus menu items
		sizeSubMenu = new Menu("Size");		
		speedSubMenu = new Menu("Speed");
		
		//Build checkboxes for sizeSubMenu
		xsmallSize = new CheckboxMenuItem("X-Small", false);
		smallSize = new CheckboxMenuItem("Small", false);
		medSize = new CheckboxMenuItem("Medium Size", true);
		largeSize = new CheckboxMenuItem("Large", false);
		xlargeSize = new CheckboxMenuItem("X-Large", false);
		
		//Add checkboxes to sizeSubMenu
		sizeSubMenu.add(xsmallSize);
		sizeSubMenu.add(smallSize);
		sizeSubMenu.add(medSize);
		sizeSubMenu.add(largeSize);
		sizeSubMenu.add(xlargeSize);
		
		//Build checkboxes for speedSubMenu
		xslowSpeed = new CheckboxMenuItem("X-Slow", false);
		slowSpeed = new CheckboxMenuItem("Slow", false);
		medSpeed = new CheckboxMenuItem("Medium Speed", true);
		fastSpeed = new CheckboxMenuItem("Fast", false);
		xfastSpeed = new CheckboxMenuItem("X-Fast", false);
		
		//Add checkboxes to sizeSubMenu
		speedSubMenu.add(xslowSpeed);
		speedSubMenu.add(slowSpeed);
		speedSubMenu.add(medSpeed);
		speedSubMenu.add(fastSpeed);
		speedSubMenu.add(xfastSpeed);
		
		//Add parametersMenu menu items
		parametersMenu.add(sizeSubMenu);
		parametersMenu.add(speedSubMenu);		
		
		//Build the environmentMenu menu items
		earthMI = new MenuItem("Earth 9.8m/s/s");
		moonMI = new MenuItem("Moon 1.622m/s/s");
		mercuryMI = new MenuItem("Mercury 3.7m/s/s");
		venusMI = new MenuItem("Venus 8.87m/s/s");
		marsMI = new MenuItem("Mars 3.711m/s/s");
		jupiterMI = new MenuItem("Jupiter 24.79m/s/s");
		saturnMI = new MenuItem("Saturn 10.44m/s/s");
		uranusMI = new MenuItem("Uranus 8.69m/s/s");
		neptuneMI = new MenuItem("Neptune 11.15m/s/s");
		plutoMI = new MenuItem("Pluto 0.62m/s/s");
		
		//Add environmentMenu items to environmentMenu
		environmentMenu.add(earthMI);
		environmentMenu.add(moonMI);
		environmentMenu.add(mercuryMI);
		environmentMenu.add(venusMI);
		environmentMenu.add(marsMI);
		environmentMenu.add(jupiterMI);
		environmentMenu.add(saturnMI);
		environmentMenu.add(uranusMI);
		environmentMenu.add(neptuneMI);
		environmentMenu.add(plutoMI);
		
		//Add menu items to ControlsMenu
		controlsMenu.add(pauseMI);
		controlsMenu.add(runMI);
		controlsMenu.add(restartMI);
		controlsMenu.add(quitMI);		
				
		
		//Add menus to menu bar
		menuBar.add(controlsMenu);
		menuBar.add(parametersMenu);
		menuBar.add(environmentMenu);
		
		
		//Instantiate panels
		topPanel = new Panel();
		bottomPanel = new Panel();
		
		
		
		//Set colors to panels
		topPanel.setBackground(Color.white);
		bottomPanel.setBackground(Color.white);
		
		//Add panels to applet
		frame.add(topPanel);
		frame.add(bottomPanel);
		
		//Add BorderLayout to top panel and size
		topPanel.setLayout(new BorderLayout(0,0));		
		topPanel.setSize(screenWidth, screenHeight);
		
		//Add the canvas to the topPanel
		topPanel.add(canvas);	
		
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
		runPauseButton = new Button("RUN");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.ipady = 40;
		gbc.gridx = 0;
		gbc.gridy = 0;
		bottomPanel.add(runPauseButton, gbc);
		
		//Set constraints, add pause button to bottomPanel
		restartButton = new Button("RESTART");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		bottomPanel.add(restartButton, gbc);
		
		//Set constraints, add quit button to bottomPanel
		quitButton = new Button("QUIT");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 2;
		gbc.gridy = 0;
		bottomPanel.add(quitButton, gbc);
		
		//Set constraints, add velocity label to bottomPanel
		label = new Label("INITIAL WEAPON VELOCITY");
		gbc.gridx = 0;
		gbc.gridy = 1;
		bottomPanel.add(label, gbc);
		
		//Set constraints, add speed scrollbar to bottomPanel
		weaponVelocitySB = new Scrollbar(Scrollbar.HORIZONTAL, 210, 1, 29, 391);
		weaponVelocitySB.setBackground(Color.cyan);
		weaponVelocitySB.setBlockIncrement(60);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		bottomPanel.add(weaponVelocitySB, gbc);
		
		//Set constraints, add speed label to bottomPanel
		label = new Label("ANGLE");
		gbc.gridx = 0;
		gbc.gridy = 3;
		bottomPanel.add(label, gbc);
		
		//Set constraints, add speed scrollbar to bottomPanel
		angleSB = new Scrollbar(Scrollbar.HORIZONTAL, (int)deg, 1, 0, 91);
		angleSB.setBackground(Color.cyan);
		angleSB.setBlockIncrement(2);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 4;
		bottomPanel.add(angleSB, gbc);
		
		//Screen rectangle dimensions
		//screen = new Rectangle(0,0,500,300);
		
		//Register button to receive events
		runPauseButton.addActionListener(this);
		restartButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		//Register scrollbars to receive events
		weaponVelocitySB.addAdjustmentListener(this);
		angleSB.addAdjustmentListener(this);
		
		//Register canvas to receive mouse events
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		
		//Register controlMenu MenuItems to receive events
		pauseMI.addActionListener(this);
		runMI.addActionListener(this);
		restartMI.addActionListener(this);
		quitMI.addActionListener(this);
		
		//Register environment MenuItems to receive events
		earthMI.addActionListener(this);
		moonMI.addActionListener(this);
		mercuryMI.addActionListener(this);
		venusMI.addActionListener(this);
		marsMI.addActionListener(this);
		jupiterMI.addActionListener(this);
		saturnMI.addActionListener(this);
		uranusMI.addActionListener(this);
		neptuneMI.addActionListener(this);
		plutoMI.addActionListener(this);
		
		//Register checkboxMenuItems to receive events
		xsmallSize.addItemListener(this);
		smallSize.addItemListener(this);
		medSize.addItemListener(this);
		largeSize.addItemListener(this);
		xlargeSize.addItemListener(this);
		
		//Register checkboxMenuItems to receive events
		xslowSpeed.addItemListener(this);
		slowSpeed.addItemListener(this);
		medSpeed.addItemListener(this);
		fastSpeed.addItemListener(this);
		xfastSpeed.addItemListener(this);
		
		
		//Create rectangles
		rect1 = new Rectangle(100, 50, 40, 40);
		rect2 = new Rectangle(100, 100, 40, 40);
		rect3 = new Rectangle(100, 150, 40, 40);
				
		//Set initial loopTime value
		//loopTime = (weaponVelocitySBInverter - weaponVelocitySB.getValue()) * weaponVelocitySBMultiplier;
		
		//Add menus to frame
		frame.setMenuBar(menuBar);
		
		frame.addWindowListener(this);
		frame.addComponentListener(this);
		frame.setTitle("CannonVSBall: Destroy the Evil Ball Before It Destroys You!");
		frame.setSize(xWinMax, yWinMax);
		frame.setVisible(true);
		start();
	}
	
	public static void main(String args[])
	{
		//Create new instance of the application
		CannonVSBall app = new CannonVSBall();
		app.setBackground(Color.white);
	}
	
	public void start()
	{
		if (t == null)
		{
			t = new Thread(this);
			t.start();
			
		}
		//System.out.println("Initial: "+weaponV0);
		
	}
	
	public void stop()
	{
		t = null;
		
	}
	
	public void quit()
	{
		runPauseButton.removeActionListener(this);
		restartButton.removeActionListener(this);
		quitButton.removeActionListener(this);
		
		pauseMI.removeActionListener(this);
		runMI.removeActionListener(this);
		restartMI.removeActionListener(this);
		quitMI.removeActionListener(this);
		
		xsmallSize.removeItemListener(this);
		smallSize.removeItemListener(this);
		medSize.removeItemListener(this);
		largeSize.removeItemListener(this);
		xlargeSize.removeItemListener(this);
		
		xslowSpeed.removeItemListener(this);
		slowSpeed.removeItemListener(this);
		medSpeed.removeItemListener(this);
		fastSpeed.removeItemListener(this);
		xfastSpeed.removeItemListener(this);
		
		weaponVelocitySB.removeAdjustmentListener(this);
		angleSB.removeAdjustmentListener(this);
		
		canvas.removeMouseListener(this);
		canvas.removeMouseMotionListener(this);
		
		earthMI.removeActionListener(this);
		moonMI.removeActionListener(this);
		mercuryMI.removeActionListener(this);
		venusMI.removeActionListener(this);
		marsMI.removeActionListener(this);
		jupiterMI.removeActionListener(this);
		saturnMI.removeActionListener(this);
		uranusMI.removeActionListener(this);
		neptuneMI.removeActionListener(this);
		plutoMI.removeActionListener(this);
	}
	
	//Thread to run
	public void run()
	{
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		
			for( ; ; )
			{
				try 
				{				
					canvas.repaint();
					Thread.sleep(loopTime);
					
					
				} catch(InterruptedException e) {}
				
				
			}
			
		
		
		
	}
	
	//Handling Window Events
	@Override
	public void windowOpened(WindowEvent e) {		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		stop();
		//Remove all listeners
		quit();
		//Stop the thread
		System.exit(0);
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	//Handling Component Events
	@Override
	public void componentResized(ComponentEvent e)
	{
		
	}
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
		
	}
	
	@Override
	public void componentShown(ComponentEvent e)
	{
		
	}
	
	@Override
	public void componentMoved(ComponentEvent e)
	{
		
	}
	
	//Actions to be performed when buttons are pressed
	@Override
	public void actionPerformed(ActionEvent buttonOrMenuEvent)
	{
		//Actions performed when runPauseButton is pressed
		if (buttonOrMenuEvent.getSource() == runPauseButton)
		{
			if (runPauseButton.getLabel() == "RUN")
			{
				runPauseButton.setLabel("PAUSE");
				startFlag = true;
				stopFlag = false;
				loopTime = 50;
				start();			
			}
			else if (runPauseButton.getLabel() == "PAUSE")
			{
				runPauseButton.setLabel("RUN");
				startFlag = false;
				stopFlag = true;
				loopTime = 50;
				stop();
			}			
		}
		//Actions if Pause menu item pressed
		else if (buttonOrMenuEvent.getSource() == pauseMI)
		{
			runPauseButton.setLabel("RUN");
			startFlag = false;
			stopFlag = true;
			loopTime = 50;
			stop();
		}
		//Actions of run menu item pressed
		else if (buttonOrMenuEvent.getSource() == runMI)
		{
			runPauseButton.setLabel("PAUSE");
			startFlag = true;
			stopFlag = false;
			loopTime = 50;
			start();	
		}
		
		//Actions performed when restartButton is pressed
		else if ((buttonOrMenuEvent.getSource() == restartButton)
				|| buttonOrMenuEvent.getSource() == restartMI)
		{
			startFlag = false;
			stopFlag = true;
			stop();
			ballScore = -1;
			yourScore = 0;
			deg = 45;
			xPos = 0;
			yPos = 0;
			runPauseButton.setLabel("RUN");
			angleSB.setValue(45);
			weaponVelocitySB.setValue(210);
			loopTime = 50;
		}
		else if ((buttonOrMenuEvent.getSource() == quitButton) || 
				(buttonOrMenuEvent.getSource() == quitMI))
		{
			stop();
			quitFlag = true;
			//Remove all listeners
			quit();			
			
		}
		
		//Environment MenuItems
		//Actions of run menu item pressed
		if (buttonOrMenuEvent.getSource() == earthMI)
		{
			gravity = earthGravity;
			earthFlag = true;
			
			earthFlag = true;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Earth";
			envGravity = actualEarthGravity;
			
			//Change position when planet changes
			xPos = 0;
			yPos = 0;
		}
		else if (buttonOrMenuEvent.getSource() == moonMI)
		{
			gravity = moonGravity;
			moonFlag = true;
			
			earthFlag = false;
			moonFlag = true;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Moon";
			envGravity = actualMoonGravity;
			
			//Change position when planet changes
			xPos = 40;
			yPos = 40;
		}
		else if (buttonOrMenuEvent.getSource() == mercuryMI)
		{
			gravity = mercuryGravity;
			mercuryFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = true;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Mercury";
			envGravity = actualMercuryGravity;
			
			//Change position when planet changes
			xPos = 70;
			yPos = 70;
		}
		else if (buttonOrMenuEvent.getSource() == venusMI)
		{
			gravity = venusGravity;
			venusFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = true;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Venus";
			envGravity = actualVenusGravity;
			
			//Change position when planet changes
			xPos = 115;
			yPos = 115;
		}
		else if (buttonOrMenuEvent.getSource() == marsMI)
		{
			gravity = marsGravity;
			marsFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = true;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Mars";
			envGravity = actualMarsGravity;
			
			//Change position when planet changes
			xPos = 81;
			yPos = 110;
		}
		else if (buttonOrMenuEvent.getSource() == jupiterMI)
		{
			gravity = jupiterGravity;
			jupiterFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = true;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Jupiter";
			envGravity = actualJupiterGravity;
			
			//Change position when planet changes
			xPos = 304;
			yPos = 250;
		}
		else if (buttonOrMenuEvent.getSource() == saturnMI)
		{
			gravity = saturnGravity;
			saturnFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = true;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Saturn";
			envGravity = actualSaturnGravity;
			
			//Change position when planet changes
			xPos = 99;
			yPos = 200;
		}
		else if (buttonOrMenuEvent.getSource() == uranusMI)
		{
			gravity = uranusGravity;
			uranusFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = true;
			neptuneFlag = false;
			plutoFlag = false;
			
			environment = "Uranus";
			envGravity = actualUranusGravity;
		}
		else if (buttonOrMenuEvent.getSource() == neptuneMI)
		{
			gravity = neptuneGravity;
			neptuneFlag = true;
			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = true;
			plutoFlag = false;
			
			environment = "Neptune";
			envGravity = actualNeptuneGravity;
			
			//Change position when planet changes
			xPos = 122;
			yPos = 222;
		}
		else if (buttonOrMenuEvent.getSource() == plutoMI)
		{
			gravity = plutoGravity;
			plutoFlag = true;			
			earthFlag = false;
			moonFlag = false;
			mercuryFlag = false;
			venusFlag = false;
			marsFlag = false;
			jupiterFlag = false;
			saturnFlag = false;
			uranusFlag = false;
			neptuneFlag = false;
			plutoFlag = true;
			
			environment = "Pluto";
			envGravity = actualPlutoGravity;
			
			//Change position when planet changes
			xPos = 199;
			yPos = 56;
		}	
		
	}
	
	//Actions performed for keys
	@Override
	public void keyPressed(KeyEvent keyPressed)
	{
		
	}
	
	@Override
	public void keyReleased(KeyEvent keyReleased)
	{
		
	}
	
	@Override
	public void keyTyped(KeyEvent keyTyped)
	{
		//Key code for pause
		if (keyTyped.getKeyCode() == KeyEvent.VK_P)
		{
			runPauseButton.setLabel("RUN");
			startFlag = false;
			stopFlag = true;
			stop();			
		}
		
		//Key code for run
		else if (keyTyped.getKeyCode() == KeyEvent.VK_R)
		{
			runPauseButton.setLabel("PAUSE");
			startFlag = true;
			stopFlag = false;
			start();			
		}
		
		//Key code for run
		else if (keyTyped.getKeyCode() == KeyEvent.VK_S)
		{
			startFlag = false;
			stopFlag = true;
			stop();
			ballScore = -1;
			yourScore = 0;
			deg = 45;
			xPos = 0;
			yPos = 0;
			runPauseButton.setLabel("RUN");
			angleSB.setValue(45);
			weaponVelocitySB.setValue(210);			
		}
		
		//Key code for run
		else if (keyTyped.getKeyCode() == KeyEvent.VK_Q)
		{
			stop();
			quitFlag = true;
			//Remove all listeners
			quit();
			
		}		
		
	}
	
	

	//Action to be performed when Checkbox Menu Items changed
	@Override
	public void itemStateChanged(ItemEvent checkboxMenuItemEvent)
	{
		//Actions for Size Menu
		if (checkboxMenuItemEvent.getSource() == xsmallSize)
		{
			if (xsmallSize.getState() == true)
			{
				smallSize.setState(false);
				medSize.setState(false);
				largeSize.setState(false);
				xlargeSize.setState(false);
				width = 15;
				height = 15;
			}
			
		}
		else if (checkboxMenuItemEvent.getSource() == smallSize)
		{
			if (smallSize.getState() == true)
			{
				xsmallSize.setState(false);
				medSize.setState(false);
				largeSize.setState(false);
				xlargeSize.setState(false);
				width = 30;
				height = 30;
			}
		}
		else if (checkboxMenuItemEvent.getSource() == medSize)
		{
			if (medSize.getState() == true)
			{
				xsmallSize.setState(false);
				smallSize.setState(false);
				largeSize.setState(false);
				xlargeSize.setState(false);
				width = 50;
				height = 50;
			}
		}
		else if (checkboxMenuItemEvent.getSource() == largeSize)
		{
			if (largeSize.getState() == true)
			{
				xsmallSize.setState(false);
				smallSize.setState(false);
				medSize.setState(false);
				xlargeSize.setState(false);
				width = 70;
				height = 70;
			}
		}
		else if (checkboxMenuItemEvent.getSource() == xlargeSize)
		{
			if (xlargeSize.getState() == true)
			{
				xsmallSize.setState(false);
				smallSize.setState(false);
				medSize.setState(false);
				largeSize.setState(false);
				width = 90;
				height = 90;
			}
		}
		
		//Actions for Speed Menu
		if (checkboxMenuItemEvent.getSource() == xslowSpeed)
		{
			if (xslowSpeed.getState() == true)
			{
				slowSpeed.setState(false);
				medSpeed.setState(false);
				fastSpeed.setState(false);
				xfastSpeed.setState(false);
				
				//Check direction before changing speed
				if(xStep < 0)
					xStep = -1;
				else
					xStep = 1;
				
				if(yStep < 0)
					yStep = -1;
				else
					yStep = 1;
				
			}
			
		}
		else if (checkboxMenuItemEvent.getSource() == slowSpeed)
		{
			if (slowSpeed.getState() == true)
			{
				xslowSpeed.setState(false);
				medSpeed.setState(false);
				fastSpeed.setState(false);
				xfastSpeed.setState(false);
				
				//Check direction before changing speed
				if(xStep < 0)
					xStep = -3;
				else
					xStep = 3;
				
				if(yStep < 0)
					yStep = -3;
				else
					yStep = 3;
				
			}
			
		}
		else if (checkboxMenuItemEvent.getSource() == medSpeed)
		{
			if (medSpeed.getState() == true)
			{
				xslowSpeed.setState(false);
				slowSpeed.setState(false);
				fastSpeed.setState(false);
				xfastSpeed.setState(false);
								
				//Check direction before changing speed
				if(xStep < 0)
					xStep = -6;
				else
					xStep = 6;
				
				if(yStep < 0)
					yStep = -6;
				else
					yStep = 6;
				
			}
			
		}
		else if (checkboxMenuItemEvent.getSource() == fastSpeed)
		{
			if (fastSpeed.getState() == true)
			{
				xslowSpeed.setState(false);
				slowSpeed.setState(false);
				medSpeed.setState(false);
				xfastSpeed.setState(false);
				
				//Check direction before changing speed
				if(xStep < 0)
					xStep = -10;
				else
					xStep = 10;
				
				if(yStep < 0)
					yStep = -10;
				else
					yStep = 10;
				
			}
			
		}
		else if (checkboxMenuItemEvent.getSource() == xfastSpeed)
		{
			if (xfastSpeed.getState() == true)
			{
				xslowSpeed.setState(false);
				slowSpeed.setState(false);
				medSpeed.setState(false);
				fastSpeed.setState(false);
				
				//Check direction before changing speed
				if(xStep < 0)
					xStep = -20;
				else
					xStep = 20;
				
				if(yStep < 0)
					yStep = -20;
				else
					yStep = 20;
				
			}
			
		}
		
		
	}
	
	//Actions to be performed when scroll bar is adjusted
	public void adjustmentValueChanged(AdjustmentEvent sbChanged)
	{
		//If adjustment came from the speed scroll bar
		if (sbChanged.getSource() == weaponVelocitySB)
		{		
				//Prevent adjustment while projectile is moving
				if (weaponMotion == false) 
				{
				//Adjust firing power to something reasonable
				tempSBValue = sbChanged.getValue();
				tempSBValue = (int) (tempSBValue/powerBalance);
				weaponV0 = tempSBValue;
				}
				
				System.out.println(weaponV0);
		}
		
		//If adjustment came from the size scroll bar
		if (sbChanged.getSource() == angleSB)
		{			
			deg = sbChanged.getValue();
			//Set size scroll bar movement flag
			//angleSBMoveFlag = true;
			//System.out.println(deg);
			
		}
		
	}
	
	//Actions performed when mouse is clicked
	public void mouseClicked(MouseEvent mouseClick)
	{
		mouseClickX = mouseClick.getX();
		mouseClickY = mouseClick.getY();
		mouseClickedFlag = true;
		mouseClickedFlagForWeapon = true;
		mouseClickCount = mouseClick.getClickCount();
		mouseButtonNumber = mouseClick.getButton();
		//System.out.println("MouseClickX: " + mouseClickX);
		//System.out.println("MouseClickY: " + mouseClickY);
		
		
	}
	
	//Actions performed when mouse entered component
	public void mouseEntered(MouseEvent mouseEnter)
	{
		
	}
	
	//Actions performed when mouse exists component
	public void mouseExited(MouseEvent mouseExit)
	{
		
	}
	
	//Actions performed when mouse is pressed
	public void mousePressed(MouseEvent mousePress)
	{
		//mouseX = 0;
		//mouseY = 0;
		mousePressedX = mousePress.getX();
		mousePressedY = mousePress.getY();
		mousePressedFlag = true;
		//System.out.println("mousePressedX: " + mousePressedX);
		//System.out.println("mousePressedY: " + mousePressedY);
		
	}
	
	//Actions performed when mouse is released
	public void mouseReleased(MouseEvent mouseRelease)
	{
		mouseReleasedX = mouseRelease.getX();
		mouseReleasedY = mouseRelease.getY();
		mouseReleasedFlag = true;
		//System.out.println("mouseReleasedX: " + mouseReleasedX);
		//System.out.println("mouseReleasedY: " + mouseReleasedY);
	}
	
	//Actions performed when mouse is dragged
	public void mouseDragged(MouseEvent mouseDrag)
	{
		mouseDraggedX = mouseDrag.getX();
		mouseDraggedY = mouseDrag.getY();
		mouseDraggedFlag = true;
		//System.out.println("mouseDraggedX: " + mouseDraggedX);
		//System.out.println("mouseDraggedY: " + mouseDraggedY);
		
		
	}
	
	//Actions performed when mouse is dragged
	public void mouseMoved(MouseEvent mouseMove)
	{
		
	}
	
	
	
	//Converts degrees to radians
	public double degToRad(double degs)
	{
		return (degs/180)*Math.PI;
		
	}
	
	//Converts radians to degrees
	public double radToDeg(double rads)
	{
		return (rads * 180)/Math.PI;
		
	}
	
	//No radio buttons in menus for AWT, allow only one check for submenus
	//Buggy right now, may use in conjunction with event listeners
	public void uncheckSubMenus()
	{
		//Allow only one check for sizeSubMenu
		if (xsmallSize.getState() == true)
		{
			smallSize.setState(false);
			medSize.setState(false);
			largeSize.setState(false);
			xlargeSize.setState(false);
		}
		else if (smallSize.getState() == true)
		{
			xsmallSize.setState(false);
			medSize.setState(false);
			largeSize.setState(false);
			xlargeSize.setState(false);
		}
		else if (medSize.getState() == true)
		{
			xsmallSize.setState(false);
			smallSize.setState(false);
			largeSize.setState(false);
			xlargeSize.setState(false);
		}
		else if (largeSize.getState() == true)
		{
			xsmallSize.setState(false);
			smallSize.setState(false);
			medSize.setState(false);
			xlargeSize.setState(false);
		}
		else if (xlargeSize.getState() == true)
		{
			xsmallSize.setState(false);
			smallSize.setState(false);
			medSize.setState(false);
			largeSize.setState(false);
		}
		
		//Allow only one check for sizeSubMenu
		if (xslowSpeed.getState() == true)
		{
			slowSpeed.setState(false);
			medSpeed.setState(false);
			fastSpeed.setState(false);
			xfastSpeed.setState(false);
		}
		else if (slowSpeed.getState() == true)
		{
			xslowSpeed.setState(false);
			medSpeed.setState(false);
			fastSpeed.setState(false);
			xfastSpeed.setState(false);
		}
		else if (medSize.getState() == true)
		{
			xsmallSize.setState(false);
			smallSize.setState(false);
			largeSize.setState(false);
			xlargeSize.setState(false);
		}
		else if (fastSpeed.getState() == true)
		{
			xfastSpeed.setState(false);
			fastSpeed.setState(false);
			medSpeed.setState(false);
			xfastSpeed.setState(false);
		}
		else if (xfastSpeed.getState() == true)
		{
			xslowSpeed.setState(false);
			slowSpeed.setState(false);
			medSpeed.setState(false);
			fastSpeed.setState(false);
		}
	}
	
	//A canvas that takes an image; we draw on the image
	class BallCanvas extends Canvas
	{	
		private static final long serialVersionUID = 1L;
		
		//Image variable and graphics context
		Image buffer;
		Graphics g;
		
		//Double buffering applied
		public void paint(Graphics cg)
		{
			update(cg);
		}
		
		public void update(Graphics cg)
		{
			//uncheckSubMenus();
			//Create image
			buffer = createImage( (int)topPanel.getWidth(), (int)topPanel.getHeight());
			
			if (g != null)
				g.dispose();
			
			//Apply graphics context to the image
			g = buffer.getGraphics();
						
			//Draw the ball
			g.setColor(Color.magenta);
			g.drawOval(xPos, yPos, width, height);
			g.setColor(Color.green);
			g.fillOval(xPos, yPos, width, height);
			//System.out.println(xStep);
			
			//Draw laser
			//g.setColor(Color.red);
			//g.drawLine(xPos, yPos, xPlayMax, yPlayMax);
			
			//Show scores on screen
			
			//Show planet and its gravity
			g.setColor(Color.black);
			
			//Display scores
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString("Ball: " + ballScore, (int)(xWinMax/10), (int) (yWinMin + 15));
			g.drawString("You: " + yourScore, (int)(xWinMax/10), (int) (yWinMin + 30));
						
			//Display environment information			
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString("Environment: " + environment, (int)(xWinMax/6), (int) (yWinMin + 15));
			g.drawString("g : " + envGravity + "m/s/s", (int)(xWinMax/6), (int) (yWinMin + 30));
			
			//Display target information			
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString("TARGET", (int)(xWinMax/3), (int) (yWinMin + 15));
			g.drawString("X : " + xPos, (int)(xWinMax/3), (int) (yWinMin + 30));
			g.drawString("Y : " + yPos, (int)(xWinMax/3), (int) (yWinMin + 45));
			
			//Display target information
			//Get back actual velocity without power balance
			actualWeaponV0 = weaponV0 * powerBalance;
			actualWeaponV0Feet = actualWeaponV0 * 3.28;
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString("Initial Velocity: " + actualWeaponV0 + "m/s / " + actualWeaponV0Feet + "ft/s", (int)(xWinMax/1.5), (int) (yWinMin + 15));
			g.drawString("Angle : " + deg, (int)(xWinMax/1.5), (int) (yWinMin + 30));
			g.drawString("Time : " + timeBalance + "s", (int)(xWinMax/1.5), (int) (yWinMin + 45));
			
			//Display missle/projectile information
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString("PROJECTILE", (int)(xWinMax/2.3), (int) (yWinMin + 15));
			g.drawString("X : " + weaponX, (int)(xWinMax/2.3), (int) (yWinMin + 30));
			g.drawString("Y : " + weaponY, (int)(xWinMax/2.3), (int) (yWinMin + 45));
			//g.drawString("hDistance : " + xDistance, (int)(xWinMax/2.3), (int) (yWinMin + 60));
			//g.drawString("vDistance : " + yDistance, (int)(xWinMax/2.3), (int) (yWinMin + 75));
					
			//Draw line based on angles for testing
			g.setColor(Color.red);
			
			refX1 = cannonX1; //Maximum x-coordinate
			refY1 = cannonY1; //Maximum y-coordinate
			refX2 = cannonFrontX; //
			refY2 = cannonY1;
						
			//Length of reference line
			refRadius = refX1 - refX2;
			refXOffset = refX1;
			refYOffset = refY1 - refRadius;			
			
			//Converting from regular coordinates to computer coordinates
			//deg = 45;
			
			//Calculate X
			rad = degToRad(deg);
			
			//Store radians for projectile alpha angle calculation
			alpha = rad;
			
			testRefX = (int) (Math.cos(rad)*refRadius);
			testRefX = (refX1 - testRefX);
						
			//Calculate Y
			testRefY = (int) (Math.sin(rad)*refRadius);
			testRefY = (refRadius - testRefY);
			testRefY = (refY1 - refRadius) + testRefY;
			//System.out.println("P2: ("+testRefX+","+testRefY+")");
			//g.drawLine(refX1, refY1, testRefX, testRefY);
						
			//Calculate angle between bottom of cannon and point 3 (angular offset)
			angOffset = cannonWidth/(double)refRadius;
			angOffset = Math.atan(angOffset);
			angOffset = radToDeg(angOffset);
			
			//Calculate cannon hypotenuse
			cannonHyp = ((double)refRadius * (double)refRadius) + (cannonWidth * cannonWidth);
			cannonHyp = Math.sqrt(cannonHyp);
			
			//System.out.println(Math.ceil(cannonHyp));
			//g.drawLine(refX1, refY1, testRefX, testRefY);
			
			degPlusOffset = deg + angOffset;
			//System.out.println(degPlusOffset);
			
			//Calculate P3X
			rad = degToRad(degPlusOffset);
			p3X = (Math.cos(rad)*cannonHyp);
			p3X = (refX1 - p3X);			
			
			//Calculate P3Y
			//degPlusOffset = deg + angOffset;
			//rad = degToRad(degPlusOffset);
			p3Y = (Math.sin(rad)*cannonHyp);
			p3Y = (cannonHyp - p3Y);
			//System.out.println(p3Y);
			p3Y = (refY1 - cannonHyp) + p3Y;
			
			/*//Calculate Y
			testRefY = (int) (Math.sin(rad)*refRadius);
			testRefY = (refRadius - testRefY);
			testRefY = (refY1 - refRadius) + testRefY;*/
			
			//System.out.println("P3: ("+p3X+","+p3Y+")");
			//Instantiate cannon, set cannon color
			cannon = new Polygon();
			g.setColor(Color.black);
			
			//Set cannon coordinates
			cannonX1 = cannonOrigX;
			cannonY1 = cannonOrigY;
			
			cannonX2 = testRefX;
			cannonY2 = testRefY;
			
			cannonX3 = (int)p3X;
			cannonY3 = (int)p3Y;
			
			cannonX4 = cannonX1 + (cannonX3 - cannonX2);
			cannonY4 = cannonY1 - (cannonY2 - cannonY3);
			
			//Add points to cannon polygon
			cannon.addPoint(cannonX1, cannonY1);
			cannon.addPoint(cannonX2, cannonY2);
			cannon.addPoint(cannonX3, cannonY3);
			cannon.addPoint(cannonX4, cannonY4);
			
			//Create cannon points
			cannonPoint1 = new Point(cannonX1, cannonY1);
			cannonPoint2 = new Point(cannonX2, cannonY2);
			cannonPoint3 = new Point(cannonX3, cannonY3);
			cannonPoint4 = new Point(cannonX4, cannonY4);
			
			//System.out.println(cannonX2);
			//System.out.println(cannonY2);
			
			//Get circle's rectangle
			circleRect = new Rectangle(xPos, yPos, width, height);
			
			//Add points to rectangle
			cannonRect = new Rectangle(cannonX2-10, cannonY2-10, cannonX2+100, cannonY2+100);
			//g.drawRect(cannonX2-10, cannonY2-10, cannonX2+100, cannonY2+100);
			//Draw cannonBall rectangle to test for mouse clicks
			cannonBallRect = new Rectangle(cannonBallX, cannonBallY, cannonBallWidth, cannonBallHeight);
			
			//Weapon Rectangle
			weaponRect = new Rectangle(weaponX, weaponY, 10, 10);
			g.drawRect(weaponX, weaponY, 10, 10);
			
			//Make projectile destroy ball
			if (weaponMotion)
			{
				
				if (circleRect.contains(weaponRect))
				{					
					yourScore++;
					g.setColor(Color.GRAY);
					g.drawOval(xPos, yPos, width, height);
					g.setColor(Color.BLACK);
					totalTime = totalTime + timeBalance;
					
					weaponMotion = false;
					mouseClickedFlagForWeapon = false;
					weaponX = 0;
					weaponY = 0;
					time = 0;
					timeAndLoopTime = 0;
					
					startFlag = false;
					stopFlag = true;
					stop();
					deg = 45;
					xPos = 0;
					yPos = 0;
					runPauseButton.setLabel("RUN");
					angleSB.setValue(45);
					weaponVelocitySB.setValue(210);
					loopTime = 50;
					
				}
				
				//Make projectile destroy rectangles
				for (i = 0; i < Blocks.size(); i++)
				{
					if (Blocks.elementAt(i).contains(weaponRect))
						Blocks.removeElementAt(i);													
					
				}
				
				
			}
			
			System.out.println(loopTime);
			
			//Make ball destroy cannonBall and cannonRectangle
			if ((circleRect.intersects(cannonBallRect)) || (circleRect.intersects(cannonRect)))
			{					
				ballScore++;
				g.setColor(Color.GRAY);
				g.drawOval(xPos, yPos, width, height);
				g.setColor(Color.BLACK);
				totalTime = totalTime + timeBalance;
				
				weaponMotion = false;
				mouseClickedFlagForWeapon = false;
				weaponX = 0;
				weaponY = 0;
				time = 0;
				timeAndLoopTime = 0;
				
				startFlag = false;
				stopFlag = true;
				stop();
				deg = 45;
				xPos = 0;
				yPos = 0;
				runPauseButton.setLabel("RUN");
				angleSB.setValue(45);
				weaponVelocitySB.setValue(210);
				loopTime = 50;
				
			}
			
			//Draw the cannon muzzle
			g.drawPolygon(cannon);
			
			//Place cannon ball in a suitable position
			cannonBallX = cannonX1 - 15;
			cannonBallY = cannonY1 - 15;
			
			g.fillOval(cannonBallX, cannonBallY, cannonBallWidth, cannonBallHeight);
			
			
			
			
			//Calculate initial velocity components
			weaponV0X = weaponV0 * Math.cos(alpha);
			weaponV0Y = weaponV0 * Math.sin(alpha);
			
			//Fix initial velocity to work with computer coordinates
			weaponV0Y = weaponV0Y * -1;
			
			//Draw weapon
			if (weaponMotion == false)
			{
				g.fillOval(cannonX3, cannonY3, cannonWidth, cannonWidth);
				weaponX = cannonX3;
				weaponY = cannonY3;
			}
			
			
			
			//Fire weapon when mouse clicked on cannon muzzle or cannon ball
			if (mouseClickedFlagForWeapon)
			{
				if ((mouseClickCount == 1) && (mouseButtonNumber == 1))
				{
					if ((cannon.contains(mouseClickX, mouseClickY)) 
							|| (cannonBallRect.contains(mouseClickX, mouseClickY)))
					{
						if (startFlag)						
							weaponMotion = true;							
					}
					
					mouseClickCount = 0;
				}
			
			}
			
			
			//Motion of weapons
			if (weaponMotion == true)
			{
				//Get initial positions to calculate distance
				wInitialxPos = weaponX;
				wInitialyPos = weaponY;
				
				//Synchronizing computer time with human time
				timeAndLoopTime = timeAndLoopTime + loopTime;
				time = timeAndLoopTime/50;
				timeBalance = time / 20;
							

				if (weaponSystem == "projectile")
				{
					//Make projectile move in x
					weaponX = (int) (cannonX3 - (weaponV0X * time));
					
					//Make projectile move in y
					temp = weaponV0Y * time;
					temp2 = (0.5)*(gravity)*(time * time);
					
					//Fix computer vertical offset
					weaponY = (int)(temp - temp2) + cannonY3;					
					
					//Draw weapon
					g.fillOval(weaponX, weaponY, cannonWidth, cannonWidth);
					
				}
				
			}
			
			//Display statement if projectile goes out of screen bounds
			if (weaponMotion)
			{
				if ((weaponX < 0) && (weaponY < yPlayMax))
				{
					//Capture final weapon x and y
					//wFinalxPos = weaponX;
					//wFinalyPos = weaponY;
					//xDistance = wFinalxPos - wInitialxPos;
					//yDistance = wFinalyPos - wInitialyPos;
					
					lostProjectile = true;
					startTime = 0;
					stopTime = 40;
				}
				
			}
			
					
			//System.out.println("WeaponX: " + weaponX);
			//System.out.println("WeaponY: " + weaponY);
			//System.out.println(stopTime);
				
			if ((lostProjectile) && (startTime != stopTime))
			{
				startTime++;				
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString("Projectile Outside Tracking Area!!!", (int)(xWinMax/9), (int) (yWinMin + 100));
			}
			else
				lostProjectile = false;
					
			//Stop motion if weapon goes outside tracking area
			if ((weaponX < 0) || (weaponY > yPlayMax))
			{
				//Capture final position of weapon's x and y
				if (!((weaponX < 0) && (weaponY < yPlayMax)))
				{
					//wFinalxPos = weaponX;
					//wFinalyPos = weaponY;
					//xDistance = wFinalxPos - wInitialxPos;
					//yDistance = wFinalyPos - wInitialyPos;
				}
				
				weaponMotion = false;
				mouseClickedFlagForWeapon = false;
				weaponX = 0;
				weaponY = 0;
				time = 0;
				timeAndLoopTime = 0;
			}
			
			//Draw boundary lines
			g.setColor(Color.black);
			g.drawLine(xWinMax, yWinMin, xWinMax, yPlayMax); //Right boundary line
			g.drawLine(xWinMin, yWinMin, xWinMax, yWinMin);	//Top boundary line
			g.drawLine(xWinMin, yWinMin, xWinMin, yPlayMax); 	//Left boundary line
			g.drawLine(xWinMin, yPlayMax, xWinMax, yPlayMax);//Bottom boundary line
			
			//Make the ball move
			if (startFlag)
			{
				xPos = xPos + xStep;
				yPos = yPos + yStep;
			}
			
			//Make rectangles disappear with mouse click
			if (mousePressedFlag)
			{
				if (mouseReleasedFlag)
				{
					rectWidth = mouseReleasedX - mousePressedX;
					rectHeight = mouseReleasedY - mousePressedY;
					
					//A click is a rectangle with no width and no height
					if ((rectWidth == 0) && (rectHeight == 0))
					{
						//Prime the loop below
						repeat = true;
						
						//Remove all rectangles within a click
						while (repeat)
						{
							//Get size of vector and remove all elements with click position
							BlocksSize1 = Blocks.size();
							for (i = 0; i < Blocks.size(); i++)
							{
								if ((mouseClickCount == 2) && (mouseButtonNumber == 1))
								{
									if (Blocks.elementAt(i).contains(mouseClickX, mouseClickY))
										Blocks.removeElementAt(i);
								
								}
																	
								
							}
							
							//Get size of vector and remove all elements with click position again
							BlocksSize2 = Blocks.size();
							for (i = 0; i < Blocks.size(); i++)
							{
								if ((mouseClickCount == 2) && (mouseButtonNumber == 1))
								{
									if (Blocks.elementAt(i).contains(mouseClickX, mouseClickY))
										Blocks.removeElementAt(i);
								
								}						
								
							}
							
							//If two sizes not the same, we may still have rectangles in vector that need deleted
							if (BlocksSize1 == BlocksSize2)
								repeat = false;
						}
						
						
						
						mousePressedFlag = false;
						mouseReleasedFlag = false;
						
						
					}
										
				}
				
				
				
			} 
				
			
			
		//Draw rectangles using mouse
		if (mousePressedFlag)
		{
			if (mouseReleasedFlag)
			{
				if (mouseDraggedFlag)
				{
					/*
					mousePressedX = Math.min(mousePressedX,  mouseReleasedX);
					mousePressedY = Math.min(mousePressedY,  mouseReleasedY);
					mouseReleasedX = Math.max(mousePressedX,  mouseReleasedX);
					mouseReleasedY = Math.max(mousePressedY,  mouseReleasedY);*/
					
					//Do not penetrate right border
					if (mousePressedX > xPlayMax)
						mousePressedX = xPlayMax;
					
					if (mouseReleasedX > xPlayMax)
						mouseReleasedX = xPlayMax;
					
					//Do not penetrate bottom border
					if (mousePressedY > yPlayMax)
						mousePressedY = yPlayMax;
					
					if (mouseReleasedY > yPlayMax)
						mouseReleasedY = yPlayMax;
					
					//Do not penetrate left border
					if (mousePressedX < xPlayMin)
						mousePressedX = xPlayMin;
					
					if (mouseReleasedX < xPlayMin)
						mouseReleasedX = xPlayMin;
					
					//Do not penetrate top border
					if (mousePressedY < xPlayMin)
						mousePressedY = xPlayMin;
					
					if (mouseReleasedY < yPlayMin)
						mouseReleasedY = yPlayMin;
					
					//Positive x, positive y rectangle
					if ((mouseReleasedX > mousePressedX) && (mouseReleasedY > mousePressedY))
					{
						rectWidth = mouseReleasedX - mousePressedX;
						rectHeight = mouseReleasedY - mousePressedY;
						//Create new rectangle
						g.setColor(Color.blue);
						g.drawRect(mousePressedX,  mousePressedY,  rectWidth, rectHeight);
						rect1 = new Rectangle(mousePressedX, mousePressedY, rectWidth, rectHeight);
						
					}
					//Negative x, negative y rectangle
					else if ((mouseReleasedX < mousePressedX) && (mouseReleasedY < mousePressedY))
					{
						rectWidth = mousePressedX - mouseReleasedX;
						rectHeight = mousePressedY - mouseReleasedY;
						g.drawRect(mouseReleasedX,  mouseReleasedY,  rectWidth, rectHeight);
						rect1 = new Rectangle(mouseReleasedX,  mouseReleasedY,  rectWidth, rectHeight);
					}
					//Negative x, positive y rectangle
					else if ((mouseReleasedX < mousePressedX) && (mouseReleasedY > mousePressedY))
					{
						rectWidth = mousePressedX - mouseReleasedX;
						rectHeight = mouseReleasedY - mousePressedY;
						g.drawRect(mouseReleasedX,  mousePressedY,  rectWidth, rectHeight);
						rect1 = new Rectangle(mouseReleasedX,  mousePressedY,  rectWidth, rectHeight);
					}
					//Positive x, negative y rectangle
					else if ((mouseReleasedX > mousePressedX) && (mouseReleasedY < mousePressedY))
					{
						rectWidth = mouseReleasedX - mousePressedX;
						rectHeight = mousePressedY - mouseReleasedY;
						g.drawRect(mousePressedX,  mouseReleasedY,  rectWidth, rectHeight);
						rect1 = new Rectangle(mousePressedX,  mouseReleasedY,  rectWidth, rectHeight);
					}
					
					g.setColor(Color.black);
					rectangleAddedFlag = true;						
					
					
					mousePressedX = 0;
					mousePressedY = 0;
					mouseReleasedX = 0;
					mouseReleasedY = 0;
					mousePressedFlag = false;
					mouseDraggedFlag = false;
					mouseReleasedFlag = false;
							
				}

		
			}
		}
			
			
			//Add rectangles if they have been created
			if (rectangleAddedFlag == true)
			{
				//Remove rectangles that are completely covered up by other rectangles
				for (i = 0; i < Blocks.size(); i++)
				{
					//Create new rectangle from rectangle in vectors
					rect2X = (int) Blocks.elementAt(i).getX();
					rect2Y = (int) Blocks.elementAt(i).getY();
					rect2Width = (int) Blocks.elementAt(i).getWidth();
					rect2Height = (int) Blocks.elementAt(i).getHeight();
					
					rect2 = new Rectangle(rect2X, rect2Y, rect2Width, rect2Height);
					
					if (rect1.contains(rect2))
						Blocks.removeElementAt(i);					
				}
				
				//Do it again to remove smaller rectangles added later
				for (i = 0; i < Blocks.size(); i++)
				{
					//Create new rectangle from rectangle in vectors
					rect2X = (int) Blocks.elementAt(i).getX();
					rect2Y = (int) Blocks.elementAt(i).getY();
					rect2Width = (int) Blocks.elementAt(i).getWidth();
					rect2Height = (int) Blocks.elementAt(i).getHeight();
					
					rect2 = new Rectangle(rect2X, rect2Y, rect2Width, rect2Height);
					
					if (rect1.contains(rect2))
						Blocks.removeElementAt(i);					
				}
				
				
				
				//Only add rectangle to vector if it doesn't intersect with the circle's rectangle
				if (!(rect1.intersects(circleRect)))
				{
					//Cannot draw on cannon
					if (!(rect1.intersects(cannonBallRect)))
					{	
						if (!(rect1.intersects(cannonRect)))
						{	
							Blocks.addElement(rect1);										
							
						}
													
						
					}
					
											
				}
								
							
				//System.out.println(Blocks.size());
				rectangleAddedFlag = false;					
			}
			
			
			//Draw all the rectangles in the vector
			for (i = 0; i < Blocks.size(); i++)
			{
				g.setColor(Color.blue);				
				g.drawRect((int) Blocks.elementAt(i).getX(),
						(int) Blocks.elementAt(i).getY(), 
						(int) Blocks.elementAt(i).getWidth(), 
						(int) Blocks.elementAt(i).getHeight());
				g.setColor(Color.black);			
			}
			
			
			
			
			
			/*Make circle bounce off play area boundaries */				
			//Bounce off left boundary
			if (xPos < xPlayMin)
				xStep = xStep * -1;
			
			//Bounce off right boundary
			while (xPos+width >= xPlayMax)
			{
				//Move the x-coordinate back 
				xPos = xPos - firstXStep;
				
				//Change direction
				xStep = xStep * -1;		
										
			}
			
			//Bounce off top boundary
			if (yPos < yPlayMin)
				yStep = yStep * -1;
			
			//Bounce off bottom boundary
			while (yPos+height >= yPlayMax)
			{
				//Move the x-coordinate back 
				yPos = yPos - firstXStep;
				
				//Change direction
				yStep = yStep * -1;
				
			}
			
			//If size scroll bar is moved
			//if (angleSBMoveFlag == true)
			//{
				//Store original width and height of circle
				origWidth = width;
				origHeight = height;
				
				//Scroll bar adjustment means adjustment in circle/rectangle width or height
				//width = angleSB.getValue()*10;
				//height = angleSB.getValue()*10;
				
				//Do not allow circle/rectangle to grow and penetrate right play border
				if ((xPos + width) > xPlayMax)
				{
					//If beyond border, move back in the x direction
					while ((xPos + width) > xPlayMax)
					{
						xPos = xPos - firstXStep;
					}
										
				}
				
				//Do not allow circle/rectangle to grow and penetrate left play border
				if ((xPos + width) < xPlayMin)
				{
					//If beyond border, move forward in the x direction
					while ((xPos + width) < xPlayMin)
					{
						xPos = xPos + firstXStep;
					}
					
				}
				
				//Do not allow ball to grow and penetrate bottom play border
				if ((yPos + height) > yPlayMax)
				{
					//If beyond border, move back in the y direction
					while ((yPos + height) > yPlayMax)
					{
						yPos = yPos - firstYStep;
					}
					
					
				}
				
				//Do not allow circle/rectangle to grow and penetrate top play border
				if ((yPos + height) < yPlayMin)
				{
					//If beyond border, move forward in the y direction
					while ((yPos + height) < yPlayMax)
					{
						yPos = yPos + firstYStep;
					}
					
				}
								
				//Clear the size scroll bar movement flag
				angleSBMoveFlag = false;					
				
			//}
			
			/*Bounce off of rectangles blocks in the vector*/				
			for (j = 0; j < Blocks.size(); j++)
			{
				//If moving straight right
				//If point of contact is xPos+w, yPos				
				if (Blocks.elementAt(j).contains(xPos+width, yPos))
				{
					if (xStep > 0)
						xStep = xStep * -1;
				}
				//If point of contact is xPos+w, yPos+h
				if (Blocks.elementAt(j).contains(xPos+width,  yPos+height))
				{
					if (xStep > 0)
						xStep = xStep * -1;
				}
				//If perfect hit at top, left corner of rectangle	
				if (Blocks.elementAt(j).contains(xPos+width,  yPos+height))
				{
					if ((xStep > 0) && (yStep > 0))
					{
						xStep = xStep * -1;
						yStep = yStep * -1;
						
					}
				}
				//If moving straight down
				//If point of contact is xPos, yPos+h
				if (Blocks.elementAt(j).contains(xPos,  yPos+height))
				{
					if (yStep > 0)
						yStep = yStep * -1;
				}
				//If point of contact is xPos+w, yPos+h
				if (Blocks.elementAt(j).contains(xPos+width,  yPos+height))
				{
					if (yStep > 0)
						yStep = yStep * -1;
				}
				//If perfect hit at top, right corner of rectangle
				if (Blocks.elementAt(j).contains(xPos,  yPos+height))
				{
					if ((xStep < 0) && (yStep > 0))
					{
						xStep = xStep * -1;
						yStep = yStep * -1;
						
					}
				}
				//If moving straight left
				//If point of contact is xPos, yPos
				if (Blocks.elementAt(j).contains(xPos,  yPos))
				{
					if (xStep < 0)
						xStep = xStep * -1;
				}
				//If moving straight left
				//If point of contact is xPos, yPos
				if (Blocks.elementAt(j).contains(xPos,  yPos+height))
				{
					if (xStep < 0)
						xStep = xStep * -1;
				}
				//If perfect hit at bottom, right corner of rectangle
				if (Blocks.elementAt(j).contains(xPos,  yPos))
				{
					if ((xStep < 0) && (yStep < 0))
					{
						xStep = xStep * -1;
						yStep = yStep * -1;
						
					}
				}
				//If moving straight up
				//If point of contact is xPos, yPos
				if (Blocks.elementAt(j).contains(xPos,  yPos))
				{
					if (yStep < 0)
						yStep = yStep * -1;
				}
				//If point of contact is xPos+width, yPos
				if (Blocks.elementAt(j).contains(xPos+width,  yPos))
				{
					if (yStep < 0)
						yStep = yStep * -1;
				}
				//If perfect hit at bottom, left corner of rectangle
				if (Blocks.elementAt(j).contains(xPos+width,  yPos))
				{
					if ((xStep > 0) && (yStep < 0))
					{
						xStep = xStep * -1;
						yStep = yStep * -1;
						
					}
				}
			}
			
			//Gray background on canvas upon quit
			if (quitFlag)
			{
				startFlag = false;
				g.setColor(Color.gray);
				g.drawOval(xPos,  yPos,  width, height);
				g.fillOval(xPos,  yPos,  width, height);
				canvas.setBackground(Color.gray);
				
			}
			
			
			//Draw the image
			cg.drawImage(buffer, 0, 0, null);
			
		}
		
		
				
	}
	
	
}

