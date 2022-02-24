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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public void paint(Graphics graphicInterface)
    {
    
    	graphicInterface.fillRect(50, 50, 600, 600);
        for(int x=50; x<=600; x+=150)
        {
        
            for(int y=50; y<=600; y+=150)
            {
            	graphicInterface.clearRect(x, y, 75, 75);
            }
        }
        
    
         for(int x=125; x<=600; x+=150)
        {
        
            for(int y=125; y<=600; y+=150)
            {
            	graphicInterface.clearRect(x, y, 75, 75);
            }
        }
        
    }

	/**
	 * Create the frame.
	 */
	public BoardGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setTitle("Lazy Chess Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/assets/white_king.png")));
		setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(getLayout());
		contentPane.setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon(BoardGUI.class.getResource("/assets/white_castle.png")));
//		lblNewLabel.setBounds(10, 540, 118, 110);
//		contentPane.add(lblNewLabel);
	}
}
