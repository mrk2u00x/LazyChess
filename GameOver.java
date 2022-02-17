/**
 * @author - Michael Robinson-Elmslie
 * @create - date 2022-02-16
 * @modify - date NA
 * @desc  - GameOver class is the end point of the program. 
 *          From this windows, the user can either go to 
 *          the start menu or quite program. 
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameOver implements Runnable {
    public void run() {
        // Setting game over window properties
    	final JFrame gameOverWindowView = new JFrame("Lazy Chess");
    	gameOverWindowView.setResizable(false);
    	gameOverWindowView.setSize(1260, 720);
        Box components = Box.createVerticalBox();
        gameOverWindowView.add(components);
    	
        // Setting program icon
        try { gameOverWindowView.setIconImage(ImageIO.read(new File("assets\\lazzyChessLogo.jpg"))); }
        catch (Exception e) { System.out.println("file missing: lazzyChessLogo.png"); }
        
        // Setting game title
        final JPanel titlePanel = new JPanel();
        components.add(titlePanel);
        final JLabel titleLabel = new JLabel("Game Over ;(");
        titlePanel.add(titleLabel);
        
        // setting start menu button
        final JButton startMenu = new JButton("   Start Menu   ");
        startMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new StartMenu());
                gameOverWindowView.dispose();
            }
        });
        
        // setting quit button
        Box buttons = Box.createHorizontalBox();
        final JButton quit = new JButton("      Quit      ");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameOverWindowView.dispose();
            }
        });
        
       // adding buttons to screen
        buttons.setFocusable(false);
        buttons.add(startMenu);
        buttons.add(Box.createHorizontalStrut(50));
        buttons.add(quit);
        components.add(buttons);
        Component space = Box.createGlue();
        components.add(space);
        
        gameOverWindowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverWindowView.setVisible(true);
    }
}