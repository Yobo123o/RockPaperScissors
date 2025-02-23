import java.util.Random;

/**
 * Strategy where the computer selects a random move.
 */
public class RandomStrategy implements Strategy {
    private static final String[] moves = {"Rock", "Paper", "Scissors"};
    private Random random = new Random();

    @Override
    public String determineMove(String lastPlayerMove, int rockCount, int paperCount, int scissorsCount) {
        return moves[random.nextInt(3)];
    }
}
