package com.epurashu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epurashu.adapters.BoardAdapter;
import com.epurashu.dto.BoardDTO;
import com.epurashu.persistence.entity.Board;
import com.epurashu.persistence.repository.BoardRepository;
import com.epurashu.service.exceptions.BoardException;

@Service
public class BoardService
{
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository)
    {
        this.boardRepository = boardRepository;
    }

    public List<BoardDTO> findAll()
    {
        return BoardAdapter.toDTOList(boardRepository.findAll());
    }

    public BoardDTO findById(Integer id)
    {
        Board board = getBoardById(id);
        return BoardAdapter.toDTO(board);
    }

    public BoardDTO createBoard(BoardDTO boardDTO)
    {
        Board board = BoardAdapter.toEntity(boardDTO);
        Board savedBoard = boardRepository.save(board);
        return BoardAdapter.toDTO(savedBoard);
    }

    public BoardDTO updateBoard(Integer id, BoardDTO boardDTO)
    {
        Board board = getBoardById(id);
        if (!board.getId().equals(boardDTO.getId()))
        {
            throw new RuntimeException("Id of entity not the same with path id");
        }
        return BoardAdapter.toDTO(boardRepository.save(BoardAdapter.toEntity(boardDTO)));
    }

    private Board getBoardById(Integer id)
    {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.orElseThrow(() -> new BoardException("Board with id " + id + " not found"));
    }
}