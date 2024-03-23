import javax.swing.JOptionPane;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        int min = 1;
        int max = 100;
        int rounds = 3;
        int totalScore = 0;

        JOptionPane.showMessageDialog(null, "Welcome to the Number Guessing Game!\nYou have " + rounds + " rounds to play.");

        for (int round = 1; round <= rounds; round++) {
            int randomNumber = generateRandomNumber(min, max);
            int attempts = 0;
            int score = 100; 
            JOptionPane.showMessageDialog(null, "Round " + round + "\nYou have picked a number between " + min + " and " + max + ".\nTry to guess it.");

            while (true) {
                String userInput = JOptionPane.showInputDialog("Enter your guess (between " + min + " and " + max + "):");
                int guess = Integer.parseInt(userInput);
                attempts++;

                if (guess == randomNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number in " + attempts + " attempts.\nYour score for this round: " + score);
                    totalScore += score;
                    break;
                } else if (guess < randomNumber) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                }

                score -= 10;
                if (score <= 0) {
                    JOptionPane.showMessageDialog(null, "You've run out of attempts. The correct number was " + randomNumber + ".\nYour score for this round: 0");
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over!\nYour total score: " + totalScore);
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

