import java.awt.Color;
import java.awt.Graphics;



public class StatsBar {

	private static final Color MY_COLOR = Color.LIGHT_GRAY;
	private static final Color TEXT_COLOR = Color.red;
	
	
	private static int myHeight;
	private static int myWidth;
	
	private static int leftEdge;
	private static int topEdge;
	
	
	public StatsBar( int leftEdgeIn, int topEdgeIn, int widthIn, int heightIn) {
		
		topEdge = topEdgeIn;
		leftEdge = leftEdgeIn;
		myWidth = widthIn;
		myHeight = heightIn;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void draw (Graphics g)
	{
		g.setColor (MY_COLOR);
		g.fillRect(leftEdge, topEdge, myWidth, myHeight);
		
		g.setColor(TEXT_COLOR);
		g.drawString("Statistics: ", leftEdge + 10, topEdge + 15);
		
	}
	

	
	

}
