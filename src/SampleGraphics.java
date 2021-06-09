/******************************************************************************************
 * 
 * Program: SampleGraphics
 * Created by: Mike Thibodeaux/Richard Steinberg
 * Created on: 9/11/07
 * 
 * Description:
 * This program demonstrates some basic Java graphics. It introduces these classes (types):
 *   JFrame, Graphics, Color, Font, Polygon
 * and these primitive graphics operations:
 *   setColor(), setFont()
 *   drawString(), drawLine()
 *  drawRect(), fillRect(), drawRoundRect(), fillRoundRect(), draw3DRect(), fill3DRect()
 *   drawOval(), fillOval(), drawArc(), fillArc()
 *   addPoint(), fillPolygon(), drawPolygon()
 * 
 * CODE REQUIREMENTS:
 *   The minimum requirements for a graphics program are A through D below. 
 * COORDINATE SYSTEM: 
 *   The (x, y) coordinate system is not quite what you'd expect. (0, 0) is at the UPPER
 *   LEFT corner of the window. From there, x measures to the right and y measures DOWN!
 * COLORS: 
 *   Built in colors are Color.xxx, where xxx can be any of the following: black, blue, 
 *   cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, white, yellow.
 *   Create new colors with new Color(RED, GREEN, BLUE), where RED, GREEN and BLUE are 
 *   integers from 0 to 255.
 * FONTS: 
 *   Default fonts are "SansSerif", "Serif", "Monospaced", "Dialog", "DialogInput".
 *   Font styles are Font.PLAIN, Font.BOLD, Font.ITALIC, or Font.BOLD + Font.ITALIC.
 *
 *****************************************************************************************/

// A - You need these two imports
import java.awt.*;      
import javax.swing.*;

// B - Your class must extend JFrame
public class SampleGraphics extends JFrame
{
 public static void main(String args[])
 {
  // C - Create and display the window. Use YOUR class name, not SampleGraphics!
  SampleGraphics gp = new SampleGraphics();
  gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  gp.setTitle("Sample Graphics");   // Optional title
  gp.setSize(1000, 800);     // Set the width and height of the window
  gp.setVisible(true);     // Display the window
 }

 // D - You need a paint() method with one Graphics parameter. This tells the computer 
 // what to draw each time the screen is painted.  Put all drawing instructions here.
 public void paint(Graphics g)   
 {
  setBackground (Color.blue);
   /***************** BASIC *****************/
  // 1 - Write text in the window 
  g.setFont(new Font("Serif", Font.PLAIN, 30)); 
  g.drawString("basic", 200, 80);   // Draw the String with its lower left corner
            // at (200, 80). Since we haven't set a
            // color, use the default color - black.
  // 2 - Draw lines 
  g.setColor(Color.blue);     // Select a predefined color
  g.drawLine(70, 100, 70, 200);   // Draw line (70, 100) to (70, 200) - vertical
  g.setColor(new Color(255, 0, 255));  // Define a color: red=255, green=0, blue=255
  g.drawLine(100, 100, 500, 200);   // Draw line from (100, 100) to (500, 200)

  // 3 - Draw two rectangles
  g.setColor(Color.red);
  g.drawRect(100, 200, 250, 350);   // Draw the border (outline) of a rectangle
            // with upper left corner at (100, 200), 
            // width = 250, height = 350
  g.fillRect(200, 300, 150, 50);   // Fill the rectangle rather than drawing the
            // border. Since no new color was set,
            // continue using the last color.
          
  // 4 - Draw ovals. An oval is defined by the rectangle that contains it.
  g.setColor(Color.green);    
  g.drawOval(200, 400, 100, 40);   // Draw the border of the oval that fits just
            // inside the rectangle at (200, 400), 
  g.setColor(Color.orange);    // width = 100, height = 40
  g.fillOval(220, 410, 25, 25);   // Fill an oval
  
  // 5 - Draw a polygon
  Polygon p = new Polygon();    // Define a polygon, then add as many points 
  p.addPoint(100, 700);     // as you want
  p.addPoint(400, 700);
  p.addPoint(250, 600);
  g.setColor(Color.yellow);
  g.fillPolygon(p);      // Fill the polygon p
  g.setColor(Color.black);
  g.drawPolygon(p);      // Now draw its border in black
  
  
  /***************** FANCY *****************/
  // 6 - Another string
  g.setColor(Color.blue);
  g.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 50)); 
  g.drawString("fancy", 650, 80);
  
  // 7 - Draw arcs. An arc is part of an oval.
  g.setColor(Color.black);  
  g.drawArc(650, 150, 40, 80, 0, 120); // Draw an arc of the oval at (650, 150), 
            // width = 40, height = 80,
            // start angle=0 degrees (right of center) 
            // arc angle=120 degrees (counter-clockwise)
  g.setColor(Color.pink);  
  g.fillArc(750, 150, 40, 80, 0, 120); // Same shape, but filled
  
  // 8 - More rectangle variations - round and 3D rectangles.
  g.setColor(Color.cyan);
  g.fillRoundRect(600, 300, 100, 80, 30, 20);
  g.setColor(Color.black);
  g.drawRoundRect(600, 300, 100, 80, 30, 20);
  
  g.setColor(Color.orange);
  g.fillRect(730, 230, 120, 190);   // We need a background to see this
  g.setColor(Color.lightGray);
  g.fill3DRect(750, 250, 80, 50, true); // raised rectangle
  g.fill3DRect(750, 350, 80, 50, false); // recessed rectangle
  
  // 9 - More interesting polygon example
  g.setColor(Color.magenta);
  Polygon q = new Polygon();
  for(int i = 0; i < 7; i++)
  {
   double angleInDegrees = i * 360.0 / 7;
   double angleInRadians = angleInDegrees * Math.PI / 180;
   int x = (int)(750 + 100 * Math.cos(angleInRadians));
   int y = (int)(600 + 100 * Math.sin(angleInRadians));
   q.addPoint(x ,y);
  }
  g.fillPolygon(q);
 }
}
