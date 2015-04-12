
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author yuanxinhuang
// @studnr 184519

public abstract class Bok
{
	 String forfatter, tittel;
	 int sideantall;
	 double pris;
         Bok neste;

  public Bok()
  {
	}

  public Bok( String f, String t, int sider, double p )
  {
		forfatter = f;
		tittel = t;
		sideantall = sider;
		pris = p;
        neste = null;
	
  }
  public String getForfatter()
	{
		return forfatter;
	}


  public String getTittel()
  {
		return tittel;
	}

  public String toString()
  {
		String s ="<=======================> \nforfatter: "+ forfatter + "; \n";
		s += "Tittel: "+tittel + "; \n";
		s += "Sidentall: "+sideantall + " s. \n";
		s += "Pris: " +pris +".-kr ";
		return s;
	}
        
  public void skrivObjektTilFil( DataOutputStream output ) {
          try {
            // Skriver datafeltenes verdier til fil. >
            output.writeUTF(forfatter);
            output.writeUTF(tittel);
            output.writeInt(sideantall);
            output.writeDouble(pris);

          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
        }

  public boolean lesObjektFraFil( DataInputStream input ) {
          try {
            forfatter = input.readUTF();
            tittel = input.readUTF();
            sideantall = input.readInt();
            pris = input.readDouble();
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
          //< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
          return false;
        }
}
