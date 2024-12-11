package server;

import shared.EventManagerInterface;
import shared.ParticipantManagerInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventServer {
    private static final int PORT = 1099; // Default RMI port
    private static final int MAX_THREADS = 10; // Maximum number of threads

    public static void main(String[] args) {
        try {
            // Create an instance of the event manager
            EventManagerImpl eventManager = new EventManagerImpl(); // Added line for EventManager
            EventManagerInterface eventStub = eventManager; // Stub for EventManager

            // Create the instance of the participant manager and pass the EventManager instance
            ParticipantManagerImpl participantManager = new ParticipantManagerImpl(eventStub); // Updated line
            ParticipantManagerInterface participantStub = participantManager; // Stub for ParticipantManager

            // Create and register the RMI registry
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("ParticipantManager", participantStub);
            registry.bind("EventManager", eventStub); // Bind EventManager
            System.out.println("Server ready to accept requests...");

            // Create a thread pool to manage other potential tasks
            ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

            // Example of using the thread pool (optional)
            executorService.submit(() -> System.out.println("Multithreaded server active."));
        } catch (Exception e) {
            System.err.println("Error in server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
