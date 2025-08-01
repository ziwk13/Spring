package org.shining.crud.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.shining.crud.model.dto.BoardDTO;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDAO {

  private final SqlSessionTemplate template;
  
  public List<BoardDTO> getBoards() {
    return template.selectList("mybatis.mapper.boardMapper.getBoards");
  }
  
  public BoardDTO getBoardById(Integer bid) {
   return template.selectOne("mybatis.mapper.boardMapper.getBoardById", bid); 
  }
  
  public int insertBoard(BoardDTO board) {
    return template.insert("mybatis.mapper.boardMapper.insertBoard", board);
  }
  
  public int updateBoard(BoardDTO board) {
    return template.update("mybatis.mapper.boardMapper.updataBoard", board);
  }
  
  public int deletedBoardById(Integer bid) {
    return template.delete("mybatis.mapper.boardMapper.deletedBoardById", bid);
  }
}
