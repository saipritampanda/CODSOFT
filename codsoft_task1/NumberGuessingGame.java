import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean assure = true;

        do {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("I have generated a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            int attempts = 0;
            int userGuess;
            

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempt(s)!");
                    score += maxAttempts - attempts + 1;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low. Try again!");
                } else {
                    System.out.println("Your guess is too high. Try again!");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("You have used all your attempts. The correct number was: " + targetNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            
            if (playAgain.equals("yes")) {
                assure = true;
            }
            else{
                assure = false;
            }
            System.out.println();
        } while (assure);

        System.out.println("Your final score is: " + score);
        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
}
