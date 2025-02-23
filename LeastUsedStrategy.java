import java.util.HashMap;
import java.util.Map;

/**
 * Strategy where the computer selects a move that beats the player's least used move.
 */
public class LeastUsedStrategy implements Strategy {
    @Override
    public String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount) {
        // Track which move was used the least
        Map<String, Integer> moveCounts = new HashMap<>();
        moveCounts.put("Rock", rockCount);
        moveCounts.put("Paper", paperCount);
        moveCounts.put("Scissors", scissorsCount);

        // Find the least used move
        String leastUsed = moveCounts.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get().getKey();

        return counterMove(leastUsed);
    }

    private String counterMove(String move) {
        switch (move) {
            case "Rock": return "Paper";  // Paper beats Rock
            case "Paper": return "Scissors";  // Scissors beats Paper
            case "Scissors": return "Rock";  // Rock beats Scissors
            default: return "Rock";
        }
    }
}
