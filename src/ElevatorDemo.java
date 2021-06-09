import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ElevatorDemo extends JFrame {

	final static int NUM_ELEVATORS = 3;
	final static int NUM_FLOORS = 7;
	
	private static final int WINDOW_WIDTH = 1200;		// Window size
	private static final int WINDOW_HEIGHT = 900;		// Window size
	private static final int TOP_OF_WINDOW = 22;	// Top of the visible window
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color BUILDING_COLOR = Color.BLUE;
	
	private static Building theBuilding;
	
	Elevator[] theElevators;
	
	Request [] newRequests; // ones added in current time period
	ArrayList <Request> pendingRequests; // until they are placed in an elevator
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ElevatorDemo demo = new ElevatorDemo();

	}
	
	public ElevatorDemo ()
	{		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		theElevators = new Elevator [NUM_ELEVATORS];
		pendingRequests = new ArrayList <Request>();
		theBuilding = new Building (WINDOW_HEIGHT, WINDOW_WIDTH, theElevators, NUM_FLOORS, pendingRequests); // or just use NUM_ELEVATORS? 
		
		for (int j = 0; j < theElevators.length; j++)
		{
			theElevators[j] = new Elevator(NUM_FLOORS);
		}
		
		System.out.println("Number of elevators: " + theElevators.length);
		
		if (ElevatorRequestReader.openRequestFile())
			System.out.println("File found");
		else
			System.out.println("File not found");
		
		  Scanner console = new Scanner(System.in);
		  setVisible(true);
		
		while (true) // set to run to end of file
		{
			System.out.println("\nHit return to move.");
			String timeTick = console.nextLine();
			   
			newRequests = ElevatorRequestReader.getRequests(); // also prints time update
			
			for (int i = 0; i < theElevators.length; i++)
				// show status for each elevator--
			{
				System.out.println("\t" + theElevators[i]);
			}
			
			System.out.println ("\n\tNew requests: " + newRequests.length);
			
			for (int j = 0; j < newRequests.length; j++)
			{
				System.out.println ("\t\t" + newRequests[j]);
				pendingRequests.add(newRequests[j]);
			}
			
			System.out.println ("\n\tPending requests: " + pendingRequests.size());

			for (Request r: pendingRequests)
			{
				System.out.println ("\t\t" + r);
			}
		
			for (int i = 0; i < theElevators.length; i++)
				// each elevator--
			{
				//System.out.println("\t" + theElevators[i]);
				
				theElevators[i].removePassengersAtCurrentFloor();
				
				// each passenger asks if he should board current elevator
				for (int j = 0; j < pendingRequests.size(); j++)
				{
					if (pendingRequests.get(j).shouldBoard(theElevators[i]))
					{
						theElevators[i].stopMoving();
						
						System.out.print("\n\tElevator " + theElevators[i].getID() + " STOPS ");
						System.out.println("to pick up: " + pendingRequests.get(j));
						
						theElevators[i].pickUp (pendingRequests.remove(j)); // write this method
						//j--; // requests will drop down in ArrayList
				
					}

				}
				
				if (theElevators[i].canMove())
				{
					theElevators[i].move();
				}
				else
				{
					theElevators[i].startMoving();
				}
			}
			repaint();
		}
	}
	
	public void paint(Graphics g)
	{
		// Clear the window.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		theBuilding.draw(g);
	
	}
	
}		
		


