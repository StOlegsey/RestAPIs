package com.example.MyTestAPIs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("SELECT t.t_file FROM Ticket t WHERE t.t_number in (?1)")
    public List<String> findFilesByID(List<Integer> ids);

    @Query("SELECT t FROM Ticket t WHERE t.t_number in (?1)")
    public Ticket findTicketByNum(Integer num);

}
