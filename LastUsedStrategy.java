/**
 * Strategy where the computer selects the move that beats the player's last move.
 */
public class LastUsedStrategy implements Strategy {
    @Override
    public String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount) {
        if (lastPlayerMove == null) return "Rock"; // Default first move
        return counterMove(lastPlayerMove);
    }

    /**
     * Returns the move that beats the given move.
     *
     * @param move The player's move
     * @return The move that beats the player's move
     */
    private String counterMove(String move) {
        switch (move) {
            case "Rock": return "Paper";
            case "Paper": return "Scissors";
            case "Scissors": return "Rock";
            default: return "Rock";
        }
    }
}
