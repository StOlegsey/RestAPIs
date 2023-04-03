package com.example.MyTestAPIs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Setter
@Getter
public class Event {

    public Event() {
    }

    public Event(Integer event_id, String place_name, String event_name) {
        this.event_id = event_id;
        this.place_name = place_name;
        this.event_name = event_name;
    }

    @Id
    @Column(length = 45)
    private int event_id;

    @Column(length = 45)
    private String place_name;

    @Column(length = 45)
    private String event_name;

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", place_name='" + place_name + '\'' +
                ", event_name='" + event_name + '\'' +
                '}';
    }
}