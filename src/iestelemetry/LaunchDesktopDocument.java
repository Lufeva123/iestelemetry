
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iestelemetry;

import java.awt.Desktop;
import java.io.File;



/**
 *
 * @author Pedro.Pena
 */


/**
 * This class will use the host OS to launch registered documents
 */
public class LaunchDesktopDocument {

    /**
     * Constructor of the class that opens the file specified in the parameters and, if the desktop is supported, opens the file.
     * @param f the file to be opened.
     * @throws Exception 
     */
    public LaunchDesktopDocument(File f)throws Exception{
        Desktop dt = Desktop.getDesktop();
        if(dt.isDesktopSupported()){
            try{
                dt.open(f);
            }// end try
            catch(Exception e){

                throw e;
            }// end catch


        }//end if

    }// end constructor

}
