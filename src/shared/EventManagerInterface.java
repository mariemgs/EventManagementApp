package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EventManagerInterface extends Remote {
    int createEvent(String eventName, String date, int maxParticipants) throws RemoteException;
    boolean cancelEvent(int eventId) throws RemoteException;
    boolean eventExists(int eventId) throws RemoteException; // Add this method
}
