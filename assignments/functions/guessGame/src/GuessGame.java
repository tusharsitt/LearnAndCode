import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private GameRange range;
    private int target;
    private int guessCountTaken = 1;

    public void start() {
        setupGame();
        playLoop();
    }

    private void setupGame() {
        while (true) {
            try {
                int min = promptInt("Enter lower bound: ");
                int max = promptInt("Enter upper bound: ");
                this.range = new GameRange(min, max);
                this.target = generateTarget();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid bounds: " + e.getMessage());
            }
        }
    }

    private void playLoop() {
        boolean hasWon = false;
        while (!hasWon) {
            int guess = promptInt("Enter your guess: ");
            hasWon = processGuess(guess);
        }
    }

    private boolean processGuess(int guess) {
        if (guess > target) {
            System.out.println("Too high!");
            guessCountTaken++;
            return false;
        } else if (guess < target) {
            System.out.println("Too low!");
            guessCountTaken++;
            return false;
        } else {
            System.out.println("Correct! You guessed " +  target + " in " + guessCountTaken + " guesses.");
            return true;
        }
    }

    private int generateTarget() {
        return random.nextInt(range.upper() - range.lower() + 1) + range.lower();
    }

    private int promptInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}