/* Classname: MainMenu.java

Author: Demeter Group

Last Modified: July 10, 2023; 11:40 PM

Modification:

Class Description: The `MainMenu` class is the main control hub for a text-based game "Chasing Change: The Escape Room for World Changers", allowing players to create a character, choose to start the game, view leaderboards, or quit.

 */

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        CharacterSetup setup = new CharacterSetup();
        setup.createAccount();

        System.out.println("Welcome, " + characterSetup.getName() + "! Let's start the game.");

        // Based on user input or game logic, navigate to different screens
        int selectedOption = getUserInput();
        switch (selectedOption) {
            case 1:
                StartGame startgame = new StartGame();
                startgame.displayStartGame();
                break;

            case 2:
                Leaderboards leaderboards = new Leaderboards();
                leaderboards.displayLeaderboards();
                break;
            case 3:
                Quit quit = Quit();
                quit.displayQuit();
                break;
            default:
                System.out.println("Invalid option selected. Please try again.");
                break;
        }

        // Additional game logic and flow as needed

        System.out.println("Thanks for playing Chasing Change: The Escape Room for World Changers!");
    }

}

    private static void displayMainMenu() {
        System.out.println("=== Chasing Change: The Escape Room for World Changers ===");
        System.out.println("1. Start Game ");
        System.out.println("2. View Leaderboards/Achievements");
        System.out.println("3. Quit ");
        System.out.println("=======================================================");
        System.out.print("Enter your choice: ");
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int selectedOption = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        return selectedOption;
    }
}
