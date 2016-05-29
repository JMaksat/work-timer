import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import java.awt.TrayIcon;

public class tTimer {
	static Timer timer1 = new Timer();
	static int StartStop;

	public tTimer(String s) {

		if (s == "WORK") {
			// Next break at
			DestTime();

			// launch schedule with timer
			timer1.schedule(new Work(), WTGear.fullWork);
			StartStop = StartStop + 1;
		} else {
			tFrame.lTimer.setText("00:00");

			// launch schedule with timer
			timer1.schedule(new Pause(), WTGear.fullPause);
			StartStop = StartStop + 1;
		}
	}

	public static class Work extends TimerTask {
		public void run() {
			String wTitle = "Attention";
			String wCaption = "Would you like to take a break?";
		
			if (StartStop < 2) {
				
				WorkTimer.trayIcon.displayMessage(wCaption, wTitle, TrayIcon.MessageType.INFO);
				
				int Result = JOptionPane.showConfirmDialog(null,
						wCaption, wTitle,
						JOptionPane.YES_NO_OPTION);
				if (Result == 0) {
					new tTimer("PAUSE");
				} else {
					new tTimer("WORK");
				}
			}
			
			StartStop = 0;
		}
	}

	public static class Pause extends TimerTask {
		public void run() {
			String pTitle = "Attention";
			String pCaption = "Would you like to work?";

			if (StartStop < 2) {
				
				WorkTimer.trayIcon.displayMessage(pCaption, pTitle, TrayIcon.MessageType.INFO);
				
				int Result = JOptionPane.showConfirmDialog(null,
						pCaption, pTitle,
						JOptionPane.YES_NO_OPTION);
				if (Result == 0) {
					new tTimer("WORK");
				} else {
					new tTimer("PAUSE");
				}
			}

			StartStop = 0;
		}
	}

	public static void DestTime() {
		Calendar calInitial = Calendar.getInstance();  
        int offsetInitial = calInitial.get(Calendar.DST_OFFSET);		
		
		SimpleDateFormat lFormat = new SimpleDateFormat("HH:mm:ss");
		
		long lMillis = System.currentTimeMillis();
		
		Date lDate = new Date(lMillis + WTGear.fullWork - offsetInitial);
		
		tFrame.lTimer.setText(lFormat.format(lDate));
	}

}
