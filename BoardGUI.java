import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BoardGUI extends JFrame {

	private JPanel contentPane;
	private Logic gameBoard;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> run());	
	}
	
	public static void run()
	{
		try
		{
			BoardGUI frame = new BoardGUI();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    public void paint(Graphics g)
    {
       paintBoard(g);
    }

	public void paintBoard(Graphics g)
	{
		for(int y = 0; y < 4; y++)
		for(int x = 0; x < 4; x++)
		{
			int baseX = 150 * x + 8;
			int baseY = 150 * y + 30;
			
			g.setColor(Color.WHITE);
			g.fillRect(baseX, baseY, 75, 75);
			g.fillRect(baseX + 75, baseY + 75, 75, 75);

			g.setColor(Color.BLACK);
			g.fillRect(baseX + 75, baseY, 75, 75);
			g.fillRect(baseX, baseY + 75, 75, 75);
		}	
	}

	
	public BoardGUI()
	{
		this("P1", "P2");
	}
	
	
	/**
	 * Create the frame.
	 */
	public BoardGUI(String name1, String name2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.pink);
		setResizable(false);
		setSize(616, 638);
		setTitle("Lazy Chess Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/assets/white_king.png")));
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(getLayout());
		contentPane.setLayout(null);


		gameBoard = new Logic(name1, name2);
		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon(BoardGUI.class.getResource("/assets/white_castle.png")));
//		lblNewLabel.setBounds(10, 540, 118, 110);
//		contentPane.add(lblNewLabel);
	}
}
