package client;

import shared.EventManagerInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class EventManagerClient {
    private final EventManagerInterface eventManager;

    public EventManagerClient() throws Exception {
        eventManager = (EventManagerInterface) Naming.lookup("rmi://localhost/EventManager");
    }

    public void createEvent() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date (e.g., 2024-12-15): ");
        String date = scanner.nextLine();
        System.out.print("Enter maximum participants: ");
        int maxParticipants = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int eventId = eventManager.createEvent(name, date, maxParticipants);
        System.out.println("Event created successfully! ID: " + eventId);
    }

    public void cancelEvent() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event ID to cancel: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = eventManager.cancelEvent(eventId);
        if (success) {
            System.out.println("Event canceled successfully!");
        } else {
            System.out.println("Event cancellation failed. Check the event ID.");
        }
    }

    public void menu() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Event Manager Menu =====");
            System.out.println("1. Create Event");
            System.out.println("2. Cancel Event");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createEvent();
                case 2 -> cancelEvent();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            EventManagerClient client = new EventManagerClient();
            client.menu();
        } catch (Exception e) {
            System.err.println("Error in EventManagerClient: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
