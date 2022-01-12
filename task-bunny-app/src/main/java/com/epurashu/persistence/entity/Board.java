package com.epurashu.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.epurashu.persistence.entity.TicketRank;

@Entity
@Table(name = "TB_BOARD")
public class Board implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "assignedBoard", fetch = FetchType.EAGER)
    private List<TicketRank> ticketRankList = new ArrayList<>();

    public Board()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<TicketRank> getTicketRankList()
    {
        return ticketRankList;
    }

    public void setTicketRankList(List<TicketRank> ticketRankList)
    {
        this.ticketRankList = ticketRankList;
    }
}