package com.example.ticketing_system.domain.model;

public class Event {
    private Long id;
    private String eventName;
    private String eventDate;
    private Venue venue;

    public Event(Long id, String eventName, String eventDate, Venue venue) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
    }

    public Event(String eventName, String eventDate, Venue venue) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public Venue getVenue() { return venue; }
    public void setVenue(Venue venue) { this.venue = venue; }
}
