
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

 *
 * @author yuanxinhuang
 */
public abstract class Roman extends Bok
{
	protected String sjanger;

	protected Roman( String f, String t, int sider, double p, String s )
        {
		super( f, t, sider, p );
		sjanger = s;
	}

	public String toString()
        {
		String s = super.toString();
		s += "\n. Sjanger: " + sjanger;
		return s;
	}
        public Roman() {

    }
}


class NorskRoman extends Roman
{
	private String languageform;

	public NorskRoman( String f, String t,  int s, double p, String sj, String m )
        {
		super( f,t, s, p, sj );
		languageform = m;
	}
        public NorskRoman() {

        }
       
	public String toString()
        {
		String s = super.toString();
		s += "\n Språk" + languageform;
		return s;
	}
        
           public void skrivObjektTilFil( DataOutputStream output ) {
          try {
            // Skriver datafeltenes verdier til fil. 
            super.skrivObjektTilFil(output);
            output.writeUTF(sjanger);
            //output.writeUTF("bok;"+tittel+";"+forfatter+";"+sideantall+";"+pris+"\n");
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
       
        public boolean lesObjektFraFil( DataInputStream input ) {
          try {
            super.lesObjektFraFil(input);
            sjanger = input.readUTF();
            return true;
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
            return false;
          }
          //< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
        }
}


class UtenlandskRoman extends Roman
{
	private String language;

	public UtenlandskRoman( String f, String t,  int s, double p, String sj, String sp )
        {
		super( f, t, s, p, sj );
		language = sp;
	}
        public UtenlandskRoman() {

        }
     
	public String toString() {
		String s = super.toString();
		s += "\nLanguage: " + language;
		return s;
	}
        
    public void skrivObjektTilFil( DataOutputStream output ) {
          try {
            // Skriver datafeltenes verdier til fil. 
            super.skrivObjektTilFil(output);
            output.writeUTF(sjanger);
            //output.writeUTF("bok;"+tittel+";"+forfatter+";"+sideantall+";"+pris+"\n");
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
       
        public boolean lesObjektFraFil( DataInputStream input ) {
          try {
            super.lesObjektFraFil(input);
            sjanger = input.readUTF();
            return true;
          } catch (IOException ex) {
            Logger.getLogger(Bok.class.getName()).log(Level.SEVERE, null, ex);
            return false;
          }
        }
}