import java.util.Random;

/**
 * Strategy where the computer has a 10% chance to cheat and always pick the winning move.
 */
public class CheatStrategy implements Strategy {
    private Random random = new Random();

    @Override
    public String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount) {
        if (random.nextDouble() < 0.10) { // 10% chance to cheat
            return counterMove(lastPlayerMove);
        }
        return new RandomStrategy().determineMove(lastPlayerMove, rockCount, paperCount, scissorsCount);
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
