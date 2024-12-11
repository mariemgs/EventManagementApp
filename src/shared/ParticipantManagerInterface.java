package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ParticipantManagerInterface extends Remote {
    boolean registerParticipant(int eventId, String participantName) throws RemoteException;
    boolean unregisterParticipant(int eventId, String participantName) throws RemoteException;
    List<String> getParticipants(int eventId) throws RemoteException;
}
