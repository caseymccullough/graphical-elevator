import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Request {

	private static final int WIDTH = 12;
	private static final int HEIGHT = 30;
	private static final Color BASE_COLOR = Color.GREEN;
	private static final Color TEXT_COLOR = Color.MAGENTA;
	
	private static final Font REQUEST_FONT = new Font ("Times New Roman", 0, 4);
	
	private int time;
	private String name;
	private int myStart;
	private int myDestination;
	private int floorsTraveled;
	
	public Request (int timeIn, String nameIn, int startIn, int destinationIn)
	{
		time = timeIn;
		name = nameIn;
		myStart = startIn;
		myDestination = destinationIn;
		floorsTraveled = 0;	
		
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the start
	 */
	public int getStart() {
		return myStart;
	}

	/**
	 * @return the destination
	 */
	public int getDestination() {
		return myDestination;
	}

	/**
	 * @return the floorsTraveled
	 */
	public int getFloorsTraveled() {
		return floorsTraveled;
	}
	
	public boolean goingUp ()
	// positive = going up
	// negative = going down
	// zero? --> same floor, no need to board!
	{
		return myDestination - myStart > 0;
	}
	
	public boolean shouldBoard (Elevator e)
	// Request objects decide if they should board elevator. 
	{
		if (this.goingUp() == e.isGoingUp() && myStart == e.getCurrentFloor())
		// elevator is going desired direction and is currently on my floor
		{
			System.out.println(this.getName() + " decides to board, destination = " + this.getDestination());
			return true;
		}
		return false; 
	}
	
	public String toString ()
	{
		String s = "From floor: " + myStart + " to floor: " + myDestination + " (" + name + ")";
		return s;
	}
	
	public void draw (int xPos, int yPos, Graphics g)
	{
		g.setColor (BASE_COLOR);
		// the body = a rectangle
		g.fillRect(xPos, yPos - HEIGHT, WIDTH, HEIGHT);
		// a round head
		g.fillOval(xPos, yPos - HEIGHT - WIDTH, WIDTH, WIDTH);
		
		
		g.setColor(TEXT_COLOR);
		g.drawString (name, xPos, yPos -2);
	}
	

}
