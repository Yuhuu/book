// @author yuanxinhuang
// @studnr 184519

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.io.IOException;

public class Bokarkiv extends JFrame
{
 private static final long serialVersionUID = 2636820735385156013L;
  JTextField forfatterfelt, tittelfelt, sideantallsfelt, prisfelt, fagfelt, skolefagfelt,
            klassetrinnfelt, sjangerfelt, language, languageform;
  JButton regFagbok, regSkolebok, regNRoman, regURoman, visReg;
  JTextArea printarea;
  JLabel fagJL, skolefagJL,tittelJL, forfatterJL,sjangerJL,languageJL,languageformJL;
  
  private Bokregister register = new Bokregister();

  public Bokarkiv()
  {
   try{ 
       register.lesFraFil("bokliste.dat");
       } catch (IOException ex)
       {
         java.util.logging.Logger.getLogger(Bokarkiv.class.getName()).log(Level.SEVERE, null, ex);
       }       
    printarea = new JTextArea( 10, 20 );
    forfatterfelt = new JTextField( 30 );
    tittelfelt = new JTextField( 30  );
    sideantallsfelt = new JTextField( 4 );
    prisfelt = new JTextField( 6 );
    fagfelt = new JTextField( 30  );
    skolefagfelt = new JTextField( );
    klassetrinnfelt = new JTextField(15);
    sjangerfelt = new JTextField(15);
    language = new JTextField();
    languageform = new JTextField();
 
    forfatterJL = new JLabel( "Forfatter:" );
    tittelJL = new JLabel( "Tittel:" );
    fagJL = new JLabel( "Fagområde:" );
    skolefagJL = new JLabel( "Skolefag:" );
    languageJL = new JLabel( "Språk:");
    languageformJL = new JLabel( "Målform (b = bokmål, n = nynorsk):" );
    sjangerJL  = new JLabel( "Sjanger:" ) ;
    
    regFagbok = new JButton( "Registrer fagbok" );
    regSkolebok = new JButton( "Registrer skolebok" );
    regNRoman = new JButton( "Registrer norsk roman" );
    regURoman = new JButton( "Registrer utenlandsk roman" );
    visReg = new JButton( "Vis bokregister" );
   
    printarea.setEditable( false );
    
    JPanel panel3 = new JPanel();
    panel3.add( fagJL );
    panel3.add( fagfelt );
    panel3.setBorder(new TitledBorder("Fagbok"));
    JPanel panel4 = new JPanel();
    panel4.setLayout( new GridLayout(2,2) );
    panel4.add( skolefagJL );
    panel4.add( skolefagfelt );
    panel4.add( new JLabel( "Klassetrinn:" ) );
    panel4.add( klassetrinnfelt );
    panel4.setBorder(new TitledBorder("Skolebok"));
    JPanel panel5 = new JPanel();
    panel5.setLayout( new GridLayout(3,2) );
    panel5.setBorder(new TitledBorder("Roman"));
    panel5.add( sjangerJL);
    panel5.add( sjangerfelt );
    panel5.add( languageJL );
    panel5.add( language );
    panel5.add( languageformJL  );
    panel5.add( languageform );
   JPanel panel1 = new JPanel();
   panel1.setLayout( new FlowLayout() );
   panel1.add( forfatterJL);
   panel1.add( forfatterfelt );
   panel1.add( tittelJL );
   panel1.add( tittelfelt );
   panel1.add( new JLabel( "Sideantall:" ) );
   panel1.add( sideantallsfelt );
   panel1.add( new JLabel( "Pris:" ) );
   panel1.add( prisfelt );
   panel1.add( panel3 );
   panel1.add( panel4);
   panel1.add( panel5);
 
   
   
   JPanel panel2= new JPanel();
   panel2.setLayout( new GridLayout(3,2) );
   panel2.add( regFagbok );
   panel2.add( regSkolebok );
   panel2.add( regNRoman );
   panel2.add( regURoman );
   panel2.add( visReg );
   panel1.add( panel2 );
   
   add( new JScrollPane( printarea ), BorderLayout.WEST);
   add( panel1, BorderLayout.CENTER);
    Knappelytter lytter = new Knappelytter();
    regFagbok.addActionListener( lytter );
    regSkolebok.addActionListener( lytter );
    regNRoman.addActionListener( lytter );
    regURoman.addActionListener( lytter );
    visReg.addActionListener( lytter );
    setVisible( true );
    addWindowListener(new WindowAdapter(){
      
            public void windowClosing(WindowEvent e) {
              register.skrivTilfil("bokliste.dat");
              System.exit(0);}
    		});
  }

