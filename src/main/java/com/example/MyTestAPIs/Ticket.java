package com.example.MyTestAPIs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Setter
@Getter
public class Ticket {

    public Ticket() {
    }

    public Ticket(Integer t_number, Integer event_id, Integer user_id) {
        this.t_number = t_number;
        this.t_file = t_number.toString()+".zip";
        this.event_id = event_id;
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 45)
    private Integer t_number;

    @Column(length = 45)
    private Integer event_id;
    @Column(length = 45)
    private Integer user_id;
    @Column(length = 45)
    private String t_file;


}