import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Building {

	private int numFloors;
	private int numElevators;
	
	private Elevator[] myElevators;
	private ArrayList <Request> theWaiting; 
	
	private static final Color MY_COLOR = Color.blue;
	private static final Color SHAFT_COLOR = Color.YELLOW;
	private static final Color FLOOR_COLOR = Color.LIGHT_GRAY;
	private static final Color ELEVATOR_COLOR = Color.CYAN;
	
	private static int screenHeight;
	private static int screenWidth;
	
	private static int leftEdge;
	private static int topEdge;
	private static int bottomEdge;
	
	private static int elevatorsLeftEdge; 
	
	private static final int FLOOR = 100; // where building is drawn
	
	private static final int LEFT_EDGE_GAP = 100; 
	private static final int RIGHT_EDGE_GAP = 300;  // gap intended to show real-time statistics
	
	private static final int SHAFT_WIDTH = 70;
	private static final int FLOOR_HEIGHT = 100; // how tall is each floor
	
	private static final int FLOOR_THICKNESS = 10; 
	private static final int WINDOW_HEIGHT = FLOOR_HEIGHT - FLOOR_THICKNESS; 
	private static final int WINDOW_WIDTH = SHAFT_WIDTH - 10;
	
	private static StatsBar theStatsBar;
	
	private static int buildingWidth;
	
	public Building( int screenHeightIn, int screenWidthIn, Elevator [] elevatorsIn, int floors, ArrayList <Request> theWaitingIn) {
		
		myElevators = elevatorsIn;  
		theWaiting = theWaitingIn;
		
		screenHeight = screenHeightIn;
		screenWidth = screenWidthIn;
		
		numFloors = floors;
		numElevators = elevatorsIn.length;
		
		buildingWidth = screenWidth - RIGHT_EDGE_GAP - LEFT_EDGE_GAP;
		leftEdge = LEFT_EDGE_GAP;
		topEdge = screenHeight  - FLOOR - FLOOR_HEIGHT * numFloors;
		bottomEdge = screenHeight - FLOOR;
		
		elevatorsLeftEdge = LEFT_EDGE_GAP + (buildingWidth - numElevators * SHAFT_WIDTH) / 2;
		
		theStatsBar = new StatsBar (screenWidth - RIGHT_EDGE_GAP + 15, topEdge, RIGHT_EDGE_GAP - 30, numFloors * FLOOR_HEIGHT);
		
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame ("Building Demo");
		
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//frame.paint (Graphics g);
	}
	
	public void draw (Graphics g)
	{
		g.setColor (Color.BLACK);
		g.fillRect(0, 0, 1200, 800);
		
		g.setColor(MY_COLOR);
		g.fillRect(LEFT_EDGE_GAP, topEdge, buildingWidth, numFloors * FLOOR_HEIGHT);
		
			for (int floor = 1; floor <= numFloors; floor++)
			{
				int topWindowEdge = topEdge  + (numFloors - floor + 1) * FLOOR_HEIGHT - WINDOW_HEIGHT;
				
				// draw floors
				g.setColor(FLOOR_COLOR);
				g.fillRect(leftEdge, topEdge + (floor) * FLOOR_HEIGHT, buildingWidth, FLOOR_THICKNESS);
			
				for (int shaft = 0; shaft < numElevators; shaft++)
				{	
					// draw elevator shafts	
					if (myElevators[shaft].getCurrentFloor() != floor)
					{
						g.setColor(SHAFT_COLOR);
						int leftWindowEdge = elevatorsLeftEdge + shaft * SHAFT_WIDTH + (SHAFT_WIDTH - WINDOW_WIDTH);
						g.fillRect(leftWindowEdge, topWindowEdge, WINDOW_WIDTH, WINDOW_HEIGHT);
						
					}
					else
					{
						g.setColor(ELEVATOR_COLOR);
						int leftWindowEdge = elevatorsLeftEdge + shaft * SHAFT_WIDTH + (SHAFT_WIDTH - WINDOW_WIDTH);
						g.fillRect(leftWindowEdge, topWindowEdge, WINDOW_WIDTH, WINDOW_HEIGHT);
						
						// draw all passengers (requests) on this elevator. 
						ArrayList <Request> requestsOnThisElevator = myElevators[shaft].getMyRequests();
						int passengerX = leftWindowEdge + 10;
						int passengerY = topWindowEdge + WINDOW_HEIGHT;
						
						for (Request r: requestsOnThisElevator)
						{
							r.draw(passengerX, passengerY, g);
							passengerX += 20; // each passenger is 20 pixels from the next.
							
						}
						
					}

				
					

				}
			}
			theStatsBar.draw(g);
		
	}
	
	
	
	

}
