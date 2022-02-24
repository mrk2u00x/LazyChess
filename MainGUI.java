import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MainGUI {

	private JFrame mainGUI;
	private JTextField whitePlayerName;
	private JTextField blackPlayerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.mainGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainGUI = new JFrame();
		mainGUI.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/assets/white_king.png")));
		mainGUI.setTitle("Lazy Chess");
		mainGUI.setBounds(100, 100, 513, 507);
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		mainGUI.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem menuNewGame = new JMenuItem("New Game");
		fileMenu.add(menuNewGame);

		JMenuItem menuSave = new JMenuItem("Save");
		fileMenu.add(menuSave);

		JMenuItem menuQuit = new JMenuItem("Quit");
		menuQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(menuQuit);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem menuHelp = new JMenuItem("Help");
		menuHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpMenuItemActionPerformed(e);
			}
		});
		helpMenu.add(menuHelp);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutMenuItemActionPerfromed(e);
			}
		});
		helpMenu.add(menuAbout);
		mainGUI.getContentPane().setLayout(null);
		
		JLabel labelVoiceChess = new JLabel("Voice Chess");
		labelVoiceChess.setBounds(203, 0, 82, 17);
		labelVoiceChess.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainGUI.getContentPane().add(labelVoiceChess);
		
		JLabel whiteIcon = new JLabel("");
		whiteIcon.setIcon(new ImageIcon(MainGUI.class.getResource("/assets/white_king.png")));
		whiteIcon.setBounds(89, 60, 128, 119);
		mainGUI.getContentPane().add(whiteIcon);
		
		whitePlayerName = new JTextField();
		whitePlayerName.setToolTipText("Insert Player Name");
		whitePlayerName.setBounds(295, 93, 86, 20);
		mainGUI.getContentPane().add(whitePlayerName);
		whitePlayerName.setColumns(10);
		
		JLabel whitePlayerLabel = new JLabel("Player 1:");
		whitePlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		whitePlayerLabel.setBounds(234, 95, 51, 14);
		mainGUI.getContentPane().add(whitePlayerLabel);
		
		JLabel blackIcon = new JLabel("");
		blackIcon.setIcon(new ImageIcon(MainGUI.class.getResource("/assets/black_king.png")));
		blackIcon.setBounds(89, 223, 128, 128);
		mainGUI.getContentPane().add(blackIcon);
		
		blackPlayerName = new JTextField();
		blackPlayerName.setToolTipText("Insert Player Name");
		blackPlayerName.setBounds(295, 279, 86, 20);
		mainGUI.getContentPane().add(blackPlayerName);
		blackPlayerName.setColumns(10);
		
		JLabel blackPlayerLabel = new JLabel("Player 2:");
		blackPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		blackPlayerLabel.setBounds(234, 281, 51, 14);
		mainGUI.getContentPane().add(blackPlayerLabel);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.setToolTipText("Starts New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameMenuItemActionPerformed(e);
			}
		});
		newGameButton.setBounds(33, 390, 103, 23);
		mainGUI.getContentPane().add(newGameButton);
		
		JButton helpButton = new JButton("Help");
		helpButton.setToolTipText("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpMenuItemActionPerformed(e);
			}
		});
		helpButton.setBounds(146, 390, 102, 23);
		mainGUI.getContentPane().add(helpButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Exits Program");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setBounds(258, 390, 103, 23);
		mainGUI.getContentPane().add(quitButton);
		
		JButton testButton = new JButton("TEST");
		testButton.setBounds(371, 390, 100, 23);
		mainGUI.getContentPane().add(testButton);
	}
	
	//Creates new About form
	private void aboutMenuItemActionPerfromed(java.awt.event.ActionEvent e) {
		About aboutWindow = new About();
		aboutWindow.setVisible(true);
	}
	//Creates new Help Form
	private void helpMenuItemActionPerformed(java.awt.event.ActionEvent e) {
		Help helpWindow = new Help();
		helpWindow.setVisible(true);
	}
	//Creates chess board form
	private void newGameMenuItemActionPerformed(java.awt.event.ActionEvent e) {
		BoardGUI boardGUI = new BoardGUI();
		boardGUI.setVisible(true);
	}
}
