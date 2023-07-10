/*
Classname: CharacterSetup.java

Author: Demeter Group

Last Modified: July 10, 2023; 11:40 PM

Modification:

Class Description: The CharacterSetup class in this Java program is responsible for setting up a new player's account, including taking user input to set their username and select their character's appearance.

 */

public class CharacterSetup {

    public class CharacterSetup {
        private String name;
        private String appearance;

        public void createAccount() {
            Scanner input = new Scanner(System.in);

            System.out.print("Enter your username: ");
            name = input.nextLine();

            appearance = getPlayerAppearance();

            System.out.println("Account created successfully!\nUsername: " + name + "\nAppearance: " + appearance);
        }

        private static String getPlayerAppearance() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select your appearance:");
            System.out.println("1. Swag");
            System.out.println("2. Doctor");
            System.out.println("3. Teacher");
            // Add more appearance options as needed

            int selectedOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            String playerAppearance;
            switch (selectedOption) {
                case 1:
                    playerAppearance = "swag.png";
                    break;
                case 2:
                    playerAppearance = "doctor.png";
                    break;
                case 3:
                    playerAppearance = "teacher.png";
                    break;
                // Assign other appearance filenames as needed
                default:
                    playerAppearance = "default.png";
                    break;
            }

            return playerAppearance;
        }

        // Getter and setter methods for 'name' and 'appearance' if needed
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAppearance() {
            return appearance;
        }

        public void setAppearance(String appearance) {
            this.appearance = appearance;
        }

        // Other methods related to account setup
    }


}
