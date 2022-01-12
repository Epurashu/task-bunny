package com.epurashu;

import static com.epurashu.controllers.Utils.COMPLEX_PASSWORD;
import static com.epurashu.controllers.Utils.getAlphaNumericString;
import static com.epurashu.controllers.Utils.getRandomDate;
import static com.epurashu.controllers.Utils.getRandomEmail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.epurashu.controllers.BoardController;
import com.epurashu.controllers.RegistrationController;
import com.epurashu.controllers.TicketController;
import com.epurashu.controllers.TicketRankController;
import com.epurashu.controllers.UserController;
import com.epurashu.dto.AppUserRole;
import com.epurashu.dto.BoardDTO;
import com.epurashu.dto.TicketDTO;
import com.epurashu.dto.TicketDetailsDTO;
import com.epurashu.dto.TicketRankDTO;
import com.epurashu.dto.UsersDTO;

@SpringBootTest(classes = ProjectManagementApplication.class)
@AutoConfigureMockMvc
public class SpringBootTestEnvironment
{
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected UserController userController;
    @Autowired
    protected TicketController ticketController;
    @Autowired
    protected RegistrationController registrationController;
    @Autowired
    protected BoardController boardController;
    @Autowired
    protected TicketRankController ticketRankController;
    protected UsersDTO usersDTO;
    protected TicketDTO ticketDTO;
    protected TicketDetailsDTO ticketDetailsDTO;
    protected List<TicketDTO> ticketDTOS = new ArrayList<>();
    protected BoardDTO boardDTO;
    protected TicketRankDTO ticketRankDTO;
    protected List<TicketRankDTO> ticketRankDTOS = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        createUser();
    }

    private void createTwoTickets()
    {
        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());

        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());

        ticketDetailsDTO = new TicketDetailsDTO(null, getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString());
        ticketDTO = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, ticketDetailsDTO);
        this.ticketDTOS.add(ticketController.createTicket(ticketDTO).getBody());
    }

    private void addTicketToBoard()
    {
        this.ticketRankDTO = new TicketRankDTO();
        this.ticketRankDTO.setAssignedTicket(ticketDTOS.get(1));
        this.ticketRankDTO.setAssignedBoard(boardDTO);
        this.ticketRankDTOS
            .add(ticketRankDTO = ticketRankController.addTicketToBoard(boardDTO.getId(), ticketRankDTO).getBody());
    }

    private void createUser()
    {
        this.usersDTO = new UsersDTO();
        usersDTO.setUserName(getAlphaNumericString());
        usersDTO.setEmail(getRandomEmail());
        usersDTO.setPassword(COMPLEX_PASSWORD);
        usersDTO.setAppUserRole(AppUserRole.ADMIN);
        usersDTO.setActive(true);
        usersDTO = userController.createUser(this.usersDTO).getBody();
        createBoard();
        createTwoTickets();
        addTicketToBoard();
    }

    private void createBoard()
    {
        this.boardDTO = new BoardDTO();
        boardDTO.setName(getAlphaNumericString());
        boardDTO = boardController.createBoard(this.boardDTO).getBody();
    }
}