  public void nyFagbok()
  {
		String forfatter = forfatterfelt.getText();
		String tittel = tittelfelt.getText();
		String fag = fagfelt.getText();
		if(forfatter.length() == 0 || tittel.length() == 0
		|| fag.length() == 0 )
		{
			visMelding("Fyll ut nødvindige tekstfelt");
			
			if(fag.length() == 0){
			fagJL.setForeground(Color.RED);
			}
			if(forfatter.length() == 0){
				forfatterJL.setForeground(Color.RED);
				}
			if(tittel.length() == 0){
				tittelJL.setForeground(Color.RED);
				}
			return;
		}
		try
		{
			int sideantall = Integer.parseInt(sideantallsfelt.getText());
			double pris = Double.parseDouble(prisfelt.getText());
			register.settInn(new Fagbok(forfatter, tittel, sideantall, pris, fag));
			visMelding( "Ny fagbok registrert." );
			slettFelter();
		}
		catch(NumberFormatException nfe)
		{
			visMelding("Feil i tallformat");
		}
	}


  public void nySkolebok()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String skolefag = skolefagfelt.getText();
    if ( forfatter.length() == 0 ||
         tittel.length() == 0 || skolefag.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      if(skolefag.length() == 0){
			skolefagJL.setForeground(Color.RED);
			}
			if(forfatter.length() == 0){
				forfatterJL.setForeground(Color.RED);
				}
			if(tittel.length() == 0){
				tittelJL.setForeground(Color.RED);
				}
      return;
    }
    try {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      int trinn = Integer.parseInt( klassetrinnfelt.getText() );
      register.settInn(
          new Skolebok( forfatter, tittel, sideantall, pris, trinn, skolefag ) );
      visMelding( "Ny skolebok registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallformat!" );
    }
  }

  public void nyNorskRoman()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String sjanger = sjangerfelt.getText();
    
    String languageform = language.getText();
    if ( forfatter.length() == 0 || tittel.length() == 0
         || sjanger.length() == 0 || languageform.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      if(sjanger.length() == 0){
    	  sjangerJL.setForeground(Color.RED);
			}
			if(forfatter.length() == 0){
				forfatterJL.setForeground(Color.RED);
				}
			if(tittel.length() == 0){
				tittelJL.setForeground(Color.RED);
				}
			if(languageform.length() == 0){
				languageformJL.setForeground(Color.RED);
				}
			return;
    }
    try {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      char languagecode = languageform.charAt( 0 );
      if ( languagecode == 'b' )
        languageform = "bokmål";
      else
        languageform = "nynorsk";
      register.settInn(
          new NorskRoman( forfatter, tittel, sideantall, pris, sjanger, languageform ) );
      visMelding( "Ny norsk roman registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallat!" );
    }
  }

  public void nyUtenlandskRoman()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String sjanger = sjangerfelt.getText();
    String langue = language.getText();
    if ( forfatter.length() == 0 || tittel.length() == 0 ||
         sjanger.length() == 0 || langue.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      if(sjanger.length() == 0){
    	  sjangerJL.setForeground(Color.RED);
			}
			if(forfatter.length() == 0){
				forfatterJL.setForeground(Color.RED);
				}
			if(tittel.length() == 0){
				tittelJL.setForeground(Color.RED);
				}
			if(langue.length() == 0){
				languageJL.setForeground(Color.RED);
				}
      return;
    }
    try
    {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      register.settInn(
          new UtenlandskRoman( forfatter, tittel, sideantall, pris, sjanger, langue ) );
      visMelding( "Ny utenlandsk roman registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallformat!" );
    }
  }

  public void visRegister()
  {
    register.skrivListe( printarea );
  }

  private void visMelding( String melding )
  {
	  printarea.setText( melding );
	  colorBlack();
  }

  private void slettFelter()
  {
    forfatterfelt.setText( "" );
    tittelfelt.setText( "" );
    sideantallsfelt.setText( "" );
    prisfelt.setText( "" );
    fagfelt.setText( "" );
    skolefagfelt.setText( "" );
    klassetrinnfelt.setText( "" );
    sjangerfelt.setText( "" );
    language.setText( "" );
    languageform.setText( "" );
  }
  
  private void colorBlack(){
	  forfatterJL.setForeground(Color.black);
	    tittelJL.setForeground(Color.black);
	    fagJL.setForeground(Color.black);
	    skolefagJL.setForeground(Color.black);
	    sjangerJL.setForeground(Color.black);
	    languageJL.setForeground(Color.black);
	    languageformJL.setForeground(Color.black);
	    
  }

  private class Knappelytter implements ActionListener
  {
     
    public void actionPerformed( ActionEvent e )
    {
      if ( e.getSource() == regFagbok )
        nyFagbok();
      else if ( e.getSource() == regSkolebok )
        nySkolebok();
      else if ( e.getSource() == regNRoman )
        nyNorskRoman();
      else if ( e.getSource() == regURoman )
        nyUtenlandskRoman();
      else if ( e.getSource() == visReg )
        visRegister();
    }
  }
}
