package com.epurashu.persistence.repository;

import java.util.List;

import com.epurashu.persistence.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>
{
    String USERID = "userId";

    @Query("SELECT ticket"
        + " FROM Ticket ticket"
        + " WHERE ticket.createdBy.id = :" + USERID)
    List<Ticket> findTicketsCreatedByUser(@Param(USERID) Integer userId);

    @Query("SELECT ticket"
        + " FROM Ticket ticket"
        + " WHERE ticket.assignedTo.id = :" + USERID)
    List<Ticket> findTicketsAssignedToUser(@Param(USERID) Integer userId);
}