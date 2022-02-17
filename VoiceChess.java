/**
 * @author - Michael Robinson-Elmslie
 * @create - date 2022-02-16
 * @modify - date NA
 * @desc  - VoiceChess class is the entry point into our program. 
 *          This class invokes a new StartMenu class which the user sees
 */

import javax.swing.SwingUtilities;

public class VoiceChess implements Runnable {
    public void run() {
        SwingUtilities.invokeLater(new StartMenu());
    }
    
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new VoiceChess());
    }
}
