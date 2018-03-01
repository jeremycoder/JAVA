import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Move extends Applet implements ActionListener, AdjustmentListener 
{
	private static final long serialVersionUID = 1L;
	
	//Create buttons
	Button startStopButton;
	Button circleRectButton; 
	Button tailsNoTailsButton; 
	Button clearButton;
	Button quitButton;
	
	//Create scroll bars
	Scrollbar speedSB, sizeSB;
	
	//Create initial scroll bar values
	int speedSBStartValue = 8;
	int sizeSBStartValue = 5;
	
	//Create labels
	Label speedLabel, sizeLabel;	
	
	//Original button dimensions
	int buttonLength = 100;
	int buttonWidth = 40;
	
	//Initial loopTime based on original position of speed Scroll bar
	int loopTime = 50;
	
	//Applet window minimums and maximums
	int xWinMin = 0;
	int yWinMin = 0;
	int xWinMax = 500;
	int yWinMax = 550;
	
	//Play area (where objects can move around) minimums and maximums
	int xPlayMin = 0;
	int yPlayMin = 0;
	int xPlayMax = 485;
	int yPlayMax = 285;
	
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
	
	int origSizeSBValue = 6; //Temporary value to store size scroll bar values
		
	boolean startFlag = false; //Flag to signal start/stop of motion	
	boolean cirFlag = false; //Flag to signal display of circle/rectangle
	boolean tailsFlag = false; //Flag to signal display/not display tails
	boolean clearFlag = false; //Flag to signal returning to original state
	boolean sizeSBMoveFlag = false; //Flag to signal movement in size scroll bar
	
	/*Speed scroll bar inverter and multiplier
	These two numbers relate the scroll bar movements to loopTime. loopTime
	is the delay of the thread. loopTime determines how fast the
	circle/rectangle moves. LoopTime: Larger numbers mean slower speed.
	Smaller numbers mean faster speed. Subtract scroll bar value from
	speedSBInverter to make small scroll bar values mean large loopTime values
	which mean small speed. The same for large scroll bar values.
	speedSBMultiplier multiplies the difference to get a good speed range*/
	int speedSBInverter = 16;
	int speedSBMultiplier = 5;
		
	Thread t; //This thread will execute the moving of circle/rectangle
	
	//Set size of applet, create thread, and start it
	public void init()
	{
		//Use no layout manager
		setLayout(null);
		
		//Set button labels
		startStopButton = new Button("START");
		circleRectButton = new Button("    CIRCLE    ");
		tailsNoTailsButton = new Button("  TAILS  ");
		clearButton = new Button("CLEAR");
		quitButton = new Button("QUIT");
		
		//Initialize speed label
		speedLabel = new Label("SPEED");
		
		//Initialize speed scroll bar
		speedSB = new Scrollbar(Scrollbar.HORIZONTAL, speedSBStartValue, 1, 1, 15);
		speedSB.setPreferredSize(new Dimension(420,30));
		speedSB.setBackground(Color.WHITE);
		speedSB.setBlockIncrement(2);
		
		//Initialize Size label
		sizeLabel = new Label("SIZE");
		
		//Initialize size scroll bars
		sizeSB = new Scrollbar(Scrollbar.HORIZONTAL, sizeSBStartValue, 1, 1, 9);
		sizeSB.setPreferredSize(new Dimension(420,30));
		sizeSB.setBackground(Color.WHITE);
		sizeSB.setBlockIncrement(2);		
		
		//Set button positions
		startStopButton.setBounds(0, 300, buttonLength, buttonWidth);
		circleRectButton.setBounds(100, 300, buttonLength, buttonWidth);		
		tailsNoTailsButton.setBounds(200, 300, buttonLength, buttonWidth);
		clearButton.setBounds(300, 300, buttonLength, buttonWidth);
		quitButton.setBounds(400, 300, buttonLength, buttonWidth);
		
		//Set label and scroll bar positions
		speedLabel.setBounds(0, 350, 50, 30);
		speedSB.setBounds(0,380, xWinMax, 50);
		sizeLabel.setBounds(0, 430, 50, 30);
		sizeSB.setBounds(0,460, xWinMax, 50);
				
		//Add buttons to applet
		add(startStopButton);
		add(circleRectButton);		
		add(tailsNoTailsButton);
		add(clearButton);
		add(quitButton);
		
		//Add labels and scroll bars to applet
		add(speedLabel);
		add(speedSB);
		add(sizeLabel);
		add(sizeSB);
		
		//Register buttons to receive action events
		startStopButton.addActionListener(this);
		circleRectButton.addActionListener(this);		
		tailsNoTailsButton.addActionListener(this);
		clearButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		//Register scroll bars to receive adjustment events
		speedSB.addAdjustmentListener(this);
		sizeSB.addAdjustmentListener(this);
		
		//Set size of applet
		resize(xWinMax,yWinMax);
		
		//Create new thread, and start the thread
		t = new CircleThread(this);
		t.start();
		
		//Set initial loopTime value
		loopTime = (speedSBInverter - speedSB.getValue()) * speedSBMultiplier;
		
	}
	
	public void start()
	{
		
	}
	
	
	//Actions to be performed when buttons are pressed
	public void actionPerformed(ActionEvent buttonPressed)
	{
		//If START/STOP button is pressed
		if (buttonPressed.getSource() == startStopButton)
		{
			//Change label from "START" to "STOP", set flag
			if (startStopButton.getLabel()== "START")
			{
				startStopButton.setLabel("STOP");
				startFlag = true;
				
												
			}
			
			//Change label from "STOP" to "START", clear flag
			else if (startStopButton.getLabel()== "STOP")
			{
				startStopButton.setLabel("START");
				startFlag = false;
			}
		}
				
		//If "CIRCLE/RECTANGLE" button pressed
		if (buttonPressed.getSource() == circleRectButton)
		{
			//Change label from "CIRCLE" to "RECTANGLE", set circle flag
			if (circleRectButton.getLabel()== "    CIRCLE    ")
			{
				circleRectButton.setLabel("RECTANGLE");
				cirFlag = true;
				
			}
			
			//Change label from "RECTANGLE" to "CIRCLE", clear circle flag
			else if (circleRectButton.getLabel()== "RECTANGLE")
			{
				circleRectButton.setLabel("    CIRCLE    ");
				cirFlag = false;
				
			}
			
		}
		
		//If "TAILS/NO TAILS" button pressed,
		if (buttonPressed.getSource() == tailsNoTailsButton)
		{
			//Change label from "NO TAILS" to "TAILS", clear tails flag
			if (tailsNoTailsButton.getLabel()== "NO TAILS")
			{
				tailsNoTailsButton.setLabel("  TAILS  ");
				tailsFlag = false;
				
			}
			
			//Change label from "TAILS" to "NO TAILS", set tails flag 
			else if (tailsNoTailsButton.getLabel()== "  TAILS  ")
			{
				tailsNoTailsButton.setLabel("NO TAILS");
				tailsFlag = true;
				
			}
		}
		
		//If "CLEAR" button pressed, set clear flag
		if (buttonPressed.getSource() == clearButton)
		{
			clearFlag = true;
		}
		
		//If "QUIT" button pressed, set clear flag
		if (buttonPressed.getSource() == quitButton)
		{
			//Remove event listeners from buttons
			startStopButton.removeActionListener(this);
			circleRectButton.removeActionListener(this);
			tailsNoTailsButton.removeActionListener(this);
			clearButton.removeActionListener(this);
			quitButton.removeActionListener(this);
			
			//Remove event listeners from scroll bars
			speedSB.removeAdjustmentListener(this);
			sizeSB.removeAdjustmentListener(this);
			
			//Close the applet
			System.exit(0);			
		}
		
		
		
		
		
	}
	
	
	//Capture scroll bar adjustments
	public void adjustmentValueChanged(AdjustmentEvent sbChanged)
	{
		//If adjustment came from the speed scroll bar
		if (sbChanged.getSource() == speedSB)
		{
			///Relate movement in speed scroll bar to speed of circle/rectangle
			loopTime = (speedSBInverter - speedSB.getValue()) * speedSBMultiplier;
			
		}
		
		//If adjustment came from the size scroll bar
		if (sbChanged.getSource() == sizeSB)
		{			
			//Set size scroll bar movement flag
			sizeSBMoveFlag = true;			
			
		}
		
	}
	
	
	class CircleThread extends Thread
	{
		Applet thisApplet;		//Thread needs to know the applet
		
		//Constructor stores the applet to paint
		public CircleThread(Applet thisApplet)
		{
			this.thisApplet = thisApplet;
		}
		
		//This method runs the thread
		public void run()
		{
			while (true)
			{	
				//Make the thread pause for a little while
				try {
					Thread.sleep(loopTime);
				} catch (InterruptedException e) {}
				
				//Create graphics context for this applet
				Graphics page = thisApplet.getGraphics();
				
				/*Make circle/rectangle bounce off play area boundaries */				
				//Bounce off left boundary
				if (xPos < xPlayMin)
					xStep = xStep * -1;
				
				//Bounce off right boundary
				if (xPos+width >= xPlayMax)
				{
					xStep = xStep * -1;
				}
					
				
				//Bounce off top boundary
				if (yPos < yPlayMin)
					yStep = yStep * -1;
				
				//Bounce off bottom boundary
				if (yPos+height >= yPlayMax)
				{
					
					yStep = yStep * -1;
				}
					
				
				//If size scroll bar is moved
				if (sizeSBMoveFlag == true)
				{
					//Store original width and height of circle
					origWidth = width;
					origHeight = height;
					
					//Erase current rectangle and circle
					page.setColor(getBackground());
					page.drawRect(xPos, yPos, width, height);
					page.drawOval(xPos, yPos, width, height);
					
					page.setColor(getForeground());		
					
					//Scroll bar adjustment means adjustment in circle/rectangle width or height
					width = sizeSB.getValue()*10;
					height = sizeSB.getValue()*10;
					
					//Do not allow circle/rectangle to grow and penetrate right play border
					if ((xPos + width) > xPlayMax)
					{
						//If beyond border, move back in the x direction
						while ((xPos + width) > xPlayMax)
						{
							xPos = xPos - firstXStep;
						}
						
						page.setColor(getBackground());
						if (cirFlag == true)
							page.drawOval(xPos, yPos, width, height);
						else
							page.drawRect(xPos, yPos, width, height);
						
						page.setColor(getForeground());
					}
					
					//Do not allow circle/rectangle to grow and penetrate bottom play border
					if ((yPos + height) > yPlayMax)
					{
						//If beyond border, move back in the x direction
						while ((yPos + height) > yPlayMax)
						{
							yPos = yPos - firstYStep;
						}
						
						page.setColor(getBackground());
						
						if (cirFlag == true)
							page.drawOval(xPos, yPos, width, height);
						else
							page.drawRect(xPos, yPos, width, height);
						
						page.setColor(getForeground());
					}
									
					//Clear the size scroll bar movement flag
					sizeSBMoveFlag = false;					
					
				}
								
				//If "START" button is pressed
				if (startFlag == true)
				{
					//Keep original positions
					origxPos = xPos;
					origyPos = yPos;
								
					//If "RECTANGLE" button is pressed
					if (cirFlag == true)
					{
						//Erase current rectangle
						page.setColor(getBackground());
						page.drawRect(xPos, yPos, width, height);
						
						page.setColor(getForeground());					
					
						//When "TAILS" button is pressed
						if (tailsFlag == true)
						{
							//Draw a circle, move, then draw another circle
							page.drawOval(xPos, yPos, width, height);
							xPos = xPos+xStep;
							yPos = yPos+yStep;
							
							page.drawOval(xPos, yPos, width, height);
							
						}
						
						//When "NO TAILS" button is pressed
						else if (tailsFlag == false)
						{
							//Draw a circle, move, draw another circle, then erase first circle
							page.drawOval(xPos, yPos, width, height); 
							xPos = xPos+xStep;
							yPos = yPos+yStep;
							
							
							page.drawOval(xPos, yPos, width, height);
							
							page.setColor(getBackground());
							page.drawOval(origxPos, origyPos, width, height);
						}
					
					}
					
					//If "RECTANGLE" button is pressed
					else if (cirFlag == false)
					{
						//Erase the current circle
						page.setColor(getBackground());
						page.drawOval(xPos, yPos, width, height);
						
						page.setColor(getForeground());
						
						//If "TAILS" button is pressed
						if (tailsFlag == true)
						{
							//Draw a rectangle, move, draw another rectangle
							page.drawRect(xPos, yPos, width, height); 
							xPos = xPos+xStep;
							yPos = yPos+yStep;
							
							page.drawRect(xPos, yPos, 50, 50);
							
						}
						
						//If "NO TAILS" button is pressed
						else if (tailsFlag == false)
						{
							//Draw a rectangle, move, draw another rectangle, erase first rectangle
							page.drawRect(xPos, yPos, width, height);
							xPos = xPos+xStep;
							yPos = yPos+yStep;
							
							page.drawRect(xPos, yPos, width, height);
							
							page.setColor(getBackground());
							page.drawRect(origxPos, origyPos, width, height);
						}
						
					}
					
				}
				
				//If "STOP" button is pressed
				if (startFlag == false)
				{
					//If "CIRCLE" button is pressed
					if (cirFlag == true)
					{
						//Erase current rectangle
						page.setColor(getBackground());
						page.drawRect(xPos, yPos, width, height);
						
						//Draw a circle
						page.setColor(getForeground());
						page.drawOval(xPos, yPos, width, height);						
					}
					
					//If "RECTANGLE" button is pressed
					else if (cirFlag == false)
					{
						//Erase current circle
						page.setColor(getBackground());
						page.drawOval(xPos, yPos, width, height);
						
						//Draw a rectangle
						page.setColor(getForeground());
						page.drawRect(xPos, yPos, width, height);
					}
					
					
				}
				
				//If "CLEAR" button pressed, reset everything to original values
				if (clearFlag == true)
				{
					//Reset loop time
					loopTime = (speedSBInverter - speedSB.getValue()) * speedSBMultiplier;
					
					//Reset x and y positions
					xPos = firstXPos;
					yPos = firstYPos;
					
					//Reset width and height
					width = firstWidth;
					height = firstHeight;
					
					//Reset steps
					xStep = firstXStep;
					yStep = firstYStep;
					
					//Reset button labels
					startStopButton.setLabel("START");
					circleRectButton.setLabel("    CIRCLE    ");
					tailsNoTailsButton.setLabel("  TAILS  ");
					clearButton.setLabel("CLEAR");
					quitButton.setLabel("QUIT");
					
					//Reset scroll bar values
					speedSB.setValue(speedSBStartValue);
					sizeSB.setValue(sizeSBStartValue);
					
					//Reset flags
					startFlag = false; 	
					cirFlag = false; 
					tailsFlag = false; 
					clearFlag = false;
					
					//Clear the whole screen
					update(page);
										
				}
				
				
			
			}
				
		}
		
		
	}
	
	
	
}


	