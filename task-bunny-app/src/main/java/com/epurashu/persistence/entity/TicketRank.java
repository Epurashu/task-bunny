package com.epurashu.persistence.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TICKET_RANK")
public class TicketRank implements Serializable
{
    @EmbeddedId
    private TicketRankForeignKey ticketRankForeignKey = new TicketRankForeignKey();

    @ManyToOne
    @MapsId("ticketId")
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "id")
    private Ticket assignedTicket;

    @ManyToOne
    @MapsId("boardId")
    @JoinColumn(name = "BOARD_ID", referencedColumnName = "id")
    private Board assignedBoard;

    private Integer ticketRank;

    public TicketRank()
    {
    }

    public Integer getTicketRank()
    {
        return ticketRank;
    }

    public void setTicketRank(Integer ticketRank)
    {
        this.ticketRank = ticketRank;
    }

    public TicketRankForeignKey getTicketRankForeignKey()
    {
        return ticketRankForeignKey;
    }

    public void setTicketRankForeignKey(TicketRankForeignKey ticketRankForeignKey)
    {
        this.ticketRankForeignKey = ticketRankForeignKey;
    }

    public Board getAssignedBoard()
    {
        return assignedBoard;
    }

    public void setAssignedBoard(Board assignedBoard)
    {
        this.assignedBoard = assignedBoard;
    }

    public Ticket getAssignedTicket()
    {
        return assignedTicket;
    }

    public void setAssignedTicket(Ticket assignedTicket)
    {
        this.assignedTicket = assignedTicket;
    }
}