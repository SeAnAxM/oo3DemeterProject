// StartGame.java

import java.util.Scanner;
public class StartGame {
    public void startLayout1() {
        Layout1 layout1 = new Layout1();
        layout1.start();
        // If player successfully completes Layout 1, proceed to Layout 2
        Layout2 layout2 = new Layout2();
        layout2.start();
        // If player successfully completes Layout 2, proceed to Layout 3
        Layout3 layout3 = new Layout3();
        layout3.start();

        // Player has now completed all layouts
        System.out.println("Congratulations, you've completed all layouts! Here's your time: " + // Show time
                "\nWould you like to play again or return to the main menu? Enter 1 for Play Again or 2 for Main Menu");
        Scanner scanner = new Scanner(System.in);
        int selectedOption = scanner.nextInt();

        if (selectedOption == 1) {
            startLayout1(); // Restart the game
        } else {
            MainMenu.main(new String[0]); // Return to the main menu
        }
    }
}
