package com.example.MyTestAPIs;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public MainController(TicketService ticketService, TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @PostMapping("/api/genTickets")
    public List<Integer> getTickets(Integer event_id, Integer amount, Integer user_id){

        List<Integer> tickets = ticketService.generateTickets(event_id, amount, user_id);
        return tickets;
    }

    @GetMapping("/api/downloadTickets")
    public List<String> downloadTickets(@RequestBody List<Integer> tickets){
        return ticketRepository.findFilesByID(tickets);
    }

    @RequestMapping(value = "/api/deleteTicket", params = "ticket_num", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTicket(@RequestParam("ticket_num") Integer ticket_num){
        ticketService.deleteTicket(ticket_num);
        return "Ticket "+ticket_num+" has been deleted";
    }

    @RequestMapping(value = "/api/updateTicket", params = {"ticket_num", "event_id", "t_file", "user_id"}, method = RequestMethod.PATCH)
    @ResponseBody
    public String updateTicket(@RequestParam("ticket_num") Integer ticket_num,
                               @RequestParam("event_id") Integer event_id,
                               @RequestParam("t_file") String t_file, @RequestParam("user_id") Integer user_id){
        Ticket ticket = ticketRepository.findTicketByNum(ticket_num);
        ticket.setEvent_id(event_id);
        ticket.setT_file(t_file);
        ticket.setUser_id(user_id);
        ticketRepository.save(ticket);
        return "Ticket "+ticket_num+" has been updated";
    }

    //------------------------EVENTS---------

    @PostMapping("/api/setEvent")
    public String setEvent(Integer event_id, String event_name, String place_name){
        Event event = new Event(event_id, place_name, event_name);
        eventRepository.save(event);
        return event.toString();
    }

    @GetMapping("/api/getEvent")
    public String getEvent(Integer event_id){
        Optional<Event> event = eventRepository.findById(event_id);
        return event.toString();
    }

    @RequestMapping(value = "/api/deleteEvent", params = "event_id", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteEvent(@RequestParam("event_id") Integer event_id){
        Optional<Event> event = eventRepository.findById(event_id);
        eventRepository.delete(event.get());
        return "Event "+event_id+" has been deleted";
    }

    @RequestMapping(value = "/api/updateEvent", params = {"event_id", "place_name", "event_name"}, method = RequestMethod.PATCH)
    @ResponseBody
    public String updateEvent(@RequestParam("event_id") Integer event_id,
                               @RequestParam("event_name") String event_name,
                               @RequestParam("place_name") String place_name){
        Event event = new Event(event_id, place_name, event_name);
        eventRepository.save(event);
        return "Event "+event_id+" has been updated";
    }

    //----------------------------USERS-------------------

    @PostMapping("/api/setUser")
    public String setUser(String user_name, Integer user_age, String country){
        User user = new User(user_name, user_age, country);
        userRepository.save(user);
        return user.toString();
    }

    @GetMapping("/api/getUser")
    public String getUser(Integer user_id){
        Optional<User> user = userRepository.findById(user_id);
        return user.toString();
    }

    @RequestMapping(value = "/api/deleteUser", params = "user_id", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestParam("user_id") Integer user_id){
        Optional<User> user = userRepository.findById(user_id);
        userRepository.delete(user.get());
        return "User "+user_id+" has been deleted";
    }

    @RequestMapping(value = "/api/updateUser", params = {"user_id", "user_name", "user_age", "country"}, method = RequestMethod.PATCH)
    @ResponseBody
    public String updateUser(@RequestParam("user_id") Integer user_id,
                             @RequestParam("user_name") String user_name,
                             @RequestParam("user_age") Integer user_age,
                             @RequestParam("country") String country){
        User user = new User(user_name, user_age, country);
        user.setId(user_id);
        userRepository.save(user);
        return "User "+user_id+" has been updated";
    }
}
