/**
 * This class sends the URI Codes to the UDB-9000
 *  
 **/

package iestelemetry;
import gnu.io.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;



/**
 * This class writes the deck box configuration info to the serial port
 * and configures it. It sends the URI Codes to the UDB-9000class and runs in the background as a thread.
 * @author Pedro Pena
 */
public class SendURICommand extends Thread{
    SerialPort port;
    String deckBox = "";
    boolean stopped = false;
    PrintStream os = null;

    JComponent jComponents[];

/**
 * This method is invoked when the class is instantiated and it starts
 * the class.
 */
public void run(){


            try{


     os = new PrintStream(port.getOutputStream());
     os.print("+++");
     os.print("\r");
     Thread.sleep(1000);
     os.print("\rat%u"+((JComboBox)(this.jComponents[0])).getSelectedItem()+"\r");
     Thread.sleep(21000); // wait 21 seconds to end transponde mode
     os.print("ats15=6\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch

      


 this.setJComponentsEnable(true);


// busyButton.setEnabled(true);

}// end run

/**
 * Sets the serial port to be opened.
 * @param sp the serial port to be opened
 */
public void setPort(SerialPort sp){
    port = sp;

}// end setPort
/**
 * sets the deck box to be used.
 * @param db String- name of the deck box to be used.
 */
public void setDeckBox(String db){
    deckBox = db;
}// end setDeckBox

/**
 * Stops this process from running.
 */
public void stopThread(){
    stopped=true;

}// end stopThread



/**
 * Passes a reference of the JComponents to this class so it knows how to enable and disable the buttons on the User Interface.
 * @param j the array of JComponents.
 */
public void setJComponents(JComponent j[]){
    jComponents = j;

}// end

/**
 * Sets the state of the JComponents: enabled (true), disabled (false).
 * @param e the state of the JComponenets
 */
 public void setJComponentsEnable(boolean e)   {
    for (int i = 0 ; i < jComponents.length ; i++){
        jComponents[i].setEnabled(e);

    }// end for}

}
}
