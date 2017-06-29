
package iestelemetry;
import gnu.io.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;


 
/**
 * This class writes the deck box configuration info to the serial port
 * and configures it. This class runs in the background as a thread.
 * 02/21/2013 changed continuous transponde command to ats15=6 so that 
 * box does not start in continous transponde mode.
 * @author Pedro Pena
 */
public class configureDeckBox_UDB9000 extends DeckBox{


/**
 * This method is invoked when the class is instantiated and it starts
 * the class
 */
public void run(){
    freqFormat = new DecimalFormat("##.##");

    
if(port!=null){

    sendEscapeSequence();
    pause(delayTime);


    for(double f = 7.00 ; f <= 16.00 ; f+=.25){


        if(f == 10.00 || f == 10.50  ||f == 11.00 || f == 11.50 || f == 12.00 || f == 12.50 || f ==13.00 || f ==13.50 ){

            adjustChannelReceiveThreshold(f,0);
            pause(delayTime);
        }// end if
        else
        {
            adjustChannelReceiveThreshold(f,-1.00);
            pause(delayTime);


        }// end else


    }// end for

    setGlobalReceiveThreshold(receiveThreshold);
    pause(delayTime);
    setRXPulseWidth(receivePulseWidth);
    pause(delayTime);
    setTemperatureCompensatedOscillator(2);         // setting because URI says so
    pause(delayTime);
    setListenTimeout(30);   //sets listening timeout to 25  secs 
    pause(delayTime);    
    startContinuousTranspondMode();
    pause(delayTime);
    }// end if

   
     this.setJComponentsEnable(true);


clearPort();


}// end run

/**
 * Puts the deck unit in a mode that it can receive commands.
 * Exits continuous transpond mode.
 */
public void sendEscapeSequence(){
    try{
     os = new PrintStream(port.getOutputStream());
     os.print("+++");
     os.print("\r");


     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch


}// end sendEscapeSequnce

/**
 * Puts in a mode that it is continuously listening for signals.
 */
public void startContinuousTranspondMode(){
    try{
     os = new PrintStream(port.getOutputStream());
     os.print("\rats15=6\r");


     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch


}//end continuousTranspondMode

/**
 * Adjusts the sensitivity (threshold) of a given channel.
 * @param ch the channel to be adjusted
 * @param val the value of the sensitivity to be set
 */
public void adjustChannelReceiveThreshold(double ch, double val){
    try{
     os = new PrintStream(port.getOutputStream());
     os.print("rxadj " + ch + " " + val + "\r");
     
     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch

}// end adjustChannelReceiveThreshold

/**
 * Sets the default receive threshold for all channels.
 * @param rst 
 */
public void setGlobalReceiveThreshold(int rst){

    try{
     os = new PrintStream(port.getOutputStream());
     os.print("ats21*" + rst + "\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch

}// end setGlobalReceiveThreshold

/**
 * Sets the time length (width) of the pulse in milliseconds.
 * @param rpw the time length of a pulse in milliseconds.
 */
void setRecievePulseWidth(int rpw){
receivePulseWidth = rpw;
}// end setRecievePulseWidth

/**
 * Sets the time length (width) of the pulse in milliseconds.
 * @param rpw the time length of a pulse in milliseconds.
 */
void setRXPulseWidth(int rpw){

    try{
     os = new PrintStream(port.getOutputStream());
     os.print("ats33*" + rpw + "\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch    
}//end set Ping Length

/**
 * Stabilizes the oscillator.
 * @param tco 
 */
void setTemperatureCompensatedOscillator(int tco){

    try{
     os = new PrintStream(port.getOutputStream());
     os.print("ats49*" + tco + "\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch  
}//set TCO

/**
 * Clears the data buffer.
 */
public void clearDataBuffer(){
    
    try{
     os = new PrintStream(port.getOutputStream());
     os.print("atbc" + "\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch     
    
}// end clear data buffer

/**
 * Puts the box in transpond mode after a signal is sent for the specified amount of time.
 * @param to the amount of time the box will be on transpond mode.
 */
public void setListenTimeout(int to)
{
    try{
     os = new PrintStream(port.getOutputStream());
     os.print("ats7=" + to + "\r");

     if(os!=null){
         os.flush();
         os.close();
     }

}
    catch(Exception e){
    e.printStackTrace();
    }// end catch       
    
}//end setListenTimeout

}// end class
