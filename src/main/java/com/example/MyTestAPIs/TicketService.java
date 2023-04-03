package com.example.MyTestAPIs;

import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Integer> generateTickets(Integer event_id, Integer amount, Integer user_id){

        List<Integer> tickets = new ArrayList<>();

        for(int i = 0; i < amount; i++){

            int randValue = ThreadLocalRandom.current().nextInt(7000, 7000 + event_id + amount);
            Ticket ticket = new Ticket(randValue, event_id, user_id);
            ticketRepository.save(ticket);
            tickets.add(randValue);
        }
        return tickets;
    }
    @PreRemove
    public void deleteTicket(Integer ticket_id){
        Ticket ticket = ticketRepository.findTicketByNum(ticket_id);
        ticketRepository.delete(ticket);
    }
}
