import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/*
 * May 28, 2013
 * This is my very first program on Java.
 * Purpose of the program is remind to have a rest and continue work after that.
 */

public class WorkTimer {
	  static Image image = Toolkit.getDefaultToolkit().getImage("image//favicon.gif");
	  static TrayIcon trayIcon = new TrayIcon(image, "WorkTimer");
	  
	public static void main(String args[]) {
	    if (SystemTray.isSupported()) {
	        SystemTray tray = SystemTray.getSystemTray();

	        trayIcon.setImageAutoSize(true);

	        try {
	          tray.add(trayIcon);
	        } catch (AWTException e) {
	          System.err.println("TrayIcon could not be added.");
	        }
	    }
	        
		WTGear.Start();
	}

}
