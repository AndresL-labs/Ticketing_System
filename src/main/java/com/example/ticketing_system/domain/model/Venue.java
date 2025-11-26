package com.example.ticketing_system.domain.model;

public class Venue {
    private Long id;
    private String venueName;
    private String location;
    private int capacity;

    public Venue() {}

    public Venue(Long id, String venueName, String location, int capacity) {
        this.id = id;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
    }

    public Venue (String venueName, String location, int capacity) {
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName;}

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}