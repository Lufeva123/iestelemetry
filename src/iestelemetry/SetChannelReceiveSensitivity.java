
package iestelemetry;
import gnu.io.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;



/**
 * This class calibrates the sensitivity of the receiver by sending a single ping.
 * @author Pedro Pena
 */
public class SetChannelReceiveSensitivity extends Thread{
    SerialPort port;
    String deckBox = "";
    boolean stopped = false;
    PrintStream os = null;
    String receiveSensitivity="";
    String channel = "";

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
     os.print("rxadj " + channel +" " + receiveSensitivity +"\r");
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
 * Sets the Rx sensitivity of a specified channel based on the threshold sensitivity of that channel.
 * @param ch the channel to be configured
 * @param sny the Rx sensitivity to be applied to that channel.
 */
public void setChannelandRxSensitivity(String ch, String sny){

channel = ch;
this.receiveSensitivity = sny;
}// end




}
