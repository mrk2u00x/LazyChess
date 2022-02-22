import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public About() {
		super();
		initComponents();
		this.getRootPane().setDefaultButton(closeButton);
		this.setLocationRelativeTo(null);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/assets/white_king.png")));
		setTitle("About");
		setBounds(100, 100, 644, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea testAreaAbout = new JTextArea();
			testAreaAbout.setEditable(false);
			testAreaAbout.setBounds(37, 161, 560, 236);
			testAreaAbout.setWrapStyleWord(true);
			testAreaAbout.setLineWrap(true);
			testAreaAbout.setText("Voice Chess is a simple chess game a user can play that enables voice commands to control the pieces. As it stands, this segment of the market remains poorly addressed, with most of the solutions being a mishmash of different software that do not mesh well together. In scenarios we found programs implementing such a feature, they were buggy because the voice parser had a difficult time distinguishing between speech like \u201Cb4 v.s. before, a2 v.s. a to, c3 v.s. see three.\u201D This is a result of the English language not having detailed phonetic information. The NATO phonetic alphabet, a set of words used in oral communication to represent the alphabet, gives rise to a lexicon that contains rich phonetic information we can employ to overcome this limitation.");
			contentPanel.add(testAreaAbout);
		}
		{
			JLabel labelLazyChess = new JLabel("Lazy Chess About Page");
			labelLazyChess.setIcon(new ImageIcon(About.class.getResource("/assets/white_king.png")));
			labelLazyChess.setFont(new Font("Tahoma", Font.BOLD, 16));
			labelLazyChess.setBounds(139, 11, 321, 128);
			contentPanel.add(labelLazyChess);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeButtonActionPerformed(e);
					}
				});
				closeButton.setActionCommand("OK");
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
			}
		}
	}
	private void closeButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	private javax.swing.JButton closeButton;

}
