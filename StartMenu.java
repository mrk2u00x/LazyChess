/**
 * @author - Michael Robinson-Elmslie
 * @create - date 2022-02-16 
 * @modify - date NA
 * @desc   - StartMenu is the first window the user sees.
 *           It has two text fields: playerOneInput and playerTwoInput.
 *           The values from the text field are assigned to playerOneName and playerTwoName.
 *           playerOneName and playerTwoName are then passed to a new GameView class, 
 *           which ultimately starting a new game the user can play
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StartMenu implements Runnable {
    public void run() {
        // Setting start menu window properties
    	final JFrame startMenuWindowView = new JFrame("Lazy Chess");
        startMenuWindowView.setResizable(false);
        startMenuWindowView.setSize(1260, 720);
        Box components = Box.createVerticalBox();
        startMenuWindowView.add(components);

        // Setting program icon
        try { startMenuWindowView.setIconImage(ImageIO.read(new File("assets\\lazzyChessLogo.jpg"))); }
        catch (Exception e) { System.out.println("file missing: lazzyChessLogo.png"); }

        // Setting game title
        final JPanel titlePanel = new JPanel();
        components.add(titlePanel);
        final JLabel titleLabel = new JLabel("Voice Chess");
        titlePanel.add(titleLabel);

        // setting player 1: Black
        final JPanel playerOne = new JPanel();
        components.add(playerOne, BorderLayout.EAST);
        final JLabel playerOneBlackPiece = new JLabel();
        try {
            playerOneBlackPiece.setIcon(new ImageIcon(ImageIO.read(new File("assets\\black_king.png"))));
            playerOne.add(playerOneBlackPiece);
        } catch (Exception e) {
            System.out.println("file missing: black_king.png");
        }
        // getting name for playerOne from user
        final JTextField playerOneInput = new JTextField("Player_One", 15);
        playerOne.add(playerOneInput);

        // setting player 2: White
        final JPanel playerTwo = new JPanel();
        components.add(playerTwo);
        final JLabel playerTwoWhitePiece = new JLabel();
        try {
            playerTwoWhitePiece.setIcon(new ImageIcon(ImageIO.read(new File("assets\\white_king.png"))));
            playerTwo.add(playerTwoWhitePiece);
        }  catch (Exception e) {
            System.out.println("file missing: white_king.png");
        }
        // getting name for playerTwo from user
        final JTextField playerTwoInput = new JTextField("Player_Two", 15);
        playerTwo.add(playerTwoInput);
        
        // setting start button
        final JButton newGame = new JButton("   New  Game   ");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /**
                 * for the time being, this section is being commented out
                 * as the GameView class hasn't been implmented yet.
                 */

                /*
                String playerOneName = playerOneInput.getText();
                String playerTwoName = playerTwoInput.getText();
                
                // starting new game with playerOne vs playerTwo
                new GameView(playerOneName, playerTwoName);
                */
                startMenuWindowView.dispose();
            }
        });
        
        // setting quit button
        Box buttons = Box.createHorizontalBox();
        final JButton quit = new JButton("      Quit      ");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	startMenuWindowView.dispose();
            }
          });
        
        // setting help button
        final JButton help = new JButton("      Help      ");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(startMenuWindowView, "Input name for player 1\nInput name for player 2\nclick \"New Game\" to start playing.\n", "Help Screen", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // setting test game over button
        final JButton testGameOver = new JButton("      TEST      ");
        testGameOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // starting new game over window -- testing GameOver
                SwingUtilities.invokeLater(new GameOver());
                startMenuWindowView.dispose();
            }
        });
       
        // adding buttons to screen
        buttons.setFocusable(false);
        buttons.add(newGame);
        buttons.add(Box.createHorizontalStrut(50));
        buttons.add(help);
        buttons.add(Box.createHorizontalStrut(50));
        buttons.add(quit);
        buttons.add(Box.createHorizontalStrut(50));
        buttons.add(testGameOver);
        components.add(buttons);
        // seperating buttons from bottom of screen
        Component space = Box.createGlue();
        components.add(space);



        startMenuWindowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startMenuWindowView.setVisible(true);
    }
}