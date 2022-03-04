/**
 * @author - Michael
 * @create - date 2022-03-01
 * @modify - date 2022-03-03
 * @desc   - main menu
 */

package GUI;

import java.awt.BorderLayout;
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

import Information.Tag;

public class MainGUI implements Runnable {
    private static final int VERTICAL_SPACE = 50;
    private static final int COLUMN_SPACE = 10;
    private JFrame mainGUI;
    private JPanel blackPlayerPanel;
    private JPanel whitePlayerPanel;
    private JTextField blackPlayerTextField;
    private JTextField whitePlayerTextField;
    private Box mainGUIComponents;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainGUI());
    }
    
    public void run() {
        initializeMainMenu();
        mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGUI.setVisible(true);
    }

    private void initializeMainMenu() {
        createFrame();;
        createBoxComponents();
        addGameTitle();
        addPlayerFields();
        addPlayerTextField();
        addButtons();
    }

    private void createFrame() {
        // creating new frame
        mainGUI = new JFrame(Tag.TITLE);
        // set icon
        try {
            mainGUI.setIconImage(ImageIO.read(new File(Tag.LAZY_ICON)));
        } catch  (Exception e) { 
            System.err.println("file missing: " + Tag.LAZY_ICON); 
        }
        // set size
        mainGUI.setSize(Tag.IMAGE_WIDTH * 8, Tag.IMAGE_HEIGHT * 8);
        mainGUI.setResizable(false);
    }

    private void createBoxComponents() {
        // creating vertical component to place items
        mainGUIComponents = Box.createVerticalBox();
        mainGUI.add(mainGUIComponents);
    }

    private void addGameTitle() {
        final JLabel gameTitlLabel = new JLabel(Tag.TITLE);
        mainGUIComponents.add(Box.createVerticalStrut(VERTICAL_SPACE), BorderLayout.NORTH);
        mainGUIComponents.add(gameTitlLabel);
        mainGUIComponents.add(Box.createVerticalStrut(VERTICAL_SPACE));
    }

    private void addPlayerFields() {
        addBlackPlayerFields();
        addWhitePlayerFields();
    }

    private void addBlackPlayerFields() {
        // create new panel for player one
        blackPlayerPanel = new JPanel();
        mainGUIComponents.add(blackPlayerPanel, BorderLayout.EAST);
        // set icon
        try {
           final JLabel blackPieceIcon = new JLabel();
           blackPieceIcon.setIcon(new ImageIcon(ImageIO.read(new File(Tag.BLACK_KING))));
           blackPlayerPanel.add(blackPieceIcon);
        } catch (Exception e) {
            System.err.println("file missing: " + Tag.BLACK_KING);
        }
    }

    private void addWhitePlayerFields() {
        // create new panel for player two
        whitePlayerPanel = new JPanel();
        mainGUIComponents.add(Box.createGlue());
        mainGUIComponents.add(whitePlayerPanel);
        // set icon
        try {
            final JLabel whitePieceIcon = new JLabel();
            whitePieceIcon.setIcon(new ImageIcon(ImageIO.read(new File(Tag.WHITE_KING))));
            whitePlayerPanel.add(whitePieceIcon);
         } catch (Exception e) {
             System.err.println("file missing: " + Tag.WHITE_KING);
         }
         
    }

    private void addPlayerTextField() {
        blackPlayerTextField = new JTextField();
        whitePlayerTextField = new JTextField();
        blackPlayerPanel.add(blackPlayerTextField);
        whitePlayerPanel.add(whitePlayerTextField);
        blackPlayerTextField.setToolTipText("Enter Player 2 Name Here");
        whitePlayerTextField.setToolTipText("Enter Player 1 Name Here");
        blackPlayerTextField.setColumns(COLUMN_SPACE);
        whitePlayerTextField.setColumns(COLUMN_SPACE);
    }

    private void addButtons() {
        Box buttonBox = Box.createHorizontalBox();
        // new game/ play
        final JButton newGame = new JButton("Play");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGameItemActionPerfromed(e);
            }
        });
        buttonBox.add(newGame);
        buttonBox.add(Box.createHorizontalStrut(COLUMN_SPACE));
        // help
        final JButton help = new JButton("Help");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpItemActionPerfromed(e);
            }
        });
        buttonBox.add(help);
        buttonBox.add(Box.createHorizontalStrut(COLUMN_SPACE));
        // quit
        final JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitItemActionPerfromed(e);
            }
        });
        buttonBox.add(quit);
        // add buttons to frame
        mainGUIComponents.add(buttonBox);
        mainGUIComponents.add(Box.createGlue());
        mainGUIComponents.add(Box.createGlue());
    }

    private void newGameItemActionPerfromed(java.awt.event.ActionEvent e) {
        new GameGUI();
        mainGUI.dispose();
    }

    private void helpItemActionPerfromed(java.awt.event.ActionEvent e) {
        JOptionPane.showMessageDialog(mainGUI,
        "Input name for Player 1\n" +
        "Input name for player 2\n" +
        "Click new ''play'' to start playing!",
        "Help Menu", JOptionPane.INFORMATION_MESSAGE);
    }

    private void quitItemActionPerfromed(java.awt.event.ActionEvent e) {
        int quit = JOptionPane.showConfirmDialog(mainGUI, "Are you sure you want to quite?", "Quite", JOptionPane.OK_CANCEL_OPTION);
        if(quit == JOptionPane.OK_OPTION) {
            mainGUI.dispose();
        }
    }
}