/**
 * This class sends a single ping
 *  
 **/

package iestelemetry;
import gnu.io.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;



/**
 * This class writes the deck box configuration info to the serial port
 * and configures it. It sends a single ping and runs in the background as a thread.
 * @author Pedro Pena
 */
public class SetRXThreshold extends Thread{
    SerialPort port;
    String deckBox = "";
    boolean stopped = false;
    PrintStream os = null;
    int receiveThreshold =0 ;

    JComponent jComponents[];

/**
 * This method is invoked when the class is instantiated and it starts
 * the class
 */
public void run(){


    try{
     os = new PrintStream(port.getOutputStream());
     os.print("+++");
     os.print("\r");
     Thread.sleep(500);
     os.print("ats21*" + receiveThreshold + "\r");
     Thread.sleep(500);
     os.print("ats15=6\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch

      





// busyButton.setEnabled(true);

}// end run

/**
 * Sets the serial port to be opened.
 * @param sp
 */
public void setPort(SerialPort sp){
    port = sp;

}// end setPort
/**
 * sets the deck box to be used
 * @param db String- name of the deck box to be used.
 */
public void setDeckBox(String db){
    deckBox = db;
}// end setDeckBox

/**
 * Stops the running of this process.
 */
public void stopThread(){
    stopped=true;

}// end stopThread

/**
 * Sets the default sensitivity for all channels once the program starts.
 * @param rt 
 */
public void setGlobalReceiveThreshold(int rt){

receiveThreshold = rt;
}// end




}
