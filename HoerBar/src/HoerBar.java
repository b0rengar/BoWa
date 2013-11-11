import java.net.UnknownHostException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.bff.javampd.MPD;
import org.bff.javampd.exception.MPDConnectionException;

import bowa.gui.GuiContainer;

/**
 * 
 */

/**
 * @author Phillip
 *
 */
public class HoerBar {

	/**
	 * main-methode
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
		
		String server = "";
		if (System.getProperty("os.name").startsWith("Windows")){
			server = "192.168.137.254";
		}
		else{
			server = "127.0.0.1";
		}

			
		try {
			new GuiContainer(new MPD(server)).setVisible(true);
		} catch (UnknownHostException | MPDConnectionException e) {
			e.printStackTrace();
			System.exit(1);
		}

		
		

	}

}
