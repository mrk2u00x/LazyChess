/**
 * @author - Michael
 * @create - date 2022-03-01
 * @modify - date 2022-03-03
 * @desc   - game gui
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BoardComponents.Board;
import Information.Tag;

public class GameGUI {
    private JFrame gameGUI;
    private Board boardGUI;

    public GameGUI() { 
        initializeGameGUI();
    }

    private void initializeGameGUI() {
        createFrame();
        AddButtons();
        creatBoardGUI();
        setSize();
        this.gameGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createFrame() {
        gameGUI = new JFrame("Lazzy Chess");
        try {
            gameGUI.setIconImage(ImageIO.read(new File(Tag.WHITE_KING)));
        } catch  (Exception e) { 
            System.out.println("file missing: " + Tag.WHITE_KING);
        }
        this.gameGUI.setLayout(new BorderLayout(0, 20));
    }

    private void creatBoardGUI() {
        this.boardGUI = new Board(this);
        this.gameGUI.add(boardGUI);
    }

    private void setSize() {
        this.gameGUI.setSize(gameGUI.getPreferredSize());
        this.gameGUI.setMinimumSize(gameGUI.getPreferredSize());
        this.gameGUI.setVisible(true);
        this.gameGUI.setResizable(false);
    }

    private JPanel AddButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3, 10, 10));

        final JButton quite = new JButton("Quit");
        quite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                quitItemActionPerfromed(e);
            }
        });

        final JButton newGame = new JButton("Main Menu");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenuItemActionPerfromed(e);
            }
        });

        buttons.add(quite);
        buttons.add(newGame);
        gameGUI.add(buttons, BorderLayout.NORTH);
        return buttons;
    }
    private void quitItemActionPerfromed(java.awt.event.ActionEvent e) {
        int quit = JOptionPane.showConfirmDialog(gameGUI, "Are you sure you want to quite?", "Quite", JOptionPane.OK_CANCEL_OPTION);
        if(quit == JOptionPane.OK_OPTION) {
            gameGUI.dispose();
        }
    }

    private void mainMenuItemActionPerfromed(java.awt.event.ActionEvent e) {
        int quit = JOptionPane.showConfirmDialog(gameGUI,
        "Are you sure you want to go to main menu?" + 
        "\nThis game session has not been saved.", "Main Menu", JOptionPane.OK_CANCEL_OPTION);
        if(quit == JOptionPane.OK_OPTION) {
            SwingUtilities.invokeLater(new MainGUI());
            gameGUI.dispose();
        }
    }
}