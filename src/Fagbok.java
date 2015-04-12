
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

 * @author yuanxin huang
 */
public class Fagbok extends Bok
{
	private String faget;

	public Fagbok( String f, String t, int sider, double p, String omr )
        {
		super( f, t, sider, p );
		faget = omr;
	}

      
	public String toString()
        {
		String s = super.toString();
		s += "\nFagområde: "  + faget ;
		return s;
	}
     public Fagbok() {

        }
        
     public void skrivObjektTilFil( DataOutputStream output ) {
          try {
            // Skriver datafeltenes verdier til fil. >
            output.writeUTF("Fagbok");
            super.skrivObjektTilFil(output);
            output.writeUTF(faget);
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
       
     public boolean lesObjektFraFil( DataInputStream input ) {
          try {
            super.lesObjektFraFil(input);
            faget = input.readUTF();
            return true;
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
            return false;
          }
          //< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
        }
}
