package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.BusDetails;
import com.example.Entity.Ticketdetails;
import com.example.Entity.UserInfo;
import com.example.Repository.BusDetailRepository;
import com.example.Repository.TicketdetailRepository;
import com.example.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private BusDetailRepository busRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketdetailRepository ticketdetailRepository;
	public List<BusDetails> searchBusByUser(BusDetails bus) {
            
            if (bus.getFromdestination().equals(bus)&& bus.getTodestination().equals(bus)) {
                return busRepository.findAll();
            }
			return null;
            
	}
	public String bookBus(int id,int busid) {
		BusDetails bus=busRepository.findById(busid);
		UserInfo user=userRepository.findById(id);
		if(user==null && bus==null)
		{
			return "id's are not found";
		}
		else {
			if(ticketdetailRepository.existsByUserAndBus(user,bus)) {
				return "user already book the ticket for the bus";
			}
			else {
				Ticketdetails bookbus=new Ticketdetails();
				bookbus.setUser(user);
				bookbus.setBus(bus);
				ticketdetailRepository.save(bookbus);
			}
		}
		return "reservation successfully";
	}
}


