package client;

import shared.ParticipantManagerInterface;

import java.rmi.Naming;
import java.util.Scanner;

public class EventParticipantClient {
    public static void main(String[] args) {
        try {
            // Connect to the ParticipantManager on the server
            ParticipantManagerInterface participantManager =
                    (ParticipantManagerInterface) Naming.lookup("rmi://localhost/ParticipantManager");

            // Scanner for user input
            Scanner scanner = new Scanner(System.in);

            System.out.println("===== Welcome to the Event Management System =====");

            // Request information from the user
            System.out.print("Enter the event ID: ");
            int eventId = scanner.nextInt(); // Read the event ID

            scanner.nextLine(); // Consume the newline remaining after nextInt()

            System.out.print("Enter your name: ");
            String participantName = scanner.nextLine(); // Read the participant's name

            // Call the registration method on the server
            boolean success = participantManager.registerParticipant(eventId, participantName);

            // Display the result
            if (success) {
                System.out.println("Successfully registered for event ID: " + eventId);
            } else {
                System.out.println("Registration failed. Check the event ID or availability.");
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error connecting to RMI server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
