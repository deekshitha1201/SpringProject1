package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.BusDetails;
import com.example.Entity.Ticketdetails;
import com.example.Entity.UserInfo;

public interface TicketdetailRepository extends JpaRepository<Ticketdetails, Integer> {

	boolean existsByUserAndBus(UserInfo user, BusDetails bus);

}
