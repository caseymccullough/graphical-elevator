import java.awt.Graphics;
import java.util.ArrayList;

public class Elevator {

	private static int numElevators = 0;
	
	private int topFloor; // highest floor this elevator will access
	private int bottomFloor; 
	
	private int myID;
	private int currentFloor;
	
	private boolean canMove;
	private boolean goingUp;
	
	private int myHeight;  // of the elevator cabin, same as height of a floor in a building
	private int myWidth;
	
	
	private ArrayList <Request> myRequests;
	
	public Elevator(int top)
	{
		numElevators++;
		myID = numElevators; 
		
		goingUp = true;
		myRequests = new ArrayList <Request>();	
		
		bottomFloor = 1; 
		topFloor = top;
		
		currentFloor = bottomFloor;
	}
	
	public int getCurrentFloor ()
	{
		return currentFloor;
	}
	
	public int getID()
	{
		return myID;
	}
	
	public ArrayList <Request> getMyRequests()
	{
		return myRequests;
	}
	
	public String toString ()
	{
		String s;
		
		s = "\n\tElevator " + myID + " on floor: " + currentFloor + ", going " + this.upOrDown();
		s += "\n\tContains " + myRequests.size() + " passengers: ";
		s += this.listPassengers();
		return s;
		
	}
	
	public String listPassengers ()
	{
		String s = "";
		int count = 1;
		for (Request r: myRequests)
		{
			s += "\n\t\t # " + count + ": " + r;
		}
		return s;
	}
	
	public void removePassengersAtCurrentFloor()
	{
		for (int current = 0; current < myRequests.size(); current++)
		{	
			if (myRequests.get(current).getDestination() == currentFloor)
			{
				
				System.out.println("\tPassenger gets off: " + myRequests.get(current));
				myRequests.remove(current);
				
				current--; // arraylist will collapse so we need to check at same spot!
				canMove = false;
				
			}
		}
		
	}
	
	public void move ()
	{
		System.out.print ("\n\t-->Elevator # " + myID);
		
		if (goingUp)
		{
			currentFloor++;
			System.out.print(" goes up one floor to " + currentFloor);
			
			if (currentFloor == topFloor)
			{
				reverseDirection();
			}
		}
		else
		{
			currentFloor--;
			System.out.print(" goes down one floor to " + currentFloor);
			if (currentFloor == bottomFloor)
			{
				reverseDirection();
			}
		}
	}
	
	private String upOrDown()
	{
		if (goingUp)
		{
			return "up";
		}
		else
		{
			return "down";
		}
		
	}
	
	public void reverseDirection()
	{
		goingUp = !goingUp;
		System.out.println (" and reverses direction");
		
	}
	
	public void startMoving()
	{
		canMove = true;
		
	}
	
	public void stopMoving()
	{
		canMove = false;	
	}
	
	public boolean canMove()
	{
		return canMove;
	}
	
	public boolean isGoingUp()
	{
		return goingUp;
	}
	
	public void pickUp (Request r)
	{
		myRequests.add(r);
	}
	
	public Request dropOff (int pos)
	{
		return myRequests.remove(pos);
	}
	
	
	public void draw (Graphics g)
	{
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Elevator e = new Elevator(5);
		System.out.println(e);
		
	}
	
	
}
