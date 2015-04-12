//Bokprogram.java
import javax.swing.*;
public class Bokprogram
{
  public static void main( String[] args )
  {
	  Bokarkiv frame = new Bokarkiv();
	  frame.setSize(740,650);//frame size
	  frame.setLocationRelativeTo(null);//center a frame
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setVisible(true);
	  frame.setTitle("Bok arkiv");
    
  }
}
