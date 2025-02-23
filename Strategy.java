/**
 * Interface for different computer move strategies.
 */
public interface Strategy {
    /**
     * Determines the computer's move based on the strategy.
     *
     * @param lastPlayerMove The player's last move (null if first round)
     * @param rockCount Number of times the player has chosen Rock
     * @param paperCount Number of times the player has chosen Paper
     * @param scissorsCount Number of times the player has chosen Scissors
     * @return The computer's move as a String ("Rock", "Paper", or "Scissors")
     */
    String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount);
}
