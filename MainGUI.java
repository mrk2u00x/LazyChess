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
import java.awt.Color;
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
		initializeFrame();
		initializeMenu();
		initializePlayer();
		initializeButton();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeFrame() {
		mainGUI = new JFrame();
		mainGUI.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/assets/white_king.png")));
		mainGUI.setTitle("Lazy Chess");
		mainGUI.setBounds(100, 100, 513, 507);
		mainGUI.setResizable(false);
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Lazy Chess");
		title.setBounds(203, 10, 82, 17);
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainGUI.getContentPane().add(title);
	}

	private void initializeMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		mainGUI.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem menuNewGame = new JMenuItem("New Game");
		fileMenu.add(menuNewGame);

		JMenuItem menuSave = new JMenuItem("Save");
		fileMenu.add(menuSave);

		JMenuItem menuQuit = new JMenuItem("Quit");
		menuQuit.addActionListener(e -> System.exit(0));
		fileMenu.add(menuQuit);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem menuHelp = new JMenuItem("Help");
		menuHelp.addActionListener(e -> helpMenuItemActionPerformed(e));
		helpMenu.add(menuHelp);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(e -> aboutMenuItemActionPerfromed(e));
		helpMenu.add(menuAbout);
	}

	private void initializePlayer()
	{
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
	}
	
	private void initializeButton()
	{
		//New game button
		JButton newGameButton = new JButton("New Game");
		newGameButton.setToolTipText("Starts New Game");
		newGameButton.addActionListener(e -> newGameMenuItemActionPerformed(e));
		newGameButton.setBounds(33, 390, 103, 23);
		mainGUI.getContentPane().add(newGameButton);
		newGameButton.setBackground(Color.WHITE);
		
		//Help button
		JButton helpButton = new JButton("Help");
		helpButton.setToolTipText("Help");
		helpButton.addActionListener(e -> helpMenuItemActionPerformed(e));
		helpButton.setBounds(146, 390, 102, 23);
		mainGUI.getContentPane().add(helpButton);
		helpButton.setBackground(Color.WHITE);
		
		//Quit Button
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Exits Program");
		quitButton.addActionListener(e -> System.exit(0));
		quitButton.setBounds(258, 390, 103, 23);
		mainGUI.getContentPane().add(quitButton);
		quitButton.setBackground(Color.WHITE);
		
		JButton testButton = new JButton("TEST");
		testButton.setBounds(371, 390, 100, 23);
		mainGUI.getContentPane().add(testButton);
		testButton.setBackground(Color.WHITE);
	}

	//Creates new About form
	private void aboutMenuItemActionPerfromed(ActionEvent e) {
		About aboutWindow = new About();
		aboutWindow.setVisible(true);
	}
	
	//Creates new Help Form
	private void helpMenuItemActionPerformed(ActionEvent e) {
		Help helpWindow = new Help();
		helpWindow.setVisible(true);
	}
	
	//Creates chess board form
	private void newGameMenuItemActionPerformed(ActionEvent e) {
		//Get names from the field
		String p1 = whitePlayerName.getText();
		p1 = p1.isEmpty() ? "P1" : p1;

		String p2 = blackPlayerName.getText();
		p2 = p2.isEmpty() ? "P2" : p2;

		BoardGUI boardGUI = new BoardGUI(p1, p2);
		boardGUI.setVisible(true);
		mainGUI.dispose();
	}
}
