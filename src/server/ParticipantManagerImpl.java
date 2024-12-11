package server;

import shared.ParticipantManagerInterface;
import shared.EventManagerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParticipantManagerImpl extends UnicastRemoteObject implements ParticipantManagerInterface {
    private HashMap<Integer, List<String>> participants = new HashMap<>();
    private EventManagerInterface eventManager; // Reference to EventManager

    public ParticipantManagerImpl(EventManagerInterface eventManager) throws RemoteException {
        this.eventManager = eventManager; // Initialize with EventManager instance
    }

    @Override
    public boolean registerParticipant(int eventId, String participantName) throws RemoteException {
        if (!eventManager.eventExists(eventId)) {
            return false; // Event does not exist, registration fails
        }
        // Registration logic here
        return true; // Registration success (for demonstration)
    }

    @Override
    public List<String> getParticipants(int eventId) throws RemoteException {
        return participants.getOrDefault(eventId, new ArrayList<>());
    }

    @Override
    public boolean unregisterParticipant(int eventId, String participantName) throws RemoteException {
        List<String> participantList = participants.get(eventId);
        if (participantList != null) {
            return participantList.remove(participantName);
        }
        return false; // Participant not found
    }
}
