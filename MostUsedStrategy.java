import java.util.HashMap;
import java.util.Map;

/**
 * Strategy where the computer selects a move that beats the player's most used move.
 */
public class MostUsedStrategy implements Strategy {
    @Override
    public String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount) {
        Map<String, Integer> moveCounts = new HashMap<>();
        moveCounts.put("Rock", rockCount);
        moveCounts.put("Paper", paperCount);
        moveCounts.put("Scissors", scissorsCount);

        // Find the most used move
        String mostUsed = moveCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        return counterMove(mostUsed);
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
