import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * This class represents the main GUI for the Rock Paper Scissors game.
 * It implements multiple AI strategies using the Strategy Design Pattern.
 */
public class RockPaperScissorsFrame extends JFrame {
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private int rockCount = 0;
    private int paperCount = 0;
    private int scissorsCount = 0;
    private String lastPlayerMove = null;

    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private JTextArea resultArea;

    private Random random = new Random();

    /**
     * Constructor that sets up the game UI.
     */
    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        // Create game title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Rock Paper Scissors");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Create game buttons panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridy = 0;

        JButton rockButton = createImageButton("rock.png");
        JButton paperButton = createImageButton("paper.png");
        JButton scissorsButton = createImageButton("scissors.png");

        gbc.gridx = 0;
        buttonPanel.add(rockButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(paperButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(scissorsButton, gbc);

        // Quit button
        gbc.gridy = 1;
        gbc.gridx = 1;
        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(120, 40));
        quitButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(quitButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Create game stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));
        statsPanel.setLayout(new GridLayout(6, 1, 5, 5));
        statsPanel.setPreferredSize(new Dimension(180, 150));

        JLabel playerWinsText = new JLabel("Player Wins:", SwingConstants.CENTER);
        playerWinsLabel = new JLabel("0", SwingConstants.CENTER);
        JLabel computerWinsText = new JLabel("Computer Wins:", SwingConstants.CENTER);
        computerWinsLabel = new JLabel("0", SwingConstants.CENTER);
        JLabel tiesText = new JLabel("Ties:", SwingConstants.CENTER);
        tiesLabel = new JLabel("0", SwingConstants.CENTER);

        statsPanel.add(playerWinsText);
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsText);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesText);
        statsPanel.add(tiesLabel);

        add(statsPanel, BorderLayout.EAST);

        // Create text area for results
        resultArea = new JTextArea(8, 30);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);

        // Add action listeners
        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * Creates a JButton with an ImageIcon scaled to fit the button.
     *
     * @param imagePath The path of the image file
     * @return JButton with an image icon
     */
    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(130, 130));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        return button;
    }

    /**
     * Plays a round using a randomly selected strategy.
     *
     * @param playerChoice The move chosen by the player
     */
    private void playRound(String playerChoice) {
        if (playerChoice.equals("Rock")) rockCount++;
        if (playerChoice.equals("Paper")) paperCount++;
        if (playerChoice.equals("Scissors")) scissorsCount++;

        Strategy strategy = selectRandomStrategy();
        String computerChoice = strategy.determineMove(lastPlayerMove, rockCount, paperCount, scissorsCount);
        lastPlayerMove = playerChoice;

        String result = determineWinner(playerChoice, computerChoice);
        resultArea.append(result + " [" + strategy.getClass().getSimpleName().replace("Strategy", "") + "]\n");

        playerWinsLabel.setText(String.valueOf(playerWins));
        computerWinsLabel.setText(String.valueOf(computerWins));
        tiesLabel.setText(String.valueOf(ties));
    }

    /**
     * Selects a random strategy for the computer.
     *
     * @return A Strategy object representing the chosen AI move logic
     */
    private Strategy selectRandomStrategy() {
        Strategy[] strategies = {
                new LeastUsedStrategy(),
                new MostUsedStrategy(),
                new LastUsedStrategy(),
                new RandomStrategy(),
                new CheatStrategy()
        };
        return strategies[new Random().nextInt(strategies.length)];
    }

    /**
     * Determines the winner based on game rules.
     *
     * @param playerChoice  The player's move
     * @param computerChoice The computer's move
     * @return Formatted result message indicating the winner
     */
    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            ties++;
            return playerChoice + " matches " + computerChoice + " (Tie)";
        }

        String result;
        if (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) {
            result = "Rock breaks Scissors (Player wins)";
            playerWins++;
        } else if (playerChoice.equals("Paper") && computerChoice.equals("Rock")) {
            result = "Paper covers Rock (Player wins)";
            playerWins++;
        } else if (playerChoice.equals("Scissors") && computerChoice.equals("Paper")) {
            result = "Scissors cuts Paper (Player wins)";
            playerWins++;
        } else {
            if (computerChoice.equals("Rock") && playerChoice.equals("Scissors")) {
                result = "Rock breaks Scissors (Computer wins)";
            } else if (computerChoice.equals("Paper") && playerChoice.equals("Rock")) {
                result = "Paper covers Rock (Computer wins)";
            } else {
                result = "Scissors cuts Paper (Computer wins)";
            }
            computerWins++;
        }
        return result;
    }
}
