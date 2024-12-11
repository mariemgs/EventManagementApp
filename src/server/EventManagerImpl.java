package server;

import shared.Event;
import shared.EventManagerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class EventManagerImpl extends UnicastRemoteObject implements EventManagerInterface {
    private HashMap<Integer, Event> events = new HashMap<>();
    private int eventIdCounter = 0;

    public EventManagerImpl() throws RemoteException {}

    @Override
    public int createEvent(String eventName, String date, int maxParticipants) throws RemoteException {
        int eventId = ++eventIdCounter;
        events.put(eventId, new Event(eventId, eventName, date, maxParticipants));
        return eventId;
    }

    @Override
    public boolean cancelEvent(int eventId) throws RemoteException {
        return events.remove(eventId) != null;
    }

    // New method to check if an event exists
    @Override
    public boolean eventExists(int eventId) throws RemoteException {
        return events.containsKey(eventId);
    }
}
