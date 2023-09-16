import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        GuessGame();
    }

    public static void GuessGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minNum = 1;
        int maxNum = 100;
        int attempts = 0;
        int secretNumber = random.nextInt(maxNum - minNum + 1) + minNum;
        
        System.out.println("-----> Welcome <-----" );
        System.out.println("To The Guess the Number Game!");
        System.out.println("Think of a number between " + minNum + " and " + maxNum + ".");
        
        while (true) {
            System.out.print("Enter your guess number: ");
            int userGuess;
            try {
                userGuess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid number. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }
            
            attempts++;
            
            if (userGuess < minNum || userGuess > maxNum) {
                System.out.println("Please guess a number between " + minNum + " and " + maxNum + ".");
            } else if (userGuess < secretNumber) {
                System.out.println("Too low. Try again.");
            } else if (userGuess > secretNumber) {
                System.out.println("Too high. Try again.");
            } else {
                System.out.println("Congratulations!.. You guessed the number " + secretNumber + " in " + attempts + " attempts.");
                break;
            }
        }
        
        scanner.close();
    }
}
