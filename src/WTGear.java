import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WTGear {
	static int secWork = 0;
	static int secPause = 0;
	static String Str;
	static long fullWork;
	static long fullPause;
	static tFrame Frm = new tFrame();

	public static void Start() {
		ReadData();
		InitFrame();

		fullWork = secWork * 1000;
		fullPause = secPause * 1000;

		tTimer.StartStop = 0;
		new tTimer("WORK");
	}

	private static void ReadData() {
		try {
			BufferedReader out = new BufferedReader(new InputStreamReader(
					new FileInputStream("res//data")));
			while (out.ready()) {
				Str = out.readLine();
				if (Str.indexOf("WORK") == 0) {
					secWork = Integer.parseInt(Str.substring(Str.indexOf("=") + 1));
				}
				if (Str.indexOf("PAUSE") == 0) {
					secPause = Integer.parseInt(Str.substring(Str.indexOf("=") + 1));
				}
			}
			out.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Unable to read data!");
		}
	}

	public static void SaveChanges() {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("res//data")));

			out.write("WORK=" + tFrame.tWork.getText());
			out.newLine();
			out.write("PAUSE=" + tFrame.tPause.getText());

			out.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Unable to save changes!");
		}

		fullWork = Integer.parseInt(tFrame.tWork.getText()) * 1000;
		fullPause = Integer.parseInt(tFrame.tPause.getText()) * 1000;

		new tTimer("WORK");
	}

	private static void InitFrame() {
		Frm.setTitle("WorkTimer");
		Frm.setVisible(true);
		Frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frm.setSize(300, 200);
		Frm.setResizable(false);
		Frm.setLocationRelativeTo(null);

		tFrame.tWork.setText(String.valueOf(secWork));
		tFrame.tPause.setText(String.valueOf(secPause));
	}

}
