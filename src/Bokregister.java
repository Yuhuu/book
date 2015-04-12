import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Bokregister
{
  private Bok forste;
  //registrerer et bokobjekt
  //REGISTER BOKOBJEKT
  public void settInn( Bok ny )
	{
    if( ny == null ) return;

    if( forste == null ) // tom liste:
    {
      forste = ny;
      return;
    }
					
    Bok loper = forste;
    while( loper.neste != null )
    {
        loper = loper.neste;
      }
     loper.neste = ny;
  }


  //utskrift av innhold i bokliste
  public void skrivListe( JTextArea boker )
  {
    if ( forste == null )
      boker.append( "Ingen bøker registrert." );
    else
    {
      boker.setText( "" );
      Bok loper = forste;
      while ( loper != null )
      {
        boker.append( loper.toString() + "\n" );
        loper = loper.neste;
      }
    }
  }
   public void skrivTilfil( String filnavn ) {
    try {
      /*<  skrivObjektTilFil-metoden i Bok-klassen >*/
      DataOutputStream dout = new DataOutputStream(new FileOutputStream(filnavn));
      Bok hjelpebok = forste;
      
      while (hjelpebok != null) {
        hjelpebok.skrivObjektTilFil(dout);
        hjelpebok = hjelpebok.neste;
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Bokregister.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
   public void lesFraFil( String filnavn ) throws IOException {
       
      try {
      DataInputStream din = new DataInputStream(new FileInputStream(filnavn));
      while (true) {
        String type = din.readUTF();
          switch (type) {
              case "Fagbok":
                  Fagbok fagbok = new Fagbok();
                  if (fagbok.lesObjektFraFil(din)) {
                      settInn(fagbok);
                  }       break;
              case "Skolebok":
                  Skolebok skolebok = new Skolebok();
                  if (skolebok.lesObjektFraFil(din)) {
                      settInn(skolebok);
          }       break;
              case "Norskroman":
                  NorskRoman roman = new NorskRoman();
                  if (roman.lesObjektFraFil(din)) {
                      settInn(roman);
          }       break;
              case "Utenlandskroman":
                  UtenlandskRoman ulroman = new UtenlandskRoman();
                  if (ulroman.lesObjektFraFil(din)) {
                      settInn(ulroman);
          }       break;
          }
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Bokregister.class.getName()).log(Level.SEVERE, null, ex);
    } catch (EOFException ex) {
      System.out.println("lykke");
    }
  }
}



