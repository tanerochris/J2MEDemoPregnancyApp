/*Program that calculates the number of days a person is pregnant */
/*Authors*/
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Pregnant extends MIDlet implements CommandListener {
    // display manager
    Display display = null;
    
    // textbox
    TextBox input = null;

    //avg period
    int avgPeriod = 28;

    //choicegroup
	// ChoiceGroup periods = new ChoiceGroup("Period", Choice.POPUP);

    // ticker
    Ticker ticker = new Ticker("Welcome to your pregnancy test");


    // today date
    DateField today = new DateField("Today's date: ", DateField.DATE);
    
    //last period
     DateField lastperiod = new DateField("last period: ", DateField.DATE);

    // form
    Form form = new Form("Enter Details");

    
    // command
    static final Command backCommand = new Command("Back", Command.BACK, 0);
    static final Command mainMenuCommand = new Command("Main", Command.SCREEN, 1);
    static final Command exitCommand = new Command("Exit", Command.STOP, 2);
    static final  Command ok= new Command("Ok", Command.OK, 3);
    String currentMenu = null;

    // constructor.
    public Pregnant() {
    }

   
    public void startApp() throws MIDletStateChangeException {
      display = Display.getDisplay(this);
      //combobox init
  
    
     /* periods.append("27");
      periods.append("28");
      periods.append("29");
      periods.append("30");
      periods.append("31");*/
      form.append(today);
      form.append(lastperiod);
    //  form.append(periods);
      form.addCommand(exitCommand);
      form.addCommand(ok);
      form.setCommandListener(this);
      form.setTicker(ticker);

      mainMenu();
    }

    public void pauseApp() {
      display = null;
      form = null;
      input = null;
      
       
    }

    public void destroyApp(boolean unconditional) {
      notifyDestroyed();
    }

    // main menu
    void mainMenu() {
      display.setCurrent(form);
      currentMenu = "Main"; 
    }

    /*text box to display result*/
    public void textBox() {
      System.out.println("textbox called");
      input = new TextBox("Result:", "", 255, TextField.UNEDITABLE | TextField.ANY);
      input.setTicker(new Ticker("Your result"));
      input.addCommand(backCommand);
      input.setCommandListener(this);
      input.setString(pregnancyTestResult());
      display.setCurrent(input);
      currentMenu = "input";
    }
   
  //function  
   public String pregnancyTestResult(){
   	// validate that date was written 
   	  if(today.getDate()==null ||lastperiod.getDate()==null || (today.getDate().getTime() - lastperiod.getDate().getTime())<0 ){

   	  		return "An error occured,either one of the required fields was not entered or there is inconsistency with date,please try again";
   	  }else{

   	  		if((today.getDate().getTime() - lastperiod.getDate().getTime()) < avgPeriod*24*60*60*1000)
   	  		 return "you are not pregnant ";

   	  			else if((today.getDate().getTime() - lastperiod.getDate().getTime()) == avgPeriod*24*60*60*1000)

   	  				return "you are not pregnant";
            
   	  				else{
   	  					
   	  					long range = (today.getDate().getTime() - lastperiod.getDate().getTime());
   	  					//get the number of months pregnant
   	  					long time = 60*60*24*1000;
   	  					long days = range/time;
   	  					long month = days/30;
   	  					long week = (days%30)/7;
   	  					long day = (days%30)%7;
   	  					
   	  					return "Congratulations you are " + month +" months " + week + " weeks " + day +" days pregnant.We advice you to undertake follow ups";
   	  				}
   	  }
    
	 }

   /**
    * Handle events.
    */  
   public void commandAction(Command c, Displayable d) {
      String label = c.getLabel();
      if (label.equals("Exit")) {
         destroyApp(true);
      } else if (label.equals("Back") ) {
          if(currentMenu.equals("input") ) {
            // go back to menu
            mainMenu();
          } 

      } else if (label.equals("Ok") ){
         textBox();
         }
            
      }
  
} 
