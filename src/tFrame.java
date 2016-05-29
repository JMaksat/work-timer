import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class tFrame extends JFrame {

	private static final long serialVersionUID = -5637770505364920925L;
	static JLabel lWork, lPause, lTimerText, lTimer;
	static JTextField tWork;
	static JTextField tPause;
	static JButton bEdit, bAccept;
	eHandler handler = new eHandler();

	public tFrame() {

		JPanel panel = new JPanel();
		panel.setLayout(null);

		lTimerText = new JLabel("Next break at ");
		lTimerText.setBounds(30, 5, 200, 30);
		panel.add(lTimerText);

		lTimer = new JLabel("00:00");
		lTimer.setBounds(170, 5, 195, 30);
		panel.add(lTimer);

		lWork = new JLabel("Working Time sec");
		lWork.setBounds(30, 40, 200, 30);
		panel.add(lWork);

		tWork = new JTextField(10);
		tWork.setBounds(170, 42, 100, 25);
		tWork.setEditable(false);
		panel.add(tWork);

		lPause = new JLabel("Break time sec");
		lPause.setBounds(30, 75, 195, 30);
		panel.add(lPause);

		tPause = new JTextField(10);
		tPause.setBounds(170, 77, 100, 25);
		tPause.setEditable(false);
		panel.add(tPause);

		bEdit = new JButton("Edit");
		bEdit.setBounds(30, 115, 110, 30);
		bEdit.setEnabled(true);
		panel.add(bEdit);

		bAccept = new JButton("Accept");
		bAccept.setBounds(160, 115, 110, 30);
		bAccept.setEnabled(false);
		panel.add(bAccept);

		getContentPane().add(panel);

		bEdit.addActionListener(handler);
		bAccept.addActionListener(handler);
	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == bEdit) {
					tWork.setEditable(true);
					tPause.setEditable(true);
					bEdit.setEnabled(false);
					bAccept.setEnabled(true);
				}

				if (e.getSource() == bAccept) {
					tWork.setEditable(false);
					tPause.setEditable(false);
					bEdit.setEnabled(true);
					bAccept.setEnabled(false);
					WTGear.SaveChanges();
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Unnown error!");
			}
			;
		}
	}

}
