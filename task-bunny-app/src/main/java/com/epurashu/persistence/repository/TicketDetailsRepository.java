package com.epurashu.persistence.repository;

import com.epurashu.persistence.entity.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epurashu.dto.TicketDetailsDTO;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Integer>
{
    default void mergeAndFlush(TicketDetails ticketDetails, TicketDetailsDTO ticketDetailsDTO)
    {
        ticketDetails.setTeamName(ticketDetailsDTO.getTeamName());
        ticketDetails.setDescription(ticketDetailsDTO.getDescription());
        ticketDetails.setSprintName(ticketDetailsDTO.getSprintName());
        saveAndFlush(ticketDetails);
    }
}
