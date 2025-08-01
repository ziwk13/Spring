package org.shining.crud.service;

import java.util.List;

// 주요 네이밍 : get, find, create, add, update, modify, delete, remove 등

import org.shining.crud.model.dto.BoardDTO;

public interface BoardService {
  List<BoardDTO> findAllBoards();
  BoardDTO findBoardById(Integer bid);  // 상세보기
  boolean addBoard(BoardDTO board);  // 추가
  boolean modifyBoard(BoardDTO board);
  boolean removeBoard(Integer bid); // 삭제
}
