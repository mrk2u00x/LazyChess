import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardGUI extends JFrame
{

	private JPanel contentPane;
	private Logic gameBoard;
	private Point selectedStart;
	private Point selecctedEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		new BoardGUI();
		//EventQueue.invokeLater(() -> run());	
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
				int baseX = 150 * x + 15;
				int baseY = 150 * y + 50;
				
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
		setBackground(Color.gray);
		setResizable(false);
		setSize(640, 750);
		setTitle("Lazy Chess Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/assets/white_king.png")));
		setLocationRelativeTo(null);
		setLayout(null);
		
/*
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(getLayout());
		contentPane.setLayout(null);
*/
		gameBoard = new Logic(name1, name2);

		initializeButtons();
		//Image a = Toolkit.getDefaultToolkit().getImage(BoardGUI.class.getResource("assets/black_bishop.png");
		//gridButtons[0][0].setIcon(new ImageIcon(a));
		
		setVisible(true);
	}

	public void selectedPoint(Point p)
	{
		
		System.out.println("setected point : " + p);
		//if(gameBoard.legalSelect(p))
	}

	public void initializeButtons()
	{
			
		JButton[][] gridButtons = new JButton[8][8];
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
			{	
				final int pX = x;
				final int pY = 8 - y;

				gridButtons[x][y] = new JButton();
				gridButtons[x][y].setBounds(75 * x + 10, 75 * y + 30, 75, 75);
				gridButtons[x][y].addActionListener(e -> selectedPoint(new Point(pX, pY)));
				gridButtons[x][y].setOpaque(false);
				gridButtons[x][y].setContentAreaFilled(false);
				gridButtons[x][y].setBorderPainted(false);
				
				getContentPane().add(gridButtons[x][y]);
			}
	}
}
