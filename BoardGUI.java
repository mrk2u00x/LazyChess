import Information.Layout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.MouseInputListener;

public class BoardGUI extends JFrame implements MouseInputListener
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
		int size = Layout.GRID_SIZE;
		
		for(int y = 0; y < 4; y++)
			for(int x = 0; x < 4; x++)
			{
				int baseX = 150 * x + 15;
				int baseY = 150 * y + 50;
				
				g.setColor(Color.WHITE);
				g.fillRect(baseX, baseY, size, size);
				g.fillRect(baseX + size, baseY + size, size, size);

				g.setColor(Color.BLACK);
				g.fillRect(baseX + size, baseY, size, size);
				g.fillRect(baseX, baseY + size, size, size);
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
		setTitle("Voice Chess Game");
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
		
		setVisible(true);
	}

	public void selectedPoint(Point p)
	{
		
		System.out.println("setected point : " + p);
		//if(gameBoard.legalSelect(p))
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
}
