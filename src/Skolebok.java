
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuanxin huang
 */
public class Skolebok extends Bok
{
	private int klassetrinn;
	private String skolefag;

	public Skolebok( String f, String t,int sider, double p, int kt, String fag )
        {
		super( f, t, sider, p );
		klassetrinn = kt;
		skolefag = fag;
	}

        public Skolebok() {
        }
	public String toString()
        {
		String s = super.toString();
		s += "\nTrinn: " + klassetrinn;
		s += "\nSkolefag: " + skolefag;
		return s;
	}
        
        public void skrivObjektTilFil( DataOutputStream output ) {
          try {
            // Skriver datafeltenes verdier til fil. >
            output.writeUTF("Skolebok");
            super.skrivObjektTilFil(output);
            output.writeInt(klassetrinn);
            output.writeUTF(skolefag);
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    
        public boolean lesObjektFraFil( DataInputStream input ) {
          try {
            super.lesObjektFraFil(input);
            klassetrinn = input.readInt();
            skolefag = input.readUTF();
            return true;
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
            return false;
          }
        }
}
