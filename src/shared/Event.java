package shared;

import java.io.Serializable;

public class Event implements Serializable {
    private int id;
    private String name;
    private String date;
    private int maxParticipants;

    public Event(int id, String name, String date, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.maxParticipants = maxParticipants;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }


    public int getMaxParticipants() {
        return maxParticipants;
    }

    // Override toString for better readability
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", maxParticipants=" + maxParticipants +
                '}';
    }
}
