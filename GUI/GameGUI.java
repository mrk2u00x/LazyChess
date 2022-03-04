package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
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
        addButtons();
        creatBoardGUI();
        setSize();
        this.gameGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createFrame() {
        gameGUI = new JFrame("Lazzy Chess");
        gameGUI.setIconImage(new ImageIcon(Tag.LAZY_ICON).getImage());
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

    private void addButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3, 10, 10));

        final JButton quite = new JButton("Quit");
        final JButton mainMenu = new JButton("Main Menu");
        
        quite.setBackground(new Color(251, 252, 247));
        mainMenu.setBackground(new Color(251, 252, 247));
        
        quite.addActionListener((e) -> quitItemActionPerformed(e));
        mainMenu.addActionListener((e) ->  mainMenuItemActionPerformed(e));
        
        buttons.add(quite);
        buttons.add(mainMenu);
        gameGUI.add(buttons, BorderLayout.NORTH);
    }
    
    private void quitItemActionPerformed(ActionEvent e) {
        int quit = JOptionPane.showConfirmDialog(gameGUI, "Are you sure you want to quite?", "Quite", JOptionPane.OK_CANCEL_OPTION);
        if(quit == JOptionPane.OK_OPTION) gameGUI.dispose();
    }

    private void mainMenuItemActionPerformed(ActionEvent e) {
        int quit = JOptionPane.showConfirmDialog(gameGUI,
        "Are you sure you want to go to main menu?" + 
        "\nThis game session has not been saved.",
        "Main Menu", JOptionPane.OK_CANCEL_OPTION);
        if(quit == JOptionPane.OK_OPTION) {
            SwingUtilities.invokeLater(new MainGUI());
            gameGUI.dispose();
        }
    }
}