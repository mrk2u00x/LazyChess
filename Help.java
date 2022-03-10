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

public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public Help() {
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
			Help dialog = new Help();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Help.class.getResource("/assets/white_king.png")));
		setTitle("Help");
		setBounds(100, 100, 466, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea textAreaHelp = new JTextArea();
			textAreaHelp.setEditable(false);
			textAreaHelp.setBounds(37, 161, 363, 55);
			textAreaHelp.setWrapStyleWord(true);
			textAreaHelp.setLineWrap(true);
			textAreaHelp.setText("Input name for Player 1 and 2 to start game.\r\nClick New Game to create a new chess game");
			contentPanel.add(textAreaHelp);
		}
		{
			JLabel labelLazyChessHelp = new JLabel("Voice Chess Help Page");
			labelLazyChessHelp.setIcon(new ImageIcon(About.class.getResource("/assets/white_king.png")));
			labelLazyChessHelp.setFont(new Font("Tahoma", Font.BOLD, 16));
			labelLazyChessHelp.setBounds(65, 11, 321, 128);
			contentPanel.add(labelLazyChessHelp);
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
