import javax.swing.*;

/**
 * The main entry point for the Rock Paper Scissors game.
 * Initializes and displays the game window.
 */
public class RockPaperScissorsRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsFrame().setVisible(true));
    }
}
